package jeu;

/**
 * Cette classe représente un Niveau d'une partie d'un jeu de Tower Defense
 *
 * @author Elmontassir Rachid
 * @author Wilfried L. Bounsi
 *
 */

public class Niveau {
	private String name; 
	private int dureePause; // duréee de pause entre les vagues
	private int energieInitiale; 
	private int toleranceMobiles;
	
	public Niveau(String name, int dureePause, int energieInitiale, int toleranceMobiles) {
		this.name = name;
		this.dureePause = dureePause;
		this.energieInitiale = energieInitiale;
		this.toleranceMobiles = toleranceMobiles;
	}
	
	public String getName() {
		return name;
	}
	public int getDureePause() {
		return dureePause;
	}
	public int getEnergieInitiale() {
		return energieInitiale;
	}
	public int getToleranceMobiles() {
		return toleranceMobiles;
	}
}
