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
            if (HttpHandler.getRequest("checkUser/" + userName)
                    .isSuccessful()) {
                System.out.println("You're username has been saved as " + userName);
            } else {
                System.out.println("A new user has been created and saved as " + userName);
            }
        } catch (IOException e) {
            System.out.println("Could not connect to server");
        }
    }

    public void createRoom() {
        
    }

    public void viewRooms() {
        Moshi moshi = new Moshi.Builder()
                .add(new UuidAdapter())
                .add(new CalendarAdapter())
                .build();
        JsonAdapter<RoomsAPI> jsonAdapter = moshi.adapter(RoomsAPI.class);
        try {
            String choice;
            do {
                System.out.println("Do you want to view private or public rooms?");
                choice = Util.getLine();
            } while (!choice.equalsIgnoreCase("private") &&
                    !choice.equalsIgnoreCase("public"));

            String res = HttpHandler.getRequest("rooms")
                    .body()
                    .string();

            jsonAdapter.fromJson(res)
                    .getRooms()
                    .forEach(System.out::println);
        } catch (IOException e) {
            e.printStackTrace();
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
