package element.statique;

/**
 * Cette classe représente une {@link NatureTerrain} de type Entree dans un jeu de Tower Defense
 *
 * @author Elmontassir Rachid
 * @author Wilfried L. Bounsi
 */

public class Entree extends NatureTerrain{

	public Entree(String symbol, String nom) {
		super(symbol, nom);
	}

	@Override
	public NatureTerrainType getType() {
		return NatureTerrainType.ENTREE;
	}

	@Override
	public Entree clone(){
		return new Entree(getSymbol(), getNom());
	}

}
