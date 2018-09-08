package application.model.DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

import application.model.VO.artwork;


public class MySQLArtworkDAOImpl implements ArtworkDAO {
	
	private Connection con = null;
	private PreparedStatement prep = null;
	private ResultSet res = null;
	
	
	private final String SELECT_INFO_ARTBYISBNORTITLE = "SELECT * FROM artwork WHERE isbn = ? OR title = ?";
	private final String INSERT_ARTWORK = "INSERT INTO artwork (isbn, title, description, language, year, cat_id) values(?, ?, ?, ?, ?, ?)";
	private final String CATEGORY = "SELECT * FROM category";
	
	@Override
	 public boolean insertArtwork(String isbn, String title, String description, String language, int year, int catId ) {
		try {
			 con = MySQLDAOFactory.createConnection();
			  prep = (PreparedStatement) con.prepareStatement(INSERT_ARTWORK);
			  prep.setString(1, isbn);
			  prep.setString(2, title);
			  prep.setString(3, description);
			  prep.setString(4, language);
			  prep.setInt(5, year);
			  prep.setInt(6, catId);
			  return prep.execute();
			
		}
		catch(Exception e ) {
			System.out.println(e);
		}
		return false;
	}
	@Override
	public boolean infoArtwork(String isbn, String title) {
		try {
			  con = MySQLDAOFactory.createConnection();
			  prep = (PreparedStatement) con.prepareStatement(SELECT_INFO_ARTBYISBNORTITLE);
			  prep.setString(1, isbn);
			  prep.setString(2, title);
			  res = prep.executeQuery();
			  if(res.next()) {
				  return true;
			  }
	}
		catch (SQLException e) {
		e.printStackTrace(); 
		System.out.println("Problema nel DB");
		}
	finally {
		application.model.DAO.MySQLDAOFactory.closeDbConnection(res, prep, con);
	   }
		return false;

	}
	
	public List<String> getCategory(){
		List<String> list = new ArrayList<String>();
		try {
			  con = MySQLDAOFactory.createConnection();
			  prep = (PreparedStatement) con.prepareStatement(CATEGORY);
			 
			  res = prep.executeQuery();
			  while(res.next()) {
				  list.add(res.getString(1) +" " + res.getString(2) );
			  }
	}
		catch (SQLException e) {
		e.printStackTrace(); 
		System.out.println("Problema nel DB");
		}
	finally {
		application.model.DAO.MySQLDAOFactory.closeDbConnection(res, prep, con);
	   }
		return list;
	}
	@Override
	public int selectIdArtwork(String isbn, String title) {
		try {
			  con = MySQLDAOFactory.createConnection();
			  prep = (PreparedStatement) con.prepareStatement(SELECT_INFO_ARTBYISBNORTITLE);
			  prep.setString(1, isbn);
			  prep.setString(2, title);
			  res = prep.executeQuery();
			  if(res.next()) {
				  return res.getInt(1);
			  }
	}
		catch (SQLException e) {
		e.printStackTrace(); 
		System.out.println("Problema nel DB");
		}
	finally {
		application.model.DAO.MySQLDAOFactory.closeDbConnection(res, prep, con);
	   }
		return 0;
	}
	@Override
	public artwork selectArt(String isbn, String title) {
		try {
			  con = MySQLDAOFactory.createConnection();
			  prep = (PreparedStatement) con.prepareStatement(SELECT_INFO_ARTBYISBNORTITLE);
			  prep.setString(1, isbn);
			  prep.setString(2, title);
			  res = prep.executeQuery();
			  if(res.next()) {
				  return new artwork(res);
			  }
	}
		catch (SQLException e) {
		e.printStackTrace(); 
		System.out.println("Problema nel DB");
		}
	finally {
		application.model.DAO.MySQLDAOFactory.closeDbConnection(res, prep, con);
	   }
		return null;
		}
	
	
	
	

}
