package application.controller;

import application.model.DAO.AssignmentDAO;
import application.model.DAO.DAOFactory;

public class assignment_Controller {
	private DAOFactory mysqlfactory;
	private AssignmentDAO assigndao;
	
	public assignment_Controller() {
	     
		mysqlfactory = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
		assigndao = mysqlfactory.getAssignmentDAO();
				
	}
	
	public boolean insertAssignment(int image_id, int user_id) {   //INSERISCI UN ASSEGNAMENTO
		return assigndao.insertAssignToPage(image_id, user_id);
	}
	
	public boolean deleteAssignment(int image_id, int user_id) {	//CANCELLA UN ASSEGNAMENTO
		return assigndao.deleteAssignToPage(image_id, user_id);
	}
}
