package application.front.view.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.Map;
import java.util.TreeMap;

import application.controller.assignment_Controller;
import application.controller.image_Controller;
import application.controller.user_Controller;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class assegnamentPageController {

	@FXML
	private Image image1;
	@FXML
    private ListView<String> lista = new ListView<String>();
	@FXML
	private ImageView imageview ;
	@FXML
	private Label label;
	@FXML
	ObservableList<String> list1 = FXCollections.observableArrayList();

    private Map<Integer, application.model.VO.image> immtxt;

	private int i=1;
	
	private image_Controller ic = new image_Controller();
	
	private assignment_Controller ac = new assignment_Controller();
	
	private Label assegnamento;
	
	
	private user_Controller uc = new user_Controller();
	
	// METODO CHE SETTA LE IMMAGINI CON I RELATIVI TRANSCRIBER ASSEGNATI E NON
	public void selectImgTxt() throws SQLException, FileNotFoundException {
        immtxt = new TreeMap<Integer, application.model.VO.image>();
     
        immtxt.putAll(ic.validationz()); 
        if(!immtxt.isEmpty()) {
            setImg(immtxt.get(i).getImage_url());        
            setlav(immtxt.get(i).getId());

            }
            else {
                imageview.setImage(null);
                assegnamento.setText("non hai nessuna opera da assegnare");
            }
    }
	
	// METODO PER SETTARE I TRANSCRIBER
	 private void setlav(int id) throws SQLException { 
		 lista.getItems().clear();
		 list1.clear();
	   
	        list1.addAll(uc.getTranscriberAssign(id));
		    list1.addAll(uc.getTranscriberNotAssign(id));
			lista.getItems().addAll(list1);

	        
	}

	 // METODO CHE VIENE CHIAMATO PER SETTARE LE IMMAGINI
	public void setImg(String img_url) throws FileNotFoundException {
	        File file = new File("src/application/img/" + img_url);
	        image1 = new Image(file.toURI().toString());
	        imageview.setImage(image1);
	    }
	
	// METODO PER SFOGLIARE IN AVANTI LE IMMAGINI
	  public void avanti() throws FileNotFoundException, SQLException {
	        
		    label.setText("");
	    	i = i + 1;
	        if (!immtxt.containsKey(i)) i = 1;
	        setImg(immtxt.get(i).getImage_url());
	        setlav(immtxt.get(i).getId());

	        System.out.println(immtxt.get(i).getImage_url() +" "+ i);
	    }
	  // METODO PER SFOGLIARE INDIETRO LE IMMAGINI
	    public void indietro() throws FileNotFoundException, SQLException {
	        label.setText("");
	    	i = i - 1;
	        if (!immtxt.containsKey(i)) i = immtxt.size();
	        setImg(immtxt.get(i).getImage_url());
	        setlav(immtxt.get(i).getId());

	        System.out.println(immtxt.get(i).getImage_url() +" "+ i);
	    }
	    
	    // METODO PER ASSEGNARE UN TRANSCRIBER ALL'IMMAGINE
	    public void plus() throws SQLException, FileNotFoundException {
	 	    	System.out.println(lista.getSelectionModel().getSelectedItem().substring(0, 1)); // cosi mi prendo l'id dell'utente
	 	    	System.out.println(immtxt.get(i).getArtwork_id());
	 	    	ac.insertAssignment(immtxt.get(i).getId(), Integer.parseInt(lista.getSelectionModel().getSelectedItem().substring(0, 1)));
	 	        setImg(immtxt.get(i).getImage_url());
	 	        setlav(immtxt.get(i).getId());
	    }
	    
	    // METODO PER TOGLIERE UN TRANSCRIBER ASSEGNATO ALL'IMMAGINE
	    public void minus() throws SQLException, FileNotFoundException {
	    	System.out.println(lista.getSelectionModel().getSelectedItem().substring(0, 2)); // cosi mi prendo l'id dell'utente
 	    	System.out.println(immtxt.get(i).getArtwork_id());
 	    	ac.deleteAssignment(immtxt.get(i).getId() , Integer.parseInt(lista.getSelectionModel().getSelectedItem().substring(0, 1)));
	        setImg(immtxt.get(i).getImage_url());
	        setlav(immtxt.get(i).getId());
	    }
}
	
	
	

	
