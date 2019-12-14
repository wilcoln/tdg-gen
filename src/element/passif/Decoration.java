package element.passif;

public class Decoration extends NatureTerrain{

	public Decoration(String symbol, String nom) {
		super(symbol, nom);
	}

	@Override
	public Decoration clone(){
		return new Decoration(getSymbol(), getNom());
	}

}
