package Model;

public class Carte {
	
	private Salle[][] salles;
	
	// une carte est composé d'une matrice de salles
	public Carte(Salle[] salles) {
		int k = 0;
		for(int i = 0; i < 9; i++) {
			for(int j = 0; j < 9; j++) {
				this.salles[i][j] = salles[k]; 
				k++;
			}
		}
	}
}
