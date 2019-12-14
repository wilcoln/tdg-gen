package element.passif;

public class Sortie extends NatureTerrain{

	public Sortie(String symbol, String nom) {
		super(symbol, nom);
	}

	@Override
	public Sortie clone(){
		return new Sortie(getSymbol(), getNom());
	}
}
