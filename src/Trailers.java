//Trailers Class
import java.util.*;
public class Trailers extends Room {
   int currentDay;

   //Constructors
   public Trailers() {
   
   }
   public Trailers(int day){
      currentDay = day;
   }
   
   //Getters
   public int getCurrentDay(){
      return currentDay;
   }
   //Setters
   
   //Other Methods
   public void startDay(List<Player> players){
      for (Player p : players){
         p.setRoom(this);
      }
   }
   
   public void endDay(){
   }
   
   public void updateRules(){
   }
   
   public void interact(Player player){
   }
}