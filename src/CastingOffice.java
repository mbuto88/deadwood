//Casting Office Class
import java.util.*;

public class CastingOffice extends Room {

	 private ArrayList<Upgrade> upgrades;
	 private ArrayList<Room> nearbyRooms;

   //Constructors
   public CastingOffice() {
		 this.upgrades = new ArrayList<Upgrade>();
		 this.nearbyRooms = new ArrayList<Room>();
   }

	 public CastingOffice(ArrayList<Room> nearby, ArrayList<Upgrade> upgradeList) {
		 this.upgrades = upgradeList;
		 this.nearbyRooms = nearby;
   }

   //Upgrade rank method
   public void raiseRank(Player player, String currency, int level){
		 //Player must be in casting office
		 if(player.getLocation().equals(this.getName())){

			 //look for the desired upgrade
			 int i = 0;
				while(i < this.upgrades.size()){

					//Check if the player can afford upgrade
					if((level == upgrades.get(i).getLevel() )&& upgrades.get(i).getCurrency().equals(currency) ){
						boolean isValid = (player.spend(upgrades.get(i).getAmt(), currency) > 0);
						if(isValid){
							player.levelUp(isValid, level);
							System.out.println("Upgrade successful, your new rank is " + level);
						}
					}
			  }
		  }
	  }

@Override
public void interact(Player player) {
	// TODO Auto-generated method stub
	
}
   }
