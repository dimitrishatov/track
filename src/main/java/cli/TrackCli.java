package cli;

import java.util.Locale;
import java.util.Scanner;

public class TrackCli {
    public static void main(String[] args) {
        Person user = new Person();
        user.initialize();
        mainLoop(user);
    }

    public static void mainLoop(Person user) {
        Scanner scanner = new Scanner(System.in);

        char c;
        while ((c = displayMenu(scanner)) != 'Q') {
            switch (c) {
                case 'C':
                    user.createRoom();
                    break;
                case 'V':
                    user.viewRooms();
                    break;
            }
        }

        System.out.println("Leaving track");
    }

    private static char displayMenu(Scanner scanner) {
        System.out.println("(C)reate room");
        System.out.println("(V)iew room");
        System.out.println("(Q)uit");

        return Util.getChar();
    }
}
