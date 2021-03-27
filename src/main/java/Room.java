import java.util.UUID;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Date;

public class Room {
   private final UUID ROOM_KEY;
   private ArrayList<User> users;
   private ArrayList<Habit> habits;
   private HashMap<User, Integer> scores;
   private Date endDate;

   public Room(ArrayList<User> users, ArrayList<Habit> habits, Date endDate) {
      ROOM_KEY = UUID.randomUUID();
      scores = new HashMap<User, Integer>();

      this.users = users;
      this.habits = habits;
      this.endDate = endDate;
   }

   public UUID getROOM_KEY() {
      return ROOM_KEY;
   }

   public ArrayList<Habit> getHabits() {
      return habits;
   }

   public ArrayList<User> getUsers() {
      return users;
   }

   public Date getEndDate() {
      return endDate;
   }

   public HashMap<User, Integer> getScores() {
      return scores;
   }
}
