//Card Class
public class Card{

	private String name;
	public int number;
	private String desc;
	private String img;
	public Part[] parts;
	public int budget;

	//Constructors
	Card(){
		this.name = "card";
		this.number = 0;
		this.desc = "Cliche VHS intelligentsia poke franzen ennui lyft 8-bit lomo.";
		this.img = "Img-name and location";
		this.parts = null;
		this.budget = 9002;
	}

	Card(String name, int number, String desc, String img, Part[] parts, int budget){
		this.name = name;
		this.number = number;
		this.desc = desc;
		this.img = img;
		this.parts = parts;
		this.budget = budget;
	}

	//Getters
	public String showName() {
		return this.name;
	}

	//Payout method
	public String payout(boolean onCard, boolean isSuccessful, boolean rehearsing) {
		if(onCard){
			if(isSuccessful){
				//advance scene here
				//Give out 2 fame
				return "2fame";
			}else{
				return "nothing";
			}
		} else if(!onCard && !rehearsing){
			if(isSuccessful){
				//advance scene
				return "1dollar1fame";
			}else{
				return "1dollar";
			}
		} else {
				System.out.println("Practice makes perfect");
				return 0;
		}
	}
}
