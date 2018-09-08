package application.front.view.controller;


import application.controller.user_Controller;
import application.model.VO.user;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;

import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

public class administratorPageController {
	@FXML
	private ListView<String> lista1 = new ListView<String>();

	@FXML
	private  ObservableList<String> userlist = FXCollections.observableArrayList();
	
	@FXML
	private ComboBox<String> role;
	
	@FXML
	private  ObservableList<String> roleList = FXCollections.observableArrayList("viewer","transcriber","uploader","manager","administrator");

	@FXML
	private ListView<String> lista2 = new ListView<String>();
	
	@FXML
	private ObservableList<String> userlist2 = FXCollections.observableArrayList();
	
	user_Controller uc = new user_Controller();
	@FXML
	private TextField email;
	
	@FXML
	private Label label;
		
	public void showuser() {	//MOSTRA TUTTI GLI UTENTI NON ADMIN REGISTRATI NEL SISTEMA
		role.setItems(roleList);
		lista1.getItems().clear();
		userlist.clear();
		userlist.addAll(uc.showUserNotAdmin());
	    lista1.getItems().addAll(userlist);
	}
	
	public void deleteUser() {	//CANCELLA L'UTENTE SELEZIONATO
		uc.deleteUser(lista1.getSelectionModel().getSelectedItem());
		showuser();
	}
	
	public void userCanDownload() {	//ABILITA ALL'UTENTE LA POSSIBILITA' DI EFFETTUARE DOWNLOAD
		label.setText("");
		if(!uc.updateToDownload(lista1.getSelectionModel().getSelectedItem().toString())) { label.setText("utente downloader"); 
		System.out.println("update to download"); }
		showuser();
	}	 

	public void promote(){  //PROMUOVI L'UTENTE SELEZIONATO 
		uc.promoteOrRevoke(lista1.getSelectionModel().getSelectedItem(), role.getValue(), 1);
		 showRegistry(); 
	}
	
	public void revoke(){  //REVOCA IL RUOLO PER L'UTENTE SELEZIONATO
		
		uc.promoteOrRevoke(lista1.getSelectionModel().getSelectedItem(), role.getValue(), 0);
		 showRegistry(); 
	}
	
	public void showRegistry() {  //MOSTRA L'ANAGRAFICA DELL'UTENTE CONNESSO
		lista2.getItems().clear();
		userlist2.clear();
		
		 user a = (user) (uc.userGetInfo(lista1.getSelectionModel().getSelectedItem()));
		 userlist2.add("cf : " + a.getCf());
		 userlist2.add("cognome : " + a.getCognome());
		 userlist2.add("nome : " + a.getNome());
		 userlist2.add("data di nascita : " + a.getDataNascita());
		 userlist2.add("email : " + a.getEmail());
		 userlist2.add("professione : " + a.getProfessione());
		 userlist2.add("residenza : " + a.getResidenza());
		 userlist2.add("titolo di studio : " + a.getTitoloStudio());
		 if (a.isViewer()) userlist2.add("viewer");
		 else userlist2.add("not viewer");
		 if (a.isTranscriber()) userlist2.add("transcriber");
		 else userlist2.add("not transcriber");
		 if (a.isUploader()) userlist2.add("uploader");
		 else userlist2.add("not uploader");
		 if (a.isManager()) userlist2.add("manager");
		 else userlist2.add("not manager");
		 if (a.isAdministrator()) userlist2.add("admin");
		 else userlist2.add("not admin");
		 if(a.isDownloader()) userlist2.add("downloader");
		 else userlist2.add("not downloader");
		 lista2.getItems().addAll(userlist2);
	}

}




