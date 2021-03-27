import static spark.Spark.*;
import com.fasterxml.jackson.databind.ObjectMapper;

public class TrackMain {
   public static void main(String[] args) {
      ObjectMapper map = new ObjectMapper();

      UsersAPI usersAPI = new UsersAPI();
      RoomsAPI roomsAPI = new RoomsAPI();


      User exampleUser = new User("dimitri");

      get("/user", (req, res) -> map.writeValueAsString(exampleUser));


   }
}
