package cli;

import com.squareup.moshi.*;
import server.RoomsAPI;

import java.io.IOException;
import java.util.Calendar;
import java.util.UUID;


public class Person {
    private String userName;

    public void initialize() {
        try {
            System.out.print("Welcome to Track! Enter your username to get started: ");
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
        PrefixFetcher.setRoom(Util.getLine());
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
        Moshi moshi = new Moshi.Builder()
                .add(new UuidAdapter())
                .add(new CalendarAdapter())
                .build();
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
