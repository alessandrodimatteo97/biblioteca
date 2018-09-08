package application.model.DAO;

public interface AssignmentDAO {

	public boolean insertAssignToPage(int image_id, int user_id);
	
	public boolean deleteAssignToPage(int image_id, int user_id);
}
