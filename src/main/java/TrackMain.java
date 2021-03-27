import static spark.Spark.*;

public class TrackMain {
   public static void main(String[] args) {
      get("/", (req, res) -> "Endpoint");
   }
}
