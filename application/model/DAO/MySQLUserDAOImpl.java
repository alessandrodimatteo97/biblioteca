package application.model.DAO;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;

import application.model.DAO.MySQLDAOFactory;
import application.model.VO.user;


public class MySQLUserDAOImpl implements UserDAO{
	private Connection con = null;
	PreparedStatement prep = null;
	Statement stm = null;
	ResultSet res = null;
	
	private static final String CORRECT_INPUT = "SELECT * FROM user WHERE email = ? AND password = ? ";
	private static final String EMAIL_ALREADY_EXISTS = "SELECT email FROM user WHERE email = ? ";
	private static final String GET_USER = "SELECT * FROM user WHERE email = ? and password = ? ";
	private static final String REGISTER_USER = "INSERT INTO user(email, password, name, surname, birth_date, residence, qualification, profession, fiscal_code, transcriber, uploader, manager, administrator, viewer, request, downloader) VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?, '0', '0', '0', '0', '1', '0', '0' " +" )";
    private static final String UPDATE_VIEWER = "UPDATE user SET request = 1 WHERE usr_id = ? ";
    private static final String SHOW_USER = "SELECT email FROM user where administrator != 1";
    private static final String DELETE_USER = "DELETE FROM user WHERE email = ? ";
    private static final String UPDATE_DOWNLOAD = "UPDATE user SET downloader = '1' WHERE email = ?";
    private static final String TRASCRIBER_UPDATE = "UPDATE user SET transcriber = ? WHERE email = ?";
    private static final String UPLOADER_UPDATE = "UPDATE user SET uploader = ? WHERE email = ?";
    private static final String VIEWER_UPDATE = "UPDATE user SET viewer = ? WHERE email = ?";
    private static final String MANAGER_UPDATE = "UPDATE user SET manager = ? WHERE email = ?";
    private static final String ADMIN_UPDATE = "UPDATE user SET administrator = ? WHERE email = ?";
    private static final String SHOW_ANAG = "SELECT * FROM user where email = ?";
    private static final String TRANSCRIBER_ASSIGNED = "SELECT usr_id, name, surname, transcriber FROM user, write_assignment WHERE transcriber >= 1 AND image_id= ? and write_assignment.user_id = user.usr_id;";  
    private static final String TRANSCRIBER_NOTASSIGNED = " SELECT *  FROM user LEFT JOIN write_assignment ON (user.usr_id = write_assignment.user_id and write_assignment.image_id = ? ) where  write_assignment.user_id is null and transcriber >= 1 " ;
	private static final String GET_TRANSCRIBER = "SELECT usr_id, name, transcriber FROM user WHERE transcriber >= 1;";
	private static final String LEVEL_TRASCRIBER = "UPDATE user SET transcriber = ? WHERE usr_id = ? "; 
;
    @Override
	public boolean CorrectLogInput(String email, String password) {
				
		
		  try {
		  con = MySQLDAOFactory.createConnection();
		  prep = (PreparedStatement) con.prepareStatement(CORRECT_INPUT);
		  prep.setString(1, email);
		  prep.setString(2,password);
		  res = prep.executeQuery(); 
		  
		  if (res.next()) {
			  return true;
			 // 
			  
		  }
		  
		  } catch (SQLException e) {e.printStackTrace(); System.out.println("Errore Query");}	
		   finally {
			   application.model.DAO.MySQLDAOFactory.closeDbConnection(res, prep, con);
		   }
		  
		  	return false;
    }
    
    
	
	public void registraUtente(String email, String password, String name, String surname, Date birth_date, String residence, String qualification, String profession, String fiscal_code) {
		
		try {
			  con = MySQLDAOFactory.createConnection();
			  prep = (PreparedStatement) con.prepareStatement(REGISTER_USER);
			  prep.setString(1, email);
			  prep.setString(2, password);
			  prep.setString(3, name);
			  prep.setString(4, surname);
			  prep.setDate(5, birth_date);
			  prep.setString(6, residence);
			  prep.setString(7, qualification);
			  prep.setString(8, profession);
			  prep.setString(9, fiscal_code);
			  prep.executeUpdate();
		} catch (SQLException e) {e.printStackTrace(); System.out.println("Problema registrazione utente nel DB");}
		finally {
			application.model.DAO.MySQLDAOFactory.closeDbConnection(res, prep, con);
		   }
		
	}
	
	


	@Override
	public void getUser(String email, String password) {
		try {
			  con = MySQLDAOFactory.createConnection();
			  prep = (PreparedStatement) con.prepareStatement(GET_USER);
			  prep.setString(1, email);
			  prep.setString(2, password);
			  res = prep.executeQuery(); 
			  
			  if (res.next()) {
				 user user1 = new user(res);
				 user a = user.getIstance();
				 a.setglobal(user1);
				  
			  }
			  
			  } catch (SQLException e) {e.printStackTrace(); System.out.println("Errore Query");}	
			   finally {
				   application.model.DAO.MySQLDAOFactory.closeDbConnection(res, prep, con);
			   }
			  
		
	}
	public boolean emailUsed(String email) {
		
		
		  try {
		  con = MySQLDAOFactory.createConnection();
		  prep = (PreparedStatement) con.prepareStatement(EMAIL_ALREADY_EXISTS);
		  prep.setString(1, email);
		 
		  res = prep.executeQuery(); 
		  
		  if (res.next()) {
			  return true;
			 
			  
		  }
		  
		  } catch (SQLException e) {e.printStackTrace(); System.out.println("Errore Query");}	
		   finally {
			   application.model.DAO.MySQLDAOFactory.closeDbConnection(res, prep, con);
		   }
		  
		  	return false;
  }






	@Override
	public void updateUserToTranscr(int id) {
		try {
			  con = MySQLDAOFactory.createConnection();
			  prep = (PreparedStatement) con.prepareStatement(UPDATE_VIEWER);
			  prep.setInt(1, id);
			 prep.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace(); 
			System.out.println("Problema per la rischiesta utente nel DB");
			}
		finally {
			application.model.DAO.MySQLDAOFactory.closeDbConnection(res, prep, con);
		   }
		
	}
	
	public List<String> showUser() {
		List<String> list = new ArrayList<String>();
		try {
			  con = MySQLDAOFactory.createConnection();
			  prep = (PreparedStatement) con.prepareStatement(SHOW_USER);
			  res = prep.executeQuery();
			  while(res.next()) {
				  list.add(res.getString(1));
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
	
	public boolean deleteUser(String email) {
		try {
			  con = MySQLDAOFactory.createConnection();
			  prep = (PreparedStatement) con.prepareStatement(DELETE_USER);
			  prep.setString(1, email);
			return prep.execute();
		} catch (SQLException e) {
			e.printStackTrace(); 
			System.out.println("Problema nel DB");
			return false;
			}
		finally {
			application.model.DAO.MySQLDAOFactory.closeDbConnection(res, prep, con);
		   }
		
		
	}

	public boolean updateDownload(String email) {
		try {
			con = MySQLDAOFactory.createConnection();
			prep = (PreparedStatement) con.prepareStatement(UPDATE_DOWNLOAD);
			prep.setString(1, email);
			return prep.execute();
		}
		catch(SQLException e) {
			e.printStackTrace(); 
			System.out.println("Problema nel DB");
			return false;
		}
		finally {
			application.model.DAO.MySQLDAOFactory.closeDbConnection(res, prep, con);
		}
	}
	



	@Override
	public void promOrRevViewer(String email, int ur) {
		try {
			con = MySQLDAOFactory.createConnection();
			prep = (PreparedStatement) con.prepareStatement(VIEWER_UPDATE);
	 		prep.setInt(1, ur);
	 		prep.setString(2, email);
	 		prep.executeUpdate();
			}
			catch(SQLException e) {
				e.printStackTrace(); 
				System.out.println("Problema nel DB");
				
			}
			finally {
				application.model.DAO.MySQLDAOFactory.closeDbConnection(res, prep, con);
			}
		
	}



	@Override
	public void promOrRevTrascr(String email, int ur) {
		try {
		con = MySQLDAOFactory.createConnection();
		prep = (PreparedStatement) con.prepareStatement(TRASCRIBER_UPDATE);
 		prep.setInt(1, ur);
 		prep.setString(2, email);
 		prep.executeUpdate();

		}
		catch(SQLException e) {
			e.printStackTrace(); 
			System.out.println("Problema nel DB");
			
		}
		finally {
			application.model.DAO.MySQLDAOFactory.closeDbConnection(res, prep, con);
		}
	}



	@Override
	public void promOrRevUpload(String email, int ur) {
		
		try {
			con = MySQLDAOFactory.createConnection();
			prep = (PreparedStatement) con.prepareStatement(UPLOADER_UPDATE);
	 		prep.setInt(1, ur);
	 		prep.setString(2, email);
	 		prep.executeUpdate();

			}
			catch(SQLException e) {
				e.printStackTrace(); 
				System.out.println("Problema nel DB");
				
			}
			finally {
				application.model.DAO.MySQLDAOFactory.closeDbConnection(res, prep, con);
			}
	}



	@Override
	public void promOrRevMan(String email, int ur) {
		try {
			con = MySQLDAOFactory.createConnection();
			prep = (PreparedStatement) con.prepareStatement(MANAGER_UPDATE);
	 		prep.setInt(1, ur);
	 		prep.setString(2, email);
	 		prep.executeUpdate();

			}
			catch(SQLException e) {
				e.printStackTrace(); 
				System.out.println("Problema nel DB");
				
			}
			finally {
				application.model.DAO.MySQLDAOFactory.closeDbConnection(res, prep, con);
			}
		
	}



	@Override
	public void promOrRevAdmin(String email, int ur) {
		try {
			con = MySQLDAOFactory.createConnection();
			prep = (PreparedStatement) con.prepareStatement(ADMIN_UPDATE);
	 		prep.setInt(1, ur);
	 		prep.setString(2, email);
	 		prep.executeUpdate();

			}
			catch(SQLException e) {
				e.printStackTrace(); 
				System.out.println("Problema nel DB");
				
			}
			finally {
				application.model.DAO.MySQLDAOFactory.closeDbConnection(res, prep, con);
			}
		
	}
	
	public user showInfo(String email) {
		try {
			con = MySQLDAOFactory.createConnection();
			prep = (PreparedStatement) con.prepareStatement(SHOW_ANAG);
			prep.setString(1, email);
			res = prep.executeQuery();
			res.next();
			return new user(res);
		}
		catch(SQLException e) {
			e.printStackTrace(); 
			System.out.println("Problema nel DB");
			
		}
		finally {
			application.model.DAO.MySQLDAOFactory.closeDbConnection(res, prep, con);
		}
		return null;
	}
	
	
	public List<String> assigned(int imageid) {
		List<String > list1 = new ArrayList<String>();
 
		try {
			con = MySQLDAOFactory.createConnection();
			prep = (PreparedStatement) con.prepareStatement(TRANSCRIBER_ASSIGNED);
			prep.setInt(1, imageid);
			res = prep.executeQuery();
			 while (res.next()) {
		        	System.out.println(res.getString("usr_id") +" "+res.getString("name") + " " + res.getString("surname") + res.getString("transcriber") + " assegnato");
		        	list1.add(res.getString("usr_id")+" "+res.getString("name") + " " + res.getString("surname") + " "+ res.getString("transcriber") + " assegnato");
		        }
		}
		catch(SQLException e) {
			e.printStackTrace(); 
			System.out.println("Problema nel DB");
			
		}
		finally {
			application.model.DAO.MySQLDAOFactory.closeDbConnection(res, prep, con);
		}
		return list1;
		
		
	}
	
	public List<String> notAssigned(int imageid) {
		List<String > list1 = new ArrayList<String>();
 
		try {
			con = MySQLDAOFactory.createConnection();
			prep = (PreparedStatement) con.prepareStatement(TRANSCRIBER_NOTASSIGNED);
			prep.setInt(1, imageid);
			res = prep.executeQuery();
			 while (res.next()) {
		        	System.out.println(res.getString("usr_id") +" "+res.getString("name") + " " + res.getString("surname") + res.getString("transcriber") + " non assegnato");
		        	list1.add(res.getString("usr_id")+" "+res.getString("name") + " " + res.getString("surname") + " "+ res.getString("transcriber") + "non assegnato");
		        }
		}
		catch(SQLException e) {
			e.printStackTrace(); 
			System.out.println("Problema nel DB");
			
		}
		finally {
			application.model.DAO.MySQLDAOFactory.closeDbConnection(res, prep, con);
		}
		return list1;
		
		
	}
	
	public List<String> getAllTranscriber(){
		List<String> list = new ArrayList<String>();
       
		try {
			con = MySQLDAOFactory.createConnection();
			prep = (PreparedStatement) con.prepareStatement(GET_TRANSCRIBER);
			res = prep.executeQuery();
          
           
            while (res.next()) {
            	
            	list.add(res.getString(1) + " " + res.getString(2)+ " " + res.getString(3));;
            	
            }
            
            
		}
         catch (Exception e) {
        	 System.out.println(e);
        }
		return list;
	}
	
	public void	setTranscriber(int userid, int lev) {
		try {
			con = MySQLDAOFactory.createConnection();
			prep = (PreparedStatement) con.prepareStatement(LEVEL_TRASCRIBER);
	 		prep.setInt(1, lev);
	 		prep.setInt(2, userid);
	 	    prep.executeUpdate();
	 		
			}
			catch(SQLException e) {
				e.printStackTrace(); 
				System.out.println("Problema nel DB");
				
			}
			finally {
				application.model.DAO.MySQLDAOFactory.closeDbConnection(res, prep, con);
			}
	}



	


}
