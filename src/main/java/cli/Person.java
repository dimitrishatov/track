package cli;

import cli.HttpHandler;

import java.io.IOException;
import java.util.Scanner;

public class Person {
    private String userName;

    public void initialize() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Welcome to Track! Enter your username to get started: ");
        userName = scanner.next();

        try {
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
}
