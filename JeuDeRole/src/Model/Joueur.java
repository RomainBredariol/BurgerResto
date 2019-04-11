package Model;

import java.util.ArrayList;
import java.util.List;

public class Joueur extends Entitee {
	
	private Salle emplacement;
	private Armure armure;
	private Arme arme;
	private List<Objet> sac;

	// un nouveau joueur commence avec 20 PV, 100 d'or et un sac vide
 	public Joueur(String nom) {
		super(nom, 1, 100);
		this.sac = new ArrayList<Objet>();
		// TODO arme et armure
	}

	@Override
	public void attaquer(Entitee entitee) {
		// TODO realiser une attaque ne fonctionde l'arme
		
	}
	
	public void subirDegats(int degats) {
		// TODO subit degats en fonction de l'armure
	}
	
	

}
