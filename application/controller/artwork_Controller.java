package application.controller;

import java.util.List;

import application.model.DAO.ArtworkDAO;
import application.model.DAO.DAOFactory;
import application.model.VO.artwork;

public class artwork_Controller {

	private DAOFactory mysqlfactory;
	private ArtworkDAO artworkdao;
	
	public artwork_Controller() {
	     
		mysqlfactory = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
		artworkdao = mysqlfactory.getArtworkDAO();
				
	}
	
	public boolean getInfoArtwork(String isbn, String title){   //MOSTRA LE INFORMAZIONI DI UN'OPERA
		return artworkdao.infoArtwork(isbn, title);
	}
	
	public List<String> getCategory(){		//MOSTRA LE CATEGORIE PRESENTI NEL DATABASE
		return artworkdao.getCategory();
	}
	
	//INSERISCI UNA NUOVA OPERA
	public boolean insertArtwork(String isbn, String title, String description, String language, int year, int catId) {
		return artworkdao.insertArtwork(isbn, title, description, language, year, catId);
	}
	
	public int selectIdArt(String isbn, String title) {   //DATI ISBN E TITOLO RESTITUISCE L'ID DELL'OPERA
		return artworkdao.selectIdArtwork(isbn, title);
	}
	
	public artwork selectArt(String isbn, String title) {	//DATI L'ISBN E IL TITOLO DI UN'OPERA LA RESTITUISCE
		return artworkdao.selectArt(isbn, title);
	}

}
