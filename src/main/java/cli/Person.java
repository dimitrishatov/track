package cli;

import com.squareup.moshi.*;
import okhttp3.Response;
import okhttp3.ResponseBody;
import server.Habit;
import server.Room;
import server.RoomsAPI;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;


public class Person {
    private String userName;
    private UUID currentRoomID = null;
    private String roomName = null;
    private Moshi moshi;

    public Person () {
        moshi = new Moshi.Builder()
                .add(new UuidAdapter())
                .add(new DateAdapter())
                .build();
    }

    public void initialize() throws IOException {
        System.out.println("Welcome to Track! Enter your username to get started");
        userName = Util.getLine().split(" ")[0];
        if (HttpHandler.postRequest("users/" + userName)
                .code() < 300) {
            System.out.println("A new user has been created and saved as " + userName);
        } else {
            System.out.println("Your username has been saved as " + userName);
        }
        System.out.println();
    }

    public boolean enterRoom() throws IOException {
        System.out.println("Put in the room name you want to go inside");
        String roomname = Util.getLine();

        Response res = HttpHandler.getRequest(String.format("rooms/%s/%s", userName, roomname));

        if (res.code() > 299) {
            System.out.println("That room doesn't exist in the rooms you belong to");
            return false;
        } else {
            JsonAdapter<Room> jsonAdapter = moshi.adapter(Room.class);
            try (ResponseBody responseBody = res.body()) {
                Room room = jsonAdapter.fromJson(responseBody.string());
                currentRoomID = room.getROOM_KEY();
                roomName = room.getRoomName();
                PrefixFetcher.setRoom(roomName);
                System.out.println("Room entered");
                return true;
            }
        }
    }

    public void showLeaderboard() throws IOException {
        Response res = HttpHandler.getRequest("leaderboard/rooms/" + roomName);

        if (res.code() < 300) {
            try (ResponseBody responseBody = res.body()) {
                JsonAdapter<ScoresWrapper> jsonAdapter = moshi.adapter(ScoresWrapper.class);
                jsonAdapter.fromJson(responseBody.string())
                        .scores
                        .entrySet()
                        .forEach(val -> System.out.println(val.getKey() + " " + val.getValue()));
            }
        }
    }

    public void createRoom() throws IOException {
        System.out.println("Enter room name");
        String roomName = Util.getLine();

        int status = HttpHandler.postRequest(String.format("rooms/create/%s/%s", roomName, userName))
                .code();

        if (status > 299) {
            System.out.println("Could not create room");
        } else {
            System.out.println("Room created successfully");
        }
    }

    public void viewPublicRooms() throws IOException {
        viewRooms("rooms");
    }

    public void viewPrivateRooms() throws IOException {
        viewRooms("rooms/" + userName);
    }

    private void viewRooms(String endpoint) throws IOException {
        JsonAdapter<RoomsAPI> jsonAdapter = moshi.adapter(RoomsAPI.class);

        Response res = HttpHandler.getRequest(endpoint);

        try (ResponseBody responseBody = res.body()){
            jsonAdapter.fromJson(responseBody.string())
                    .getRooms()
                    .forEach(System.out::println);
        }
    }

    public void trackHabit() throws IOException {
        System.out.println("Enter name of habit to update status for");

        String habitName = Util.getLine();

        int status = HttpHandler.postRequest(String.format("rooms/update/%s/%s/%s",
                roomName, habitName, userName)).code();

        if (status < 300) {
            System.out.println("Habit was updated");
        } else {
            System.out.println("Habit could not be updated");
        }
    }

    public void addHabit() throws IOException {
        System.out.println("Enter name of habit and point value separated by a space");

        String[] info = Util.getLine().split(" ", 2);

        if (info.length != 2) {
            System.out.println("Incorrect format");
            return;
        }

        int status = HttpHandler.postRequest(String.format("rooms/%s/%s/%s", roomName, info[0], info[1]))
                .code();

        if (status > 299) {
            System.out.println("Could not create habit");
        } else {
            System.out.println("Habit created successfully");
        }
    }

    public void listHabits() throws IOException {
        Response res = HttpHandler.getRequest("listHabits/rooms/" + roomName);

        if (res.code() < 300) {
            JsonAdapter<HabitsWrapper> jsonAdapter = moshi.adapter(HabitsWrapper.class);
            jsonAdapter.fromJson(res.body().string())
                    .habits
                    .forEach(habit -> System.out.println(habit.getName() + " " + habit.getPointVal()));
        } else {
            System.out.println("Could not get habits");
        }
    }

    public void joinRoom() throws IOException {
        System.out.println("Enter name of room you want to join");
        String name = Util.getLine();

        Response res = HttpHandler.getRequest(String.format("roomJoin/%s/%s", name, userName));

        if (res.code() < 300) {
            System.out.println("You were added to the room!");
        } else {
            System.out.println("Could not be added");
        }
    }

    public static class HabitsWrapper {
        public List<Habit> habits;

        public HabitsWrapper(List<Habit> habits) {
            this.habits = habits;
        }
    }

    public static class ScoresWrapper {
        public Map<String, Integer> scores;

        public ScoresWrapper(Map<String, Integer> scores) {
            this.scores = scores;
        }
    }

    static class UuidAdapter {
        @ToJson
        String toJson(UUID id) {
            return id.toString();
        }

        @FromJson
        UUID fromJson(String id) {
            return UUID.fromString(id);
        }
    }

    static class DateAdapter {
        @ToJson
        String toJson(Date date) {
            return date.toString();
        }

        @FromJson
        Date fromJson(String date) {
            return new Date();
        }
    }
}
