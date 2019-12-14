package element.actif;

import jeu.Partie;
import utils.Position;

public class Obstacle extends Attaquant{

	public Obstacle(String nom, int energieMax, int enerieDispo, TactiqueType tactique) {
		super(nom, energieMax, enerieDispo, tactique);
	}

	@Override
	public AttaquantType getType() {
		return AttaquantType.OBSTACLE;
	}

	// P9
	public void reparer() {
		// TODO P9 : à raffiner
		this.setEnergieMaxActuelle(this.getEnergieMaxActuelle()+1);
	}

	@Override
	public void evoluer(Partie partie) {
		super.evoluer();
		lancerProjectileSiNecessaire(partie);
	}

	@Override
	public Obstacle clone(){
		Obstacle same = new Obstacle(getNom(), getEnergieMax(), getEnergieDispo(), getTactique());
		for(Projectile p: getProjectiles())
			same.getProjectiles().add(p);
		return same;
	}

	@Override
	public String getEtat() {
		return "Obstacle \n" + super.getEtat();
	}
	
}
