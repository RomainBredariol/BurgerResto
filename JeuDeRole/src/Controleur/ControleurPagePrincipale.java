package Controleur;

import MainApp.MainApp;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

public class ControleurPagePrincipale extends ControleurFX {

	@FXML
	private Label nomJoueur;
	@FXML
	private ProgressBar vie;
	@FXML
	private Label nomArme;
	@FXML
	private Label nomArmure;
	@FXML
	private Label or;
	@FXML
	private GridPane carte;

	@Override
	public void setMainApp(MainApp main) {
		this.mainApp = main;
		setAffichageJoueur();
		setAffichageCarte();
	}

	private void setAffichageJoueur() {
		// set des infos du joueur
		this.nomJoueur.setText(this.mainApp.joueurEnJeu.getNom());
		this.vie.setProgress(this.mainApp.joueurEnJeu.getPV());
		this.or.setText(String.valueOf(this.mainApp.joueurEnJeu.getOR()));
		// this.nomArme.setText(this.mainApp.joueurEnJeu.getArme().getNom());
		// this.nomArmure.setText(this.mainApp.joueurEnJeu.getArmure().getNom());
	}

	private void setAffichageCarte() {
		// set de la map
		for (int colonne = 0; colonne < 9; colonne++) {
			for (int ligne = 0; ligne < 9; ligne++) {
				ImageView image;

				if (colonne == 0 && ligne == 0) {
					image = new ImageView("/Controleur/icon/caseEntre.png");
				} else {
					image = new ImageView("/Controleur/icon/caseCache.png");
				}

				image.setFitHeight(28);
				image.setFitWidth(28);
				
				this.carte.setMargin(image, new Insets(10));
				this.carte.add(image, colonne, ligne);
			}
		}
	}

	public void majCarte() {
		int ligne = this.mainApp.joueurEnJeu.getEmplacementLigne();
		int colonne = this.mainApp.joueurEnJeu.getEmplacementColonne();

		this.carte.getChildren().remove(0);
		this.carte.getChildren().add(0, new ImageView("/Controleur/icon/case.png"));
	}

	@FXML
	public void clicHaut() {
		int ligne = this.mainApp.joueurEnJeu.getEmplacementLigne();
		// TODO supprimer l'icon man sur la case
		if (ligne != 0) {
			this.mainApp.joueurEnJeu.setEmplacementLigne(ligne + 1);
		}
	}

	@FXML
	public void clicBas() {
		int ligne = this.mainApp.joueurEnJeu.getEmplacementLigne();
		// TODO supprimer l'icon man sur la case
		if (ligne != 8) {
			this.mainApp.joueurEnJeu.setEmplacementLigne(ligne - 1);
		}
	}

	@FXML
	public void clicGauche() {
		int colonne = this.mainApp.joueurEnJeu.getEmplacementColonne();
		// TODO supprimer l'icon man sur la case
		if (colonne != 8) {
			this.mainApp.joueurEnJeu.setEmplacementLigne(colonne - 1);
		}
	}

	@FXML
	public void clicDroite() {
		int colonne = this.mainApp.joueurEnJeu.getEmplacementColonne();
		// TODO supprimer l'icon man sur la case
		if (colonne != 0) {
			this.mainApp.joueurEnJeu.setEmplacementLigne(colonne + 1);
		}
	}

}
