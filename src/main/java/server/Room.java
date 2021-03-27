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

   public ArrayList<Habit> getHabits() {
      return habits;
   }

   public ArrayList<User> getUsers() {
      return users;
   }

   public Calendar getEndDate() {
      return endDate;
   }

   public HashMap<User, Integer> getScores() {
      return scores;
   }
}
