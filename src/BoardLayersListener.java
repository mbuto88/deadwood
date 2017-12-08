
/*

    Deadwood GUI helper file
    Author: Moushumi Sharmin
    This file shows how to create a simple GUI using Java Swing and Awt Library
    Classes Used: JFrame, JLabel, JButton, JLayeredPane
 ********************************************
 	Modified by: Nick Amundsen
*/


import java.awt.*;
import javax.swing.*;
//import javax.swing.ImageIcon;

import java.awt.event.*;
import java.util.ArrayList;


public class BoardLayersListener extends JFrame {

  // Private Attributes

  // JLabels
  JLabel boardlabel;
  ArrayList<JLabel> cardLabels = new ArrayList<JLabel>();
  ArrayList<JLabel> playerLabels = new ArrayList<JLabel>();
  ArrayList<JLabel> takeLabels = new ArrayList<JLabel>();;
  JLabel mLabel;
  
  // Information text area and scroll pane
  JTextArea logTextArea;
  JScrollPane logSPane;
  JTextArea playerInfoTextArea;
  
  //JButtons
  JButton bAct;
  JButton bRehearse;
  JButton bMove;
  JButton bChooseRole;
  JButton bEndTurn;
  JButton bUpgrade;
  
  // JLayered Pane
  JLayeredPane bPane;


  // Adds text to the game log 
  public void updateGameLog(String newEvent) {
	  logTextArea.setText(logTextArea.getText() + "\n" + newEvent);
  }
  
  // Dialogue box for taking a role, and moving the player
  public void roleDialogue(int x) {
	  if (x == 1) {
		  JOptionPane.showMessageDialog(null, "Congrats, you got the part!");
		  playerLabels.get(Deadwood.turn).setBounds(Deadwood.players.get(Deadwood.turn).getPart().getX(), Deadwood.players.get(Deadwood.turn).getPart().getY(), 
					 Deadwood.players.get(Deadwood.turn).getPart().getW(), Deadwood.players.get(Deadwood.turn).getPart().getH());
		  this.updateGameLog(Deadwood.players.get(Deadwood.turn).getName());
	  } 
	  else  if (x == 2) {
		  JOptionPane.showMessageDialog(null, "Rank not high enough!");
	  } 
	  else {
		  JOptionPane.showMessageDialog(null, "Part already taken!");
	  }
  }
  
  //Dialogue box for rehearsing a part
  public void rehearseDialogue(int x) {
	  if (x == 1) {
		  JOptionPane.showMessageDialog(null, "You now have " + Deadwood.players.get(Deadwood.turn).getRehearsalMarkers() + " Reahearsal Markers!");
		  Deadwood.GUIBoard.currentTurn(Deadwood.players.get(Deadwood.turn));
	  } 
	  else  if (x == 2) {
		  JOptionPane.showMessageDialog(null, "You don't have a part yet!");
	  } 
	  else {
		  JOptionPane.showMessageDialog(null, "You already have enough Rehearsal Markers!");
	  }
  }
 
  //Dialogue box for acting out a part
  public void actingDialogue(int x) {
	  if (x == 1) {
		  JOptionPane.showMessageDialog(null, "You need to roll a " + Deadwood.players.get(Deadwood.turn).getScene().getCard().getBudget() + " or higher to advance");
	  } 
	  else  if (x == 2) {
		  JOptionPane.showMessageDialog(null, "Acting successful!");
		  Deadwood.GUIBoard.currentTurn(Deadwood.players.get(Deadwood.turn));
	  } 
	  else  if (x == 3) {
		  JOptionPane.showMessageDialog(null, "Scene completed!");
		  Deadwood.GUIBoard.currentTurn(Deadwood.players.get(Deadwood.turn));
	  } 
	  else  if (x == 4) {
		  JOptionPane.showMessageDialog(null, "Time for the wrap bonus!");
		  Deadwood.GUIBoard.currentTurn(Deadwood.players.get(Deadwood.turn));
	  } 
	  else  if (x == 5) {
		  JOptionPane.showMessageDialog(null, "End of your turn!");
		  endTurn();
        	  }
	  else {
		  JOptionPane.showMessageDialog(null, "Acting failed, try again next time!");
		  JOptionPane.showMessageDialog(null, "End of your turn!");
		  endTurn();
	  } 
  }
  public void endTurn(){
  Deadwood.players.get(Deadwood.turn).setOver(true);
		  Deadwood.turn++;
          Deadwood.turn %= Deadwood.players.size();
          Deadwood.players.get(Deadwood.turn).takeTurn(Deadwood.board, Deadwood.players);

  }
  
  // Add players to the board frame
  public void addPlayers() {
	 int numberOfPlayers = 10;
	 while (numberOfPlayers < 2 | numberOfPlayers > 8){
		 String numberInput = (String) JOptionPane.showInputDialog("Please enter the number of players (2-8):");
		 if (numberInput == null) {
			 JOptionPane.showMessageDialog(null, "Thanks for playing!");
			 System.exit(0);
		 } else {
			 try { 
				 numberOfPlayers = Integer.parseInt(numberInput);
				 if (numberOfPlayers < 2 | numberOfPlayers > 8 ) {
					 JOptionPane.showMessageDialog(null, "Number of players not allowed, please input the correct number (2-8)");
				 	} 
			 } catch (NumberFormatException nfe) {
				 JOptionPane.showMessageDialog(null, "Number of players not allowed, please input the correct number (2-8)");
				 numberOfPlayers = 10;
			 }
		 }
	 }
	 int count = 0;
	 while (count != numberOfPlayers) {
		 String name = "";
		 while (name.equals("")) {
			 name = JOptionPane.showInputDialog("Please enter the name for player "+ (count+1) +":");
			 if (name == null) {
				 JOptionPane.showMessageDialog(null, "Thanks for playing!");
				 System.exit(0);
			 }
		 }
		 Deadwood.initializePlayer(name);
		 count++;
	 }
	// Add a dice to represent a player.
      String[] dice = {"b1","c1","g1","o1","p1","r1","v1","y1"};
      for (int k = 0; k < numberOfPlayers; k++) {
   	   JLabel playerLabel = new JLabel();
   	   int playerNumber = k;

   	   int offsetX = 50 * k;
   	   int offsetY = 0;
   	   if (k > 3) {
   		offsetX = (k-4) * 50;
   		//offsetX = startX;
   		offsetY = 60;
   	   }

   	   String dicePathName = "../dice/" + dice[playerNumber] + ".png";
   	   ImageIcon pIcon = new ImageIcon(dicePathName);
   	   playerLabel.setIcon(pIcon);
   	   playerLabel.setBounds(Deadwood.rooms.get(0).getX() + offsetX, Deadwood.rooms.get(0).getY() + offsetY, 40, 40);
   	   bPane.add(playerLabel,new Integer(3));
       playerLabels.add(playerLabel);

      }
  }
  public void updatePlayer(Player player) {
      String[] dice = {"b", "c", "g", "o", "p", "r", "v", "y"};
      String dicePathName = "../dice/" + dice[Deadwood.turn] + player.getRank() + ".png";
      ImageIcon pIcon = new ImageIcon(dicePathName);
      playerLabels.get(Deadwood.turn).setIcon(pIcon);
  }
  //Display the current turn information
  public void currentTurn(Player player) {
	  String playerName = player.getName();
	  String playerMoney = Integer.toString(player.getMoney());
	  String playerFame = Integer.toString(player.getFame());
	  String playerLocation = player.getLocation().getName();
	  String playerRank = Integer.toString(player.getRank());
	  String rehearsalMarkers = Integer.toString(player.getRehearsalMarkers());
	  String part;
	  if (player.getPart() != null) {
          part = player.getPart().name;
       } else {
     	 part = "none";
       }
	  playerInfoTextArea.setText("Current player info:\n"
	  		+ "Name: " + playerName +"\n"
	  				+ "Money: " + playerMoney + "\n"
	  						+ "Fame: " + playerFame + "\n"
	  								+ "Location: " + playerLocation + "\n"
	  										+ "Part: " + part + "\n"
	  												+ "Rank: " + playerRank + "\n"
	  														+ "Rehearsal markers: " + rehearsalMarkers);
	  }
  
  // Add cards and shot markers to board
  public void startDay () {
	// Add scene cards to the rooms
      for (int i = 2; i < Deadwood.rooms.size(); i++) {
   	   JLabel cardLabel = new JLabel();
   	   String cardPathName = "../cards/" + ((Scene)Deadwood.rooms.get(i)).getCard().getImg();
          ImageIcon cIcon =  new ImageIcon(cardPathName);
          cardLabel.setIcon(cIcon);
          cardLabel.setBounds(Deadwood.rooms.get(i).getX(),Deadwood.rooms.get(i).getY(),Deadwood.rooms.get(i).getW(),Deadwood.rooms.get(i).getH());
          cardLabel.setOpaque(true);

       // Add the card to the lower layer
          bPane.add(cardLabel, new Integer(1));
          cardLabels.add(cardLabel);
      }

      // Add the shot markers
      for (int i = 2; i < Deadwood.rooms.size(); i++) {
   	   JLabel shotLabel = new JLabel();
   	   String dicePathName = "../shot.png";
   	   ImageIcon pIcon = new ImageIcon(dicePathName);
   	   shotLabel.setIcon(pIcon);
   	   shotLabel.setBounds(((Scene) Deadwood.rooms.get(i)).getShots().get(0).getX(), ((Scene) Deadwood.rooms.get(i)).getShots().get(0).getY(),
   			   ((Scene) Deadwood.rooms.get(i)).getShots().get(0).getW(),((Scene) Deadwood.rooms.get(i)).getShots().get(0).getH());
   	   bPane.add(shotLabel,new Integer(3));
          playerLabels.add(shotLabel);
      }
  }
  
  
  //Constructor
  public BoardLayersListener() {

       // Set the title of the JFrame
       super("Deadwood");
       // Set the exit option for the JFrame
       setDefaultCloseOperation(EXIT_ON_CLOSE);

       // Create the JLayeredPane to hold the display, cards, dice and buttons

       bPane = getLayeredPane();


       // Create the Deadwood board

       boardlabel = new JLabel();
       ImageIcon icon =  new ImageIcon("../board.jpg");
       boardlabel.setIcon(icon);
       boardlabel.setBounds(0,0,icon.getIconWidth(),icon.getIconHeight());

       // Add the board to the lowest layer
       bPane.add(boardlabel, new Integer(0));

       // Set the size of the GUI

       setSize(icon.getIconWidth()+300,icon.getIconHeight()+100);
       
       // Create the Menu for action buttons
       
       mLabel = new JLabel("MENU");
       mLabel.setBounds(icon.getIconWidth()+40, 0, 100, 20);
       bPane.add(mLabel,new Integer(2));
       
       // Create scroll pane and text box for displaying the game log
       
       logTextArea = new JTextArea();
       logTextArea.setEditable(false);
       logTextArea.setText("Game log:");
       logSPane = new JScrollPane(logTextArea);
       logSPane.setBounds(icon.getIconWidth()+10, 120, 250, 200);
       logSPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
       bPane.add(logSPane,new Integer(2));
       
       // Create the text box for displaying current player info
       
       playerInfoTextArea = new JTextArea();
       playerInfoTextArea.setEditable(false);
       playerInfoTextArea.setText("Current player info:\n");
       playerInfoTextArea.setBounds(icon.getIconWidth()+10, 340, 160, 200);
       bPane.add(playerInfoTextArea,new Integer(2));
       
       // Create Action buttons
       
       bAct = new JButton("ACT");
       bAct.setBackground(Color.white);
       bAct.setBounds(icon.getIconWidth()+10, 30, 120, 20);
       bAct.addMouseListener(new boardMouseListener());

       bRehearse = new JButton("REHEARSE");
       bRehearse.setBackground(Color.white);
       bRehearse.setBounds(icon.getIconWidth()+10, 60, 120, 20);
       bRehearse.addMouseListener(new boardMouseListener());

       bMove = new JButton("MOVE");
       bMove.setBackground(Color.white);
       bMove.setBounds(icon.getIconWidth()+10, 90, 120, 20);
       bMove.addMouseListener(new boardMouseListener());

       bChooseRole = new JButton("CHOOSE PART");
       bChooseRole.setBackground(Color.white);
       bChooseRole.setBounds(icon.getIconWidth()+120, 30, 120, 20);
       bChooseRole.addMouseListener(new boardMouseListener());

       bEndTurn = new JButton("END TURN");
       bEndTurn.setBackground(Color.white);
       bEndTurn.setBounds(icon.getIconWidth()+120, 60, 120, 20);
       bEndTurn.addMouseListener(new boardMouseListener());
       
       bUpgrade = new JButton("UPGRADE");
       bUpgrade.setBackground(Color.white);
       bUpgrade.setBounds(icon.getIconWidth()+120, 90, 120, 20);
       bUpgrade.addMouseListener(new boardMouseListener());
       // Place the action buttons in the top layer
       
       bPane.add(bAct, new Integer(2));
       bPane.add(bRehearse, new Integer(2));
       bPane.add(bMove, new Integer(2));
       bPane.add(bChooseRole, new Integer(2));
       bPane.add(bEndTurn, new Integer(2));
       bPane.add(bUpgrade, new Integer(2));
  }

  // This class implements Mouse Events

  class boardMouseListener implements MouseListener{

      // Code for the different button clicks
      public void mouseClicked(MouseEvent e) {
    	  
    	 // Act command to act out a scene
    	  
         if (e.getSource()== bAct){
        	 if (Deadwood.players.get(Deadwood.turn).canAct()) {
        		 Deadwood.players.get(Deadwood.turn).act(Deadwood.board, Deadwood.players);       		 
        	 } 
        	 else if (Deadwood.players.get(Deadwood.turn).getPart() == null) {
        		 JOptionPane.showMessageDialog(null, "You dont have a part!");
        	 } else {
        		 JOptionPane.showMessageDialog(null, "Wait until next turn!");
        	 }
         }
         
         // Rehearse command to rehearse a scene
         
         else if (e.getSource()== bRehearse){
            if (Deadwood.players.get(Deadwood.turn).canAct()) {
            	Deadwood.players.get(Deadwood.turn).rehearse();
            } 
            else if (Deadwood.players.get(Deadwood.turn).getPart() == null) {
       		 JOptionPane.showMessageDialog(null, "You dont have a part!");
            }
            else {
       		 JOptionPane.showMessageDialog(null, "Wait until next turn!");
       	 	}
         }
         
         //	Move command that moves the player
         
         else if (e.getSource()== bMove){
        	 if (Deadwood.players.get(Deadwood.turn).canMove() && Deadwood.players.get(Deadwood.turn).getPart() == null) {
        		 ArrayList<String> adjLocations = Deadwood.players.get(Deadwood.turn).getLocation().getNearbyNames();
        		 String moveLocation = (String)JOptionPane.showInputDialog(
        				 null,
        				 "Please select where to move: ",
        				 null, JOptionPane.PLAIN_MESSAGE,
        				 null,
        				 adjLocations.toArray(),
        				 adjLocations.get(0));
        	
        		 Deadwood.players.get(Deadwood.turn).move(moveLocation);
        		 //Deadwood.players.get(Deadwood.turn).setCanMove(false);
        		 playerLabels.get(Deadwood.turn).setBounds(Deadwood.players.get(Deadwood.turn).getLocation().getX()+(40 * Deadwood.turn), Deadwood.players.get(Deadwood.turn).getLocation().getY()+120, 40, 40);
        		 Deadwood.GUIBoard.currentTurn(Deadwood.players.get(Deadwood.turn));
        		 Deadwood.GUIBoard.updateGameLog(Deadwood.players.get(Deadwood.turn).getName() + " has moved to " + moveLocation);
        	 } 
        	 else if (Deadwood.players.get(Deadwood.turn).getPart() != null) {
        		 JOptionPane.showMessageDialog(null, "You are working a part!");
        	 }
        	 else {
        		 JOptionPane.showMessageDialog(null, "You already moved this turn!");
        	 }
         }
         
         // Take role command that can give the player a role if eligible 
         
         else if (e.getSource()== bChooseRole){
        	 if (Deadwood.players.get(Deadwood.turn).canTakeRole() &&! Deadwood.players.get(Deadwood.turn).getLocation().equals(Deadwood.rooms.get(0)) &&! Deadwood.players.get(Deadwood.turn).getLocation().equals(Deadwood.rooms.get(1))) {        
        		 ArrayList<Part> extraParts = ((Scene)Deadwood.players.get(Deadwood.turn).getLocation()).getExtraParts();
        		 ArrayList<Part> mainParts = ((Scene)Deadwood.players.get(Deadwood.turn).getLocation()).getCard().getParts();
        		 ArrayList<String> availableParts = new ArrayList<String>();
        		 for (int i = 0; i < extraParts.size(); i++) {
        			 availableParts.add(extraParts.get(i).name + " (" + extraParts.get(i).getLevel() + ")");
        		 }
        		 for (int k = 0; k < mainParts.size(); k++) {
        			 availableParts.add(mainParts.get(k).name + " (" + mainParts.get(k).getLevel() + ")");
        		 }
        		 String partSelection = (String)JOptionPane.showInputDialog(
        				 null,
        				 "Please select where to move: ",
        				 null, JOptionPane.PLAIN_MESSAGE,
        				 null,
        				 availableParts.toArray(),
        				 availableParts.get(0));
        		 if (partSelection != null) {
        			 Deadwood.players.get(Deadwood.turn).takeRole(partSelection.substring(0, partSelection.length() - 4), Deadwood.players);
            		 Deadwood.GUIBoard.currentTurn(Deadwood.players.get(Deadwood.turn));
        		 }
        	 } 
        	 else if (Deadwood.players.get(Deadwood.turn).getLocation().equals(Deadwood.rooms.get(0))){
        		 JOptionPane.showMessageDialog(null, "You are in the trailer!");
        	 } 
        	 else if (Deadwood.players.get(Deadwood.turn).getLocation().equals(Deadwood.rooms.get(1))) {
        		 JOptionPane.showMessageDialog(null, "You are in the office!");
        	 } 
        	 else if (Deadwood.players.get(Deadwood.turn).getPart() != null) {
        		 JOptionPane.showMessageDialog(null, "You are working a part!");
        	 }
        	 else {
        		 JOptionPane.showMessageDialog(null, "You already took a role this turn!");
        	 } 
         }
         else if (e.getSource()== bEndTurn){
        	 endTurn();
             
         }
         else if (e.getSource()== bUpgrade){
            ArrayList<String> options = new ArrayList<String>();
            options.add("Dollars");
            options.add("Fame");
            String currencySelection = (String)JOptionPane.showInputDialog(
        				 null,
        				 "Please select an upgrade method: ",
        				 null, JOptionPane.PLAIN_MESSAGE,
        				 null,
        				 options.toArray(),
        				 options.get(0));
             ArrayList<Upgrade> upgrades = ((CastingOffice)Deadwood.rooms.get(1)).getUpgrades();
             options.clear();
             if (currencySelection.equals("Dollars")){
               for (Upgrade u : upgrades)
               {
                  if (u.getCurrency().equals("dollar") && u.getAmt() <= Deadwood.players.get(Deadwood.turn).getMoney()){
                     options.add("Level " + u.getLevel() + " - Cost: " + u.getAmt());
                  }
               }
             }
             else {
               for (Upgrade u : upgrades)
               {
                  if (u.getCurrency().equals("credit") && u.getAmt() <= Deadwood.players.get(Deadwood.turn).getFame()){
                     options.add("Level: " + u.getLevel() + " - Cost: " + u.getAmt());
                  }
               }
             }
             if (options.isEmpty()){
               JOptionPane.showMessageDialog(null, "You don't have enough to upgrade with that currency!");
             }
             else {
               String upgradeSelection = (String)JOptionPane.showInputDialog(
        				      null,
        				      "Please select an upgrade: ",
        				      null, JOptionPane.PLAIN_MESSAGE,
        				      null,
        				      options.toArray(),
        				      options.get(0));
                        
               int level = Integer.parseInt(upgradeSelection.substring(8,9));
               
               if (currencySelection.equals("Dollars")){
                  ((CastingOffice)Deadwood.rooms.get(1)).raiseRank(Deadwood.players.get(Deadwood.turn), "dollar", level);
                  JOptionPane.showMessageDialog(null, "Your rank has been upgraded!");
               }
               else{
                  ((CastingOffice)Deadwood.rooms.get(1)).raiseRank(Deadwood.players.get(Deadwood.turn), "credit", level);
               }

               updatePlayer(Deadwood.players.get(Deadwood.turn));
               updateGameLog(Deadwood.players.get(Deadwood.turn).getName() + "has upgraded to level " + Deadwood.players.get(Deadwood.turn).getRank());
             }
             System.out.println("upgrade this bitch\n");
        }
      }
      
      public void mousePressed(MouseEvent e) {

      }
      
      public void mouseReleased(MouseEvent e) {

      }
      
      public void mouseEntered(MouseEvent e) {

      }
      
      public void mouseExited(MouseEvent e) {


      }
   }
}
