package server;

import java.util.*;

public class Room {
   private final UUID ROOM_KEY;
   private final String roomName;
   private List<User> users;
   private List<Habit> habits;
   private Map<UUID, Integer> scores;
   private Date startDate;

   public Room(String roomName, User owner, ArrayList<Habit> habits) {
      ROOM_KEY = UUID.randomUUID();
      scores = new HashMap<UUID, Integer>();

      this.roomName = roomName;
      this.habits = habits;

      startDate = new Date();

      users = new ArrayList<>();
      users.add(owner);
      scores.put(owner.getUSER_ID(), 0);

      owner.getRoomIDs().add(this.ROOM_KEY);
   }

   public String getRoomName() {
      return roomName;
   }

   public UUID getROOM_KEY() {
      return ROOM_KEY;
   }

   public List<Habit> getHabits() {
      return habits;
   }

   public List<User> getUsers() {
      return users;
   }

   public Map<UUID, Integer> getScores() {
      return scores;
   }

   public void setScores(Map<UUID, Integer> scores) {
      this.scores = scores;
   }

   public Date getStartDate() {
      return startDate;
   }

   public void addUser(User user) {
      users.add(user);
      scores.put(user.getUSER_ID(), 0);
   }

   public int getPointValOfHabit(String name) {
      for (Habit habit : habits) {
         if (name.equals(habit.getName()))
            return habit.getPointVal();
      }
      return -1;
   }

   // Code from https://www.geeksforgeeks.org/sorting-a-hashmap-according-to-values/
   public HashMap<UUID, Integer> sortByValue() {
      // Create a list from elements of HashMap
      List<Map.Entry<UUID, Integer> > list =
              new LinkedList<>(scores.entrySet());
      // Sort the list
      Collections.sort(list, new Comparator<Map.Entry<UUID, Integer> >() {
         public int compare(Map.Entry<UUID, Integer> o1,
                            Map.Entry<UUID, Integer> o2)
         {
            return (o1.getValue()).compareTo(o2.getValue());
         }
      });
      // put data from sorted list to hashmap
      HashMap<UUID, Integer> temp = new LinkedHashMap<>();
      for (Map.Entry<UUID, Integer> aa : list) {
         temp.put(aa.getKey(), aa.getValue());
      }
      return temp;
   }

   public String toString() {
      return roomName;
   }
}
