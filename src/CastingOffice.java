import java.util.*;

//Casting Office Class
public class CastingOffice extends Room {
   int[] fameRequired;
   int[] moneyRequired;
<<<<<<< HEAD

=======
   int x;
   int y;
   int h;
   int w;
   ArrayList<Upgrade> upgrades;
>>>>>>> 16172047c3867689875e7b829fc506228e9a91a8
   //Constructors
   public CastingOffice() {
   
   }
<<<<<<< HEAD
=======

   public CastingOffice(ArrayList<Upgrade> upgrades, int x, int y, int h, int w) {
	   this.upgrades = upgrades;

	   this.x = x;
	   this.y = y;
	   this.h = h;
	   this.w = w;
   }
>>>>>>> 16172047c3867689875e7b829fc506228e9a91a8
   
   //Getters

   //Setters
   
   //Other Methods
   public void raiseRank(){
   }
   
   public boolean verifyPoints(){
      return false;
   }
<<<<<<< HEAD
=======

@Override
public void interact(Player player) {
	// TODO Auto-generated method stub
	
}
>>>>>>> 16172047c3867689875e7b829fc506228e9a91a8
}