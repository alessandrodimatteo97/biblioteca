package application.model.DAO;


import java.sql.Date;
import java.util.List;
import application.model.VO.user;



public interface UserDAO {
	
	public boolean CorrectLogInput(String us,String pass);
	
	public void registraUtente(String email, String password, String name, String surname, Date birth_date, String residence, String qualification, String profession, String fiscal_code) ;
		
	public void getUser(String email, String password) ;
	
	public boolean emailUsed(String email);
	
	public void updateUserToTranscr(int id);
	
	public List<String> showUser();
	
	public boolean deleteUser(String email);
	
	public boolean updateDownload(String email);
		
	public void promOrRevViewer(String email, int ur);
	
	public void promOrRevTrascr(String email, int ur);
	
	public void promOrRevUpload(String email, int ur);
	
	public void promOrRevMan(String email, int ur);
	
	public void promOrRevAdmin(String email, int ur);

	public user showInfo(String email);
	
	public List<String> assigned(int imageid);
	
	public List<String> notAssigned(int imageid);
	
	public List<String> getAllTranscriber();
	
	public void	setTranscriber(int userid, int lev);
}


