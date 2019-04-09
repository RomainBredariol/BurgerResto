package model;

import java.util.List;

public class Combat {
	
	private int tour;
	private Joueur joueur;
	private List<Monstre> monstres;
	private boolean estTermine;
	
	// un combat est constitué d'un joueur et d'un ou plusieurs monstres
	public Combat(Joueur joueur, List<Monstre> monstres) {
		this.tour = 1;
		this.joueur = joueur;
		this.monstres = monstres;
		this.estTermine = false;
	}
	
	public boolean estTermine() {
		return this.estTermine;
	}
	
	public int getTour() {
		return this.tour;
	}
	
	// continuer déclenche un tour de combat, le joueur inflige des dégats à un monstre
	// les monstres infligent leur dégats au joueur
	public void continuer() {
		this.tour++;
		// TODO : implémenter un combat
	}
	
	// fuir permet de terminer un combat, les monstres sont soignés complétement
	public void fuir() {
		this.estTermine = true;
		// TODO : implémenter la fuite
	}

}
