import static spark.Spark.*;
import com.fasterxml.jackson.databind.ObjectMapper;

public class TrackMain {
   public static void main(String[] args) {
      ObjectMapper map = new ObjectMapper();

      UsersAPI usersAPI = new UsersAPI();
      RoomsAPI roomsAPI = new RoomsAPI();

      get("/users", (req, res) -> map.writeValueAsString(usersAPI));
      get("/rooms", (req, res) -> map.writeValueAsString(roomsAPI));

      // User is added
      post("/users/:name", (request, response) -> {
         if (!usersAPI.addUser(request.params(":name"))) {
            response.status(400);
            return 400;
         }
         response.status(200);
         return request.params(":name") + "was added";
      });
   }
}
