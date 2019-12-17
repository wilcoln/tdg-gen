package element.passif;

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

	public void setEnergie(int energie) {
		this.energie = energie;
	}

	public int getVolume() {
		return volume;
	}

	public void setVolume(int volume) {
		this.volume = volume;
	}

	@Override
	public Chemin clone(){
		return new Chemin(getSymbol(), getNom(), getEnergie(), getVolume());
	}
	@Override
	public String getEtat(){
		return super.getEtat() + "\n" +
				"\tVolume: " +  getVolume() + "\n" +
				"\tEnergie: " + getEnergie() + "\n";
	}

	

}
