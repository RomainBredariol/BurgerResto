package Controleur;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;

import MainApp.MainApp;
import Model.Arene;
import Model.Arme;
import Model.Armure;
import Model.Carte;
import Model.Combat;
import Model.Joueur;
import Model.Monstre;
import Model.Objet;
import Model.Objet.type;
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
import javafx.scene.layout.VBox;

public class ControleurPagePrincipale extends ControleurFX {

	// attributs de javaFX
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
	@FXML
	private Button sauvegarder;
	@FXML
	private Button charger;
	
	
	// attributs du modele
	private Combat combatEnCours;
	private Salle salleEnCours;
	private enum directionClic {GAUCHE, DROITE, HAUT, BAS};
	private directionClic dernierClic;

	@Override
	public void setMainApp(MainApp main) {
		this.mainApp = main;
		setAffichageJoueur();
		refreshCarte();
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
			String texteBouton;
			if (obj.getType() == type.ARME || obj.getType() == type.ARMURE) {
				texteBouton = "Equiper";
			} else {
				texteBouton = "Utiliser";
			}
			Button boutonUtilisation = new Button(texteBouton);
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

	// utiliser un objet
	public void clicUtiliserObjet(String nomObjet) {
		HashMap<String, Objet> sac = this.mainApp.joueurEnJeu.getSac();

		utiliserObjet(sac.get(nomObjet));
	}

	//utilise un objet contenu dans le sac
	public void utiliserObjet(Objet obj) {
		switch (obj.getClass().getSimpleName()) {
		case "Arme":
			// desequiper l'ancienne arme
			this.mainApp.joueurEnJeu.getArme().desequiper();
			//equipe l'arme et supprime l'objet du sac
			obj.equiper();
			this.mainApp.joueurEnJeu.setArme((Arme) obj);
			this.mainApp.joueurEnJeu.supprimerObjetSac(obj);
			break;
		case "Armure":
			// desequiper l'ancienne armure
			this.mainApp.joueurEnJeu.getArmure().desequiper();
			//equipe l'armure et supprime l'objet du sac
			obj.equiper();
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
		//met a jour les differents affichages
		setAffichageJoueur();
		setAffichageSac();
	}

	// écris dans la fenetre de dialogue le texte passé en paramétre
	public void ecrireDialogue(String texte) {
		// formater le texte pour qu'il ne dépasse pas la taille
		int postCurseur = 0;
		int curseur = 0;
		int facteur = 1;
		while (texte.indexOf(" ",curseur+1) >= 0) {
			curseur = texte.indexOf(" ",curseur+1);
			if (curseur > 59 * facteur) {
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

	// rafraichir la carte du jeu
	public void refreshCarte() {
		// set des informations relatifs au montre / combat
		if (this.combatEnCours != null) {
			if (this.combatEnCours.estTermine()) {
				this.nomMonstre.setText("Vous n'étes pas en combat");			
			} else {
				Monstre monstre = this.combatEnCours.getMonstre();
				this.nomMonstre.setText("Combat contre "+monstre.getNom()+" ("+monstre.getPV()+"/"+monstre.getPVMax()+")");
			}			
		} else {
			this.nomMonstre.setText("Vous n'étes pas en combat");	
		}
		// recuperer la positon du joueur
		int ligneJoueur = this.mainApp.joueurEnJeu.getEmplacementLigne();
		int colonneJoueur = this.mainApp.joueurEnJeu.getEmplacementColonne();
		
		// mise à jour de la carte
		this.carte.getChildren().clear();
		for (int colonne = 0 ; colonne < 9 ; colonne ++ ) {
			for (int ligne = 0 ; ligne < 9 ; ligne++ ) {
				int index = ligne + (colonne * 9) + 1;
				Pane pane = new Pane();
				ImageView image = new ImageView();
				// définir l'icone de l'image d'entrée
				if (colonne == 0 && ligne == 0) {
					image = new ImageView("/Controleur/icon/caseEntre.png");
				} else {
					Salle salle = recupererSalle(colonne, ligne);
					//si la salle n'est pas déjà visitée alors afficher une case cachée
					if (!salle.estDejaVisitee()) {
						image = new ImageView("/Controleur/icon/caseCache.png");					
					} else {
						// sinon afficher l'image en fonction du type de la salle
						switch (salle.getDescription()) {
						case BOUTIQUE:
							image = new ImageView("/Controleur/icon/shop.png");
							break;
						case SALLE:
							image = new ImageView("/Controleur/icon/case.png");
							break;
						case ARENE:
							if (salle.estResolue()) {
								image = new ImageView("/Controleur/icon/monstreMort.png");
							} else {
								image = new ImageView("/Controleur/icon/monstre.png");
							}
							break;
						case MUR:
							System.out.println("mur");
							image = new ImageView("/Controleur/icon/mur.png");
						default:
							break;
						}
					}			
				}
//				System.out.println("ligne joueur : "+ligneJoueur);
//				System.out.println("ligne : "+ligne);
//				System.out.println("Colonene joueur  : "+colonneJoueur);
//				System.out.println("colonne : "+colonne);
				image.setFitHeight(28);
				image.setFitWidth(28);
				pane.getChildren().add(0,image);
				pane.getChildren().get(0).setLayoutX(10);
				this.carte.add(pane, colonne, ligne);
				if (ligneJoueur == ligne && colonneJoueur == colonne) {
					image = new ImageView("/Controleur/icon/dot.png");
					image.setFitWidth(11);
					image.setFitHeight(12);
					
					pane.getChildren().add(1,image);
					pane.getChildren().get(1).setLayoutY(9);
					pane.getChildren().get(1).setLayoutX(18);
				}
			}
		}
	}

	// faire un déplacement vers le haut si possible
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
			salle.visiterSalle();
			if (salle.getDescription() != enumDescription.MUR) {
				this.mainApp.joueurEnJeu.setEmplacementLigne(ligne - 1);
				// Si la salle est une boutique
				if (salle.getDescription() == enumDescription.BOUTIQUE) {
					// entre dans la boutique
					entrerDansBoutique();
				}
				if (!salle.estResolue()) {
					resoudreSalle(salle);
				}
			}
		}
		refreshCarte();
	}

	// faire un déplacement vers le bas si possible
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
			salle.visiterSalle();
			if (salle.getDescription() != enumDescription.MUR) {
				this.mainApp.joueurEnJeu.setEmplacementLigne(ligne + 1);
				// Si la salle est une boutique
				if (salle.getDescription() == enumDescription.BOUTIQUE) {
					// entre dans la boutique
					entrerDansBoutique();
				}
				if (!salle.estResolue()) {
					resoudreSalle(salle);
				}
			}
		}
		refreshCarte();
	}

	// faire un déplacement vers la gauche si possible
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
			salle.visiterSalle();
			if (salle.getDescription() != enumDescription.MUR) {
				this.mainApp.joueurEnJeu.setEmplacementColonne(colonne - 1);
				// Si la salle est une boutique
				if (salle.getDescription() == enumDescription.BOUTIQUE) {
					// entre dans la boutique
					entrerDansBoutique();
				}
				if (!salle.estResolue()) {
					resoudreSalle(salle);
				}
			}
		}
		refreshCarte();
	}

	// faire un déplacement vers la droite si possible
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
			salle.visiterSalle();
			if (salle.getDescription() != enumDescription.MUR) {
				this.mainApp.joueurEnJeu.setEmplacementColonne(colonne + 1);
				// Si la salle est une boutique
				if (salle.getDescription() == enumDescription.BOUTIQUE) {
					// entre dans la boutique
					entrerDansBoutique();
				}
				if (!salle.estResolue()) {
					resoudreSalle(salle);
				}
			}
		}
		refreshCarte();
	}

	// sauvegarder l'état du syteme
	public void sauvegarder() {
		// Serialization  
		String filename = "save";
        try
        {    
            //Saving of object in a file 
            FileOutputStream file = new FileOutputStream(filename); 
            ObjectOutputStream out = new ObjectOutputStream(file); 
            // Method for serialization of object 
            out.writeObject(this.mainApp.joueurEnJeu); 
            out.close(); 
            file.close(); 
        } 
        catch(IOException ex) { 
            System.out.println("IOException is caught"); 
        }
        ecrireDialogue("La partie viens d'être sauvegardé !");
	}
	
	// charger l'état du systeme 
	public void charger() {
		
		// Deserialization 
		String filename = "save";
        try
        {    
            // Reading the object from a file 
            FileInputStream file = new FileInputStream(filename); 
            ObjectInputStream in = new ObjectInputStream(file); 
              
            // Method for deserialization of object 
            this.mainApp.joueurEnJeu = (Joueur)in.readObject();
            in.close(); 
            file.close(); 
        } 
        catch(IOException ex) { 
            System.out.println("IOException is caught"); 
        } 
        catch(ClassNotFoundException ex) { 
            System.out.println("ClassNotFoundException is caught"); 
        }
        setAffichageJoueur();
        setAffichageSac();
        refreshCarte();
        ecrireDialogue("La partie vien d'être chargée !");
	}
	
	// entrer dans la boutique
	private void entrerDansBoutique() {
		this.mainApp.showPagePopUp("entrerBoutique");
	}

	// resous les effets de la salle (combat, texte ...)
	private void resoudreSalle(Salle salle) {
		this.salleEnCours = salle;
		this.salleEnCours.visiterSalle();
		// si la salle est une salle vide, lire sa description et passer la salle en
		// état visité
		if (salle.getDescription() == enumDescription.SALLE) {
			ecrireDialogue(salle.getTexte());
			this.salleEnCours.resoudreSalle();
		}
		// si la salle est une arene, résoudre le combat
		if (salle.getDescription() == enumDescription.ARENE) {
			ecrireDialogue(salle.getTexte());
			// lancer le combat
			Arene arene = (Arene) salle;
			combatEnCours = new Combat(this.mainApp.joueurEnJeu, arene.getMonstre());
			// placer le nom du monstre 
			this.nomMonstre.setText("Combat contre "+arene.getMonstre().getNom()+" ("+arene.getMonstre().getPV()+"/"+arene.getMonstre().getPVMax()+")");
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
		// afficher les pv du monstre
		Monstre monstre = this.combatEnCours.getMonstre();
		this.nomMonstre.setText("Combat contre "+monstre.getNom()+" ("+monstre.getPV()+"/"+monstre.getPVMax()+")");

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
			this.salleEnCours.resoudreSalle();
			this.nomMonstre.setText("Vous n'étes pas en combat");
			
		}
		refreshCarte();
		setAffichageJoueur();
	}

	// le joueur fuis le combat
	@FXML
	public void clickFuir() {
		ecrireDialogue(combatEnCours.fuir());
		// soigner le monstre
		this.combatEnCours.getMonstre().soigner();
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
