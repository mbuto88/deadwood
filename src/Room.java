//Room Class

import java.util.*;
public abstract class Room{
		private String name;
        private List<Room> nearby;
		private List<Player> players;
		private List<String> nearbyNames;

		
		
		//Constructors
		public Room(){
      
		}
      public Room(String name, List<Room> nearby){
         this.name = name;
         this.nearby = nearby;
   
      }

		Room(String name){
			this.name = name;
		}

		//Getters
		public List<String> getNearbyNames() {
			return nearbyNames;
		}
		
		public String getName(){
			return name;
		}

		//Setters
		public void setNearbyNames(List<String> nearbyNames) {
			this.nearbyNames = nearbyNames;
		}
		public void addPlayer(Player player){
         players.add(player);
		}
      public void removePlayer(Player player){
         for (Player p : players) {
            if (player == p){
               players.remove(p);
            }
         }
      }
      public void addNearby(Room room){
         nearby.add(room);
      }
		//Other Methods
		public boolean isAdjacent(Room room){
         for (Room r : nearby) {
            if (room == r){
               return true;
            }
         }
         return false;
		}

		public abstract void interact(Player player);

      //I have no idea what this is supposed to do???
		public void setPrediction(){

		}
}
