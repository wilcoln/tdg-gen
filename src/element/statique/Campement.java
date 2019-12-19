package element.statique;

/**
 * Cette classe représente une {@link NatureTerrain} de type Campement dans un jeu de Tower Defense
 *
 * @author Elmontassir Rachid
 * @author Wilfried L. Bounsi
 */

public class Campement extends NatureTerrain{

	public Campement(String symbol, String nom) {
		super(symbol, nom);
	}

	@Override
	public NatureTerrainType getType() {
		return NatureTerrainType.CAMPEMENT;
	}

	@Override
	public Campement clone(){
		return new Campement(getSymbol(), getNom());
	}

}
