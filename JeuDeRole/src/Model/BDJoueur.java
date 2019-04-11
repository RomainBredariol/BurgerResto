package Model;

import java.util.HashMap;
import java.util.Map;

public class BDJoueur {

	private Map<String, Joueur> listeJoueur = new HashMap<String, Joueur>();

	private BDJoueur() {
	}

	private static class BDJoueurHolder {
		private final static BDJoueur instance = new BDJoueur();
	}

	public static BDJoueur getInstance() {
		return BDJoueurHolder.instance;
	}
	
	public void ajouterJoueur(Joueur newJoueur) {
		this.listeJoueur.put(newJoueur.getNom(), newJoueur);
	}
	
	public Joueur getJoueur(String nomJoueur) {
		return this.listeJoueur.get(nomJoueur);
	}
	
	public boolean isJoueur(String nomJoueur) {
		return this.listeJoueur.containsKey(nomJoueur);
	}
}
