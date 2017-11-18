public class Upgrade {

	private String currency;
	public int level;
	private int amt;
	int x;
	int y;
	int h;
	int w;

	//Constructor
	public Upgrade(String currency, int level, int amt, int x, int y, int h, int w) {
		super();
		this.currency = currency;
		this.level = level;
		this.amt = amt;
		this.x = x;
		this.y = y;
		this.h = h;
		this.w = w;
	}

	public Upgrade(){

	}

	//Getters
	public String getCurrency() {
		return currency;
	}

	public int getLevel() {
		return level;
	}

	public int getAmt() {
		return amt;
	}

	//Setters
	public void setAmt(int amt) {
		this.amt = amt;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}
}
