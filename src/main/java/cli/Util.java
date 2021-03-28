package cli;

import java.util.Scanner;

public class Util {
    static Scanner scanner = new Scanner(System.in);

    public static char getChar() {
        return scanner.nextLine().substring(0, 1).toUpperCase().toCharArray()[0];
    }

    public static String getLine() {
        return scanner.nextLine();
    }
}