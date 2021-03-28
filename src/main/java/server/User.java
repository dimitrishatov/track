package server;

import java.util.UUID;
import java.util.ArrayList;

public class User {
    private final UUID USER_ID;
    private final String userName;
    private final ArrayList<UUID> roomIDs;

    public User(String userName) {
        USER_ID = UUID.randomUUID();
        this.userName = userName;
        roomIDs = new ArrayList<UUID>();
    }

    public String getUserName() {
        return userName;
    }

    public UUID getUSER_ID() {
        return USER_ID;
    }

    public ArrayList<UUID> getRoomIDs() {
        return roomIDs;
    }
}
