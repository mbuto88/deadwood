//Player Class
public class Player{

    private Room location;
    private String name;
    private int fame;
    private int money;
    private int rehearsalMarkers;
    private Scene currentScene;
    private Part currentPart;

    //Constructors
    Player(){
    }

    Player(String name){
        this.name = name;
    }


    //Getters
    public String getName(){
        return name;
    }

    public int getFame(){
      return fame;

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

    //Setters
    public void setRoom(Room room){
        location = room;
    }


    //Other Methods
    public void takeTurn(){
    }
    
    public void calcScore(){
    }

    private void act(){
    }

    private void rehearse(){
    }

    private void move(){
    }

    private void takeRole(){
    }

    private void draw(){
    }

}
