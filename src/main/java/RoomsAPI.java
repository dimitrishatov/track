import java.util.ArrayList;
import java.util.Date;

public class RoomsAPI {
   private ArrayList<Room> rooms;

   public RoomsAPI() {
      rooms = new ArrayList<Room>();
   }

   public boolean addRoom(String name, ArrayList<User> users, ArrayList<Habit> habits, Date endDate) {
      for (Room room : rooms) {
         if (name.equals(room.getRoomName()))
            return false;
      }

      rooms.add(new Room(name, users, habits, endDate));
      return true;
   }

   public ArrayList<Room> getRooms() {
      return rooms;
   }
}
