package Controleur;

import javafx.fxml.FXML;

public class ControleurPageBoutique extends ControleurFX {

	@FXML
	public void retourAuJeu() {
		this.mainApp.showPage("principale");
	}
}
