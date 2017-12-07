
/*
Deadwood, a text-based representation.

Thank you to http://patorjk.com/software/taag/ and https://www.freeformatter.com/java-dotnet-escape.html
for help with the ASCII art title
*/

//Main Class
import java.util.*;

public class Deadwood{

   static Board board;
   static ArrayList<Player> players = new ArrayList<Player>();
   static ArrayList<Room> rooms = new ArrayList<Room>();
   static ArrayList<Card> cards = new ArrayList<Card>();
   static CastingOffice office;
   static BoardLayersListener GUIBoard;
   static int turn;


   public static void main(String[] args) {
		System.out.println();
 		System.out.println(" /$$      /$$           /$$                                            \r\n| $$  /$ | $$          | $$                                            \r\n| $$ /$$$| $$  /$$$$$$ | $$  /$$$$$$$  /$$$$$$  /$$$$$$/$$$$   /$$$$$$ \r\n| $$/$$ $$ $$ /$$__  $$| $$ /$$_____/ /$$__  $$| $$_  $$_  $$ /$$__  $$\r\n| $$$$_  $$$$| $$$$$$$$| $$| $$      | $$  \\ $$| $$ \\ $$ \\ $$| $$$$$$$$\r\n| $$$/ \\  $$$| $$_____/| $$| $$      | $$  | $$| $$ | $$ | $$| $$_____/\r\n| $$/   \\  $$|  $$$$$$$| $$|  $$$$$$$|  $$$$$$/| $$ | $$ | $$|  $$$$$$$\r\n|__/     \\__/ \\_______/|__/ \\_______/ \\______/ |__/ |__/ |__/ \\_______/\r\n                                                            ");
 		System.out.println(" /$$$$$$$$          \r\n|__  $$__/          \r\n   | $$     /$$$$$$ \r\n   | $$    /$$__  $$\r\n   | $$   | $$  \\ $$\r\n   | $$   | $$  | $$\r\n   | $$   |  $$$$$$/\r\n   |__/    \\______/ ");
 		System.out.println("$$$$$$$\\                            $$\\                                         $$\\ \r\n$$  __$$\\                           $$ |                                        $$ |\r\n$$ |  $$ | $$$$$$\\   $$$$$$\\   $$$$$$$ |$$\\  $$\\  $$\\  $$$$$$\\   $$$$$$\\   $$$$$$$ |\r\n$$ |  $$ |$$  __$$\\  \\____$$\\ $$  __$$ |$$ | $$ | $$ |$$  __$$\\ $$  __$$\\ $$  __$$ |\r\n$$ |  $$ |$$$$$$$$ | $$$$$$$ |$$ /  $$ |$$ | $$ | $$ |$$ /  $$ |$$ /  $$ |$$ /  $$ |\r\n$$ |  $$ |$$   ____|$$  __$$ |$$ |  $$ |$$ | $$ | $$ |$$ |  $$ |$$ |  $$ |$$ |  $$ |\r\n$$$$$$$  |\\$$$$$$$\\ \\$$$$$$$ |\\$$$$$$$ |\\$$$$$\\$$$$  |\\$$$$$$  |\\$$$$$$  |\\$$$$$$$ |\r\n\\_______/  \\_______| \\_______| \\_______| \\_____\\____/  \\______/  \\______/  \\_______|\r\n                                                              ");

	 //parse xml
	   rooms = XmlParse.roomsXmlParse();
	   cards = XmlParse.cardsXmlParse();

	 //Set nearby for room objects
	  for(int i = 0; i < rooms.size(); i++) {
		  rooms.get(i).matchNearby(rooms, (CastingOffice)rooms.get(1), (Trailers)rooms.get(0));
	  }

	//Set random cards for scene objects
	  for(int i = 2; i < rooms.size(); i++) {
		  Random randomGenerator = new Random();
		  int randomInt = randomGenerator.nextInt(cards.size());
		  rooms.get(i).setCard(cards.get(randomInt));
	  }

	//Creating board GUI component
	   GUIBoard = new BoardLayersListener();
	   GUIBoard.setVisible(true);
	   GUIBoard.addPlayers();
	   GUIBoard.startDay();

     //print greeting and instructions
     printWelcome();
     }

   public static void printWelcome(){
     //Print commands ---REMOVE---
			printCommands();

     //Construct Board
     board = new Board(rooms, players);
     turn = 0;

     while(!board.isGameOver()){
    	//Check if the day is over
       boolean isDayOver = true;

       for(Room r : board.getRooms()){
         if (r instanceof Scene){
            if (!((Scene)r).isOver()){
               isDayOver = false;
            }
         }
       }

       //Set random cards for scene objects
    	 if (isDayOver) {
    		  for(int k = 2; k < rooms.size(); k++) {
    			  Random randomGenerator = new Random();
    			  int randomInt = randomGenerator.nextInt(cards.size());
    			  rooms.get(k).setCard(cards.get(randomInt));
    		  }
    		  GUIBoard.startDay();
    		  board.nextDay(players);
       }

      //Loop the game steps
       System.out.println("Current turn: " + players.get(turn).getName());
       players.get(turn).takeTurn(board, players, turn);
       turn++;
       turn %= players.size();
     }
   }

	 public static void printCommands(){
		 //List commands
		 String[] instructionsLeftSide = new String[]{"The commands for the game are as follows: \nwho --- ", "where --- ", "move --- ", "work --- ", "upgrade $ level --- ", "upgrade cr level --- ", "rehearse --- ", "act --- ", "end --- "};
		 String[] instructionsRightSide = new String[]{"The software identifies the current player and any parts that the player is working.",
				"The software describes the current players room and any active scenes.",
				"The current player can choose a room to move to",
				"The current player can choose a role to take",
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
	   }

   public static void initializePlayer(String name){
       players.add(new Player(name, rooms.get(0)));
   }
}
