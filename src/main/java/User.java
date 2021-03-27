import java.util.UUID;

public class User {
    private final UUID USER_ID = UUID.randomUUID();
    private final String userName;

    public User(String userName) {
        this.userName = userName;
    }
}
