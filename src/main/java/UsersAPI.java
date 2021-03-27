import java.util.ArrayList;

public class UsersAPI {
   private ArrayList<User> users;

   public UsersAPI() {
      users = new ArrayList<User>();
   }

   public ArrayList<User> getUsers() {
      return users;
   }

   public boolean addUser(String username) {
      // Check if name exists
      for (User user : users) {
         if (username.equals(user.getUserName())) {
            return false;
         }
      }
      // Add user if username wasn't found
      users.add(new User(username));
      return true;
   }


}
