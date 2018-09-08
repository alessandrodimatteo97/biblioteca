package application.model.DAO;

	import java.util.List;

import application.model.VO.artwork;

	public interface ArtworkDAO {
				 
		 public boolean insertArtwork(String isbn, String title, String description, String language, int year, int catId );
		 

		 public boolean infoArtwork(String isbn, String title);
		 
		 public List<String > getCategory();
		 
		 public int selectIdArtwork(String isbn, String title);
		 
		 public artwork selectArt(String isbn, String title);
	}
