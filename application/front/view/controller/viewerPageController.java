package application.front.view.controller;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.Map;
import java.util.TreeMap;
import javax.imageio.ImageIO;
import application.controller.artwork_Controller;
import application.controller.image_Controller;
import application.model.VO.artwork;
import application.model.VO.user;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.DirectoryChooser;

public class viewerPageController{
    @FXML
    private Image image;
    @FXML
    private ImageView imageview;
    @FXML
    private Label label;
    @FXML
    private Button button;
    @FXML
    private TextArea textarea;
    @FXML
    private TextField titolo;
    @FXML
    private TextField isbn;
    @FXML
    private Button download;
    @FXML
    private TextArea descrizione;

    private int i = 1;

    private Map<Integer, application.model.VO.image> immtxt;

    private image_Controller ic = new image_Controller();
    
    private artwork_Controller ac = new artwork_Controller();

    // METODO PER TROVARE L'ID DI UNA PUBBLICAZIONE
    public int findArtwork() throws SQLException {
      artwork atwk = ac.selectArt(isbn.getText(), titolo.getText());
        if ( !(atwk == null)) {
        	descrizione.clear();            
        	descrizione.appendText(atwk.getDescription());
        	 descrizione.setWrapText(true);
            return atwk.getId();
        	
        }
        return 0;
    }
    // METODO PER SELEZIONARE LE IMMAGINI E LE RELATIVE TRASCRIZIONE
    public void selectImgTxt() throws SQLException, FileNotFoundException {
        immtxt = new TreeMap<Integer, application.model.VO.image>();
        immtxt.putAll(ic.viewArtwork(findArtwork()));
        setImg(immtxt.get(i).getImage_url());
        setTxt(immtxt.get(i).getTranscription());
    }
    // METODO PER SFOGLIARE IN AVANTI LE IMMAGINI
    public void avanti() throws FileNotFoundException {
        label.setText("");
    	i = i + 1;
        if (!immtxt.containsKey(i)) i = 1;
        setImg(immtxt.get(i).getImage_url());
        setTxt(immtxt.get(i).getTranscription());
        System.out.println(immtxt.get(i).getImage_url());
    }
    // METODO PER SFOGLIARE INDIETRO LE IMMAGINI
    public void indietro() throws FileNotFoundException {
        label.setText("");
    	i = i - 1;
        if (!immtxt.containsKey(i)) i = immtxt.size();
        setImg(immtxt.get(i).getImage_url());
        setTxt(immtxt.get(i).getTranscription());
        System.out.println(immtxt.get(i).getImage_url());
    }
    
    // METODO PER SETTARE L'IMAGEVIEW CON LA RELATIVA IMMAGINE 
    public void setImg(String img_url) throws FileNotFoundException {
        File file = new File("src/application/img/" + img_url);
        System.out.println(file.getPath());
        image = new Image(file.toURI().toString());
        imageview.setImage(image);
    }
    // METODO PER SETTARE LA TEXTFIELD IN CASO NON CI FOSSE ANCORA LA TRASCRIZIONE
    public void setTxt(String urltesto) {
        textarea.clear();
        try {
        	
        
        textarea.appendText(urltesto);
        textarea.setWrapText(true);
        }
        catch(Exception e) {
        	textarea.appendText("trascrizione da fare");
        }
        }
      
    // METODO PER SCARICARE UN'IMMAGINE NEL CASO IN CUI SI è DOWNLOADER
	public void downloader() {
		label.setText("");
		user a = user.getIstance();
	if (a.isDownloader()) {
		
	
		BufferedImage bImage = null;
		 
		          try {
		        	  DirectoryChooser directoryChooser = new DirectoryChooser();
		        	  directoryChooser.setTitle("Open Resource File");
		        	  File selectedFile = directoryChooser.showDialog(null);
		        	  String b= selectedFile.getAbsolutePath();
		        	System.out.println(b);
		        	String indirizzoimmagine = image.getUrl().substring(5, image.getUrl().length());
		              File initialImage = new File(indirizzoimmagine);
		              
		              bImage = ImageIO.read(initialImage);
		 
		              String download = b +"/" + immtxt.get(i).getImage_url() ;
		 
		              ImageIO.write(bImage, "jpg", new File(download));
		              label.setText("immagine scaricata");
		              
    }
		          catch(Exception e) {
		        	  System.out.println(e);
		          }
	}
	else {
		label.setText("non puoi scaricare");
	}
	}

    
   
 
   
   
   
   
   

	
}

