package application.front.view.controller;

import java.sql.SQLException;

import application.controller.user_Controller;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;

public class levTranscrPageController {
	private ObservableList<String> transciber =  FXCollections.observableArrayList();
 

	@FXML
	private ListView<String> lista;
	@FXML
	private ComboBox<Integer> combobox;
	
	private user_Controller uc = new user_Controller();
	@FXML
	public void setval() {	// METODO PER AGGIUNGERE ALLA COMBOBOX I LIVELLI 1,2,3,4,5
		lista.getItems().clear();
		transciber.clear();
		combobox.getItems().clear();
		combobox.getItems().add(1);
		combobox.getItems().add(2);
		combobox.getItems().add(3);
		combobox.getItems().add(4);
		combobox.getItems().add(5);

		transciber.addAll(uc.showAllTranscriber());
        lista.setItems(transciber);
	}
	
	// METODO PER CAMBIARE IL LIVELLO AI TRANSCRIBER
	public void settr() throws SQLException {
	
		 uc.transcriberLevel(Integer.parseInt(lista.getSelectionModel().getSelectedItem().substring(0, 1)) , combobox.getSelectionModel().getSelectedItem().intValue());
          setval();
	}
	
}
