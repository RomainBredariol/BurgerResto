package Model;

import java.util.HashMap;

public class Joueur extends Entitee {
	
	private HashMap<String, Integer> emplacement;
	private Armure armure;
	private Arme arme;
	private HashMap<String, Objet> sac;
	private Carte lvl1;
	private Carte lvl2;
	private Carte lvl3;
	private Carte carteEnCours;

	// un nouveau joueur commence avec 20 PV, 100 d'or et un sac vide
 	public Joueur(String nom) {
		super(nom, 1, 100);
		this.sac = new HashMap<String, Objet>();
		this.sac.put("Eau" ,new Objet("Eau", 2));
		this.emplacement = new HashMap<String, Integer>();
		this.emplacement.put("colonne", 0);
		this.emplacement.put("ligne", 0);
		this.lvl1 = new Carte(1);
		this.lvl2 = new Carte(2);
		this.lvl3 = new Carte(3);
		
		// TODO arme et armure et carte
		this.carteEnCours = lvl1;
		this.arme = new Arme("une feuille", 0, 1);
		this.armure = new Armure("un manteau", 0, 1);
		
	}
 	
 	public HashMap<String, Objet> getSac() {
		return sac;
	}

	public void ajouterObjetSac(Objet obj) {
		this.sac.put(obj.getNom(), obj);
	}
	
	public void supprimerObjetSac(Objet obj) {
		this.sac.remove(obj.getNom());
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
	public int attaquer(Entitee entitee) {
		entitee.subirDegats(this.arme.getDegats());
		return this.arme.getDegats();
	}
	
	public void subirDegats(int degats) {
		// TODO subit degats en fonction de l'armure
	}
	
	public void carteSuivante() {
		if(this.carteEnCours == this.lvl1) {
			this.carteEnCours = this.lvl2;
		}else if(this.carteEnCours == this.lvl2){
			this.carteEnCours = this.lvl3;
		}
		
	}

	public Carte getCarte() {
		return carteEnCours;
	}

}
