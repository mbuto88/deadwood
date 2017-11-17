import java.util.*;

//Scene Class
public class Scene extends Room {
<<<<<<< HEAD
   Card card;

   //Constructors
   public Scene(Card card) {
      this.card = card;
   }
   
   //Getters
   public Card getCard(){
      return card;
   }
   //Setters
=======
   String name;
   Card card;
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
>>>>>>> 16172047c3867689875e7b829fc506228e9a91a8
   
   //Other Methods
   public void getPart(){
   }
<<<<<<< HEAD
   
   public boolean evaluateWork(){
      return false;
   }
=======

public boolean evaluateWork(){
      return false;
   }

@Override
public void interact(Player player) {
	// TODO Auto-generated method stub
	
}
>>>>>>> 16172047c3867689875e7b829fc506228e9a91a8
}