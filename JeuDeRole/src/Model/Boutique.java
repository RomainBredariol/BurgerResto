package Model;

import java.util.List;

public class Boutique extends Salle{
	
	private List<Objet> marchandise;
	
	public Boutique(List<Objet> marchandise) {
		super(Salle.enumDescription.BOUTIQUE);
		this.marchandise = this.marchandise;
	}
	
	public void acheter(Objet objet) {
		// TODO : traitement
	}
	public void vendre(Objet objet) {
		// TODO : traitement
	}
	// permet de reparer une armure 
	// TODO : prix , transfert d'argent ... 
	public void reparer(Armure armure) {
		armure.reparer();
	}

	public List<Objet> getMarchandise() {
		return marchandise;
	}
	
	

}
