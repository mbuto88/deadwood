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
	
	private String getPartName() {
		return this.name;
	}
	
	public int getLevel() {
		return this.level;
	}
	public String getLine() {
		return this.line;
	}
}