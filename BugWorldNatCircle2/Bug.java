
public class Bug extends Item {

	private String species;
	private String name;
	private int ID = 0;
	private static int bugNum = 0;
	private static int step = 2;

	public Bug(int x, int y) {
		super("Bug", x, y);
		this.species = "Bug";
		this.ID = bugNum++;
	}
	
	public int getStep() {
		return Bug.step;
	}
	
	public String getSpecies() {
		return species;
	}

	public void setSpecies(String species) {
		this.species = species;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getID() {
		return ID;
	}

	public static int getBugNum() {
		return bugNum;
	}
	
	public void Move(String dir) {
		//int currentx = this.getX();
		//int currenty = this.getY();
		
		if ("N".equals(dir)) {
			//this.setY(currenty - step);
			this.setTranslateY(this.getTranslateY() - step);
		}
		if ("S".equals(dir)) {
			//this.setY(currenty + step);
			this.setTranslateY(this.getTranslateY() + step);
		}
		if ("E".equals(dir)) {
			//this.setX(currentx + step);
			this.setTranslateX(this.getTranslateX() + step);
		}
		if ("W".equals(dir)) {
			//this.setX(currentx - step);
			this.setTranslateX(this.getTranslateX() - step);
		}
		
	}


}
