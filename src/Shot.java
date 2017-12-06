public class Shot {
	
	int x;
	int y;
	int h;
	int w;
	int number;
	
	//Constructor
	public Shot(int x, int y, int h, int w, int number) {
		this.x = x;
		this.y = y;
		this.h = h;
		this.w = w;
		this.number = number;
	}

	//Getters
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

	public int getNumber() {
		return number;
	}
}
