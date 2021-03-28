package cli;

import java.io.IOException;
import java.util.Locale;
import java.util.Scanner;

public class TrackCli {
    public static void main(String[] args) {
        Person user = new Person();
        try {
            user.initialize();
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }

        try {
            mainLoop(user);
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    public static void mainLoop(Person user) throws IOException {
        char c;
        while ((c = displayMenu()) != 'Q') {
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
                case 'E':
                    if (user.enterRoom())
                        roomMenu(user);
                    break;
                case 'L':
                    user.showLeaderboard();
                    break;

            }
        }

        System.out.println("Leaving track");
    }

    private static void roomMenu(Person user) throws IOException {
        char c;
        while ((c = displayRoomMenu()) != 'E') {
            switch (c) {
                case 'T':
                    user.trackHabit();
                    break;
                case 'A':
                    user.addHabit();
                    break;
                case 'H':
                    user.listHabits();
                    break;

            }
        }

        System.out.println("Exiting room");
        PrefixFetcher.clearRoom();
    }

    private static char displayMenu() {
        System.out.println("(C)reate room");
        System.out.println("(V)iew rooms");
        System.out.println("(P)ublic rooms");
        System.out.println("(E)nter room");
        System.out.println("(Q)uit");
        System.out.println();

        return Util.getChar();
    }

    private static char displayRoomMenu() {
        System.out.println("(T)rack a habit");
        System.out.println("(A)dd habit");
        System.out.println("(H)abits");
        System.out.println("(L)eaderboard");
        System.out.println("(E)xit room");
        System.out.println();

        return Util.getChar();
    }
}
