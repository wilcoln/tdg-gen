package element.passif;

import element.Element;

public abstract class NatureTerrain implements Element {
	private String nom; // label
	private String symbol; // name
	
	public NatureTerrain(String symbol, String nom) {
		this.symbol = symbol;
		this.nom = nom;
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

    public String getEtat(){
		return getNom();
	}
}
