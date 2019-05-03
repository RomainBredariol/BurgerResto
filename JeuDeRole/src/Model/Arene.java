package Model;

public class Arene extends Salle {
	
	private Monstre monstre;

	public Arene(Monstre monstre, int index, String texte) {
		super(Salle.enumDescription.ARENE, index, texte );
		this.monstre = monstre;
	}
	
	public Monstre getMonstre() {
		return this.monstre;
	}

}
