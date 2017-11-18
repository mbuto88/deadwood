import java.util.*;

//Casting Office Class
public class CastingOffice extends Room {
	 private Player[] playersHere;
	 private ArrayList<Upgrade> upgrades;


   //Constructors
   public CastingOffice() {
		 this.upgrades = new ArrayList<Upgrade>();
		 this.playersHere = new ArrayList<Player>();
   }

	 public CastingOffice(ArrayList<Room> nearby, ArrayList<Player> players, ArrayList<Room> nearby) {
		 this.upgrades = new ArrayList<Upgrade>();
		 this.playersHere = new ArrayList<Player>();
   }

	 public CastingOffice(ArrayList<Upgrade> upgrades){
		 
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
   public int raiseRank(){
   }

   public boolean verifyPoints(){
      return false;
   }

@Override
public void interact(Player player) {
	// TODO Auto-generated method stub

}
}
