package element.statique;


import element.Element;

/**
 * Cette classe factorise le code d'une nature de terrain dans un jeu de Tower Defense
 *
 * @author Elmontassir Rachid
 * @author Wilfried L. Bounsi
 */

public abstract class NatureTerrain extends Element {
	private String nom; // label
	private String symbol; // name
	
	public NatureTerrain(String symbol, String nom) {
		super(nom);
		this.symbol = symbol;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getSymbol() {
		return symbol;
	}
	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public abstract NatureTerrainType getType();

	@Override
    public String getEtat(){
		return getNom();
	}
}
