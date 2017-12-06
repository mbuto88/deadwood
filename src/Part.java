//Part class
public class Part{

	public String name;
	private int level;
	private int x;
	private int y;
	private int h;
	private int w;
	private String line;

	//Constructors

	Part() {
		this.name = "part";
		this.level = 1;
		this.x = 0;
		this.y = 0;
		this.h = 0;
		this.w = 0;

		//https://hipsum.co
		this.line = "Snackwave gastropub mustache austin bitters tousled viral cliche af retro fashion axe "
			+ "single-origin coffee etsy +1 90s. Yuccie chicharrones taiyaki disrupt."
			+ "Cliche VHS intelligentsia poke franzen ennui lyft 8-bit lomo."
			+ "Edison bulb brooklyn mumblecore, vegan echo park jianbing paleo hoodie pok pok thundercats knausgaard small batch."
			+ "Celiac la croix cronut bespoke."
			+ "Venmo microdosing whatever pork belly.";
	}


	Part(String name, int level, int x, int y, int h, int w, String line) {
		this.name = name;
		this.level = level;
		this.x = x;
		this.y = y;
		this.h = h;
		this.w = w;
		this.line = line;
	}

	//Getters

	public String getPartName() {
		return this.name;
	}

	public int getLevel() {
		return this.level;
	}
	public String getLine() {
		return this.line;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getH() {
		return h;
	}

	public int getW() {
		return w;
	}
}
