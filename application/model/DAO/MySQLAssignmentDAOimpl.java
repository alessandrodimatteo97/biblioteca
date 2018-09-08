package application.model.DAO;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;

public class MySQLAssignmentDAOimpl implements AssignmentDAO {
	
	private Connection con = null;
	PreparedStatement prep = null;
	Statement stm = null;
	ResultSet res = null;
	private final String INSERT_ASSIGNMENT = "INSERT INTO write_assignment (image_id, user_id) VALUES (?, ?)";
	
	private final String DELETE_ASSIGNMENT =  "DELETE FROM write_assignment WHERE image_id= ? AND user_id = ?";

	@Override
	public boolean insertAssignToPage(int image_id, int user_id) {
		 try {
			  con = MySQLDAOFactory.createConnection();
			  prep = (PreparedStatement) con.prepareStatement(INSERT_ASSIGNMENT);
			  prep.setInt(1, image_id);
			  prep.setInt(2,user_id);
			 return prep.execute();
				  
			  }
			   catch (SQLException e) {e.printStackTrace(); System.out.println("Errore Query");}	
			   finally {
				   application.model.DAO.MySQLDAOFactory.closeDbConnection(res, prep, con);
			   }
			  
			  	return false;
	}
	
	

	@Override
	public boolean deleteAssignToPage(int image_id, int user_id) {
		 try {
			  con = MySQLDAOFactory.createConnection();
			  prep = (PreparedStatement) con.prepareStatement(DELETE_ASSIGNMENT);
			  prep.setInt(1, image_id);
			  prep.setInt(2,user_id);
			 return prep.execute();
				  
			  }
			   catch (SQLException e) {e.printStackTrace(); System.out.println("Errore Query");}	
			   finally {
				   application.model.DAO.MySQLDAOFactory.closeDbConnection(res, prep, con);
			   }
			  
			  	return false;
	}

}
