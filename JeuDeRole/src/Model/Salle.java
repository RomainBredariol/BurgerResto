package Model;

import java.util.List;

public class Salle {

	private String description;
	private List<Monstre> monstres;
	private Porte[] portes;
	private boolean estVisible;
	
	// une salle est composé d'une description, d'une liste de montres et de plusieurs portes
	// permettant l'accés à d'autres salles. Par defaut une salle n'est pas visible.
	public Salle(String description, List<Monstre> monstres, Porte[] portes) {
		this.description = description;
		this.monstres =monstres;
		this.portes = portes;
		this.estVisible = false;
	}
	
	public String getDescription() {
		return this.description;
	}

	public List<Monstre> getMonstres() {
		return monstres;
	}

	public Porte[] getPortes() {
		return portes;
	}

	// revele la salle sur la carte quand le joueur entre dedans.
	public void reveler() {
		this.estVisible = true;
	}
	
	// renvoie true si la salle est visible
	public boolean estVisible() {
		return this.estVisible;
	}
	
}
