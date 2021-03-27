import java.util.UUID;
import java.util.ArrayList;

public class User {
    private final UUID USER_ID = UUID.randomUUID();
    private final String userName;
    private ArrayList<Room> rooms;

    public User(String userName) {
        this.userName = userName;
    }
}
