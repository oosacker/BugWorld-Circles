import java.util.ArrayList;
import java.util.List;
import javafx.geometry.Bounds;
import javafx.scene.Scene;

public class World {

	int width = 900;
	int height = 700;

	Bug myBug;
	Plant myPlant;
	Obstacle myObs;

	ArrayList<Item> items = new ArrayList<Item>();

	public World() {

		//random bugs
		int bugNum = 80;
		int temp = 0;
		while(temp < bugNum) {
			myBug = new Bug(randomX(), randomY());
			if(isNotOverlapping(myBug.getX(), myBug.getY())) {
				items.add(myBug);
			}
			temp++;
		}

		// make some plants
		int plNum = 20;
		temp = 0;
		while(temp < plNum) {
			myPlant = new Plant(randomSize(), randomX(), randomY());
			if(isNotOverlapping(myPlant.getX(), myPlant.getY())) {
				items.add(myPlant);
			}
			temp++;
		}

		// make some obstacles
		int obsNum = 500;
		temp = 0;
		while(temp < obsNum) {
			myObs = new Obstacle(randomX(), randomY());
			if(isNotOverlapping(myObs.getX(), myObs.getY())) {
				items.add(myObs);
			}
			temp++;
		}
	}

	/**
	 * 
	 * random position on screen (x and y)
	 */
	private int randomX() {
		int x = (int)((width-1) * Math.random() + 1);
		return x;
	}

	private int randomY() {
		int y = (int)((height-1) * Math.random() + 1);
		return y;
	}

	// random plant size
	private int randomSize() {
		return (int)((9-0) * Math.random() + 1);
	}


	/**
	 * 
	 * Check if object is inside the scene window
	 */
	private boolean isInWindow(Item myitem, Scene myscene) {
		
		Bounds bounds =  myitem.getBoundsInParent();
		double sceneWidth = myscene.getWidth();
		double sceneHeight = myscene.getHeight();
		
		if (( bounds.getMinX() < 0 || bounds.getMaxX() > sceneWidth) ||
				 (bounds.getMinY() < 0 || bounds.getMaxY() > sceneHeight)) {
			//System.out.println("outside");
			return false;
		}
		
		else {
			return true;
		}
	}

	/**
	 * Check if given x and y coord is valid
	 */
	private boolean isNotOverlapping(int x, int y) {
		// check if x and y is not filled or outside world space
		for (Item myItem : items) {
			
			if ( ((x == myItem.getX()) && (y == myItem.getY())) ) {
				return false;
			}
		}
		return true;
	}
	
	/**
	 * Is bug next to something
	 */
	public List<Item> adjacentItems(Bug b) {
		
		List<Item> ret = new ArrayList<Item>();
		
		int bug_x = b.getX();
		int bug_y = b.getY();
		
		for(Item o : items) {
			
			int item_x = o.getX();
			int item_y = o.getY();
			
			if ( 
					((bug_x == item_x) && (bug_y+1 == item_y)) ||
					((bug_x == item_x) && (bug_y-1== item_y)) ||
					((bug_x+1 == item_x) && (bug_y == item_y)) ||
					((bug_x-1 == item_x) && (bug_y == item_y)) ) 
			{
				
				ret.add(o);
				
			}
			
		}
		return ret;	
	}
	
	
	
	/**
	 * Move all animals on the island randomly (or not)
	 */
	public void moveAllBugs(Scene myscene) {
		
		Bug b;
		List<Item> adjacent;
		
		for(Item item : items) {
			
			if(item instanceof Bug) {
				
				b = (Bug)item;
				adjacent = adjacentItems(b);
				
				if( adjacent.isEmpty() && isInWindow(b, myscene) ) {
					
					moveRandom(b, myscene);
					
				}
				
			}
			
		}

	}
	
	/**
	 * Move an animal in random direction
	 */
	public void moveRandom(Bug a, Scene myscene) {
		double d = Math.random();
		String dir;
		
		int currentx = a.getX();
		int currenty = a.getY();
		
		
		if (d < 0.25) { 
			dir = "N";
			if (isNotOverlapping(currentx, currenty-a.getStep()) && isInWindow(a, myscene)) {
				a.Move(dir);
			}
			else {
				//a.Move("S");
			}
		}
		else if (d < 0.5) {
			dir = "S";
			if (isNotOverlapping(currentx, currenty+a.getStep()) && isInWindow(a, myscene)) {
				a.Move(dir);
			}
			else {
				//a.Move("N");
			}
		}
		else if (d < 0.75) {
			dir = "E";
			if (isNotOverlapping(currentx+a.getStep(), currenty) && isInWindow(a, myscene)) {
				a.Move(dir);
			}
			else {
				//a.Move("W");
			}
		}
		else {
			dir = "W";
			if (isNotOverlapping(currentx-a.getStep(), currenty) && isInWindow(a, myscene) ) {
				a.Move(dir);
			}
			else {
				//a.Move("E");
			}
		}
	}
	
	
	public ArrayList<Item> getItems(){
		return items;
	}
	
	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}

}
