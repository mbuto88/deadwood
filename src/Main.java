
/*
Deadwood, a text-based representation.
*/

//Main Class
import java.util.*;

public class Main{
   //Player[] players;
   Board board;
   Static ArrayList<Player> players = new ArrayList<Player>();
   static ArrayList<Room> rooms = new ArrayList<Room>();
   static ArrayList<Card> cards = new ArrayList<Card>();
   static CastingOffice office;

   public static void main(String[] args) {
     //print greeting and instructions
     printWelcome();

     //print greeting and instructions
     printWelcome();


     //parse xml
	   rooms = XmlParse.roomsXmlParse();
	   cards = XmlParse.cardsXmlParse();
   }

   public static void printWelcome(){
     //Ask for player names

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
     }
   }

   public static void printWelcome(){
     System.out.println("Welcome to Deadwood");
     initializePlayers();

     //List commands
     String[][] instructions= new String[][]{
       {"The commands for the game are as follows: \n
       who", "where", "move room", "work part", "upgrade $ level", "upgrade cr level", "rehearse", "act", "end"},

        {"The software identifies the current player and any parts that the player is working.",
        "The software describes the current player’s room and any active scenes.",
        "The current player moves to the indicated room.",
        "Upgrade the current player to the indicated level by paying with money",
        "Upgrade the current player to the indicated level by paying with fame credits",
        "The current player rehearses",
        "The current player performs in its current role.",
        "End the current player’s turn"}
     };

     //prints the instructions to the console
      int i = 0;
      int k = 0;
      while(i < 9){
        System.out.print(instructions[i][k]);
        k++;
        System.out.print(instructions[i][k] + "\n");
        k=0;
        i++;
      }
   }

   public static void initializePlayers(){
     String name;
     Scanner cmdLine = new Scanner(System.in);
     System.out.println("How many people are playing today?");
     int total = cmdLine.nextInt();

     while(total > 0){
       System.out.println("Please enter everyones names, one line at a time");
       name = cmdLine.nextLine();
       this.players.add(new Player(name));
       total--;
     }
   }



}
