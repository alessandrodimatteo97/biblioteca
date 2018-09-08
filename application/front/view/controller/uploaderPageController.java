package application.front.view.controller;

import java.awt.image.BufferedImage;

import java.io.File;
import javax.imageio.ImageIO;
import application.controller.artwork_Controller;
import application.controller.image_Controller;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class uploaderPageController {
	@FXML
	private TextField isbn;
	@FXML
	private TextField titolo;
	@FXML
	private TextField descrizione;
	@FXML
	private TextField lingua;
	@FXML
	private TextField anno;
	@FXML
	private Label label;
	@FXML
	private ObservableList<String> idcategoriaa ;
	@FXML
	private ComboBox<String> comboBox; 	
   
	private artwork_Controller ac = new artwork_Controller();
	
	private image_Controller ic = new image_Controller();
	
	
	// METODO CHE SETTA LA COMBOBOX CON LE CATEGORIE ALL'INTERNO DEL DATABASE
	public void setcombobox() {  
		idcategoriaa = FXCollections.observableArrayList();
		comboBox.setItems(idcategoriaa);
  		idcategoriaa.addAll(ac.getCategory());
	}
	
	// METODO PER INSERIRE UNA NUOVA PUBBLICAZIONE
	public void insertart() {
		if (ac.getInfoArtwork(isbn.getText(), null)) {
			label.setText("pubblicazione già inserita");
	  } else {
		  ac.insertArtwork(isbn.getText(), titolo.getText(), descrizione.getText(), lingua.getText(), Integer.parseInt(anno.getText()), Integer.parseInt(comboBox.getValue().substring(0, 1)));
          }
	}
	// METODO PER INSERIRE UNA NUOVA IMMAGINE IN UNA PUBBLICAZIONE GIà ESISTENTE
	public void insertimg() {  
		int id;
		label.setText("");
	       	if((id = ac.selectIdArt(isbn.getText(), null))!= 0 ) {
	        		System.out.println(id);
	            	BufferedImage bImage = null;
		 
		          try {
		        	  FileChooser directoryChooser = new FileChooser();
		        	  directoryChooser.setTitle("Open Resource File");
		        	  File selectedFile = directoryChooser.showOpenDialog(null);
		        	  String b= selectedFile.getAbsolutePath();
		        	System.out.println(b);
		        	String indirizzoimmagine = b;
		              File initialImage = new File(indirizzoimmagine);
		              
		              bImage = ImageIO.read(initialImage);
		 
		              String download =  "src/application/img/"+ selectedFile.getName() ;
		 
		              ImageIO.write(bImage, "jpg", new File(download));
		              label.setText("immagine inserita");
		              		if(  !ic.insertImage(selectedFile.getName(), id))  label.setText("Immagine non inserita");;
		          			}
		          catch(Exception e) {
		        	  System.out.println(e);
		          }
	
	            }
	            else {
	        		label.setText("nessuna pubblicazione");
	}
	        

	
	
}
	
    // METODO CHE CREA UNA NUOVA VIEW CON UNA GALLERIA DI IMMAGINI RELATIVE ALLA PUBBLICAZIONE
    public void images() {
    	try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/application/front/view/imageGallery.fxml"));
            Parent root1 = fxmlLoader.load();
            ImageGallery vpc = fxmlLoader.getController(); 
            Stage stage = new Stage();
            Scene scene = new Scene(root1);
         	
            stage.setScene(scene);
            vpc.setid(isbn.getText());

            vpc.start(stage);

            stage.show();
         


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
   
}
	
	
	
	

