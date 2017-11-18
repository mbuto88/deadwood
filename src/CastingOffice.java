import java.util.*;

//Casting Office Class
public class CastingOffice extends Room {
	 private ArrayList<Player> playersHere;

	 private ArrayList<Upgrade> upgrades;
	 private ArrayList<Room> nearbyRooms;


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
