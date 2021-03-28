package cli;

import java.util.Scanner;

public class Util {
    static Scanner scanner = new Scanner(System.in);

    public static char getChar() {
        System.out.print(PrefixFetcher.getPrefix() + "$ ");
        return scanner.nextLine().substring(0, 1).toUpperCase().toCharArray()[0];
    }

    public static String getLine() {
        System.out.print(PrefixFetcher.getPrefix() + "$ ");
        return scanner.nextLine();
    }
}
