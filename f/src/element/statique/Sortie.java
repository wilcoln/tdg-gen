package element.statique;

/**
 * Cette classe représente une {@link NatureTerrain} de type Sortie dans un jeu de Tower Defense
 *
 * @author Elmontassir Rachid
 * @author Wilfried L. Bounsi
 */

public class Sortie extends NatureTerrain{

	public Sortie(String symbol, String nom) {
		super(symbol, nom);
	}

	@Override
	public NatureTerrainType getType() {
		return NatureTerrainType.SORTIE;
	}

	@Override
	public Sortie clone(){
		return new Sortie(getSymbol(), getNom());
	}
}
