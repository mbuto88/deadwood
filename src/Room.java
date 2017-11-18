//Room Class
import java.util.*;

public abstract class Room{
		private String name;
		private ArrayList<Room> nearby;
		private ArrayList<String> nearbyNames;

		//Constructors
		public Room(){
		}

		public Room(String name, ArrayList<Room> nearby){
       this.name = name;
       this.nearby = nearby;
    }

		Room(String name){
			this.name = name;
		}

		//Getters
		public ArrayList<String> getNearbyNames(){
			return nearbyNames;
		}

		public String getName(){
			return name;
		}

		public ArrayList<Room> getNearby(){
       return nearby;
    }

		//Setters
		public void setNearbyNames(ArrayList<String> nearbyNames){
			this.nearbyNames = nearbyNames;
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

    private void matchNearby(ArrayList<Room> rooms){
       for (Room r : rooms) {
          for (String s : nearbyNames) {
             if (r.getName().equals(s)){
                nearby.add(r);
             }
          }
       }
    }

		public abstract void interact(Player player);
}
