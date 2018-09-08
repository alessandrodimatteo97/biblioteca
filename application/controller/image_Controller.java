package application.controller;
import java.util.TreeMap;

import application.model.DAO.DAOFactory;
import application.model.DAO.PageDAO;
import application.model.VO.image;

public class image_Controller {

	
	private DAOFactory mysqlfactory;
	private PageDAO pagedao;
	
	public image_Controller() {
	     
		mysqlfactory = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
		pagedao = mysqlfactory.getPageDAO();
				
	}
	
	public TreeMap<Integer, image> validationz(){	//
		return pagedao.validation();
	}
	
	public TreeMap<Integer, image> viewArtwork(int artworkID){	//MOSTRA LE PAGINE DI UN'OPERA
		return pagedao.viewPages(artworkID);
	}
	
	public boolean verficaImage(int arwork_id, String image_url) {		//VERIFICA IMMAGINI
		return pagedao.verImage(arwork_id, image_url);
	}
	
	public boolean validateImage(int imageid) {		//VALIDA L'IMMAGINE
		return pagedao.updateImageval(imageid);
	}
	
	public boolean setTrascriptionImage(int image_id, String trascription ) {	//ASSOCIA AD UN ID DI UN'IMMAGINE UN CAMPO DI TRANSCRIPTION
	return pagedao.updateTrascriptionI(image_id, trascription);
	}
	
	public TreeMap<Integer, image> getImageToTranscr(int idutente){		//OTTIENE L'IMMAGINE DA TRANSCRIVERE
		return pagedao.getImageTranscr(idutente);
	}
	
	public boolean insertImage(String imageUrl, int id) {		//INSERISCI UN'IMMAGINE
		return pagedao.insertImage(imageUrl, id);
	}
	
	
	
	
}
