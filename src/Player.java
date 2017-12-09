import java.util.*;
//Player Class

public class Player{
    private Room location;
    private String name;
    private int fame;
    private int money;
    private int rank;
    private int rehearsalMarkers;
    private Scene currentScene;
    private Part currentPart;
    private boolean onCard;
    private boolean mayUpgrade;
    private boolean over;
    private boolean canMove;
    private boolean canTakeRole;
    private boolean canAct;


    //Constructors
    Player(){
      name = null;
      fame = 0;
      money = 0;
      rank = 1;
      rehearsalMarkers = 0;
      currentScene = null;
      currentPart = null;
      canTakeRole = true;
    }

    Player(String name, Room location){
        this.name = name;
        fame = 0;
        money = 0;
        rank = 1;
        rehearsalMarkers = 0;
        currentScene = null;
        currentPart = null;
        this.location = location;
        canTakeRole = true;
    }

    //Getters
    
    public String getName(){
        return name;
    }

    public boolean canAct() {
		return canAct;
	}

	public int getRehearsalMarkers() {
		return rehearsalMarkers;
	}

	public boolean isOver() {
		return over;
	}

	public boolean canMove() {
		return canMove;
	}

	public boolean canTakeRole() {
		return canTakeRole;
	}

	public int getFame(){
      return fame;
    }

    public int getRank() {
      return rank;
    }

    public int getMoney(){
      return money;
    }

    public Room getLocation(){
      return location;
    }

    public Scene getScene(){
      return currentScene;
    }

    public Part getPart(){
      return currentPart;
    }

    public boolean mayUpgrade(){
      return this.mayUpgrade;
    }

    //Setters
    
    public void setCanAct(boolean canAct) {
		this.canAct = canAct;
	}
    public void setRoom(Room room){
        location = room;
    }
    public void setScene(Scene scene){
      this.currentScene = scene;
    }
    public void setPart(Part part){
      this.currentPart = part;
    }
    public void setOver(boolean over) {
    	this.over = over;
    }
    public void setCanMove(boolean canMove) {
    	this.canMove = canMove;
    }
    private int spendMoney(int spendAmount){
      if(spendAmount > this.money){
        System.out.println("Insufficient funds. Please try a smaller amount");
        return 0;
      }
      this.money = (this.money - spendAmount);
      return spendAmount;
    }
    public void clearRehearsalMarkers() {
      rehearsalMarkers = 0;
    }

    private int spendFame(int spendAmount){
      if(spendAmount > this.fame){
        System.out.println("Insufficient fame. Please try a smaller amount");
        return 0;
      }
      this.fame = (this.fame - spendAmount);
      return spendAmount;
    }

    public int spend(int spendAmount, String currency){
      if(currency.equals("dollar")){
        return spendMoney(spendAmount);
      } else {
        return spendFame(spendAmount);
      }
    }

    public void levelUp(boolean isValid, int level){
      if(isValid){
        this.rank = level;
      }
    }
    public void addMoney(int toAdd){
      money += toAdd;
      //System.out.println(name + " recieved " + toAdd + " dollars, now a total of " + money);
    }

    public void addFame(int toAdd) {
      fame += toAdd;
      //System.out.println(name + " recieved " + toAdd + " fame, now a total of " + fame);
    }

    //Other Methods
    public void takeTurn(Board b, ArrayList<Player> players){
      if (currentPart != null) {
    	  canAct = true;
      } else {
    	  canAct = false;
      }
      over = false;
      canMove = true;
      canTakeRole = true;
      Deadwood.GUIBoard.currentTurn(this);
//      while (!over) {
//         if (currentPart == null) {
//            input = sc.nextLine();
//            //It's a secret to everybody
//            if (input.equals("flip board")){
//               System.exit(0);
//            }

//               case "who":
//                  System.out.println("Name: " + name + " $" + money + " Fame:" + fame);
//                  if (currentPart != null) {
//                     System.out.print("Current Part: " + currentPart.getPartName());
//                  } else {
//                	 System.out.print("No Current part");
//                  }
//                  System.out.println();
//                  break;
//
//               case "where":
//                  System.out.println("Currently located in " + location.getName());
//                  //System.out.println("Nearby rooms: " +  location.getNearbyNames());
//                  if (location instanceof Scene) {
//                     System.out.println("Scene currently shooting: " + ((Scene)location).getCard().showName());
//                  } else {
//                	  System.out.println("No current Scene worked");
//                  }
//                  break;
//
//               case "move":
//                  if (canMove) {
//                     System.out.println("What room do you want to move to?");
//                     System.out.print("Current nearby rooms are: ");
//
//                     //Prints out rooms available to be moved to
//                     for (Room r : location.getNearby()) {
//                        System.out.print(r.getName() + "   ");
//                     }
//                     System.out.println();
//                     input = sc.nextLine();
//
//                     //Tries to move, and checks to see if you succeeded
//                     Room oldLocation = location;
//                     move(input);
//                     if (oldLocation != location) {
//                        canMove = false;
//                     }
//                     else
//                     {
//                        System.out.println("Something went wrong, try again!");
//                     }
//                  }
//                  else {
//                     System.out.println("You've already moved this turn!");
//                  }
//                  break;
//
//               case "work":
//                  if (canTakeRole) {
//                     if (currentScene != null && !currentScene.isOver()) {
//                        if (location instanceof Scene) {
//                           System.out.println("What role would you like to take?");
//                           System.out.print("On card roles: ");
//                           for (Part p : ((Scene)location).getCard().getParts()) {
//                              boolean isTaken = false;
//                              for (Player pl : players) {
//                                 if (pl.getPart() == p){
//                                    isTaken = true;
//                                 }
//                              }
//                              if (!isTaken){
//                                 System.out.print(p.getPartName() + " (" + p.getLevel() + ")   ");
//                              }
//                           }
//
//                           System.out.println();
//                           System.out.print("Off card roles: ");
//                           for (Part p : ((Scene)location).getExtraParts()) {
//                              boolean isTaken = false;
//                              for (Player pl : players) {
//                                 if (pl.getPart() == p){
//                                    isTaken = true;
//                                 }
//                              }
//                              if (!isTaken){
//                                 System.out.print(p.getPartName() + " (" + p.getLevel() + ")   ");
//                              }
//                           }
//                           System.out.println();
//
//                           input = sc.nextLine();
//                           Part oldPart = currentPart;
//                           takeRole(input, players);
//                           if (oldPart != currentPart){
//                              canTakeRole = false;
//                           }
//                           else {
//                              System.out.println("Something went wrong, try again!");
//                           }
//                        }
//                        else {
//                           System.out.println("Sorry, you can't do that here! Try moving to a scene.");
//                        }
//                     }
//                     else {
//                        System.out.println("This scene has already wrapped");
//                     }
//                  }
//                  break;
//               case "end":
//                  over = true;
//                  break;
//               case "upgrade $":
//                  if (location instanceof CastingOffice){
//                     System.out.println("What level would you like to upgrade to?");
//                     String answer = sc.nextLine();
//                     try{
//                        int level = Integer.parseInt(answer);
//                        ((CastingOffice)location).raiseRank(this, "dollar", level);
//                     }
//                     catch (Exception e){
//                        System.out.println("Could not parse input, try again");
//                     }
//
//                  }
//                  break;
//               case "upgrade cr":
//                  if (location instanceof CastingOffice){
//                     System.out.println("What level would you like to upgrade to?");
//                     String answer = sc.nextLine();
//                     try{
//                        int level = Integer.parseInt(answer);
//                        ((CastingOffice)location).raiseRank(this, "credit", level);
//                     }
//                     catch (Exception e){
//                        System.out.println("Could not parse input, try again");
//                     }
//
//                  }
//                  break;
//               case "help":
//                  Deadwood.printCommands();
//                  break;
//               default:
//                  System.out.println("Couldn't understand input. Please try again.");
//                  break;
//            }
//         }
//         else {
//            System.out.println("Do you want to act or rehearse for your part " + currentPart.getPartName() + "?");
//            System.out.println("You have " + rehearsalMarkers + " rehearsal markers and need to roll at least a " + currentScene.getCard().getBudget());
//            input = sc.nextLine();
//            switch (input.toLowerCase()) {
//               case "act":
//                  act(b,players);
//                  over = true;
//                  break;
//
//               case "rehearse":
//                  over = rehearse();
//                  break;
//
//               default:
//                  System.out.println("Couldn't understand input. Please try again.");
//                  break;
//            }
//         }
//      }
    }

    public void act(Board b, ArrayList<Player> players){
      Deadwood.GUIBoard.actingDialogue(1);
      int roll = b.rollDice();
      boolean successful = (currentScene.getCard().getBudget() <= rehearsalMarkers + roll);
      if (successful){
    	 Deadwood.GUIBoard.actingDialogue(2);
         currentScene.completeTake();
         if (currentScene.isOver()){
        	   Deadwood.GUIBoard.actingDialogue(3);
            boolean isWrapBonus = false;
            for (Player p : players){
               if (p.getScene() == this.currentScene){
                  for (Part pa : currentScene.getCard().getParts()){
                     if (p.currentPart == pa){
                        isWrapBonus = true;
                     }
                  }
               }
            }
            if (isWrapBonus) {
               Deadwood.GUIBoard.actingDialogue(4);
               ArrayList<Integer> rolls = new ArrayList<Integer>();
               for (int i = 1; i <= currentScene.getCard().getBudget(); i++){
                  rolls.add(b.rollDice());
               }
               Collections.sort(rolls);
               ArrayList<Part> parts = currentScene.getCard().getParts();
               for (int i = rolls.size() - 1; i >= 0; i--) {
                  for (Player pl : players) {
                      if (pl.getPart() == parts.get(i % parts.size())){
                        pl.addMoney(rolls.get(i));
                      }
                      if (pl.currentScene == currentScene)
                      {
                        pl.clearRehearsalMarkers();
                      }
                  }
               }
            }
            Scene thisScene = currentScene;
            for (Player p : players) {
               if (p.getScene() == thisScene){
                  p.setPart(null);
               }
            }
            int[] result = thisScene.getCard().payout(onCard, successful);
            fame += result[0];
            money += result[1];
            rehearsalMarkers = 0;
         }
      }
      else {
    	  Deadwood.GUIBoard.actingDialogue(10);
      }
      if (currentScene != null){
         int[] result = currentScene.getCard().payout(onCard, successful);
         fame += result[0];
         money += result[1];
      }
      Deadwood.GUIBoard.actingDialogue(5);
      canAct = false;
    }

    public void rehearse(){
      if (currentPart != null && rehearsalMarkers < currentScene.getCard().getBudget())
      {
         rehearsalMarkers++;
         canAct = false;
         Deadwood.GUIBoard.rehearseDialogue(1);
      }
      else if (currentPart == null){
    	 Deadwood.GUIBoard.rehearseDialogue(2);
      }
      else {
    	 Deadwood.GUIBoard.rehearseDialogue(3);
      }
    }

    public void move(String s){
      for(Room r : location.getNearby()) {
         if (s.toLowerCase().equals(r.getName().toLowerCase())  || (s.toLowerCase() + "s").equals(r.getName().toLowerCase())){
            location = r;
            if(r.getName().toLowerCase().equals("office")){
              mayUpgrade = true;
            } else {
              mayUpgrade = false;
            }
            if(r instanceof Scene){
               currentScene = (Scene)r;
            }
            else {
               currentScene = null;
            }
            canMove = false;
         }
      }
    }

    public void takeRole(String s, ArrayList<Player> players){
      //Check each part and match it to the provided string setting it to the selected part if they match
      for(Part p : currentScene.getCard().getParts()) {
         boolean isTaken = false;
         for (Player pl : players) {
            if (pl.getPart() == p){
               isTaken = true;
            }
         }
         if (!isTaken){
            if (s.toLowerCase().equals(p.getPartName().toLowerCase())) {
               if (rank >= p.getLevel()){
                  currentPart = p;
                  Deadwood.GUIBoard.roleDialogue(2);
                  canTakeRole = false;
                  onCard = true;
               }
               else {
            	   Deadwood.GUIBoard.roleDialogue(3);
               }
            }
         }
         else if (isTaken == true && s.toLowerCase().equals(p.getPartName().toLowerCase())) {
        	 Deadwood.GUIBoard.roleDialogue(4);               
         }
      }
      for(Part p: ((Scene)location).getExtraParts()){
         boolean isTaken = false;
            for (Player pl : players) {
               if (pl.getPart() == p){
                  isTaken = true;                  
               }
            }
            if (!isTaken){
               if (s.toLowerCase().equals(p.getPartName().toLowerCase())) {
               if (rank >= p.getLevel()){
                  currentPart = p;
                  Deadwood.GUIBoard.roleDialogue(1);
                  canTakeRole = false;
               }
               else {
            	   Deadwood.GUIBoard.roleDialogue(3);
               }
            }
         } else if (isTaken == true && s.toLowerCase().equals(p.getPartName().toLowerCase())) {
        	 Deadwood.GUIBoard.roleDialogue(4);               
         }
      }
    }
}
