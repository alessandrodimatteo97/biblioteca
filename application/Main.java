package application;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;



public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("/application/front/view/loginPage.fxml"));
			Scene scene = new Scene(root,600,450);
			
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
            primaryStage.getIcons().add(new Image(Main.class.getResourceAsStream("/application/img/icon.png")));
            primaryStage.setTitle("Digital Library");
			primaryStage.setScene(scene);
			primaryStage.show();
		
	        
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {

				launch(args);
	}
}
