package application.controller;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import application.model.DAO.DAOFactory;
import application.model.DAO.UserDAO;
import application.model.VO.user;
public class user_Controller {

	
	private DAOFactory mysqlfactory;
	private UserDAO userdao;
	
	public user_Controller() {
		mysqlfactory = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
		userdao = mysqlfactory.getUserDAO();
				
	}
	
	public boolean login(String email, String password) {	//EFFETTUA IL LOGIN RESTITUENDO TRUE SE E' CORRETTO, FALSE VICEVERSA
		if(userdao.CorrectLogInput(email, password)) return true;
		return false;
	}
	
	
	public void setUserGloabal(String email, String password) throws SQLException {		//
		userdao.getUser(email, password);
	}
	
	public boolean ctrlemail(String email) {  //CONTROLLO E-MAIL
		 return userdao.emailUsed(email);
	}
	
	//EFFETTUA LA REGISTRAZIONE SULLA PIATTAFORMA 
	public void signup(String email, String password, String name, String surname, Date birth_date, String residence, String qualification, String profession, String fiscal_code) {
		userdao.registraUtente(email, password, name, surname, birth_date, residence, qualification, profession, fiscal_code);
	}
	
	public void request(int id) {  //EFFETTUA LA RICHIESTA PER DIVENTARE UN TRASCRITTORE 
		 userdao.updateUserToTranscr(id);
	}
	
	public List<String> showUserNotAdmin() {	//MOSTRA TUTTI GLI USER CHE NON SONO ADMIN
		return userdao.showUser();
	}
	
	public boolean deleteUser(String email) {	//ELIMINA UN UTENTE DALLA PIATTAFORMA
		return userdao.deleteUser(email);
	}
	
	public boolean updateToDownload(String email) {		//ABILITA IL DOWNLOAD 
		return userdao.updateDownload(email);
	}
	
	public void promoteOrRevoke(String email, String functionU, int ur) {	//METODO CHE PERMETTE DI EFFETTUARE LA REVOCA O LA PROMOZIONE DI UN DETERMINATO RUOLO
		switch (functionU)
				{
				     case "transcriber":
				 		userdao.promOrRevTrascr(email, ur);
						break;
				     		     
				     case "manager":
				    	 userdao.promOrRevMan(email, ur);
						break;
					 		     
				     case "uploader":
				    	userdao.promOrRevUpload(email, ur);
						break;
					 
				     case "administrator":
				    	 userdao.promOrRevAdmin(email, ur);
						 break;
					 
				     case "viewer":
				    	userdao.promOrRevViewer(email, ur);
						 break;
						 
				     default:
				    	 System.out.println("Ruolo non esistente");
				    	break; 
				} 
		}	
	
	public user userGetInfo(String email) {		//MOSTRA LE INFORMAZIONI DI UN UTENTE DATA L'E-MAIL
		return userdao.showInfo(email);
	}
	
	public List<String> getTranscriberAssign(int id) {
		return userdao.assigned(id);
	}
	
	public List<String> getTranscriberNotAssign(int id) {
		return userdao.notAssigned(id);
	}
	
	public void transcriberLevel(int userid, int lev) {
		userdao.setTranscriber(userid, lev);
	}
	
	public List<String> showAllTranscriber(){
		return userdao.getAllTranscriber();
	}
}
