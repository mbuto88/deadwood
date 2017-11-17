import java.util.*;

//Casting Office Class
public class CastingOffice extends Room {
   int[] fameRequired;
   int[] moneyRequired;
   int x;
   int y;
   int h;
   int w;

   //Constructors
   public CastingOffice() {
   
   }

   public CastingOffice(int x, int y, int h, int w) {
	   this.x = x;
	   this.y = y;
	   this.h = h;
	   this.w = w;
   }
   
   //Getters

   //Setters
   
   //Other Methods
   public void raiseRank(){
   }
   
   public boolean verifyPoints(){
      return false;
   }

@Override
public void interact(Player player) {
	// TODO Auto-generated method stub
	
}
}