import java.util.ArrayList;

public class UsersAPI {
   private ArrayList<Person> users;

   public UsersAPI() {
      users = new ArrayList<Person>();
   }

   public ArrayList<Person> getUsers() {
      return users;
   }

   public boolean addUser(String username) {
      // Check if name exists
      for (Person user : users) {
         if (username.equals(user.getUserName())) {
            return false;
         }
      }
      // Add user if username wasn't found
      users.add(new Person(username));
      return true;
   }


}
