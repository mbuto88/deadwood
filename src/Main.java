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
   private static ArrayList<Room> scenes = new ArrayList<Room>();
   
   public static void main(String[] args) {
	   Board board = new Board();
	   board.setRooms(scenes);
	   try {
		   
		   DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		   DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		   Document boardDoc = dBuilder.parse("board.xml");
		   boardDoc.getDocumentElement().normalize();
		   NodeList setNList = boardDoc.getChildNodes();
		   
		   //Loop to traverse the list of set nodes and create Scene objects, Part objects, and Card objects
		   for (int i = 0; i < setNList.getLength(); i++) {
			   Node setNode = setNList.item(i);
			   
			   if(setNode.getNodeType() == Node.ELEMENT_NODE) {
				   Element setElement = (Element) setNode;
				   Element areaElement = (Element) setElement.getElementsByTagName("area").item(0);
				   String tempSceneName = (String)setElement.getAttribute("name");
				   int[] tempSceneArea = new int[]{Integer.parseInt(areaElement.getAttribute("x")),
						   						Integer.parseInt(areaElement.getAttribute("y")),
						   						Integer.parseInt(areaElement.getAttribute("h")),
						   						Integer.parseInt(areaElement.getAttribute("w"))};
				   
				   ArrayList<String> nearbyNames = new ArrayList<String>();
				   Element neighborsElement = (Element) setElement.getElementsByTagName("neighbors").item(0);
				   NodeList neighborsNList = neighborsElement.getChildNodes();
				   
				   //Loop to traverse the list of neighbors and obtain names
				   for (int k = 0; k < neighborsNList.getLength(); k++) {
					   Element neighborsChildElement = (Element) neighborsNList.item(k);
					   nearbyNames.add(neighborsChildElement.getAttribute("name"));
				   }
				   
				   ArrayList<Part> tempExtraParts = new ArrayList<Part>();
				   Element extraPartsElement = (Element) setElement.getElementsByTagName("parts").item(0);
				   NodeList extraPartsNList = extraPartsElement.getChildNodes();
				   
				   //Loop to traverse the list of extra parts and create part objects
				   for (int m = 0; m < extraPartsNList.getLength(); m++) {
					   Element extraPartChildElement = (Element) neighborsNList.item(m);
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
				   
				   Scene tempScene = new Scene(tempSceneName, tempExtraParts, tempSceneArea[0], tempSceneArea[1], tempSceneArea[2], tempSceneArea[3]);
				   tempScene.setNearbyNames(nearbyNames);
				   scenes.add(tempScene);
			   }
		   }
		   
	   } catch (Exception e){
		   e.printStackTrace();
	   }
   }
}