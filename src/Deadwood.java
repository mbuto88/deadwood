
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
		  ((Scene)rooms.get(i)).setCard(cards.get(randomInt));
	  }

	//Creating board GUI component
	   GUIBoard = new BoardLayersListener();
	   GUIBoard.setVisible(true);
	   GUIBoard.addPlayers();
	   GUIBoard.startDay();

     //print greeting and instructions
	   startGame();
     }

   public static void startGame(){

     //Construct Board
     board = new Board(rooms, players);
     turn = 0;

     //Start the first turn
     players.get(turn).takeTurn(board, players);
     
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
    			  ((Scene)rooms.get(k)).setCard(cards.get(randomInt));
    		  }
    		  GUIBoard.startDay();
    		  board.nextDay(players);
       }
     }
   }


   public static void initializePlayer(String name){
       players.add(new Player(name, rooms.get(0)));
   }
}
