package server;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class RoomsAPI {
   private List<Room> rooms;

   public RoomsAPI() {
      rooms = new ArrayList<Room>();
   }

   public void addRoom(String roomName, User owner) {
      ArrayList<Habit> habits = new ArrayList<>();
      rooms.add(new Room(roomName, owner, habits));
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

   public Room getRoomByName(String name) {
      for (Room room : rooms) {
         if (room.getRoomName().equals(name)) {
            return room;
         }
      }
      return null;
   }

   public Optional<Room> getRoomForUser(String username, String roomname) {
      return rooms.stream().filter(room -> room.getRoomName().equals(roomname))
              .filter(room -> room.getUsers().stream().anyMatch(user -> user.getUserName().equals(username)))
              .findAny();
   }

   public List<Room> getRooms() {
      return rooms;
   }
}
