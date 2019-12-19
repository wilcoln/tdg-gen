package element.statique;

/**
 * Cette classe représente une {@link NatureTerrain} de type Chemin dans un jeu de Tower Defense
 *
 * @author Elmontassir Rachid
 * @author Wilfried L. Bounsi
 */

public class Chemin extends NatureTerrain{

	private int energie;
	private int volume;
	
	public Chemin(String symbol, String nom, int energie, int volume) {
		super(symbol, nom);
		this.energie = energie;
		this.volume = volume;
	}

	public int getEnergie() {
		return energie;
	}

	public int getVolume() {
		return volume;
	}

	@Override
	public Chemin clone(){
		return new Chemin(getSymbol(), getNom(), getEnergie(), getVolume());
	}

	@Override
	public NatureTerrainType getType() {
		return NatureTerrainType.CHEMIN;
	}

	@Override
	public String getEtat(){
		return super.getEtat() + "\n" +
				"\tVolume: " +  getVolume() + "\n" +
				"\tEnergie: " + getEnergie() + "\n";
	}

}
