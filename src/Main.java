
/*
Deadwood, a text-based representation.
*/

//Main Class
import java.util.*;

public class Main{
   //Player[] players;
   Board board;
   static ArrayList<Player> players = new ArrayList<Player>();
   static ArrayList<Room> rooms = new ArrayList<Room>();
   static ArrayList<Card> cards = new ArrayList<Card>();
   static CastingOffice office;

   public static void main(String[] args) {
//     //print greeting and instructions
//     printWelcome();

     //print greeting and instructions
     printWelcome();


     //parse xml
	   rooms = XmlParse.roomsXmlParse();
	   cards = XmlParse.cardsXmlParse();
   }

   public static void printWelcome(){
     //Ask for player names
	  System.out.println("Welcome to Deadwood");
	    initializePlayers();

	    //List commands
	    String[] instructionsLeftSide = new String[]{"The commands for the game are as follows: \nwho --- ", "where --- ", "move room --- ", "work part --- ", "upgrade $ level --- ", "upgrade cr level --- ", "rehearse --- ", "act --- ", "end --- "};
	     
	    String[] instructionsRightSide = new String[]{"The software identifies the current player and any parts that the player is working.",
	       "The software describes the current players room and any active scenes.",
	       "The current player moves to the indicated room.",
	       "The current player takes the indicated role",
	       "Upgrade the current player to the indicated level by paying with money",
	       "Upgrade the current player to the indicated level by paying with fame credits",
	       "The current player rehearses",
	       "The current player performs in its current role.",
	       "End the current players turn"};

	    //prints the instructions to the console
	    int i = 0;
	    while(i < 9){
	    	System.out.print(instructionsLeftSide[i]);
	        System.out.print(instructionsRightSide[i] + "\n");
	        i++;
	      }
     //Construct Board
     Board gameBoard = new Board(rooms, players);
     int turn = 0;

     while(!gameBoard.isGameOver()){
       //Loop the game steps
       players.get(turn).takeTurn(gameBoard);
       if(players.get(turn).mayUpgrade()){
         //ask player if they would like to upgrade
         //if so use board method
       }
       turn++;
     }
   }

   

   public static void initializePlayers(){
     String name;
     Scanner cmdLine = new Scanner(System.in);
     System.out.println("How many people are playing today?");
     int total = cmdLine.nextInt();
     System.out.println("Please enter everyones names, one line at a time");
     while(total >= 0){

       name = cmdLine.nextLine();
       players.add(new Player(name));
       total--;
     }
   }



}
