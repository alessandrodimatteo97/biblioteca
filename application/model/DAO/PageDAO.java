package application.model.DAO;

import java.util.TreeMap;

import application.model.VO.image;

public interface PageDAO {

	public  TreeMap<Integer, image> validation();
	
	public TreeMap<Integer, image> viewPages(int artworkID);
	
	public boolean verImage(int artwork_id, String image_url);
	
	public boolean updateImageval(int imageid);
	
	public boolean updateTrascriptionI(int imageid, String Tascription);
	
	public TreeMap<Integer, image> getImageTranscr(int idutente);
	
	public boolean insertImage(String imageUrl, int id);
}
