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
		this.setEnergieMaxActuelle(this.getEnergieMax());
	}
	
	public String toString() {
		return this.getNom();
	}
	@Override
	public Obstacle clone(){
		Obstacle same = new Obstacle(getNom(), getEnergieMax(), getEnergieDispo(), getTactique());
		for(Projectile p: getProjectiles())
			same.getProjectiles().add(p);
		return same;
	}

	public int coutReparation(){
		return getEnergieMax() - getEnergieMaxActuelle();
	}

	@Override
	public void evoluer(Partie partie){
		super.evoluer(partie);
		if(isElimine()){
			partie.getJoueur().getObstacles().remove(this);
			partie.getNotifications().add("un obstacle " + getNom() + " éliminé");
		}
	}
	@Override
	public String getEtat() {
		return "Obstacle \n" + super.getEtat();
	}
	
}
