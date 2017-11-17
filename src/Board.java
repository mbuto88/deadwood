import java.util.*;
//Board class
public class Board{
	private Player[] players;
    private ArrayList<Room> rooms;

		public void setRooms(ArrayList<Room> rooms) {
		this.rooms = rooms;
	}

		//Constructors
		Board(){

		}

		Board(ArrayList<Room> rooms){
		}

		//Getters
		public void renderBoard(){
		}

		public void showScore(){
		}

		//Other Methods
		public int rollDice(){
         Random rand = new Random();
         int val = rand.nextInt(6) + 1;
         System.out.println("You rolled a " + val);
         return val;
		}

		private int calcScore(Player p){
      return p.getFame() + p.getMoney() + p.getRank();
		}

		private void nextDay(){

		}
}
