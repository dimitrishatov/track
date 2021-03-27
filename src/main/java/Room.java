import java.util.UUID;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Date;

public class Room {
   private final UUID ROOM_KEY = UUID.randomUUID();
   private ArrayList<User> users;
   private ArrayList<Habit> habits;
   private HashMap<User, Integer> scores;
   private Date endDate;


}
