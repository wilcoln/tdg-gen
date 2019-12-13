package jeu;

public class Niveau {
	private String name; 
	private int dureePause;
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
	public void setName(String name) {
		this.name = name;
	}
	public int getDureePause() {
		return dureePause;
	}
	public void setDureePause(int dureePause) {
		this.dureePause = dureePause;
	}
	public int getEnergieInitiale() {
		return energieInitiale;
	}

	public void diminuerEnergieJoueur(int moins) {
		this.energieInitiale -= moins;
	}

	public void augmenterEnergie(int plus) {
		this.energieInitiale += plus;
	}
	public int getToleranceMobiles() {
		return toleranceMobiles;
	}
	public void setToleranceMobiles(int toleranceMobiles) {
		this.toleranceMobiles = toleranceMobiles;
	}
}
