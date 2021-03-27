import java.util.ArrayList;

public class UsersAPI {
   private ArrayList<User> users;

   public UsersAPI() {
      users = new ArrayList<User>();
   }

   public ArrayList<User> getUsers() {
      return users;
   }
}
