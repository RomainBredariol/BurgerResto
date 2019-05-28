package Model;

public class Armure extends Objet {

	private int defense;
	private int defenseMax;
	private boolean estCassee;
	
	// une armure est définie par son nom, sa valeur mais aussi la defense max qu'elle peut 
	// apporter au joueur qui la porte.
	public Armure(String nom, int valeur, int defenseMax) {
		super(nom, valeur);
		this.defenseMax = defenseMax;
		this.defense = defenseMax;
		this.estCassee = false;
		this.type = type.ARMURE;
	}

	public int getDefense() {
		return defense;
	}

	public int getDefenseMax() {
		return defenseMax;
	}
	
	// retourne le nombre de degats absorbés par l'armure
	public int subirDegats(int degats) {
		this.defense -= degats;
		int degatsAborbes = 0;
		if (this.defense < 0) {
			degatsAborbes = degats +this.defense;
			this.defense = 0;
		} else {
			degatsAborbes = degats;
		}
		return degatsAborbes;
	}
	
	// répare une armure en mettant son nombre de points de défense au max.
	public void reparer() {
		this.defense = this.defenseMax;
	}
	public boolean estCassee() {
		return this.estCassee;
	}
	
	

}
