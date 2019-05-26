package Controleur;

import java.util.HashMap;

import MainApp.MainApp;
import Model.Arene;
import Model.Arme;
import Model.Armure;
import Model.Carte;
import Model.Combat;
import Model.Objet;
import Model.Salle;
import Model.Salle.enumDescription;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
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
	private TextArea dialogue;
	@FXML
	private VBox sacVbox;
	@FXML
	private Button boutonAttaquer, boutonFuir;
	@FXML
	private Button boutonHaut, boutonGauche, boutonDroite, boutonBas;
	@FXML
	private Label pvtexte;
	@FXML
	private Label nomMonstre;
	
	private Combat combatEnCours;
	private Salle salleEnCours;
	private String logsDialogue = "";
	private enum directionClic {GAUCHE, DROITE, HAUT, BAS};
	private directionClic dernierClic;

	@Override
	public void setMainApp(MainApp main) {
		this.mainApp = main;
		setAffichageJoueur();
		setAffichageCarte();
		setAffichageSac();
	}

	// on affiche l'ensemble du sac
	private void setAffichageSac() {
		this.sacVbox.getChildren().clear();
		HashMap<String, Objet> sac = this.mainApp.joueurEnJeu.getSac();

		for (Objet obj : sac.values()) {
			HBox ligneSac = new HBox();

			// nom de l'objet
			Label nomObjet = new Label(obj.getNom());
			nomObjet.setPrefSize(86, 20);
			nomObjet.setAlignment(Pos.CENTER);

			// sa valeur
			Label valeurObjet = new Label(String.valueOf(obj.getValeur()));
			valeurObjet.setPrefSize(86, 20);
			valeurObjet.setAlignment(Pos.CENTER);

			// bouton utiliser
			Button boutonUtilisation = new Button("Utiliser");
			boutonUtilisation.setPrefSize(86, 20);
			boutonUtilisation.setAlignment(Pos.CENTER);
			boutonUtilisation.setOnAction(e -> clicUtiliserObjet(nomObjet.getText()));

			// on ajoute les elements au hbox
			ligneSac.getChildren().add(nomObjet);
			ligneSac.getChildren().add(valeurObjet);
			ligneSac.getChildren().add(boutonUtilisation);

			// on ajoute le hbox à la vbox
			this.sacVbox.getChildren().add(ligneSac);
		}
	}

	
	public void clicUtiliserObjet(String nomObjet) {
		HashMap<String, Objet> sac = this.mainApp.joueurEnJeu.getSac();

		utiliserObjet(sac.get(nomObjet));
	}

	//utilise un objet contenu dans le sac
	public void utiliserObjet(Objet obj) {
		switch (obj.getClass().getSimpleName()) {
		case "Arme":
			//equipe l'arme et supprime l'objet du sac
			this.mainApp.joueurEnJeu.setArme((Arme) obj);
			this.mainApp.joueurEnJeu.supprimerObjetSac(obj);
			break;
		case "Armure":
			//equipe l'armure et supprime l'objet du sac
			this.mainApp.joueurEnJeu.setArmure((Armure) obj);
			this.mainApp.joueurEnJeu.supprimerObjetSac(obj);
			break;
		case "Objet":
			//consomme l'objet
			if((this.mainApp.joueurEnJeu.getPV()+obj.getValeur()) <= 20 ) {
				this.mainApp.joueurEnJeu.potion(obj.getValeur());
				this.mainApp.joueurEnJeu.supprimerObjetSac(obj);
			}
			break;
		default:
			break;
		}
		//met a jour les differents affichage
		setAffichageJoueur();
		setAffichageSac();
	}

	// écris dans la fenetre de dialogue le texte passé en paramétre
	public void ecrireDialogue(String texte) {
		// formater le texte pour qu'il ne dépasse pas la taille
		System.out.println(texte);
		int postCurseur = 0;
		int curseur = 0;
		int facteur = 1;
		while (texte.indexOf(" ",curseur+1) >= 0) {
			curseur = texte.indexOf(" ",curseur+1);
			if (curseur > 60 * facteur) {
				texte = texte.substring(0, postCurseur)+"\n-"+texte.substring(postCurseur,texte.length());
				facteur++;
			}
			postCurseur = curseur;			
		}
		// ecrire
		this.dialogue.appendText("\n"+texte+"\n");
	}

	//affiche les donnees du joueur
	private void setAffichageJoueur() {
		// set des infos du joueur
		this.nomJoueur.setText(this.mainApp.joueurEnJeu.getNom());
		this.vie.setProgress(this.mainApp.joueurEnJeu.getPV());
		this.or.setText(String.valueOf(this.mainApp.joueurEnJeu.getOR()));
		this.nomArme.setText(this.mainApp.joueurEnJeu.getArme().getNom() + " ("
				+ this.mainApp.joueurEnJeu.getArme().getDegats() + ")");
		// mettre à jour les PV
		this.pvtexte.setText(this.mainApp.joueurEnJeu.getPV() + "/" + this.mainApp.joueurEnJeu.getPVMax());
		this.vie.setProgress((float) this.mainApp.joueurEnJeu.getPV() / (float) this.mainApp.joueurEnJeu.getPVMax());
		// mettre à jour la vie de l'armure
		this.nomArmure.setText(
				this.mainApp.joueurEnJeu.getArmure().getNom() + " (" + this.mainApp.joueurEnJeu.getArmure().getDefense()
						+ "/" + this.mainApp.joueurEnJeu.getArmure().getDefenseMax() + ")");
	}

	//initialise la carte
	private void setAffichageCarte() {
		// set du monstre
		this.nomMonstre.setText("Vous n'étes pas en combat");
		
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
			// Supression image
			if (index != 1 && index != 10) {
				Pane pane = (Pane) this.carte.getChildren().get(index);
				pane.getChildren().remove(1);
			}

			break;
		case 2:
			// Remplacement image par le point
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
		case 3:
			// Remplacement image par la boutique
			Pane pane = (Pane) this.carte.getChildren().get(index);

			ImageView shop = new ImageView("/Controleur/icon/shop.png");
			shop.setFitHeight(28);
			shop.setFitWidth(28);

			pane.getChildren().remove(0);
			pane.getChildren().add(0, shop);
			pane.getChildren().get(0).setLayoutX(10);

			break;
		default:
			System.out.println("Erreur");
			break;
		}
	}

	@FXML
	public void clicHaut() {
		this.dernierClic = directionClic.HAUT;
		// récuperer ligne et colonne actuel du joueur
		int ligne = this.mainApp.joueurEnJeu.getEmplacementLigne();
		int colonne = this.mainApp.joueurEnJeu.getEmplacementColonne();

		// si le joueur n'est pas déjà dans la 1er ligne et que la future salle n'est
		// pas un mur
		// alors mettre à jour la carte, l'emplacement du joueur et résoudre les effets
		// de la salle.
		if (ligne != 0) {
			// récuperer la salle vers lequel le joueur désire aller
			Salle salle = recupererSalle(colonne, ligne - 1);
			if (salle.getDescription() != enumDescription.MUR) {
				majCarte(1);

				this.mainApp.joueurEnJeu.setEmplacementLigne(ligne - 1);

				// Si la salle est une boutique
				if (salle.getDescription() == enumDescription.BOUTIQUE) {
					// entre dans la boutique
					entrerDansBoutqiue();
					majCarte(3);
				} else {
					majCarte(2);
				}

				if (!salle.estDejaVisitee()) {
					resoudreSalle(salle);
				}
			}
		}
	}

	@FXML
	public void clicBas() {
		this.dernierClic = directionClic.BAS;
		// récuperer ligne et colonne actuel du joueur
		int ligne = this.mainApp.joueurEnJeu.getEmplacementLigne();
		int colonne = this.mainApp.joueurEnJeu.getEmplacementColonne();

		// si le joueur n'est pas déjà dans la derniere ligne et que la future salle
		// n'est pas un mur
		// alors mettre à jour la carte, l'emplacement du joueur et résoudre les effets
		// de la salle.
		if (ligne != 8) {
			// récuperer la salle vers lequel le joueur désire aller
			Salle salle = recupererSalle(colonne, ligne + 1);
			if (salle.getDescription() != enumDescription.MUR) {
				majCarte(1);

				this.mainApp.joueurEnJeu.setEmplacementLigne(ligne + 1);

				// Si la salle est une boutique
				if (salle.getDescription() == enumDescription.BOUTIQUE) {
					// entre dans la boutique
					entrerDansBoutqiue();
					majCarte(3);
				} else {
					majCarte(2);
				}

				if (!salle.estDejaVisitee()) {
					resoudreSalle(salle);
				}
			}
		}
	}

	@FXML
	public void clicGauche() {
		this.dernierClic = directionClic.GAUCHE;
		// récuperer ligne et colonne actuel du joueur
		int colonne = this.mainApp.joueurEnJeu.getEmplacementColonne();
		int ligne = this.mainApp.joueurEnJeu.getEmplacementLigne();

		// si le joueur n'est pas déjà dans la colonne de gauche et que la future salle
		// n'est pas un mur
		// alors mettre à jour la carte, l'emplacement du joueur et résoudre les effets
		// de la salle.
		if (colonne != 0) {
			// récuperer la salle vers lequel le joueur désire aller
			Salle salle = recupererSalle(colonne - 1, ligne);
			if (salle.getDescription() != enumDescription.MUR) {
				majCarte(1);

				this.mainApp.joueurEnJeu.setEmplacementColonne(colonne - 1);

				// Si la salle est une boutique
				if (salle.getDescription() == enumDescription.BOUTIQUE) {
					// entre dans la boutique
					entrerDansBoutqiue();
					majCarte(3);
				} else {
					majCarte(2);
				}

				if (!salle.estDejaVisitee()) {
					resoudreSalle(salle);
				}
			}
		}
	}

	@FXML
	public void clicDroite() {
		this.dernierClic = directionClic.DROITE;
		// récuperer ligne et colonne actuel du joueur
		int colonne = this.mainApp.joueurEnJeu.getEmplacementColonne();
		int ligne = this.mainApp.joueurEnJeu.getEmplacementLigne();

		// si le joueur n'est pas déjà dans la colonne de droite et que la future salle
		// n'est pas un mur
		// alors mettre à jour la carte, l'emplacement du joueur et résoudre les effets
		// de la salle.
		if (colonne != 8) {
			// récuperer la salle vers lequel le joueur désire aller
			Salle salle = recupererSalle(colonne + 1, ligne);
			if (salle.getDescription() != enumDescription.MUR) {
				majCarte(1);

				this.mainApp.joueurEnJeu.setEmplacementColonne(colonne + 1);

				// Si la salle est une boutique
				if (salle.getDescription() == enumDescription.BOUTIQUE) {
					// entre dans la boutique
					entrerDansBoutqiue();
					majCarte(3);
				} else {
					majCarte(2);
				}

				if (!salle.estDejaVisitee()) {
					resoudreSalle(salle);
				}
			}
		}
	}

	private void entrerDansBoutqiue() {
		this.mainApp.showPagePopUp("entrerBoutique");
	}

	// resous les effets de la salle (combat, texte ...)
	private void resoudreSalle(Salle salle) {
		this.salleEnCours = salle;
		// si la salle est une salle vide, lire sa description et passer la salle en
		// état visité
		if (salle.getDescription() == enumDescription.SALLE) {
			ecrireDialogue(salle.getTexte());
			salle.visiterSalle();
		}
		// si la salle est une arene, résoudre le combat
		if (salle.getDescription() == enumDescription.ARENE) {
			ecrireDialogue(salle.getTexte());
			// lancer le combat
			Arene arene = (Arene) salle;
			combatEnCours = new Combat(this.mainApp.joueurEnJeu, arene.getMonstre());
			// placer le nom du monstre 
			this.nomMonstre.setText("Combat contre "+arene.getMonstre().getNom());
			// rendre accessible les bouton d'attaque et de fuite
			boutonAttaquer.setDisable(false);
			boutonFuir.setDisable(false);
			// rendre innaccessible les boutons de déplacement
			boutonHaut.setDisable(true);
			boutonGauche.setDisable(true);
			boutonDroite.setDisable(true);
			boutonBas.setDisable(true);
		}
		if (salle.getDescription() == enumDescription.BOUTIQUE) {
			ecrireDialogue(salle.getTexte());
		}
	}

	// un tour de combat si le joueur clic sur attaquer
	@FXML
	public void clickAttaquer() {
		ecrireDialogue(combatEnCours.continuer());

		// si le combat est terminé activer les boutons de déplacement, desactiver
		// les boutons de combat
		// mettre la salle en visité
		// enlever le nom du monstre en cours de combat
		if (combatEnCours.estTermine()) {
			boutonAttaquer.setDisable(true);
			boutonFuir.setDisable(true);
			boutonGauche.setDisable(false);
			boutonHaut.setDisable(false);
			boutonDroite.setDisable(false);
			boutonBas.setDisable(false);
			this.salleEnCours.visiterSalle();
			this.nomMonstre.setText("Vous n'étes pas en combat");
			
		}
		setAffichageJoueur();
	}

	// le joueur fuis le combat
	@FXML
	public void clickFuir() {
		ecrireDialogue(combatEnCours.fuir());
		// activer les boutons de déplacement, desactiver les boutons de combat
		boutonAttaquer.setDisable(true);
		boutonFuir.setDisable(true);
		boutonGauche.setDisable(false);
		boutonHaut.setDisable(false);
		boutonDroite.setDisable(false);
		boutonBas.setDisable(false);
		this.nomMonstre.setText("Vous n'étes pas en combat");
		switch (this.dernierClic) {
		case HAUT:
			clicBas();
			break;
		case BAS:
			clicHaut();
			break;
		case GAUCHE:
			clicDroite();
			break;
		case DROITE:
			clicGauche();
			break;
		default:
			break;
		}
	}

	// retourne la salle correspondant à la ligne et à la colonne passé en parametre
	private Salle recupererSalle(int colonne, int ligne) {
		Carte carte = this.mainApp.joueurEnJeu.getCarte();
		Salle[] salles = carte.getSalles();
		return salles[(colonne * 9) + (ligne)];
	}

}
