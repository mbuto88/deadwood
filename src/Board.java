//Board class
import java.util.*;

public class Board{
	private Room[] rooms;
  private ArrayList<Player> players;
	private int daysRemaining;
	private boolean gameOver;

	//Constructors
	Board(){
		this.players = new Player[5];
		this.players = new ArrayList<Player>();
		setGameLength();
	}

	//Only room parameters
	Board(ArrayList<Room> parsedRooms){
		this.players = new ArrayList<Player>();
		this.rooms = new Room[rooms.size()];
		setRooms(parsedRooms);
		setGameLength();
	}

	//Both room and player parameters
	Board(ArrayList<Room> parsedRooms, ArrayList<Player> parsedPlayers){
		this.players = new ArrayList<Player>();
		this.rooms = new Room[rooms.size()];
		populate(parsedRooms);
		populate(players);
		setGameLength();
	}

	//Helper methods for Constructors
	private void populate(ArrayList<Room> parsedRooms) {
		int i = 0;
		while(i < parsedRooms.size()){
			this.rooms[i] = parsedRooms.get(i);
			i++;
		}
	}

	private void populate(ArrayList<Player> parsedPlayers) {
		int i = 0;
		while(i < parsedRooms.size()){
			this.players.add(parsedRooms.get(i));
			i++;
		}
	}

	//Determines length of gameplay in game days
	private void setGameLength(int players){
		if(players <= 3){
			this.daysRemaining = 3;
		} else {
			this.daysRemaining = 4;
		}
		if(this.daysRemaining > 0){
			this.gameOver = false;
		}
	}

	//Getters

	public boolean isGameOver(){
		return this.gameOver;
	}

	/* GUI Related method
	public void renderBoard(){
	} */

	//Outputs score of all players
	public void showScore(){
		int i = 0;
		while(i < players.size()){
			system.out.println("Cr/Fame: " +  players.get(i).getFame()
																+ " $" + players.get(i).getFame());
			i++;
		}
	}

	//Outputs score of specified player
	public void showScore(Player player){
			system.out.print("Cr/Fame: " +  player.getFame()
																+ " Money: $" + players.get(i).getFame());
	}

	public int getDaysLeft(){
		return this.daysRemaining;
	}

	//Other Methods
	public int rollDice(){
		int result;
		Random roll = new Random();
	  result = roll.nextInt(6);

		System.out.println("Roll was: "+ result);
		return result;
	}

	private int calcScore(Player player){
		//dollars + fame + (5* rank)
		int score = player.getFame() + player.getMoney() + (5 * player.getRank());
		return score;
	}

	public void printScore(Player player){
		System.out.println(player.getName() + "'s score was: " + calcScore(player));
	}

	public Player printAllScores(){
		int score;
		int i = 0;
		Player winner = new Player();

		while(i < this.players.size()){
			printScore(this.players.get(i));
			score = calcScore(this.players.get(i));

			if(score > calcScore(winner)){
				winner = this.players.get(i);
			}
		}
		return winner;
	}

	public void nextDay(){
		this.daysRemaining--;
		if(this.daysRemaining == 0){
      
			//Game is over
			this.gameOver = true;
			System.out.println(printAllScores().getName());
		}
	}
}
