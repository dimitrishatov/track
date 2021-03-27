import java.util.UUID;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Date;

public class Room {
   private final UUID ROOM_KEY;
   private ArrayList<Person> users;
   private ArrayList<Habit> habits;
   private HashMap<Person, Integer> scores;
   private Date endDate;

   public Room(ArrayList<Person> users, ArrayList<Habit> habits, Date endDate) {
      ROOM_KEY = UUID.randomUUID();
      scores = new HashMap<Person, Integer>();

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

   public ArrayList<Person> getUsers() {
      return users;
   }

   public Date getEndDate() {
      return endDate;
   }

   public HashMap<Person, Integer> getScores() {
      return scores;
   }
}
