package element.actif;

public class Mobile extends Attaquant{

	private int volume;
	private int vitesse;
	
	public Mobile(String name, int volume, int energieMax, int energieDispo, int vitesse, TactiqueType tactique) {
		super(name, energieMax, energieDispo, tactique);
		this.volume = volume;
		this.vitesse = vitesse;
	}

	public int getVolume() {
		return volume;
	}
	public void setVolume(int volume) {
		this.volume = volume;
	}

	public int getVitesse() {
		return vitesse;
	}
	public void setVitesse(int vitesse) {
		this.vitesse = vitesse;
	}

	@Override
	public Mobile clone(){
		Mobile same = new Mobile(getName(), getVolume(), getEnergieMax(), getEnergieDispo(), getVitesse(), getTactique());
		for(Projectile p: getProjectiles())
			same.getProjectiles().add(p);
		return same;
	}
	@Override
	public String getEtat(){
		return "Mobile \n" + super.getEtat() + "\n" +
				"Vitesse : " + vitesse + "\n" +
				"Volume : " + volume;

	}
}
