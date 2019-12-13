package element.passif;

public class Campement extends NatureTerrain{

	public Campement(String label, String name) {
		super(label, name);
	}

	@Override
	public Campement clone(){
		return new Campement(getLabel(), getName());
	}

}
