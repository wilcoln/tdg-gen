package element.actif;

public class Obstacle extends Attaquant{

	public Obstacle(String name, int energieMax, int enerieDispo, TactiqueType tactique) {
		super(name, energieMax, enerieDispo, tactique);
	}

	// P8
	public void traverser() {
		// TODO : à implémenter
	}

	// P9
	public void reparer() {
		// TODO : à raffiner
		this.setEnergieMaxActuelle(this.getEnergieMaxActuelle()+1);
	}

	@Override
	public Obstacle clone(){
		Obstacle same = new Obstacle(getName(), getEnergieMax(), getEnergieDispo(), getTactique());
		for(Projectile p: getProjectiles())
			same.getProjectiles().add(p);
		return same;
	}

	@Override
	public String getEtat() {
		return "Obstacle \n" + super.getEtat();
	}
	
}
