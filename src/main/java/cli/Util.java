package cli;

import java.util.Scanner;

public class Util {
    static Scanner scanner = new Scanner(System.in);

    public static char getChar() {
        System.out.print(PrefixFetcher.getPrefix() + "$ ");
        String line = scanner.nextLine();
        if (line.length() == 0)
            return ' ';
        else
            return line.substring(0, 1).toUpperCase().toCharArray()[0];
    }

    public static String getLine() {
        System.out.print(PrefixFetcher.getPrefix() + "$ ");
        return scanner.nextLine();
    }
}
