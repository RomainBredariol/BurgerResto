package Model;

public class Monstre extends Entitee {
	
	private int force;
	private Objet tresor;
	
	// créer un monstre avec son nom, son nombre de pv maximum, son or, son objet
	// et sa force
	public Monstre(String nom, int pvMax, int or, Objet tresor, int force) {
		super(nom, pvMax, or);
		this.force = force;
		this.tresor = tresor;
	}

	// attaque une entitée en infligeant un nombre de degats égale a la force du monstre
	@Override
	public int attaquer(Entitee entitee) {
		entitee.subirDegats(this.force);
		return this.force;
	}
	
	// récupére le trésor du monstre
	public Objet getTresor() {
		return this.tresor;
	}
}
