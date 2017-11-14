import java.util.*;

//Scene Class
public class Scene extends Room {
   String name;
   Card card;
   private ArrayList<Part> extraParts;
   int x;
   int y;
   int w;
   int h;

   //Constructors
   public Scene(String name, ArrayList<Part> extraParts, int x, int y, int w, int h) {
	  this.name = name;
      this.extraParts = extraParts;
      this.x = x;
	  this.y = y;
	  this.h = h;
      this.w = w;
   }
  

//Getters
   public Card getCard(){
      return card;
   }
   public ArrayList<Part> getExtraParts() {
		return extraParts;
	}
   
   //Setters
   public void setCard(Card card) {
		this.card = card;
	}
   public void setExtraParts(ArrayList<Part> extraParts) {
		this.extraParts = extraParts;
	}
   
   //Other Methods
   public void getPart(){
   }

public boolean evaluateWork(){
      return false;
   }

@Override
public void interact(Player player) {
	// TODO Auto-generated method stub
	
}
}