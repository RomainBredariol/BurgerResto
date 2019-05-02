package Model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Joueur extends Entitee {
	
	private HashMap<String, Integer> emplacement;
	private Armure armure;
	private Arme arme;
	private List<Objet> sac;
	private Carte carte;

	// un nouveau joueur commence avec 20 PV, 100 d'or et un sac vide
 	public Joueur(String nom) {
		super(nom, 1, 100);
		this.sac = new ArrayList<Objet>();
		this.emplacement = new HashMap<String, Integer>();
		this.emplacement.put("colonne", 0);
		this.emplacement.put("ligne", 0);
		this.carte = new Carte();
		
		// TODO arme et armure et carte
	}
 	
 	public int getEmplacementColonne() {
 		return this.emplacement.get("colonne");
 	}
 	
 	public int getEmplacementLigne() {
 		return this.emplacement.get("ligne");
 	}
 	
 	public void setEmplacementColonne(int valeur) {
 		this.emplacement.put("colonne", valeur);
 	}
 	
 	public void setEmplacementLigne(int valeur) {
 		this.emplacement.put("ligne", valeur);
 	}

	public Armure getArmure() {
		return armure;
	}

	public void setArmure(Armure armure) {
		this.armure = armure;
	}

	public Arme getArme() {
		return arme;
	}

	public void setArme(Arme arme) {
		this.arme = arme;
	}

	@Override
	public void attaquer(Entitee entitee) {
		// TODO realiser une attaque ne fonctionde l'arme
		
	}
	
	public void subirDegats(int degats) {
		// TODO subit degats en fonction de l'armure
	}

	public Carte getCarte() {
		return carte;
	}

}
