package server;

import java.util.UUID;
import java.util.ArrayList;

public class User {
    private final UUID USER_ID;
    private final String userName;
    private final ArrayList<Room> rooms;

    public User(String userName) {
        USER_ID = UUID.randomUUID();
        this.userName = userName;
        rooms = new ArrayList<Room>();
    }

    public String getUserName() {
        return userName;
    }

    public UUID getUSER_ID() {
        return USER_ID;
    }

    public ArrayList<Room> getRooms() {
        return rooms;
    }
}
