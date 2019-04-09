package model;

public class Porte {

	private Salle salle1;
	private Salle salle2;
	private boolean estOuverte;
	
	// une porte connecte 2 salles, elle est par défaut fermée
	public Porte(Salle salle1, Salle salle2) {
		this.salle1 = salle1;
		this.salle2 = salle2;
		this.estOuverte = false;
	}
	
	// une porte qui à été ouverte donne au joueur une vision sur la carte des 2 salles
	// qu'elle relie
	public void ouvrir() {
		this.estOuverte = true;
		this.salle1.reveler();
		this.salle2.reveler();
	}
	
	public boolean estOuverte() {
		return this.estOuverte;
	}
	
	
}
