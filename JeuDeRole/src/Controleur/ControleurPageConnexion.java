package Controleur;

import Model.Joueur;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class ControleurPageConnexion extends ControleurFX {

	@FXML
	private Button commencer;
	@FXML
	private Button about;
	@FXML
	private TextField nomJoueur;

	@FXML
	public void clicBoutonCommencer() {
		if (!this.nomJoueur.getText().isEmpty()) {

			if (!super.bdJoueur.isJoueur(this.nomJoueur.getText())) {
				Joueur newJoueur = new Joueur(this.nomJoueur.getText());
				super.bdJoueur.ajouterJoueur(newJoueur);
			}
			
			this.mainApp.joueurEnJeu = super.bdJoueur.getJoueur(this.nomJoueur.getText());
			this.mainApp.showPage("principale");
			
		} else {
			this.mainApp.showPagePopUp("erreurSaisieNomJoueur");
		}
	}
	
	@FXML
	public void clicBoutonAbout() {
		this.mainApp.showPage("about");
	}

}
