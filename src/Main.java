//Main Class

import java.util.*;
import java.io.*;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;



public class Main{
   Player[] players;
   Board board;
   static ArrayList<Room> rooms = new ArrayList<Room>();
   static ArrayList<Card> cards = new ArrayList<Card>();
   
   public static void main(String[] args) {
	   Board board = new Board();
	   board.setRooms(rooms);
	   //Parsing the board.xml file to get our scene information
	   try {
		   
		   DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		   DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		   Document scenesDoc = dBuilder.parse("board.xml");
		   scenesDoc.getDocumentElement().normalize();
		   NodeList setNList = scenesDoc.getElementsByTagName("set");
		   NodeList trailerList = scenesDoc.getElementsByTagName("trailer");
		   NodeList CastingOfficeList = scenesDoc.getElementsByTagName("office");
		   
		   //Creating the trailer object and adding it to rooms
		   if(trailerList.item(0).getNodeType() == Node.ELEMENT_NODE) {
			   Element trailerElement = (Element) trailerList.item(0);
			   Element areaElement = (Element) trailerElement.getElementsByTagName("area").item(0);
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
		   if(CastingOfficeList.item(0).getNodeType() == Node.ELEMENT_NODE) {
			   Element officeElement = (Element) CastingOfficeList.item(0);
			   Element areaElement = (Element) officeElement.getElementsByTagName("area").item(0);
			   int[] tempOfficeArea = new int[]{Integer.parseInt(areaElement.getAttribute("x")),
  						Integer.parseInt(areaElement.getAttribute("y")),
  						Integer.parseInt(areaElement.getAttribute("h")),
  						Integer.parseInt(areaElement.getAttribute("w"))};
			   Trailers tempTrailer = new Trailers(1, tempOfficeArea[0], tempOfficeArea[1], tempOfficeArea[2], tempOfficeArea[3]);
			   
			   Element neighborsElementTrailers = (Element) officeElement.getElementsByTagName("neighbors").item(0);
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
		   
		   //Loop to traverse the list of set nodes and create Part objects, and then scene objects
		   for (int i = 0; i < setNList.getLength(); i++) {
			   Node setNode = setNList.item(i);
			   
			   if(setNode.getNodeType() == Node.ELEMENT_NODE) {
				   Element setElement = (Element) setNode;
				   Element areaElement = (Element) setElement.getElementsByTagName("area").item(0);
				   String tempSetName = (String)setElement.getAttribute("name");
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
						   int[] tempExtraPartArea = new int[]{Integer.parseInt(extraPartAreaElement.getAttribute("x")),
								   Integer.parseInt(extraPartAreaElement.getAttribute("y")),
								   Integer.parseInt(extraPartAreaElement.getAttribute("h")),
								   Integer.parseInt(extraPartAreaElement.getAttribute("w"))};
						   String tempLine = extraPartChildElement.getElementsByTagName("line").item(0).toString();
						   Part tempPart = new Part(tempPartName, tempLevel, tempExtraPartArea[0], tempExtraPartArea[1], tempExtraPartArea[2], tempExtraPartArea[3], tempLine);
						   tempExtraParts.add(tempPart);
					   }
				   }
				   
				   Scene tempScene = new Scene(tempSetName, tempExtraParts, tempSceneArea[0], tempSceneArea[1], tempSceneArea[2], tempSceneArea[3]);
				   tempScene.setNearbyNames(nearbyNames);
				   rooms.add(tempScene);
			   }
		   }
		   
	   } catch (Exception e){
		   e.printStackTrace();
	   }
	   
	   //Parsing the cards.xml file to get the information for our scene cards
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
				   String tempCardDesc = cardElement.getFirstChild().toString();
				   Node tempCardNode = cardElement.getElementsByTagName("scene").item(0);
				   if(tempCardNode.getNodeType() == Node.ELEMENT_NODE){					   
					   tempCardNumber = Integer.parseInt(((Element) tempCardNode).getAttribute("number"));
				   }
				   
				   ArrayList<Part> tempParts = new ArrayList<Part>();
				   NodeList partsNList = cardElement.getElementsByTagName("part");
				   
				   //Loop to traverse the list parts and create part objects
				   for (int m = 0; m < partsNList.getLength(); m++) {
					   Element partElement = (Element) partsNList.item(m);
					   String tempPartName = partElement.getAttribute("name");
					   int tempLevel = Integer.parseInt(partElement.getAttribute("level"));
					   Element partAreaElement = (Element) partElement.getElementsByTagName("area").item(0);
					   int[] tempExtraPartArea = new int[]{Integer.parseInt(partAreaElement.getAttribute("x")),
		   						Integer.parseInt(partAreaElement.getAttribute("y")),
		   						Integer.parseInt(partAreaElement.getAttribute("h")),
		   						Integer.parseInt(partAreaElement.getAttribute("w"))};
					   String tempLine = partElement.getElementsByTagName("line").item(0).toString();
					   Part tempPart = new Part(tempPartName, tempLevel, tempExtraPartArea[0], tempExtraPartArea[1], tempExtraPartArea[2], tempExtraPartArea[3], tempLine);
					   tempParts.add(tempPart);
				   }
				   
				   Card tempCard = new Card(tempCardName, tempCardNumber, tempCardDesc, tempCardIMG, tempParts, tempCardBudget);
				   cards.add(tempCard);
			   }
			   
		   }
	   } catch (Exception e){
		   e.printStackTrace();
	   }
   }
}