package MainApp;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import Controleur.ControleurEntrerBoutique;
import Controleur.ControleurFX;
import Controleur.ControleurGameOver;
import Controleur.ControleurPageAbout;
import Controleur.ControleurPageBoutique;
import Controleur.ControleurPageConnexion;
import Controleur.ControleurPageErreurSaisieNomJoueur;
import Controleur.ControleurPagePrincipale;
import Model.Joueur;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class MainApp extends Application {
	
	private Stage primaryStage;
	private Map<String, String> page = new HashMap<String, String>();
	public Joueur joueurEnJeu;
	
	@Override
	public void start(Stage primaryStage) {
		try {
			this.primaryStage = primaryStage;
			this.primaryStage.setTitle("Super Tower Rise invasion");
			
			this.page.put("connexion", "/Controleur/connexion.fxml");
			this.page.put("principale", "/Controleur/page_principale.fxml");
			this.page.put("erreurSaisieNomJoueur", "/Controleur/page_erreurSaisieNomJoueur.fxml");
			this.page.put("entrerBoutique", "/Controleur/page_entrerBoutique.fxml");
			this.page.put("boutique", "/Controleur/page_boutique.fxml");
			this.page.put("about", "/Controleur/about.fxml");
			this.page.put("gameover", "/Controleur/gameover.fxml");
			
			showPage("connexion");	

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// charge page primaire
	public void showPage(String titre_page) {
		try {
			// cree loader qui va permettre de charger les pages
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource(this.page.get(titre_page)));
			AnchorPane personOverview = (AnchorPane) loader.load();
			ControleurFX controleur;
			
			switch(titre_page) {
				case "connexion":
					controleur = new ControleurPageConnexion();
					break;
				case "principale":
					controleur = new ControleurPagePrincipale();
					break;	
				case "boutique":
					controleur = new ControleurPageBoutique();
					break;
				case "gameover":
					controleur = new ControleurGameOver();
				case "about":
					controleur = new ControleurPageAbout();
					break;
				default:
					System.out.println("** ERREUR ** : Mauvaise Page");
					break;	
			}
			
			controleur = loader.getController();
			
			// on charge la mainApp depuis le controleur de la page demande
			controleur.setMainApp(this);

			Scene scene = new Scene(personOverview);
			// on charge la scene dans le primaryStage
			primaryStage.setScene(scene);
			primaryStage.show();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	// charge page pop-up
		public void showPagePopUp(String titre_page) {
			try {
				// cree loader qui va permettre de charger les pages
				FXMLLoader loader = new FXMLLoader();
				loader.setLocation(MainApp.class.getResource(this.page.get(titre_page)));
				AnchorPane personOverview = (AnchorPane) loader.load();
				ControleurFX controleur;
				
				Stage fenetrePopUp = new Stage();
				fenetrePopUp.initOwner(primaryStage);
				
				switch(titre_page) {
					case "erreurSaisieNomJoueur":
						controleur = new ControleurPageErreurSaisieNomJoueur();
						fenetrePopUp.setTitle("Erreur");
						break;
					case "entrerBoutique":
						controleur = new ControleurEntrerBoutique();
						fenetrePopUp.setTitle("Entrer Boutique");
						break;
					default:
						System.out.println("** ERREUR ** : Mauvaise Page");
						break;	
				}
				
				controleur = loader.getController();
				
				// on charge la mainApp depuis le controleur de la page demande
				controleur.setFenetre(fenetrePopUp);
				controleur.setMainApp(this);
				
				Scene scene = new Scene(personOverview);
				// on charge la scene dans le primaryStage
				fenetrePopUp.setScene(scene);
				fenetrePopUp.show();

			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	public static void main(String[] args) {
		launch(args);
	}
}
