import java.util.ArrayList;
import java.util.Calendar;

public class RoomsAPI {
   private ArrayList<Room> rooms;

   public RoomsAPI() {
      rooms = new ArrayList<Room>();
   }

   public void addRoom(String roomName, User owner, int days) {
      ArrayList<Habit> habits = new ArrayList<>();
      ArrayList<User> users = new ArrayList<>();
      users.add(owner);

      Calendar endDate = Calendar.getInstance();
      endDate.add(Calendar.DAY_OF_YEAR, days);

      rooms.add(new Room(roomName, users, habits, endDate));
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
