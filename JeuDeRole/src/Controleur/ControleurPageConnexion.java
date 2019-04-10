package Controleur;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class ControleurPageConnexion extends ControleurFX{

	@FXML
	private Button commencer;
	@FXML
	private Button about;
	@FXML
	private TextField nomJoueur;
	
	@FXML
	public void clicBoutonCommencer() {
		if(!this.nomJoueur.getText().isEmpty()) {
			this.mainApp.showPage("principale");
		}else {
			this.mainApp.showPage("erreurSaisieNomJoueur");
		}
	}
}
