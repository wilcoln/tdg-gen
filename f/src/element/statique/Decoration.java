package element.statique;

/**
 * Cette classe représente une {@link NatureTerrain} de type Décoration dans un jeu de Tower Defense
 *
 * @author Elmontassir Rachid
 * @author Wilfried L. Bounsi
 */

public class Decoration extends NatureTerrain{

	public Decoration(String symbol, String nom) {
		super(symbol, nom);
	}

	@Override
	public NatureTerrainType getType() {
		return NatureTerrainType.DECORATION;
	}

	@Override
	public Decoration clone(){
		return new Decoration(getSymbol(), getNom());
	}

}
