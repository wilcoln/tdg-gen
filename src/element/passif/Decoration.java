package element.passif;

public class Decoration extends NatureTerrain{

	public Decoration(String label, String name) {
		super(label, name);
	}

	@Override
	public Decoration clone(){
		return new Decoration(getLabel(), getName());
	}

}
