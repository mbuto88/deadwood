
/*

   Deadwood GUI helper file
   Author: Moushumi Sharmin
   This file shows how to create a simple GUI using Java Swing and Awt Library
   Classes Used: JFrame, JLabel, JButton, JLayeredPane

*/


import java.awt.*;
import javax.swing.*;
import javax.swing.ImageIcon;

import javax.imageio.ImageIO;
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
  
  //JButtons
  JButton bAct;
  JButton bRehearse;
  JButton bMove;
  
  // JLayered Pane
  JLayeredPane bPane;
  
  // Add players to the board frame
  public void addPlayersToFrame(int number) {
	// Add a dice to represent a player. 
      String[] dice = {"b1","c1","g1","o1","p1","r1","v1","y1"};
      for (int k = 0; k < number; k++) {
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
   	   playerLabel.setBounds(Deadwood.rooms.get(0).getX() + offsetX, Deadwood.rooms.get(0).getY() + offsetY, Deadwood.rooms.get(0).getW(), Deadwood.rooms.get(0).getH());
   	   bPane.add(playerLabel,new Integer(3));
       playerLabels.add(playerLabel);
          
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

       setSize(icon.getIconWidth()+200,icon.getIconHeight()+100);
       
       // Add scene cards to the rooms
       for (int i = 2; i < Deadwood.rooms.size(); i++) {
    	   JLabel cardLabel = new JLabel();
    	   String cardPathName = "../cards/" + Deadwood.rooms.get(i).getCard().getImg();
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

      
       // Create the Menu for action buttons
       mLabel = new JLabel("MENU");
       mLabel.setBounds(icon.getIconWidth()+40,0,100,20);
       bPane.add(mLabel,new Integer(2));

       // Create Action buttons
       bAct = new JButton("ACT");
       bAct.setBackground(Color.white);
       bAct.setBounds(icon.getIconWidth()+10, 30,100, 20);
       bAct.addMouseListener(new boardMouseListener());
       
       bRehearse = new JButton("REHEARSE");
       bRehearse.setBackground(Color.white);
       bRehearse.setBounds(icon.getIconWidth()+10,60,100, 20);
       bRehearse.addMouseListener(new boardMouseListener());
       
       bMove = new JButton("MOVE");
       bMove.setBackground(Color.white);
       bMove.setBounds(icon.getIconWidth()+10,90,100, 20);
       bMove.addMouseListener(new boardMouseListener());

   
       // Place the action buttons in the top layer
       bPane.add(bAct, new Integer(2));
       bPane.add(bRehearse, new Integer(2));
       bPane.add(bMove, new Integer(2));
  }
  
  // This class implements Mouse Events
  
  class boardMouseListener implements MouseListener{
  
      // Code for the different button clicks
      public void mouseClicked(MouseEvent e) {
         
         if (e.getSource()== bAct){
            System.out.println("Acting is Selected\n");
         }
         else if (e.getSource()== bRehearse){
            System.out.println("Rehearse is Selected\n");
         }
         else if (e.getSource()== bMove){
            System.out.println("Move is Selected\n");
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
