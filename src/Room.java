//Room Class
import java.util.*;

public abstract class Room{
		private String name;
		private ArrayList<Room> nearby = new ArrayList<Room>();
		private ArrayList<String> nearbyNames;
		private int x;
		private int y;
		private int h;
		private int w;

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

		public int getX() {
			return x;
		}

		public int getY() {
			return y;
		}

		public int getH() {
			return h;
		}

		public int getW() {
			return w;
		}

		//Setters
		

		public void setX(int x) {
			this.x = x;
		}

		public void setY(int y) {
			this.y = y;
		}

		public void setH(int h) {
			this.h = h;
		}

		public void setW(int w) {
			this.w = w;
		}

		public void setName (String name) {
			this.name = name;
		}
		
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

    public void matchNearby(ArrayList<Room> rooms, CastingOffice office, Trailers trailers){
       for (Room r : rooms) {
          for (String s : nearbyNames) {
             if (r.getName().equals(s)){
                this.nearby.add(r);
             }
          }
       }
       for (String s: nearbyNames) {
         if (s.equals("office")){
            this.nearby.add(office);
         }
         if (s.equals("trailer")){
            this.nearby.add(trailers);
         }
       }
    }
}

