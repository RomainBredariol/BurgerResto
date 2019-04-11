package Controleur;

import MainApp.MainApp;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;

public class ControleurPagePrincipale extends ControleurFX{

	@FXML
	private Label nomJoueur;
	@FXML
	private ProgressBar vie;
	
	@Override
	public void setMainApp(MainApp main) {
		this.mainApp = main;
		this.nomJoueur.setText(this.mainApp.joueurEnJeu.getNom());
		this.vie.setProgress(this.mainApp.joueurEnJeu.getPV());
	}
}
