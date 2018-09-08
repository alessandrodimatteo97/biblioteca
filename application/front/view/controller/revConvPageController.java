package application.front.view.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.Map;
import java.util.TreeMap;
import application.controller.image_Controller;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class revConvPageController {
	@FXML
	private Button convalida;
	@FXML
	private TextArea textarea;
	@FXML
	private Image image;
	@FXML
	private ImageView imageview ;
	@FXML
	private Label label;
	@FXML
	private Label validazione;
	@FXML
	private ObservableList<String> list1 = FXCollections.observableArrayList();

    private Map<Integer, application.model.VO.image> immtxt;

	private int i=1;
	
	private image_Controller ic = new image_Controller();
	
	// METODO CHE SCARICA IN UNA TREEMAP LE IMMAGINI DAL DATABASE E SETTA LA IMAGEVIEW CON LA RELATIVA TRASCRIZIONE
	public void selectImgTxt() throws SQLException, FileNotFoundException {
		
        immtxt = new TreeMap<Integer, application.model.VO.image>();
        immtxt.putAll(ic.validationz());
        if(!immtxt.isEmpty()) {
        setImg(immtxt.get(i).getImage_url());
        setTxt(immtxt.get(i).getTranscription());
        }
        else {
            imageview.setImage(null);
            validazione.setText("non hai nessuna opera da modificare/convalidare");
        }
	}
	
	// METODO CHE CONVALIDA UN'IMMAGINE
	public void convalidate() throws SQLException, FileNotFoundException {
		if (ic.validateImage(immtxt.get(i).getId())) validazione.setText("immagine validata");
        selectImgTxt();
        
	}
	// METODO PER SFOGLIARE LE IMMAGINI IN AVANTI
	 public void avanti() throws FileNotFoundException {
		 	validazione.setText("");
		    i = i + 1;
	    	if (!immtxt.containsKey(i)) i = 1;			
	        setImg(immtxt.get(i).getImage_url());
	        setTxt(immtxt.get(i).getTranscription());
	        System.out.println(immtxt.get(i).getImage_url());
	    }

	 	// METODO PER SFOGLIARE LE IMMAGINI INDIETRO
	    public void indietro() throws FileNotFoundException {
		    validazione.setText("");

	    	i = i - 1;
	        if (!immtxt.containsKey(i)) i = immtxt.size();
	        setImg(immtxt.get(i).getImage_url());
	        setTxt(immtxt.get(i).getTranscription());
	        System.out.println(immtxt.get(i).getImage_url());
	    }
	    
	    // METODO PER SETTARE LE IMMAGINI NELL'IMAGEVIEW
	    public void setImg(String img_url) throws FileNotFoundException {
	        File file = new File("src/application/img/" + img_url);
	        image = new Image(file.toURI().toString());
	        imageview.setImage(image);
	    }
	    // METODO PER SETTARE LA TRASCRIZIONE DELL'IMMAGINE
	    public void setTxt(String urltesto) {
	        textarea.clear();
	        try {
	        textarea.appendText(urltesto);
	        textarea.setWrapText(true);
	        }
	        catch (Exception e){
	        	textarea.appendText("ancora nessuna trascrizione");
	        }
	        }
	    
	    // METODO PER MODIFICARE LA TRASCRIZIONE DI UN'IMMAGINE
	public void modify() throws SQLException, FileNotFoundException {
			if(ic.setTrascriptionImage(immtxt.get(i).getId(), textarea.getText())) validazione.setText("immagine aggiornata");
	        selectImgTxt();

		}	
	
	// METODO PRINCIPALE
	public boolean selectImgTxttranscr(int idutente) throws  FileNotFoundException {
		convalida.setVisible(false);
        immtxt = new TreeMap<Integer, application.model.VO.image>();    
        immtxt.putAll(ic.getImageToTranscr(idutente));
        setImg(immtxt.get(i).getImage_url());
        setTxt(immtxt.get(i).getTranscription());
        return !immtxt.isEmpty();
	}

	   
        
}
