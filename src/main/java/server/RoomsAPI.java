package server;

import java.util.ArrayList;
import java.util.Calendar;

public class RoomsAPI {
   private ArrayList<Room> rooms;

   public RoomsAPI() {
      rooms = new ArrayList<Room>();
   }

   public void addRoom(String roomName, User owner, int days) {
      ArrayList<Habit> habits = new ArrayList<>();
      rooms.add(new Room(roomName, owner, habits, days));
   }

   public void addRoom(Room room) {
      rooms.add(room);
   }

   public boolean roomAlreadyExists(String roomName) {
      for (Room room : rooms) {
         if (roomName.equals(room.getRoomName())) {
            return true;
         }
      }
      return false;
   }

   public ArrayList<Room> getRooms() {
      return rooms;
   }
}
