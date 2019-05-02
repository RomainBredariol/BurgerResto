package Model;

public class Arme extends Objet {
	
	private int degats;

	// une arme est définie par son nom, sa valeur marchande mais aussi son nombre de degats
	// qu'elle apporte en bonus au joueur.
	public Arme(String nom, int valeur,int degats) {
		super(nom, valeur);
		this.degats = degats;
	}
	
	public int getDegats() {
		return this.degats;
	}


	
	

}
