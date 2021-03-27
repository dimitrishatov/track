package server;

public class Habit {
   private String name;
   private int pointVal;

   public Habit (String name, int pointVal) {
      this.name = name;
      this.pointVal = pointVal;
   }

   public int getPointVal() {
      return pointVal;
   }

   public String getName() {
      return name;
   }
}
