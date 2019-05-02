package Model;

public class Arene extends Salle {
	
	private Monstre monstre;

	public Arene(Monstre monstre) {
		super(Salle.enumDescription.ARENE);
		this.monstre = monstre;
	}

}
