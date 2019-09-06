
import javafx.scene.shape.Circle;

public class Item extends Circle{
	private String name;

	private static int circleSize = 5;
	
	public Item(String name, int x, int y) {
		super(x, y, circleSize);
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getX() {
		return (int) this.getCenterX();
	}

	public int getY() {
		return (int) this.getCenterY();
	}

}
