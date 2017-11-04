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
	private void showName() {
		
	}
	
	//Other methods
	private void payout() {
		
	}
}