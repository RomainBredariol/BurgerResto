package Controleur;

import javafx.fxml.FXML;

public class ControleurEntrerBoutique extends ControleurFX {
	
	@FXML
	public void clicBoutonSortir() {
		this.fenetre.close();
	}
	
	@FXML
	public void clicBoutonEntrer() {
		this.fenetre.close();
		this.mainApp.showPage("boutique");
	}
}
