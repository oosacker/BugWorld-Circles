import java.util.List;
import javafx.animation.KeyFrame;
import javafx.animation.TimelineBuilder;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;

@SuppressWarnings("deprecation")
public class Graphics extends Application {
	
	World w;

	List<Item> items;

	Color bugColor = Color.BROWN;
	Color obsColor = Color.GREY;
	Color plantColor = Color.GREEN;

	KeyFrame frame;
	Scene scene;
	Group root;
	
	int width = 900, height = 700;
	int x, y;
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		
		w = new World();
		
		primaryStage.setTitle("Bug World FX");
		
		width = w.getWidth();
		height= w.getHeight();
		
		items = w.getItems();
		root = new Group(); 
		
		for(Item item : items) {
			
			if(item instanceof Bug) {
				item.setFill(bugColor);
			}
			
			if(item instanceof Plant) {
				item.setFill(plantColor);
			}
			
			if(item instanceof Obstacle) {
				item.setFill(obsColor);
			}		
			
			root.getChildren().add(item);
		}
		
		scene = new Scene(root, width, height);	

		frame = new KeyFrame(Duration.millis(30), e-> {

			w.moveAllBugs(scene);
			
		});

		TimelineBuilder.create().cycleCount(javafx.animation.Animation.INDEFINITE).keyFrames(frame).build().play();

		primaryStage.setScene(scene);
		primaryStage.sizeToScene();
		primaryStage.show();

	}

	public static void main(String[] args) {
		launch(args);

	}

}
