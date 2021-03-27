import java.io.IOException;
import java.util.Scanner;

public class User {
    private String userName;

    public User() {
        userName = null;
    }

    public void initialize() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Welcome to Track! Enter your username to get started: ");
        userName = scanner.next();

        try {
            String res = HttpHandler.getRequest("checkUser/" + userName);

        } catch (IOException e) {

        }

        System.out.println(userName);
    }
}
