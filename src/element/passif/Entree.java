package element.passif;

public class Entree extends NatureTerrain{

	public Entree(String label, String name) {
		super(label, name);
	}

	@Override
	public Entree clone(){
		return new Entree(getLabel(), getName());
	}
	

}
