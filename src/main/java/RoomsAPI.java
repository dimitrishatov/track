import java.util.ArrayList;
import java.util.Calendar;

public class RoomsAPI {
   private ArrayList<Room> rooms;

   public RoomsAPI() {
      rooms = new ArrayList<Room>();
   }

   public boolean addRoom(String roomName, User owner) {
      for (Room room : rooms) {
         if (roomName.equals(room.getRoomName()))
            return false;
      }
      ArrayList<Habit> habits = new ArrayList<>();
      ArrayList<User> users = new ArrayList<>();
      users.add(owner);

      Calendar endDate = Calendar.getInstance();
      endDate.add(Calendar.DAY_OF_YEAR, 30);

      rooms.add(new Room(roomName, users, habits, endDate));
      return true;
   }

   public ArrayList<Room> getRooms() {
      return rooms;
   }
}
