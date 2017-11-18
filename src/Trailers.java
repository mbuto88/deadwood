//Trailers Class

import java.util.*;

public class Trailers extends Room {
   int currentDay;
   int x;
   int y;
   int h;
   int w;

   //Constructors
   public Trailers() {
   }

   public Trailers(int day, int x, int y, int h, int w){
      this.currentDay = day;
      this.x = x;
      this.y = y;
      this.w = h;
      this.h = w;
   }

   //Getters
   public int getCurrentDay(){
      return currentDay;
   }

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
         System.out.println(Board.calcScore(p));
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
   }
}
