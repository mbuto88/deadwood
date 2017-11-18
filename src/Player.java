import java.util.Scanner;
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

    //Constructors
    Player(){
      fame = 0;
      money = 0;
      rank = 1;
      rehearsalMarkers = 0;
      currentScene = null;
      currentPart = null;
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
    }

    //Getters
    public String getName(){
        return name;
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
    public void setRoom(Room room){
        location = room;
    }

    private int spendMoney(int spendAmount){
      if(spendAmount > this.money){
        System.out.println("Insufficient funds. Please try a smaller amount");
        return 0;
      }
      this.money = (this.money - spendAmount);
      return spendAmount;
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

    //Other Methods
    public void takeTurn(Board b){
      boolean over = false;
      Scanner sc = new Scanner(System.in);
      boolean canMove = true;
      boolean canTakeRole = true;
      String input;
      while (!over) {
         input = sc.nextLine();
         //It's a secret to everybody
         if (input.equals("flip board")){
            System.exit(0);
         }
         if (currentPart == null) {
            switch (input.toLowerCase()){
               case "who":
                  System.out.print("Name: " + name +"\n");
                  if (currentPart != null) {
                     System.out.print("Current Part: " + currentPart.getPartName());
                  } else {
                	 System.out.print("No Current part");
                  }
                  System.out.println();
                  break;

               case "where":
                  System.out.println("Currently located in " + location.getName());
                  //System.out.println("Nearby rooms: " +  location.getNearbyNames());
                  if (location instanceof Scene) {
                     System.out.println("Scene currently shooting " + ((Scene)location).getCard().showName());
                  } else {
                	  System.out.println("No current Scene worked");
                  }
                  break;

               case "move":
                  if (canMove) {
                     System.out.println("What room do you want to move to?");
                     System.out.print("Current nearby rooms are: ");

                     //Prints out rooms available to be moved to
                     for (Room r : location.getNearby()) {
                        System.out.print(r.getName() + "   ");
                     }
                     System.out.println();
                     input = sc.nextLine();

                     //Tries to move, and checks to see if you succeeded
                     Room oldLocation = location;
                     move(input);
                     if (oldLocation != location) {
                        canMove = false;
                     }
                     else
                     {
                        System.out.println("Something went wrong, try again!");
                     }
                  }
                  else {
                     System.out.println("You've already moved this turn!");
                  }
                  break;

               case "work":
                  if (canTakeRole) {
                     if (location instanceof Scene) {
                        System.out.println("What role would you like to take?");
                        System.out.print("On card roles: ");
                        for (Part p : ((Scene)location).getCard().getParts()) {
                           System.out.print(p.getPartName() + "   ");
                        }

                        System.out.println();
                        System.out.print("Off card roles: ");
                        for (Part p : ((Scene)location).getExtraParts()) {
                           System.out.print(p.getPartName() + "   ");
                        }
                        System.out.println();

                        input = sc.nextLine();
                        Part oldPart = currentPart;
                        takeRole(input);
                        if (oldPart != currentPart){
                           canTakeRole = false;
                        }
                        else {
                           System.out.println("Something went wrong, try again!");
                        }
                     }
                     else {
                        System.out.println("Sorry, you can't do that here! Try moving to a scene.");
                     }
                  }
                  break;

               default:
                  System.out.println("Couldn't understand input. Please try again.");
                  break;
            }
         }
         else {
            System.out.println("Do you want to act or rehearse for your part " + currentPart.getPartName() + "?");
            input = sc.nextLine();
            switch (input.toLowerCase()) {
               case "act":
                  act(b);
                  over = true;
                  break;

               case "rehearse":
                  over = rehearse();
                  break;

               default:
                  System.out.println("Couldn't understand input. Please try again.");
                  break;
            }
         }
      }
    }

    private void act(Board b){
      int roll = b.rollDice();
      boolean successful = (currentScene.getCard().getBudget() <= rehearsalMarkers + roll);
      int[] result = currentScene.getCard().payout(onCard, successful);
      fame += result[0];
      money += result[1];
      }

    private boolean rehearse(){
      if (currentPart != null && rehearsalMarkers < currentScene.getCard().getBudget())
      {
         rehearsalMarkers++;
         System.out.println("You now have " + rehearsalMarkers + "Reahearsal Markers!");
         return true;
      }
      else if (currentPart == null){
         System.out.println("You don't have a part yet!");
         return false;
      }
      else {
         System.out.println("You already have enough Rehearsal Markers!");
         return false;
      }
    }

    private void move(String s){
      for(Room r : location.getNearby()) {
         if (s.toLowerCase().equals(r.getName().toLowerCase())){
            location = r;
            System.out.println("Moved to " + r.getName());
            if(r.getName().toLowerCase().equals("office")){
              this.mayUpgrade = true;
            } else {
              this.mayUpgrade = false;
            }
         }
      }
    }

    private void takeRole(String s){
      //Check each part and match it to the provided string setting it to the selected part if they match
      for(Part p : currentScene.getCard().getParts()) {
         if (s.toLowerCase().equals(p.getPartName().toLowerCase())) {
            currentPart = p;
            System.out.println("Part " + p.getPartName() + " taken!");
            onCard = true;
         }
      }
      for(Part p: ((Scene)location).getExtraParts()){
         if (s.toLowerCase().equals(p.getPartName().toLowerCase())) {
            currentPart = p;
            System.out.println("Part " + p.getPartName() + " taken!");
            onCard = false;
         }
      }
    }

    /*private void draw(){
      //for Assignment 3
    }*/

}
