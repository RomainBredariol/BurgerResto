package Model;

import java.io.Serializable;

public abstract class Entitee implements Serializable {
	

	private static final long serialVersionUID = -1680257189971689389L;
	private String nom;
	private int pvMax;
	private int pv; // pvMax du joueur = 1 
	private int or;
	private boolean estVivant;
	
	
	// créée une entitée avec son nom, son nombre de PV maximum et son or
	// l'entitée créer est vivante et son nombre de pv est égale au nombre de pv max
	public Entitee(String nom, int pvMax, int or) {
		this.nom = nom;
		this.pvMax = pvMax;
		this.pv = pvMax;
		this.or = or;
		this.estVivant = true;
	}
	
	public int getPV() {
		return this.pv;
	}
	
	public int getPVMax() {
		return this.pvMax;
	}
	
	public void addOR(int or) {
		this.or += or;
	}
	
	public int getOR() {
		return this.or;
	}
	
	public String getNom() {
		return this.nom;
	}
	
	public boolean estVivant() {
		return this.estVivant;
	}
	
	// cette methode inflige des degats à une entitée selon un calcul propre à
	// chaque entitée.
	public abstract int attaquer(Entitee entitee);
	
	// diminue le nombre de points de vie de l'entitée en fonction des dégats qui lui sont infligés
	// vérifie si l'entitée est toujours vivante aprés coup.
	public void subirDegats(int degats) {
		this.pv -= degats;
		if (this.pv <= 0 )
			this.estVivant = false;
	}
	
	public void potion(int pvGagne){
		this.pv += pvGagne;
	}
	
	// soigne l'entiée jusqu'a son nombre de point de vie max
	public void soigner() {
		this.pv = this.pvMax;
	}
	
	// augmente les PV max de 1
	public void augmenterPV() {
		this.pvMax++;
	}
	
	// depenser de l'or
	public void depenserOR(int or) {
		this.or -= or;
	}
	
	
	

}
