package element.passif;

public class Chemin extends NatureTerrain{

	private int energie;
	private int volume;
	private boolean estVisite = false;
	
	public Chemin(String label, String name, int energie, int volume) {
		super(label, name);
		this.energie = energie;
		this.volume = volume;
	}
	
	public boolean getEstVisite(){
		return estVisite;
	}
	public void setEstVisite(boolean v){
		estVisite = v;
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
		return new Chemin(getLabel(), getName(), getEnergie(), getVolume());
	}

	

}
