package Model;

public class Objet {
	
	private String nom;
	private int valeur;
	
	// un objet est définie par son nom et sa valeur marchande en or pour son achat
	// ou sa revente
	public Objet(String nom, int valeur) {
		this.nom = nom;
		this .valeur = valeur;
	}
	
	public String getNom() {
		return this.nom;
	}
	
	public int getValeur() {
		return this.valeur;
	}

}
