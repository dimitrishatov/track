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
    private Moshi moshi;

    public Person () {
        moshi = new Moshi.Builder()
                .add(new UuidAdapter())
                .add(new CalendarAdapter())
                .build();
    }


    public void initialize() {
        try {
            System.out.println("Welcome to Track! Enter your username to get started");
            userName = Util.getLine().split(" ")[0];
            if (HttpHandler.postRequest("users/" + userName)
                .code() < 300) {
                System.out.println("A new user has been created and saved as " + userName);
            } else {
                System.out.println("You're username has been saved as " + userName);
            }
            System.out.println();
        } catch (IOException e) {
            System.out.println("Could not connect to server");
        }
    }

    public void enterRoom() throws IOException {
        System.out.println("Put in the room name you want to go inside");
        String roomname = Util.getLine();

        Response res = HttpHandler.getRequest(String.format("rooms/%s/%s", userName, roomname));

        if (res.code() > 299) {
            System.out.println("That room doesn't exist in the rooms you belong to");
        } else {
            JsonAdapter<Room> jsonAdapter = moshi.adapter(Room.class);
            currentRoomID = jsonAdapter.fromJson(res.body().string()).getROOM_KEY();
            PrefixFetcher.setRoom(roomname);
            System.out.println("Room entered");
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
