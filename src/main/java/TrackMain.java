import static spark.Spark.*;
import com.fasterxml.jackson.databind.ObjectMapper;

public class TrackMain {
   public static void main(String[] args) {
      ObjectMapper map = new ObjectMapper();

      User exampleUser = new User("dimitri");

      get("/user", (req, res) -> map.writeValueAsString(exampleUser));


   }
}
