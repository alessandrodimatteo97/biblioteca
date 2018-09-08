package application.front.view.controller;


import application.model.VO.user;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


public class mainPageController {

	@FXML
	public Button admin;
	
	@FXML
	public Button mana;
	
	@FXML
	public Button tran;
	
	@FXML
	public Button upl;

	
    @FXML
    public Label info;
    
    @FXML
    public Label errore;
    
    @FXML
    public AnchorPane mainPage;
    
    @FXML
    private Label assegnamenti;
    
    @FXML
    private AnchorPane ndRoot;
    
      public void initialize() {	//INIZIALIZZA TUTTI I BOTTONI DELLA MAINPAGE
 
    	user a = user.getIstance();
    	admin.setDisable(!(a.isAdministrator()));
		mana.setDisable(!(a.isManager()));
		tran.setDisable(!(a.isTranscriber()));
		upl.setDisable(!(a.isUploader()));

		}
	
    public void anagrafica() {	//MOSTRA L'ANAGRAFICA QUANDO VIENE PREMUTO IL BOTTONE CORRISPONDENTE
       user a = application.model.VO.user.getIstance();
       System.out.println(a.getEmail()+" " + a.getNome()+ " "+ a.getCognome()+ " "+ a.getProfessione());
    	try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/application/front/view/userIDPage.fxml"));
            Parent root1 = fxmlLoader.load();
            userIDPageController controller = fxmlLoader.getController();
            controller.anagrafica(a);
            Stage stage = new Stage();
            stage.setScene(new Scene(root1));
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

   

        public void Administrator() {  //CLICCANDO SUL BOTTONE ATTIVA LA PAGINA DELL'ADMIN
        	try {
            	           FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/application/front/view/administratorPage.fxml"));
            	           AnchorPane root1 = fxmlLoader.load();
            	           administratorPageController admc = fxmlLoader.getController();
            	           ndRoot.getChildren().setAll(root1);
            	           admc.showuser();
           	           
            	       } catch (Exception e) {
            	           System.out.println(e);;
            	       }
        	}
                 	  
        public void Manager() {		//CLICCANDO SUL BOTTONE ATTIVA LA PAGINA DEL MANAGER
        	try {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/application/front/view/managerPage.fxml"));
                AnchorPane root1 = fxmlLoader.load();
                ndRoot.getChildren().setAll(root1);
            } catch (Exception e) {
                e.printStackTrace();
            }
          }
        	
            
            public void Uploader() {	//CLICCANDO SUL BOTTONE ATTIVA LA PAGINA DEL UPLOADER
            	try {
                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/application/front/view/uploaderPage.fxml"));
                    AnchorPane root1 = fxmlLoader.load();
                    uploaderPageController upc = fxmlLoader.getController();
                    ndRoot.getChildren().setAll(root1);
                    upc.setcombobox();	

                } catch (Exception e) {
                    System.out.println(e);
                	}
            }
            
            public void Transcriber() {		//CLICCANDO SUL BOTTONE ATTIVA LA PAGINA DEL TRANSCRIBER
            	
            	user a = application.model.VO.user.getIstance();

            	try {
            		
                        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/application/front/view/revConvPage.fxml"));
                        AnchorPane root1 = fxmlLoader.load();
                        revConvPageController rcpc = fxmlLoader.getController();
                       if( rcpc.selectImgTxttranscr(a.getId())) {
                           ndRoot.getChildren().setAll(root1);
                       	}
                       	else {
                       		tran.setDisable(true);
                       		assegnamenti.setText("Non hai nessuna opera assegnata");
                       		}
                    } 
            catch (Exception e) {
                    System.out.println(e);
            		assegnamenti.setText("Non hai nessuna opera assegnata");
                    }
            	}
            
            
            public void Viewer(ActionEvent actionEvent) {		//CLICCANDO SUL BOTTONE ATTIVA LA PAGINA DEL VIEWER
            	try {
                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/application/front/view/viewerPage.fxml"));
                  
                    AnchorPane root1 = fxmlLoader.load();
                    ndRoot.getChildren().setAll(root1);

                } catch (Exception e) {
                    System.out.println(e);
                }
            }

}
            