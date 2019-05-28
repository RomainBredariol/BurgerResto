package Model;

import java.io.Serializable;

public class Objet implements Serializable{
	

	private static final long serialVersionUID = 8724345839325644880L;
	private String nom;
	private int valeur;
	public enum type {ARME, ARMURE, OBJET};
	protected type type;
	protected boolean estEquipee;
	
	// un objet est définie par son nom et sa valeur marchande en or pour son achat
	// ou sa revente
	public Objet(String nom, int valeur) {
		this.nom = nom;
		this .valeur = valeur;
		this.type = type.OBJET;
		this.estEquipee = false;
	}
	
	public boolean estEquipe() {
		return this.estEquipee;
	}
	// equiper l'objet
	public void equiper() {
		this.estEquipee = true;
	}
	// desequiper l'objet
	public void desequiper() {
		this.estEquipee = false;
	}
	
	public String getNom() {
		return this.nom;
	}
	
	public int getValeur() {
		return this.valeur;
	}
	
	public type getType() {
		return this.type;
	}

}
