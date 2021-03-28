package cli;

import cli.HttpHandler;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;
import server.Room;
import server.RoomsAPI;

import java.io.IOException;
import java.util.Scanner;


public class Person {
    private String userName;

    public void initialize() {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.print("Welcome to Track! Enter your username to get started: ");
            userName = scanner.next();
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
        Moshi moshi = new Moshi.Builder().build();
        JsonAdapter<RoomsAPI> jsonAdapter = moshi.adapter(RoomsAPI.class);
        try {
            String choice;
            do {
                System.out.println("Do you want to view private or public rooms?");
                choice = Util.getLine();
            } while (!choice.equalsIgnoreCase("private") &&
                    !choice.equalsIgnoreCase("public"));

            String res = HttpHandler.getRequest("viewRooms/" + choice)
                .toString();

            jsonAdapter.fromJson(res)
                    .getRooms()
                    .forEach(System.out::println);
        } catch (IOException e) {
            System.out.println("Could not connect to server");
        }
    }
}
