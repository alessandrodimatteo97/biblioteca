package application.front.view.controller;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Map;

import application.controller.artwork_Controller;
import application.controller.image_Controller;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.TilePane;
import javafx.scene.paint.Color;
import javafx.stage.Screen;
import javafx.stage.Stage;


// QUESTA CLASSE APRE UNA NUOVA VIEW E CREA UNA GALLERIA DI IMMAGINI RELATIVA AD UNA PUBBLICAZIONE
public class ImageGallery  {
    private int id;
    
    private Stage stage;
    
    private image_Controller ic = new image_Controller();
    
    private artwork_Controller ac = new artwork_Controller();
   
    
    public void start(Stage primaryStage) throws Exception {
        stage = primaryStage;
        ScrollPane root = new ScrollPane();
        TilePane tile = new TilePane();
        root.setStyle("-fx-background-color: DAE6F3;");
        tile.setPadding(new Insets(15, 15, 15, 15));
        tile.setHgap(15);

        String path = "src/application/img/";

        File folder = new File(path);
        File[] listOfFiles = folder.listFiles();
       
        for (final File file : listOfFiles) {
             if (ic.verficaImage(id, file.getName())) { 
            	   ImageView imageView;
            	   imageView = createImageView(file);
            	   tile.getChildren().addAll(imageView);
              } 
    	}
    	
        root.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER); // Horizontal
        root.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED); // Vertical scroll bar
        root.setFitToWidth(true);
        root.setContent(tile);

        primaryStage.setWidth(Screen.getPrimary().getVisualBounds().getWidth());
        primaryStage.setHeight(Screen.getPrimary().getVisualBounds()
                .getHeight());
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    private ImageView createImageView(final File imageFile) {
        
        ImageView imageView = null;
        try {
            final Image image = new Image(new FileInputStream(imageFile), 150, 0, true,
                    true);
            imageView = new ImageView(image);
            imageView.setFitWidth(150);
            imageView.setOnMouseClicked(new EventHandler<MouseEvent>() {

                @Override
                public void handle(MouseEvent mouseEvent) {

                    if(mouseEvent.getButton().equals(MouseButton.PRIMARY)){

                        if(mouseEvent.getClickCount() == 1){
                            try {
                                BorderPane borderPane = new BorderPane();
                                ImageView imageView = new ImageView();
                                Image image = new Image(new FileInputStream(imageFile));
                                imageView.setImage(image);
                                imageView.setStyle("-fx-background-color: BLACK");
                                imageView.setFitHeight(stage.getHeight() - 10);
                                imageView.setPreserveRatio(true);
                                imageView.setSmooth(true);
                                imageView.setCache(true);
                                borderPane.setCenter(imageView);
                                borderPane.setStyle("-fx-background-color: BLACK");
                                Stage newStage = new Stage();
                                newStage.setWidth(stage.getWidth());
                                newStage.setHeight(stage.getHeight());
                                newStage.setTitle(imageFile.getName());
                                Scene scene = new Scene(borderPane,Color.BLACK);
                                newStage.setScene(scene);
                                newStage.show();
                            } catch (FileNotFoundException e) {
                                e.printStackTrace();
                            }

                        }
                        	
                        
                    }
                }
                
            });
            
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }
        return imageView;
    }

    public void setimmaginecont(Map<Integer, application.model.VO.image> immtxt) {
    } 
    public void setid(String isbn) {
    	this.id = ac.selectIdArt(isbn, null);
    	
      
    }
    

}