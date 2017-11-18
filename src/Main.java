import java.util.*;

//Main Class

public class Main{
   Player[] players;
   Board board;

   static ArrayList<Room> rooms = new ArrayList<Room>();
   static ArrayList<Card> cards = new ArrayList<Card>();
   static CastingOffice office;

   public static void main(String[] args) {
     //print greeting and instructions
     printWelcome();

     //parse xml
	   rooms = XmlParse.roomsXmlParse();
	   cards = XmlParse.cardsXmlParse();

   }

   public static void printWelcome(){
     //Ask for player names

     //List commands
   }
}
