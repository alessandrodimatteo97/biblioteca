package application.front.view.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import application.controller.user_Controller;
import application.model.VO.user;

public class userIDPageController {
    @FXML
    private Label id;
    @FXML
    private Label nome;
    @FXML
    private Label cognome;
    @FXML
    private Label datanascita;
    @FXML
    private Label email;
    @FXML
    private Label residenza;
    @FXML
    private Label cf;
    @FXML
    private Label professione;
    @FXML
    private Label titolostudio;
    @FXML
    private Label richiesta;
    
    private user user1;
    
   user_Controller uc = new user_Controller();
// METODO CHE SETTA LE VARIE LABEL CON I CAMPI RELATIVI ALL'ANAGRAFICA DELL'UTENTE
    public void anagrafica(user u) {
        nome.setText(u.getNome());
        cognome.setText(u.getCognome());
        datanascita.setText(u.getDataNascita());
        email.setText(u.getEmail());
        residenza.setText(u.getResidenza());
        cf.setText(u.getCf());
        titolostudio.setText(u.getTitoloStudio());
        professione.setText(u.getProfessione());
        this.user1 = u;
    }
    // METOODO PER FARE LA RICHIESTA PER DIVENTARE
    public void richiestadownload() {
    	richiesta.setText("");
    	if( user1.isRequest()) richiesta.setText("richiesta già fatta");
    	if(user1.isTranscriber()) richiesta.setText("sei già transcriber");
    	else {
    		
    		uc.request(user1.getId());
    		richiesta.setText("richiesta effettuata");

    	}
}
}
