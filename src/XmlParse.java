import javax.xml.parsers.DocumentBuilderFactory;
import java.util.ArrayList;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;

public class XmlParse {

	/*Parsing the board.xml file to get our scene information,
		returns a list of room objects from the board.xml file*/
	public static ArrayList<Room> roomsXmlParse() {

		//List to be returned
		ArrayList<Room> rooms = new ArrayList<Room>();

		try {
			//Creating the factory to parse the info
		   DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		   DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		   Document scenesDoc = dBuilder.parse("board.xml");
		   scenesDoc.getDocumentElement().normalize();

			 //Creating the lists of nodes to be parsed through, set, trailer, and office
		   NodeList setNList = scenesDoc.getElementsByTagName("set");
		   Node trailerNode = scenesDoc.getElementsByTagName("trailer").item(0);
		   Node CastingOfficeNode = scenesDoc.getElementsByTagName("office").item(0);

		   //Creating the trailer object and adding it to rooms
		   if(trailerNode.getNodeType() == Node.ELEMENT_NODE) {
			   Element trailerElement = (Element) trailerNode;
			   Element areaElement = (Element) trailerElement.getElementsByTagName("area").item(0);

				 //Area info is added to an array
			   int[] tempTrailerArea = new int[]{Integer.parseInt(areaElement.getAttribute("x")),
						Integer.parseInt(areaElement.getAttribute("y")),
						Integer.parseInt(areaElement.getAttribute("h")),
						Integer.parseInt(areaElement.getAttribute("w"))};
			   Trailers tempTrailer = new Trailers(1, tempTrailerArea[0], tempTrailerArea[1], tempTrailerArea[2], tempTrailerArea[3]);

			   Element neighborsElementTrailers = (Element) trailerElement.getElementsByTagName("neighbors").item(0);
			   NodeList neighborsNListTrailers = neighborsElementTrailers.getElementsByTagName("neighbor");
			   ArrayList<String> nearbyTrailerNames = new ArrayList<String>();

				 //Loop to traverse the list of neighbors and obtain names
			   for (int k = 0; k < neighborsNListTrailers.getLength(); k++) {
				   if(neighborsNListTrailers.item(k).getNodeType() == Node.ELEMENT_NODE){
					   Element neighborsChildElement = (Element) neighborsNListTrailers.item(k);
					   nearbyTrailerNames.add(neighborsChildElement.getAttribute("name"));
				   }
			   }
			   tempTrailer.setNearbyNames(nearbyTrailerNames);
			   rooms.add(tempTrailer);
		   }

		   //Creating the Casting office object and adding it to rooms
		   if(CastingOfficeNode.getNodeType() == Node.ELEMENT_NODE) {
			   Element officeElement = (Element) CastingOfficeNode;
			   Element areaElement = (Element) officeElement.getElementsByTagName("area").item(0);

			 //Area info is added to an array
			   int[] tempOfficeArea = new int[]{Integer.parseInt(areaElement.getAttribute("x")),
						Integer.parseInt(areaElement.getAttribute("y")),
						Integer.parseInt(areaElement.getAttribute("h")),
						Integer.parseInt(areaElement.getAttribute("w"))};

			   Element neighborsElementOffice = (Element) officeElement.getElementsByTagName("neighbors").item(0);
			   NodeList neighborsNListOffice = neighborsElementOffice.getElementsByTagName("neighbor");
			   ArrayList<String> nearbyOfficeNames = new ArrayList<String>();

				 //Loop to traverse the list of neighbors and obtain names
			   for (int k = 0; k < neighborsNListOffice.getLength(); k++) {
				   if(neighborsNListOffice.item(k).getNodeType() == Node.ELEMENT_NODE){
					   Element neighborsChildElement = (Element) neighborsNListOffice.item(k);
					   nearbyOfficeNames.add(neighborsChildElement.getAttribute("name"));
				   }
			   }

			   //Office object is created to be added to the list
			   CastingOffice tempOffice = new CastingOffice();
			   tempOffice.setNearbyNames(nearbyOfficeNames);
			   rooms.add(tempOffice);
		   }

		   //Loop to traverse the list of set nodes and create Part objects, and then scene objects
		   for (int i = 0; i < setNList.getLength(); i++) {
			   Node setNode = setNList.item(i);

			   if(setNode.getNodeType() == Node.ELEMENT_NODE) {
				   Element setElement = (Element) setNode;
				   Element areaElement = (Element) setElement.getElementsByTagName("area").item(0);
				   String tempSetName = (String)setElement.getAttribute("name");

				 	 //Area info is added to an array
				   int[] tempSceneArea = new int[]{Integer.parseInt(areaElement.getAttribute("x")),
						   						Integer.parseInt(areaElement.getAttribute("y")),
						   						Integer.parseInt(areaElement.getAttribute("h")),
						   						Integer.parseInt(areaElement.getAttribute("w"))};

				   ArrayList<String> nearbyNames = new ArrayList<String>();
				   Element neighborsElement = (Element) setElement.getElementsByTagName("neighbors").item(0);
				   NodeList neighborsNList = neighborsElement.getElementsByTagName("neighbor");

				   //Loop to traverse the list of neighbors and obtain names
				   for (int k = 0; k < neighborsNList.getLength(); k++) {
					   if(neighborsNList.item(k).getNodeType() == Node.ELEMENT_NODE){
						   Element neighborsChildElement = (Element) neighborsNList.item(k);
						   nearbyNames.add(neighborsChildElement.getAttribute("name"));
					   }
				   }

				   ArrayList<Part> tempExtraParts = new ArrayList<Part>();
				   Element extraPartsElement = (Element) setElement.getElementsByTagName("parts").item(0);
				   NodeList extraPartsNList = extraPartsElement.getElementsByTagName("part");

				   //Loop to traverse the list of extra parts and create part objects
				   for (int m = 0; m < extraPartsNList.getLength(); m++) {
					   if(extraPartsNList.item(m).getNodeType() == Node.ELEMENT_NODE){
						   Element extraPartChildElement = (Element) extraPartsNList.item(m);
						   String tempPartName = extraPartChildElement.getAttribute("name");
						   int tempLevel = Integer.parseInt(extraPartChildElement.getAttribute("level"));
						   Element extraPartAreaElement = (Element) extraPartChildElement.getElementsByTagName("area").item(0);

						 //Area info is added to an array
						   int[] tempExtraPartArea = new int[]{Integer.parseInt(extraPartAreaElement.getAttribute("x")),
								   Integer.parseInt(extraPartAreaElement.getAttribute("y")),
								   Integer.parseInt(extraPartAreaElement.getAttribute("h")),
								   Integer.parseInt(extraPartAreaElement.getAttribute("w"))};
						   String tempLine = extraPartChildElement.getElementsByTagName("line").item(0).getTextContent();
						   Part tempPart = new Part(tempPartName, tempLevel, tempExtraPartArea[0], tempExtraPartArea[1], tempExtraPartArea[2], tempExtraPartArea[3], tempLine);
						   tempExtraParts.add(tempPart);
					   }
				   }
					 
				   //Scene object is created to be added to the list
				   Scene tempScene = new Scene(tempSetName, tempExtraParts, tempSceneArea[0], tempSceneArea[1], tempSceneArea[2], tempSceneArea[3]);
				   tempScene.setNearbyNames(nearbyNames);
				   rooms.add(tempScene);
			   }
		   }
		} catch (Exception e){
			   e.printStackTrace();
		}
		return rooms;
	}

	//Parsing the cards.xml file to get the information for our scene cards
		public static ArrayList<Card> cardsXmlParse() {
		   ArrayList<Card> cards = new ArrayList<Card>();

		   try {
		   DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		   DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		   Document cardsDoc = dBuilder.parse("cards.xml");
		   cardsDoc.getDocumentElement().normalize();
		   NodeList cardNList = cardsDoc.getElementsByTagName("card");

		   //Loop to traverse the list of set nodes and create Part objects, and then card objects
		   for (int i = 0; i < cardNList.getLength(); i++) {
			   Node cardNode = cardNList.item(i);
			   int tempCardNumber = 0;
			   if(cardNode.getNodeType() == Node.ELEMENT_NODE) {
				   Element cardElement = (Element) cardNode;
				   String tempCardName = (String)cardElement.getAttribute("name");
				   String tempCardIMG = (String)cardElement.getAttribute("img");
				   int tempCardBudget = Integer.parseInt(cardElement.getAttribute("budget"));
				   String tempCardDesc = "";

				   Node tempScenedNode = cardElement.getElementsByTagName("scene").item(0);
				   if(tempScenedNode.getNodeType() == Node.ELEMENT_NODE){
					   tempCardNumber = Integer.parseInt(((Element) tempScenedNode).getAttribute("number"));
					   tempCardDesc = tempScenedNode.getTextContent();
				   }

				   ArrayList<Part> tempParts = new ArrayList<Part>();
				   NodeList partsNList = cardElement.getElementsByTagName("part");

				   //Loop to traverse the list parts and create part objects
				   for (int m = 0; m < partsNList.getLength(); m++) {
					   Element partElement = (Element) partsNList.item(m);
					   String tempPartName = partElement.getAttribute("name");
					   int tempLevel = Integer.parseInt(partElement.getAttribute("level"));
					   Element partAreaElement = (Element) partElement.getElementsByTagName("area").item(0);
					 //Area info is added to an array
					   int[] tempExtraPartArea = new int[]{Integer.parseInt(partAreaElement.getAttribute("x")),
		   						Integer.parseInt(partAreaElement.getAttribute("y")),
		   						Integer.parseInt(partAreaElement.getAttribute("h")),
		   						Integer.parseInt(partAreaElement.getAttribute("w"))};
					   String tempLine = partElement.getElementsByTagName("line").item(0).getTextContent();
					   //Part object is created to be added to the list
					   Part tempPart = new Part(tempPartName, tempLevel, tempExtraPartArea[0], tempExtraPartArea[1], tempExtraPartArea[2], tempExtraPartArea[3], tempLine);
					   tempParts.add(tempPart);
				   }
				   //Card part is created to be added to the list
				   Card tempCard = new Card(tempCardName, tempCardNumber, tempCardDesc, tempCardIMG, tempParts, tempCardBudget);
				   cards.add(tempCard);
			   }

		   }
	   } catch (Exception e){
		   e.printStackTrace();
	   }
	   return cards;
	}
}
