package cli;

import java.io.IOException;
import java.util.Locale;
import java.util.Scanner;

public class TrackCli {
    public static void main(String[] args) {
        Person user = new Person();
        user.initialize();

        try {
            mainLoop(user);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void mainLoop(Person user) throws IOException {
        Scanner scanner = new Scanner(System.in);

        char c;
        while ((c = displayMenu(scanner)) != 'Q') {
            switch (c) {
                case 'C':
                    user.createRoom();
                    break;
                case 'V':
                    user.viewPrivateRooms();
                    break;
                case 'P':
                    user.viewPublicRooms();
                    break;
            }
        }

        System.out.println("Leaving track");
    }

    private static char displayMenu(Scanner scanner) {
        System.out.println("(C)reate room");
        System.out.println("(V)iew rooms");
        System.out.println("(P)ublic rooms");
        System.out.println("(Q)uit");
        System.out.println();

        return Util.getChar();
    }
}
