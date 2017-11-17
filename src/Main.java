import java.util.*;

//Main Class



public class Main{
   Player[] players;
   Board board;

   static ArrayList<Room> rooms = new ArrayList<Room>();
   static ArrayList<Card> cards = new ArrayList<Card>();

   
   public static void main(String[] args) {

	   rooms = XmlParse.roomsXmlParse();
	   cards = XmlParse.cardsXmlParse();

   }
}