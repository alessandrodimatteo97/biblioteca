package application.model.DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.TreeMap;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;

import application.model.VO.image;

public class MySQLPageDAOImpl implements PageDAO {

	
	private Connection con = null;
	PreparedStatement prep = null;
	Statement stm = null;
	ResultSet res = null;
	
	private static final String IMAGE_NOTCONVALIDATE = " SELECT * FROM image WHERE validation = 0";
	private static final String IMAGE_VIEW = "SELECT * FROM image where artwork_id = ? ";
	private static final String VER_IMAGE = "SELECT * FROM image WHERE artwork_id = ? AND image_url = ?";
	private static final String VALIDATE_IMAGE = "UPDATE image SET validation=1 WHERE img_id =  ? ";
	private static final String	UPDATE_TRASCRIPTION = "UPDATE image SET transcription = ? WHERE img_id =  ? ";
	private static final String GET_IMGTRANSCR = " SELECT image.* FROM write_assignment, image WHERE image.img_id = image_id and user_id = ? and validation = 0";
	private static final String INSERT_IMAGE = "INSERT INTO image (image_url, artwork_id) values(?, ?)";
	@Override
	
	public TreeMap<Integer, image> validation() {
		int i=0;
		TreeMap<Integer,image> val = new TreeMap<Integer, image>();
		  try {
		  con = MySQLDAOFactory.createConnection();
		  prep = (PreparedStatement) con.prepareStatement(IMAGE_NOTCONVALIDATE);
		 
		  res = prep.executeQuery(); 
		  
		  while (res.next()) {
			  i = i + 1;
			  val.put(i, new image(res));
			 
		  }
		  
		  } catch (SQLException e) {e.printStackTrace(); System.out.println("Errore Query");}	
		   finally {
			   application.model.DAO.MySQLDAOFactory.closeDbConnection(res, prep, con);
		   }
		  
		  	return val;
	}

	@Override
	public TreeMap<Integer, image> viewPages(int artworkID) {
		int i=0;
		TreeMap<Integer,image> val = new TreeMap<Integer, image>();
		  try {
		  con = MySQLDAOFactory.createConnection();
		  prep = (PreparedStatement) con.prepareStatement(IMAGE_VIEW);
		 prep.setInt(1, artworkID);
		  res = prep.executeQuery(); 
		  
		  while (res.next()) {
			  i = i + 1;
			  val.put(i, new image(res));
			 
		  }
		  
		  } catch (SQLException e) {e.printStackTrace(); System.out.println("Errore Query");}	
		   finally {
			   application.model.DAO.MySQLDAOFactory.closeDbConnection(res, prep, con);
		   }
		  
		  	return val;	}

	@Override
	public boolean verImage(int artwork_id, String image_url) {
		try {
			 con = MySQLDAOFactory.createConnection();
			  prep = (PreparedStatement) con.prepareStatement(VER_IMAGE);
			 prep.setInt(1, artwork_id);
			 prep.setString(2, image_url);
			  res = prep.executeQuery(); 
			  
			  if (res.next()) {
				return true;
				 
			  }
			
		}
		catch(Exception e) {
			System.out.println("errore query");
		}
		finally {
			   application.model.DAO.MySQLDAOFactory.closeDbConnection(res, prep, con);

		}
		return false;
	}

	@Override
	public boolean updateImageval(int imageid) {
	try {
		 con = MySQLDAOFactory.createConnection();
		  prep = (PreparedStatement) con.prepareStatement(VALIDATE_IMAGE);
		 prep.setInt(1, imageid);
		   prep.executeUpdate(); 
		  
		 return true;
		
	}
	catch(Exception e) {
		System.out.println("errore query");
		return false;
	}
	finally {
		   application.model.DAO.MySQLDAOFactory.closeDbConnection(res, prep, con);

	}
	
	}

	@Override
	public boolean updateTrascriptionI(int imageid, String Tascription) {
		try {
			con = MySQLDAOFactory.createConnection();
			  prep = (PreparedStatement) con.prepareStatement(UPDATE_TRASCRIPTION);

			prep.setString(1, Tascription);
			prep.setInt(2, imageid);
			prep.execute();
			return true;
		}
		catch(Exception e){
			System.out.println(e);
			return false;
		}
		finally {
			   application.model.DAO.MySQLDAOFactory.closeDbConnection(res, prep, con);

		}
	}

	@Override
	public TreeMap<Integer, image> getImageTranscr(int idutente) {
		
		int i=0;
		TreeMap<Integer,image> tr = new TreeMap<Integer, image>();
		  try {
		  con = MySQLDAOFactory.createConnection();
		  prep = (PreparedStatement) con.prepareStatement(GET_IMGTRANSCR);
		 prep.setInt(1, idutente);
		  res = prep.executeQuery(); 
		  
		  while (res.next()) {
			  i = i + 1;
			  tr.put(i, new image(res));
			  System.out.println(res.getString(4));
			 
		  }
		  
		  } catch (SQLException e) {e.printStackTrace(); System.out.println("Errore Query");}	
		   finally {
			   application.model.DAO.MySQLDAOFactory.closeDbConnection(res, prep, con);
		   }
		  
		  	return tr;
	}
	
	public boolean insertImage(String imageUrl, int id) {
		 try {
			  con = MySQLDAOFactory.createConnection();
			  prep = (PreparedStatement) con.prepareStatement(INSERT_IMAGE);
			 prep.setString(1, imageUrl);
			 prep.setInt(2, id);
			  prep.execute(); 
			  return true;
			  }
			   catch (SQLException e) {
				  e.printStackTrace(); 
			  System.out.println("Errore Query");
			  }	
			   finally {
				   application.model.DAO.MySQLDAOFactory.closeDbConnection(res, prep, con);
			   }
			  
			  	return false;

	}
	
	
	
}
