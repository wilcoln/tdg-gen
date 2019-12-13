package element.passif;

public class Sortie extends NatureTerrain{

	public Sortie(String label, String name) {
		super(label, name);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Sortie clone(){
		return new Sortie(getLabel(), getName());
	}
}
