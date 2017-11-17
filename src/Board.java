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
		public void rollDice(){
		}

		private int calcScore(){
      return 0;
		}

		private void nextDay(){

		}
}
