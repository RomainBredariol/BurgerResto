package Model;

public class Salle {

	public enum enumDescription {ARENE, BOUTIQUE, SALLE, SORTIE, MUR};
	private enumDescription typeSalle;
	private String texte;
	private int indexMap;
	
	// une salle est composé d'une description et d'un monstre si ce n'est pas une boutique 
	//Par defaut une salle n'est pas visible.
	public Salle(enumDescription typeSalle, int indexMap, String texte) {
		this.typeSalle = typeSalle;	
		this.indexMap = indexMap;
		this.texte = texte;
	}
	
	public enumDescription getDescription() {
		return this.typeSalle;
	}

	public int getIndexMap() {
		return indexMap;
	}
	
	public String getTexte() {
		return this.texte;
	}
}
