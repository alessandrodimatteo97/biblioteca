package application.front.view.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import application.controller.user_Controller;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class signupPageController {
    @FXML
    private TextField email;
    @FXML
    private TextField password;
    @FXML
    private TextField nome;
    @FXML
    private TextField cognome;
    @FXML
    private TextField datanascita;
    @FXML
    private TextField residenza;
    @FXML
    private TextField cf;
    @FXML
    private TextField titolostudio;
    @FXML
    private TextField professione;
    @FXML
    private Button registrazione;
    @FXML
    private Button reset;
    @FXML
    private Label accesso;

    private user_Controller uc = new user_Controller() ;

    @FXML
    public void signed() throws ParseException {	// METODO PER ESEGUIRE LA REGISTRAZIONE NEL DATABASE 
        String startDate=datanascita.getText();
    	SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-mm-dd");
    	java.util.Date date = sdf1.parse(startDate);
    	java.sql.Date sqlStartDate = new java.sql.Date(date.getTime());     	
    	if (!uc.ctrlemail (email.getText())) {
    		uc.signup(email.getText(), password.getText(), nome.getText(), cognome.getText(), sqlStartDate , residenza.getText(), titolostudio.getText(), professione.getText(), cf.getText());
    	}
    }

    @FXML
    public void resetbutton() {	// METODO PER RESETTARE I TEXTFIELD
        email.setText(" ");
        password.setText(" ");
        nome.setText(" ");
        cognome.setText(" ");
        datanascita.setText(" ");
        residenza.setText(" ");
        cf.setText(" ");
        titolostudio.setText("");
        professione.setText("");

    }
}
