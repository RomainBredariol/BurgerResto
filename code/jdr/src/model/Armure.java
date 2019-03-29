package model;

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
	}

	public int getDefense() {
		return defense;
	}

	public int getDefenseMax() {
		return defenseMax;
	}
	
	public void subirDegats(int degats) {
		this.defense -= degats;
		// TODO : implementer un controle pour que cela ne devienne pas negative ? 
		// throw exception ?
	}
	
	// répare une armure en mettant son nombre de points de défense au max.
	public void reparer() {
		this.defense = this.defenseMax;
	}
	public boolean estCassee() {
		return this.estCassee;
	}

}
