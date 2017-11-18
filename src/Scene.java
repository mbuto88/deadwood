import java.util.*;

//Scene Class
public class Scene extends Room {
   String name;
   private ArrayList<Part> extraParts;
   int x;
   int y;
   int w;
   int h;

   //Constructors
   public Scene(String name, ArrayList<Part> extraParts, int x, int y, int h, int w) {
	  this.name = name;
      this.extraParts = extraParts;
      this.x = x;
	  this.y = y;
	  this.h = h;
      this.w = w;
   }
  

//Getters
 
   public ArrayList<Part> getExtraParts() {
		return extraParts;
	}
   public String getName() {
      return name;
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
}
