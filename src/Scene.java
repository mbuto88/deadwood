import java.util.*;

//Scene Class
public class Scene extends Room {
   String name;
   private ArrayList<Part> extraParts;
   int takes;
   int completedTakes;

   //Constructors
   public Scene(String name, ArrayList<Part> extraParts, int x, int y, int h, int w) {
	  this.name = name;
      this.extraParts = extraParts;
      this.x = x;
      this.y = y;
      this.h = h;
      this.w = w;
      this.takes = calcTakes();
   }
  

//Getters
 
   public ArrayList<Part> getExtraParts() {
		return extraParts;
	}
   public String getName() {
      return name;
   }
   public int getTakes(){
      return takes;
   }
   
   //Setters

   public void setExtraParts(ArrayList<Part> extraParts) {
		this.extraParts = extraParts;
	}
   
   //Other Methods
   public void getPart(){
   }

public boolean evaluateWork(){
      return false;
   }
   
   public void completeTake(){
      completedTakes++;
      System.out.println(completedTakes + " takes have now been completed out of " + takes);
   }
   
   public boolean isOver(){
      return completedTakes >= takes;
   }
   public void reset(){
      completedTakes = 0;
   }
   
   //FIX THIS LATER
   private int calcTakes(){
      switch (name.toLowerCase()){
         case "train station":
            return 3;
         case "secret hideout":
            return 3;
         case "church":
            return 2;
         case "hotel":
            return 3;
         case "main street":
            return 3;
         case "jail":
            return 1;
         case "general store":
            return 2;
         case "ranch":
            return 2;
         case "bank":
            return 1;
         case "saloon":
            return 2;
         default:
            return 0;
      }
   }
}
