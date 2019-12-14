package element.passif;

public class Campement extends NatureTerrain{

	public Campement(String symbol, String nom) {
		super(symbol, nom);
	}

	@Override
	public Campement clone(){
		return new Campement(getSymbol(), getNom());
	}

}
