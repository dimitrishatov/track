package server;

import java.util.UUID;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Calendar;

public class Room {
   private final UUID ROOM_KEY;
   private final String roomName;
   private ArrayList<User> users;
   private ArrayList<Habit> habits;
   private HashMap<User, Integer> scores;
   private int days;
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

   public ArrayList<Habit> getHabits() {
      return habits;
   }

   public ArrayList<User> getUsers() {
      return users;
   }

//   public String getEndDate() {
//      return endDate.toString();
//   }

   public HashMap<User, Integer> getScores() {
      return scores;
   }
}
