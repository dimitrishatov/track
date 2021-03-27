import java.util.UUID;
import java.util.ArrayList;
import java.util.HashMap;

public class Room {
   private final UUID ROOM_KEY = UUID.randomUUID();
   private ArrayList<User> users;
   private HashMap<User, Integer> scores;
}
