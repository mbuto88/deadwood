//Trailers Class
<<<<<<< HEAD
public class Trailers extends Room {
   int currentDay;
=======

import java.util.*;

public class Trailers extends Room {
   int currentDay;
   int x;
   int y;
   int h;
   int w;
>>>>>>> 16172047c3867689875e7b829fc506228e9a91a8

   //Constructors
   public Trailers() {
   
   }
<<<<<<< HEAD
=======
   public Trailers(int day, int x, int y, int h, int w){
      currentDay = day;
      this.x = x;
      this.y = y;
      this.w = h;
      this.h = w;
   }

>>>>>>> 16172047c3867689875e7b829fc506228e9a91a8
   
   //Getters
   public int getCurrentDay(){
      return currentDay;
   }
<<<<<<< HEAD
   //Setters
   
   //Other Methods
   public void startDay(){
   }
   
   public void endDay(){
   }
   
   public void updateRules(){
=======
   
   //Setters
   public void setCurrentDay(int currentDay) {
		this.currentDay = currentDay;
	}
   
   //Other Methods

   public void startDay(List<Player> players){
      for (Player p : players){
         p.setRoom(this);
      }
   }
   
public void endDay(List<Player> players){
      for (Player p : players){
         p.calcScore();
      }
   }
   
   public void updateRules(int numPlayers){
      if (numPlayers <= 3){
         //Set to 3 days
      }
      else if (numPlayers == 5){
         //Set player fame to 2
      }
      else if (numPlayers == 6){
         //Set player fame to 4
      }
      else if (numPlayers == 7 || numPlayers == 8){
         //Set player rank to 2
      }
   }
   
   public void interact(Player player){
      System.out.println("Nothing can be done here");

>>>>>>> 16172047c3867689875e7b829fc506228e9a91a8
   }
}