package element.dynamique;

import jeu.Partie;

import java.util.ArrayList;
import java.util.List;

/**
 * Cette classe factorise le code d'un attaquant d'un jeu de Tower Defense ( {@link Mobile}, {@link Obstacle})
 *
 * @author Wilfried L. Bounsi
 */

public abstract class Attaquant extends ElementDynamique {
	private int energieMax;
	private int energieMaxActuelle;
	private int energieDispo;
	private TactiqueType tactique;
	private List<Projectile> projectiles;

	public Attaquant(String nom, int energieMax, int energieDispo, TactiqueType tactique) {
		super(nom);
		this.energieMax = energieMax;
		this.energieMaxActuelle = energieMax;
		this.energieDispo = energieDispo;
		this.tactique = tactique;
		projectiles = new ArrayList<>();
	}
	public int getEnergieMax() {
		return energieMax;
	}
	public void augmenterEnergieMaxActuelle(int plus){
		this.energieMaxActuelle+=plus;
	}
	public void diminuerEnergieMaxActuelle(int moins){
		this.energieMaxActuelle-=moins;
		diminuerEnergieDispo(moins);
	}
	public void diminuerEnergieDispo(int moins){
		this.energieDispo-=moins;
	}
	public void setEnergieMax(int energieMax) {
		this.energieMax = energieMax;
	}

	public int getEnergieDispo() {
		return energieDispo;
	}

	public void setEnergieDispo(int enerieDispo) {
		this.energieDispo = enerieDispo;
	}

	public TactiqueType getTactique() {
		return tactique;
	}

	public void setTactique(TactiqueType tactique) {
		this.tactique = tactique;
	}

	public List<Projectile> getProjectiles() {
		return projectiles;
	}

	public abstract AttaquantType getType();
	public void setProjectiles(List<Projectile> projectiles) {
		this.projectiles = projectiles;
	}

	public void lancerProjectileSiNecessaire(Partie partie) {
		Attaquant cible = getEnemiPrio(partie);
		if(cible != null){
			for (Projectile p: getProjectiles()) {
				if(p.getPortee() >= getPosition().distanceTo(cible.getPosition())){
					Projectile pc= p.clone();
					pc.setDepart(getPosition());
					pc.setDestination(cible.getPosition());
					pc.setTypeCible(cible.getType());
					partie.getProjectilesLances().add(pc);
					diminuerEnergieMaxActuelle(p.getMasse());
				}
			}
		}
	}

	public void activer(){
		this.energieDispo = this.energieMaxActuelle;
	}

	@Override
	public void evoluer(Partie partie){
		if(!isElimine()){
			activer(); // Activation
			lancerProjectileSiNecessaire(partie);
		}
	}
	public int getEnergieMaxActuelle() {
		return energieMaxActuelle;
	}
	public void setEnergieMaxActuelle(int energieMaxActuelle) {
		this.energieMaxActuelle = energieMaxActuelle;
	}

	@Override
	public boolean isElimine() {
		return energieMaxActuelle <= 0;
	}

	public Attaquant getEnemiPrio(Partie partie){
		List<Attaquant> enemisPresents;
		if(getType().equals(AttaquantType.MOBILE))
			enemisPresents = partie.getObstaclesPresents();
		else
			enemisPresents = partie.getMobilesPresents();

		if (enemisPresents.size() > 0) {
			Attaquant enemiPrio = null;
			switch (getTactique()){
				case attaquePlusFaible:
					int energieMaxMin = Integer.MAX_VALUE;
					for(Attaquant enemi: enemisPresents)
						if(enemi.getEnergieMax() < energieMaxMin && getPosition().surMemeAxe(enemi.getPosition())){
							energieMaxMin = enemi.getEnergieMax();
							enemiPrio = enemi;
						}
					break;
				case attaquePlusFort:
					int energieMaxMax = 0;
					for(Attaquant enemi: enemisPresents)
						if(enemi.getEnergieMax() > energieMaxMax && getPosition().surMemeAxe(enemi.getPosition())){
							energieMaxMax = enemi.getEnergieMax();
							enemiPrio = enemi;
						}
					break;
				case attaquePlusProche:
					double distanceMin = Integer.MAX_VALUE;
					for(Attaquant enemi: enemisPresents)
						if(enemi.getPosition().distanceTo(getPosition()) < distanceMin && getPosition().surMemeAxe(enemi.getPosition())){
							distanceMin = enemi.getPosition().distanceTo(getPosition());
							enemiPrio = enemi;
						}
					break;
			}
			return enemiPrio;
		}
		return null;
	}

	@Override
	public String getEtat(){
		return	"\tnom : " + getNom() + "\n" +
				"\tPosition : " + getPosition() + "\n" +
				"\tenergieMax :" + energieMax + "\n" +
				"\tenergieMaxActuelle : " + energieMaxActuelle + "\n" +
				"\tenergieDispo : " + energieDispo + "\n" +
				"\ttactique : " + tactique;
	}
}
