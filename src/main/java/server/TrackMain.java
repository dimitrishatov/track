package server;

import static spark.Spark.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.ArrayList;

public class TrackMain {
   public static void main(String[] args) {
      ObjectMapper map = new ObjectMapper();

      UsersAPI usersAPI = new UsersAPI();
      RoomsAPI roomsAPI = new RoomsAPI();

      get("/users", (req, res) -> {
         res.type("application/json");
         return map.writeValueAsString(usersAPI);
      });


      get("/rooms", (req, res) -> {
         res.type("application/json");
         return map.writeValueAsString(roomsAPI);
      });


      // server.User is added
      post("/users/:name", (request, response) -> {
         if (!usersAPI.addUser(request.params(":name"))) {
            response.status(400);
            return 400;
         }
         response.status(200);
         return 200;
      });

      // Adding a room
      post("/rooms/:roomName/:owner/:days", (request, response) -> {
         if (roomsAPI.roomAlreadyExists(request.params(":roomName"))) {
            response.status(400);
            return 400;
         }
         else {
            String name = request.params(":owner");
            User owner = usersAPI.getUserByName(name);
            ArrayList<Habit> habits = new ArrayList<>();
            Room newRoom = new Room(request.params(":roomName"), owner, habits, Integer.parseInt(request.params(":days")));
            roomsAPI.addRoom(newRoom);
            response.status(200);
            return 200;
         }
      });

      // Adding person to room
      post("/rooms/:roomName/:username", (request, response) -> {
         // username does not exist
         if (!usersAPI.getUsers().contains(usersAPI.getUserByName(request.params(":username")))) {
            response.status(400);
            return 400;
         }
         // user is already in room
         else if (roomsAPI.getRoomByName(request.params(":roomName")).getUsers().contains(usersAPI.getUserByName(request.params(":username")))) {
            response.status(400);
            return 400;
         }
         // room does not exist
         else if (!roomsAPI.roomAlreadyExists(request.params(":roomName"))){
            response.status(400);
            return 400;
         }
         else {
            Room room = roomsAPI.getRoomByName(request.params("roomName"));
            User user = usersAPI.getUserByName(request.params(":username"));

            room.addUser(user);
            user.getRoomIDs().add(room.getROOM_KEY());
            response.status(200);
            return 200;
         }
      });
   }
}
