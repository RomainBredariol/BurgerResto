package Model;

public class Salle {

	public enum enumDescription {ARENE, BOUTIQUE, SALLE, SORTIE};
	private enumDescription description;
	private boolean estVisible;
	private boolean sortie;
	private int indexMap;
	
	// une salle est composé d'une description et d'un monstre si ce n'est pas une boutique 
	//Par defaut une salle n'est pas visible.
	public Salle(enumDescription description) {
		this.description = description;
		this.estVisible = false;
		this.sortie = false;
	}
	
	public enumDescription getDescription() {
		return this.description;
	}
	
	
	// revele la salle sur la carte quand le joueur entre dedans.
	public void reveler() {
		this.estVisible = true;
	}
	
	// renvoie true si la salle est visible
	public boolean estVisible() {
		return this.estVisible;
	}

	public boolean isSortie() {
		return sortie;
	}

	public void setSortie(boolean estSortie) {
		this.sortie = estSortie;
	}

	public int getIndexMap() {
		return indexMap;
	}

	public void setIndexMap(int indexMap) {
		this.indexMap = indexMap;
	}
	
}
