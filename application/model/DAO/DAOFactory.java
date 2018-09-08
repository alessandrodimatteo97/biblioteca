package application.model.DAO;

public abstract class DAOFactory {
	
	public static final int MYSQL = 0;
	public static final int ORACLE = 1;
	
	public abstract UserDAO getUserDAO();
	
	public abstract ArtworkDAO getArtworkDAO();
	
	public abstract PageDAO getPageDAO();
	
	public abstract AssignmentDAO getAssignmentDAO();
	
	public static DAOFactory getDAOFactory(int database) {
		switch (database) {
		case MYSQL:
			return new MySQLDAOFactory();
		case ORACLE:
			return null;
		default:
			return null;
		}
	}

}