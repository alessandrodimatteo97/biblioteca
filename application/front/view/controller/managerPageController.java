package application.front.view.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

import javafx.scene.layout.AnchorPane;

public class managerPageController {

	@FXML
	private AnchorPane rootPane;
	
	@FXML
	public void openAssegnament() {	// METODO CHE APRE LA FINESTRA PER GLI ASSEGNAMENTI DEI TRANSCRIBER ALLE IMMAGINI
		 try {
			  	 FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/application/front/view/assegnamentPage.fxml"));
			 	 AnchorPane pane = fxmlLoader.load();
			 	 
			 	 assegnamentPageController ac = fxmlLoader.getController();
			 	  try {
			            ac.selectImgTxt();
			           }
			           
			           catch(Exception e) {
			        	   System.out.println("non c'è nessuna opera");
			           } 
			  rootPane.getChildren().setAll(pane);
		 	} catch (Exception e) { 
	        	System.out.println(e);
	        }
 		
	}
	// METODO CHE APRE LA PAGINA PER REVISIONARE O CONVALIDARE UNA PAGINA
	public void openRevConv() {
		 try {
			 FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/application/front/view/revConvPage.fxml"));
		 	 AnchorPane pane = fxmlLoader.load();
	            revConvPageController rcpc = fxmlLoader.getController();
	           try {
	            rcpc.selectImgTxt();
	           }
	           catch (Exception e){
	        	   System.out.println("non c'è nessuna opera deficè");
	           }
				  rootPane.getChildren().setAll(pane);


	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	}
	
	
	
	// METODO CHE APRE LA PAGINE PER SETTARE IL LIVELLO DEI TRANSCRIBER
	public void levtranscr() {
		 try {
			 FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/application/front/view/levTranscrPage.fxml"));
		 	 AnchorPane pane = fxmlLoader.load();

	            levTranscrPageController ltc = fxmlLoader.getController();
	            ltc.setval();
				  rootPane.getChildren().setAll(pane);


	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	}
	
	
}
