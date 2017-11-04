//Player Class
public class Player{

		private Room location;
      private String name;
    private int fame;
    private int money;
    private int rehearsalMarkers;
    private Card currentScene;
    private Part currentPart;

		//Constructors
		Player(){
		}

		Player(String name){
			this.name = name;
		}

		//Getters
		public String getName(){
      return "nothing";
		}

    public String getFame(){
      return "nothing";
    }

    public int getMoney(){
      return 0;
		}

    public String getLocation(){
      return "nothing";
		}

    public String getScene(){
      return "nothing";
		}

    public String getPart(){
      return "nothing";
		}

		//Other Methods
		public void takeTurn(){
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
