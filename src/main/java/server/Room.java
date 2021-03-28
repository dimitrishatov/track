package server;

import java.util.*;

public class Room {
   private final UUID ROOM_KEY;
   private final String roomName;
   private List<User> users;
   private List<Habit> habits;
   private Map<User, Integer> scores;
   private Calendar endDate;

   public Room(String roomName,ArrayList<User> users, ArrayList<Habit> habits, Calendar endDate) {
      ROOM_KEY = UUID.randomUUID();
      scores = new HashMap<User, Integer>();

      this.roomName = roomName;
      this.users = users;
      this.habits = habits;
      this.endDate = endDate;
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

   public Calendar getEndDate() {
      return endDate;
   }

   public Map<User, Integer> getScores() {
      return scores;
   }

   public String toString() {
      return roomName;
   }
}
