package Controleur;

import MainApp.MainApp;
import Model.BDJoueur;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public abstract class ControleurFX {
	
	protected MainApp mainApp;
	protected Stage fenetre;
	protected BDJoueur bdJoueur = BDJoueur.getInstance();
	
	@FXML
	protected Label menu;
	
	@FXML
	public void retourPageConnexion() {
		this.mainApp.showPage("connexion");
	}
	
	public void setMainApp(MainApp main) {
		this.mainApp = main;
	}
	
	public void setFenetre(Stage fenetre) {
		this.fenetre = fenetre;
	}
}
