package Controleur;

import java.util.HashMap;

import MainApp.MainApp;
import Model.Arme;
import Model.Armure;
import Model.Objet;
import Model.Objet.type;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class ControleurPageBoutique extends ControleurFX {

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
	private VBox sacVbox;
	@FXML
	private VBox boutiqueVbox;
	@FXML
	private Label pvtexte;

	private static HashMap<String, Objet> boutique;
	static {
		boutique = new HashMap<String, Objet>();
		boutique.put("Eau", new Objet("Eau", 10));
		boutique.put("Pain", new Objet("Pain", 15));
		boutique.put("Potion", new Objet("Potion", 20));
		boutique.put("Bouclier", new Armure("Bouclier", 10, 10));
		boutique.put("Cable RJ45", new Arme("Cable RJ45", 10, 10));
		boutique.put("Switch Cisco", new Armure("Switch Cisco", 20, 20));

	}

	@FXML
	public void retourAuJeu() {
		this.mainApp.showPage("principale");
	}

	@Override
	public void setMainApp(MainApp main) {
		this.mainApp = main;
		setAffichageJoueur();
		setAffichageSac();
		setAffichageBoutique();
	}

	// on affiche l'ensemble du sac
	private void setAffichageSac() {
		this.sacVbox.getChildren().clear();
		HashMap<String, Objet> sac = this.mainApp.joueurEnJeu.getSac();
		// ajouter l'armure et l'épée du heros
		sac.put(this.mainApp.joueurEnJeu.getArme().getNom(), this.mainApp.joueurEnJeu.getArme());
		sac.put(this.mainApp.joueurEnJeu.getArmure().getNom(), this.mainApp.joueurEnJeu.getArmure());
		for (Objet obj : sac.values()) {
			HBox ligneSac = new HBox();

			// nom de l'objet
			String nom = obj.getNom();
			switch (obj.getType()) {
			case ARME:
				Arme arme = (Arme)obj;
				nom += " (arme "+arme.getDegats()+" dégats)";
				if (arme.estEquipe()) {
					nom += " équipé";
				}
				break;
			case ARMURE:
				Armure armure = (Armure)obj;
				nom += " (armure "+armure.getDefense()+"/"+armure.getDefenseMax()+")";
				if (armure.estEquipe()) {
					nom += " équipé";
				}

			default:
				break;
			}
			
			Label nomObjet = new Label(nom);
			nomObjet.setPrefSize(230, 20);
			nomObjet.setAlignment(Pos.CENTER);

			// sa valeur
			Label valeurObjet = new Label(String.valueOf(obj.getValeur()));
			valeurObjet.setPrefSize(50, 20);
			valeurObjet.setAlignment(Pos.CENTER);

			// bouton Vendre
			Button boutonVendre = new Button("Vendre");
			boutonVendre.setPrefSize(100, 20);
			boutonVendre.setAlignment(Pos.CENTER);
			boutonVendre.setOnAction(e -> clicVendreObjet(obj));
			
			// bouton Reparer
			Button boutonReparer= new Button("Reparer (20 or)");
			boutonReparer.setPrefSize(150, 20);
			boutonReparer.setAlignment(Pos.CENTER);
			boutonReparer.setOnAction(e -> clicReparerObjet(obj));

			// on ajoute les elements au hbox
			ligneSac.getChildren().add(nomObjet);
			ligneSac.getChildren().add(valeurObjet);
			// ajouter le bouton vendre si l'objet n'est pas équipé
			if (!obj.estEquipe()) {
				ligneSac.getChildren().add(boutonVendre);
			}
			// ajouter le bouton réparer seulement pour les armures
			if (obj.getType() == type.ARMURE) {
				ligneSac.getChildren().add(boutonReparer);
			}

			// on ajoute le hbox à la vbox
			this.sacVbox.getChildren().add(ligneSac);
		}
	}

	// affiche les donnees du joueur
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

	// affiche le contenu de la boutique
	private void setAffichageBoutique() {
		for (Objet obj : boutique.values()) {
			HBox ligneBoutique = new HBox();

			// nom de l'objet
			Label nomObjet = new Label(obj.getNom());
			nomObjet.setPrefSize(86, 20);
			nomObjet.setAlignment(Pos.CENTER);

			// sa valeur
			Label valeurObjet = new Label(String.valueOf(obj.getValeur()));
			valeurObjet.setPrefSize(86, 20);
			valeurObjet.setAlignment(Pos.CENTER);

			// bouton utiliser
			Button boutonUtilisation = new Button("Acheter");
			boutonUtilisation.setPrefSize(86, 20);
			boutonUtilisation.setAlignment(Pos.CENTER);
			boutonUtilisation.setOnAction(e -> clicAcheterObjet(obj));

			// on ajoute les elements au hbox
			ligneBoutique.getChildren().add(nomObjet);
			ligneBoutique.getChildren().add(valeurObjet);
			ligneBoutique.getChildren().add(boutonUtilisation);

			// on ajoute le hbox à la vbox
			this.boutiqueVbox.getChildren().add(ligneBoutique);
		}
	}

	private void clicAcheterObjet(Objet obj) {
		if (this.mainApp.joueurEnJeu.getOR() > obj.getValeur() ) {
			this.mainApp.joueurEnJeu.ajouterObjetSac(obj);
			this.mainApp.joueurEnJeu.depenserOR(obj.getValeur());
			this.setAffichageSac();
			this.setAffichageJoueur();
		}
	}
	
	private void clicReparerObjet(Objet obj) {
		Armure armure = (Armure) obj;
		if (this.mainApp.joueurEnJeu.getOR() > 20) {
			armure.reparer();
			this.mainApp.joueurEnJeu.depenserOR(20);
			this.setAffichageSac();
			this.setAffichageJoueur();
		}
	}
	
	private void clicVendreObjet(Objet obj) {
		this.mainApp.joueurEnJeu.supprimerObjetSac(obj);
		this.mainApp.joueurEnJeu.addOR(obj.getValeur());
		this.setAffichageSac();
		this.setAffichageJoueur();
	}

	

}
