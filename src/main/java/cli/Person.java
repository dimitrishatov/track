package cli;

import com.squareup.moshi.*;
import okhttp3.Response;
import server.Room;
import server.RoomsAPI;

import java.io.IOException;
import java.util.Calendar;
import java.util.UUID;


public class Person {
    private String userName;
    private UUID currentRoomID = null;
    private String roomName = null;
    private Moshi moshi;

    public Person () {
        moshi = new Moshi.Builder()
                .add(new UuidAdapter())
                .add(new CalendarAdapter())
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
            currentRoomID = jsonAdapter.fromJson(res.body().string()).getROOM_KEY();
            roomName = jsonAdapter.fromJson(res.body().string()).getRoomName();
            PrefixFetcher.setRoom(roomName);
            System.out.println("Room entered");
            return true;
        }
    }

    public void showLeaderboard() {
        if (currentRoomID == null) {
            System.out.println("You must be inside of a room to see leaderboard");
        } else {
            // TODO: 3/28/21
        }
    }

    public void createRoom() throws IOException {
        System.out.println("Enter room name and days the room should last for " +
                "separated by spaces");
        String[] info = Util.getLine().split(" ", 2);

        if (info.length != 2) {
            System.out.println("Incorrect format");
            return;
        }

        int status = HttpHandler.postRequest(String.format("rooms/%s/%s/%s", info[0], userName, info[1]))
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

        String res = HttpHandler.getRequest(endpoint)
                .body()
                .string();

        jsonAdapter.fromJson(res)
                .getRooms()
                .forEach(System.out::println);
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

    public void listHabits() {

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

    static class CalendarAdapter {
        @ToJson
        String toJson(Calendar date) {
            return date.toString();
        }

        @FromJson
        Calendar fromJson(String date) {
            return Calendar.getInstance();
        }
    }
}
