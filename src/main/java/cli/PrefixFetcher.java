package cli;

public class PrefixFetcher {
    private static String room = null;

    public static String getPrefix() {
        return room == null ? "" : "(" + room + ") ";
    }

    public static void setRoom(String room) {
        PrefixFetcher.room = room;
    }

    public static void clearRoom() {
        room = null;
    }
}
