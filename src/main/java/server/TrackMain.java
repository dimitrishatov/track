package server;

import static spark.Spark.*;

import cli.Person;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.*;

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

      get("/rooms/:username/:roomname", (req, res) -> {
         Optional<Room> room = roomsAPI.getRoomForUser(req.params(":username"), req.params(":roomname"));
         if (room.isEmpty()) {
            res.status(400);
            return 400;
         } else {
            res.type("application/json");
            return map.writeValueAsString(room.get());
         }
      });

      // Adding a user
      post("/users/:name", (request, response) -> {
         if (!usersAPI.addUser(request.params(":name"))) {
            response.status(400);
            return 400;
         }
         response.status(200);
         return 200;
      });

      // Adding a room
      post("/rooms/create/:roomName/:owner", (request, response) -> {
         if (roomsAPI.roomAlreadyExists(request.params(":roomName"))) {
            response.status(400);
            return 400;
         }
         else {
            String name = request.params(":owner");
            User owner = usersAPI.getUserByName(name);
            ArrayList<Habit> habits = new ArrayList<>();
            Room newRoom = new Room(request.params(":roomName"), owner, habits);
            roomsAPI.addRoom(newRoom);
            response.status(200);
            return 200;
         }
      });

      // Adding person to room
      post("/rooms/addPerson/:roomName/:username", (request, response) -> {
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
            Room room = roomsAPI.getRoomByName(request.params(":roomName"));
            User user = usersAPI.getUserByName(request.params(":username"));

            room.addUser(user);
            user.getRoomIDs().add(room.getROOM_KEY());
            response.status(200);
            return 200;
         }
      });

      // Add habit to be tracked to room
      post("/rooms/:roomName/:habit/:pointValue", (request, response) -> {
         Room room = roomsAPI.getRoomByName(request.params(":roomName"));
         room.getHabits().add(new Habit(request.params(":habit"), Integer.parseInt(request.params(":pointValue"))));
         response.status(200);
         return 200;
      });

      // Update points based on habit for username
      post("/rooms/update/:roomName/:habit/:username", (request, response) -> {
         Room room = roomsAPI.getRoomByName(request.params(":roomName"));
         int points = room.getPointValOfHabit(request.params(":habit"));
         User key = usersAPI.getUserByName(request.params(":username"));

         Map<String, Integer> hmap = room.getScores();
         hmap.put(key.getUserName(), hmap.get(key.getUserName()) + points);
         room.setScores(hmap);
         response.status(200);
         return 200;
      });

      // Lists habits for given room
      get("listHabits/rooms/:roomName", (request, response) -> {
         Room room = roomsAPI.getRoomByName(request.params(":roomName"));
         response.type("application/json");
         return map.writeValueAsString(new Person.HabitsWrapper(room.getHabits()));
      });

      get("leaderboard/rooms/:roomName", (request, response) -> {
         Room room = roomsAPI.getRoomByName(request.params(":roomName"));
         response.type("application/json");
         return map.writeValueAsString(new Person.ScoresWrapper(room.sortByValue()));
      });

      get("roomJoin/:roomName/:userName", (req, res) -> {
         Room room = roomsAPI.getRoomByName(req.params(":roomName"));

         if (room == null) {
            res.status(400);
            return "400";
         }

         User user = usersAPI.getUserByName(req.params(":username"));
         room.addUser(user);

         res.status(200);
         return "ok";
      });
   }
}
