//Player Class
public class Player{

<<<<<<< HEAD
		private Room location;
      private String name;
=======
    private Room location;
    private String name;
>>>>>>> 16172047c3867689875e7b829fc506228e9a91a8
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


<<<<<<< HEAD
		//Getters
		public String getName(){
      return "nothing";
		}

    public String getFame(){
      return "nothing";
=======
    //Getters
    public String getName(){
        return name;
    }

    public int getFame(){
      return fame;

>>>>>>> 16172047c3867689875e7b829fc506228e9a91a8
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

<<<<<<< HEAD
    public String getLocation(){
      return "nothing";
		}

    public String getScene(){
      return "nothing";
		}

    public String getPart(){
      return "nothing";
		}
=======
    public Part getPart(){
      return currentPart;
    }

    //Setters
    public void setRoom(Room room){
        location = room;
    }

>>>>>>> 16172047c3867689875e7b829fc506228e9a91a8

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
