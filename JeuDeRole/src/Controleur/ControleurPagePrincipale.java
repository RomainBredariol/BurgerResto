package Controleur;

import java.util.HashMap;

import MainApp.MainApp;
import Model.Objet;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

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
	@FXML
	private VBox dialogue;
	@FXML
	private VBox sacVbox;
	
	@Override
	public void setMainApp(MainApp main) {
		this.mainApp = main;
		setAffichageJoueur();
		setAffichageCarte();
		setAffichageSac();
	}

	
	private void setAffichageSac() {
		HashMap<Integer, Objet> sac = this.mainApp.joueurEnJeu.getSac();
		int indexSac = 0;
		
		while(sac.containsKey(indexSac)) {
			HBox ligneSac = new HBox();
						
			Label nomObjet = new Label(sac.get(indexSac).getNom());
			Label valeurObjet = new Label(String.valueOf(sac.get(indexSac).getValeur()));
			Button boutonUtilisation = new Button("Utiliser");
		
			ligneSac.getChildren().add(nomObjet);
			ligneSac.getChildren().add(valeurObjet);
			ligneSac.getChildren().add(boutonUtilisation);
			
			
			this.sacVbox.getChildren().add(ligneSac);
			indexSac++;
		}
	}
	// écris dans la fenetre de dialogue le texte passé en paramétre
	public void ecrireDialogue(String texte) {
		dialogue.getChildren().add(new Label(texte));
		
	}
	
	

	private void setAffichageJoueur() {
		// set des infos du joueur
		this.nomJoueur.setText(this.mainApp.joueurEnJeu.getNom());
		this.vie.setProgress(this.mainApp.joueurEnJeu.getPV());
		this.or.setText(String.valueOf(this.mainApp.joueurEnJeu.getOR()));
		this.nomArme.setText(this.mainApp.joueurEnJeu.getArme().getNom());
		this.nomArmure.setText(this.mainApp.joueurEnJeu.getArmure().getNom());
	}

	private void setAffichageCarte() {
		// set de la map
		for (int colonne = 0; colonne < 9; colonne++) {
			for (int ligne = 0; ligne < 9; ligne++) {
				Pane pane = new Pane();
				ImageView image;

				if (colonne == 0 && ligne == 0) {
					image = new ImageView("/Controleur/icon/caseEntre.png");
				} else {
					image = new ImageView("/Controleur/icon/caseCache.png");
				}

				image.setFitHeight(28);
				image.setFitWidth(28);

				pane.getChildren().add(image);
				pane.getChildren().get(0).setLayoutX(10);
				this.carte.add(pane, colonne, ligne);
			}
		}
	}

	public void majCarte(int etape) {
		int ligne = this.mainApp.joueurEnJeu.getEmplacementLigne();
		int colonne = this.mainApp.joueurEnJeu.getEmplacementColonne();

		int index = ligne + (colonne * 9) + 1;
		
		switch (etape) {
		case 1:
			//Supression image
			if(index != 1) {
				Pane pane = (Pane) this.carte.getChildren().get(index);
				pane.getChildren().remove(1);
			}
				
			break;
		case 2:
			//Remplacement image
			if (index != 1) {
							
				Pane pane = (Pane) this.carte.getChildren().get(index);

				ImageView imageCaseVide = new ImageView("/Controleur/icon/case.png");
				imageCaseVide.setFitHeight(28);
				imageCaseVide.setFitWidth(28);

				ImageView dot = new ImageView("/Controleur/icon/dot.png");
				dot.setFitWidth(11);
				dot.setFitHeight(12);

				pane.getChildren().remove(0);
				pane.getChildren().add(0, imageCaseVide);
				pane.getChildren().get(0).setLayoutX(10);
				
				pane.getChildren().add(1, dot);
				pane.getChildren().get(1).setLayoutX(18);
				pane.getChildren().get(1).setLayoutY(8);
				
			}
			break;
		default:
			System.out.println("Erreur");
			break;
		}
	}

	@FXML
	public void clicHaut() {
		int ligne = this.mainApp.joueurEnJeu.getEmplacementLigne();

		if (ligne != 0) {
			majCarte(1);
			this.mainApp.joueurEnJeu.setEmplacementLigne(ligne - 1);
			majCarte(2);
		}
		ecrireDialogue("putepute");
	}

	@FXML
	public void clicBas() {
		int ligne = this.mainApp.joueurEnJeu.getEmplacementLigne();

		if (ligne != 8) {
			majCarte(1);
			this.mainApp.joueurEnJeu.setEmplacementLigne(ligne + 1);
			majCarte(2);
		}
	}

	@FXML
	public void clicGauche() {
		int colonne = this.mainApp.joueurEnJeu.getEmplacementColonne();

		if (colonne != 0) {
			majCarte(1);
			this.mainApp.joueurEnJeu.setEmplacementColonne(colonne - 1);
			majCarte(2);
		}
	}

	@FXML
	public void clicDroite() {
		int colonne = this.mainApp.joueurEnJeu.getEmplacementColonne();

		if (colonne != 8) {
			majCarte(1);
			this.mainApp.joueurEnJeu.setEmplacementColonne(colonne + 1);
			majCarte(2);
		}
	}
	
}
