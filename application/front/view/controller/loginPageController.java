
package application.front.view.controller;

import application.Main;
import application.controller.user_Controller;
import javafx.fxml.FXMLLoader;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.Node;




public class loginPageController {

    @FXML
    public Label textLabel;
    @FXML
    public TextField userName;
    @FXML
    public PasswordField userPassword  ;
    @FXML
    public Label isConnected;
    
    private user_Controller uc = new user_Controller() ;


    @FXML			
    public void login(ActionEvent actionEvent) throws Exception {	// METODO PER IL LOGIN, SE LOGIN DA SUCCESSO APRE LA MAINPAGE
    		
       	if( uc.login(userName.getText(), userPassword.getText())) {
    		uc.setUserGloabal(userName.getText(), userPassword.getText());
    		((Node) (actionEvent.getSource())).getScene().getWindow().hide();
               
    		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/application/front/view/mainPage.fxml"));
            Parent mainPage = fxmlLoader.load();
           
     
            Stage stage = new Stage();
            
            stage.setScene(new Scene(mainPage));
            stage.setFullScreen(true);
            stage.setFullScreenExitHint("");
            stage.show();
    	}
         else {
                isConnected.setStyle("-fx-border-color: red;");
                isConnected.setText("Si è verificato un errore. Controlla che l'email e la password siano corretti e riprova.");
 			} 
}
    // METODO PER APRIRE LA FINESTRA DI REGISTRAZIONE
    public void signup() throws Exception {
        try {
            Parent signup_root = FXMLLoader.load(getClass().getResource("/application/front/view/signupPage.fxml"));
            Stage stage = new Stage();
            stage.getIcons().add(new Image(Main.class.getResourceAsStream("/application/img/icon.png")));
            stage.setTitle("Digital Library");
            stage.setScene(new Scene(signup_root));
            stage.show();


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

	
	
	
