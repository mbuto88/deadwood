import java.util.*;

//Card Class
public class Card{

	private String name;
	public int number;
	private String desc;
	private String img;
	public ArrayList<Part> parts;
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

	Card(String name, int number, String desc, String img, ArrayList<Part> parts, int budget){
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
   public int getBudget() {
      return budget;
   }
   public ArrayList<Part> getParts(){
      return parts;
   }
   
	public String getImg() {
	return img;
}

	//Payout method (returns 2 deep array of #of fame gained and #of dollars gained
	public int[] payout(boolean onCard, boolean isSuccessful) {
		if(onCard){
			if(isSuccessful){
				//advance scene
				return new int[] {2, 0};
			}else{
				return new int[] {0, 0};
			}
		} else {
			if(isSuccessful){
				//advance scene
				return new int[] {1, 1};
			}else{
				return new int[] {0, 1};
			}
		}
	}
}
