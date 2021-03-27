import java.util.ArrayList;

public class RoomsAPI {
   private ArrayList<Room> rooms;

   public RoomsAPI() {
      rooms = new ArrayList<Room>();
   }

   public ArrayList<Room> getRooms() {
      return rooms;
   }
}
