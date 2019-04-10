package Controleur;

import MainApp.MainApp;
import javafx.stage.Stage;

public abstract class ControleurFX {
	
	public MainApp mainApp;
	public Stage fenetre;
	
	public void setMainApp(MainApp main) {
		this.mainApp = main;
	}
	
	public void setFenetre(Stage fenetre) {
		this.fenetre = fenetre;
	}
}
