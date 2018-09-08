package application.model.VO;

import java.sql.ResultSet;
import java.sql.SQLException;

public class assignment {
	private int w_a_id, image_id, user_id;

	
	public assignment() {}
	
	public assignment(int w_a_id, int image_id, int user_id) {
		this.w_a_id = w_a_id;
		this.image_id = image_id;
		this.user_id = user_id;
	}
	
	public assignment(ResultSet resultSet) throws SQLException {
		this.setW_a_id(resultSet.getInt("art_id"));
		this.setImage_id(resultSet.getInt("image_id"));
		this.setUser_id(resultSet.getInt("user_id"));
	}
	
	public int getImage_id() {
		return image_id;
	}
	
	public void setImage_id(int image_id) {
		this.image_id = image_id;
	}
	
	public int getUser_id() {
		return user_id;
	}
	
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	
	public int getW_a_id() {
		return w_a_id;
	}
	
	public void setW_a_id(int w_a_id) {
		this.w_a_id = w_a_id;
	}
}
