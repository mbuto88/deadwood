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

	 public CastingOffice( ArrayList<Upgrade> upgradeList, int x, int y, int h, int w) {
		 this.upgrades = upgradeList;
		 this.setX(x);
		 this.setY(y);
		 this.setH(h);
		 this.setW(w);
   }
   
   public ArrayList<Upgrade> getUpgrades(){
      return upgrades;
   }
   //Upgrade rank method
   public void raiseRank(Player player, String currency, int level){
		 //Player must be in casting office
       /*
		 if(player.getLocation() instanceof CastingOffice){

			 //look for the desired upgrade
				for (int i = 0; i < this.upgrades.size(), i++){

					//Check if the player can afford upgrade
					if((level == upgrades.get(i).getLevel() )&& upgrades.get(i).getCurrency().equals(currency) ){
                  if (player.spend(upgrades.get(i).getAmt(), currency) > 0){
                     player.levelUp(isValid, level);
							System.out.println("Upgrade successful, your new rank is " + level);
                  }
                  else {
                     System.out.println("You don't have enough " + upgrades.get(i).getCurrency() + "s" );
                  }
					}
			  }
		  }
        */
        
        
        
        
        
        boolean foundUpgrade = false;
        for (Upgrade u : upgrades) {
            if (u.getLevel() == level && u.getCurrency().equals(currency)){
               foundUpgrade = true;
               int check;
               if (u.getCurrency().equals("dollars")){
                  check = player.getMoney();
               }
               else{
                  check = player.getFame();
               }
               if (check >= u.getAmt()){
                  player.levelUp(true, level);
						System.out.println("Upgrade successful, your new rank is " + level);
                  
               }
               else {
                  System.out.println("You don't have enough " + u.getCurrency() + "s" );
               }
               
            }
        }
        if (!foundUpgrade){
            System.out.println("That's not an upgrade you can get!");
        }
	  }
   }
