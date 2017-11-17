import java.util.*;

//Casting Office Class
public class CastingOffice extends Room {
   //private int[] fameRequired;
   //private int[] moneyRequired;

	 private Upgrade[] payInFame;
	 private Upgrade[] payInCash;
	 private Player[] whosHere;

	 //private ArrayList<Upgrade> upgrades;

	 /* the following variables will be used for the
	 		next assignment for GUI rendering
	 int x; //x-position
   int y; //y-position
   int h; //height
   int w; //width
	 */


   //Constructors
   public CastingOffice() {
		 this.payInCash = new Upgrade[1];
		 this.payInFame = new Upgrade[1];

		 this.whosHere = new Player[5];
		 //GUI Stuff
		 /*
		 this.x = x;
		 this.y = y;
		 this.h = h;
		 this.w = w;*/

   }

	 public CastingOffice(ArrayList<Upgrade> upgrades){
		 int i = upgrades.size();
		 while(i > 0){
			 //if upgrade is type fame

			 //if upgrade is type cash

		 }





	 }
	 /*
   public CastingOffice(ArrayList<Upgrade> upgrades, int x, int y, int h, int w) {
	   this.upgrades = upgrades;

	   this.x = x;
	   this.y = y;
	   this.h = h;
	   this.w = w;
   }*/

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
