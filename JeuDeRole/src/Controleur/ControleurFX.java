package Controleur;

import MainApp.MainApp;
import Model.BDJoueur;
import Model.Joueur;
import javafx.stage.Stage;

public abstract class ControleurFX {
	
	protected MainApp mainApp;
	protected Stage fenetre;
	protected BDJoueur bdJoueur = BDJoueur.getInstance();
	
	
	public void setMainApp(MainApp main) {
		this.mainApp = main;
	}
	
	public void setFenetre(Stage fenetre) {
		this.fenetre = fenetre;
	}
}
