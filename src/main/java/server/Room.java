package server;

import java.util.*;

public class Room {
   private final UUID ROOM_KEY;
   private final String roomName;
   private int days;
   private List<User> users;
   private List<Habit> habits;
   private Map<User, Integer> scores;
   private Calendar endDate;

   public Room(String roomName, User owner, ArrayList<Habit> habits, int days) {
      ROOM_KEY = UUID.randomUUID();
      scores = new HashMap<User, Integer>();

      this.roomName = roomName;
      this.habits = habits;
      this.days = days;
      // Todo: implement calendar

      users = new ArrayList<>();
      users.add(owner);
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

//   public String getEndDate() {
//      return endDate.toString();
//   }

   public Map<User, Integer> getScores() {
      return scores;
   }

   public String toString() {
      return roomName;
   }
}
