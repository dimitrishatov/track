package server;

import java.util.*;

public class Room {
   private final UUID ROOM_KEY;
   private final String roomName;
   private List<User> users;
   private List<Habit> habits;
   private Map<User, Integer> scores;
   private Date startDate;

   public Room(String roomName, User owner, ArrayList<Habit> habits) {
      ROOM_KEY = UUID.randomUUID();
      scores = new HashMap<User, Integer>();

      this.roomName = roomName;
      this.habits = habits;

      startDate = new Date();

      users = new ArrayList<>();
      users.add(owner);
      scores.put(owner, 0);

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

   public Map<User, Integer> getScores() {
      return scores;
   }

   public void setScores(Map<User, Integer> scores) {
      this.scores = scores;
   }

   public Date getStartDate() {
      return startDate;
   }

   public void addUser(User user) {
      users.add(user);
      scores.put(user, 0);
   }

   public int getPointValOfHabit(String name) {
      for (Habit habit : habits) {
         if (name.equals(habit.getName()))
            return habit.getPointVal();
      }
      return -1;
   }

   public String toString() {
      return roomName;
   }
}
