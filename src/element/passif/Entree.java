package element.passif;

public class Entree extends NatureTerrain{

	public Entree(String symbol, String nom) {
		super(symbol, nom);
	}

	@Override
	public Entree clone(){
		return new Entree(getSymbol(), getNom());
	}
	

}
