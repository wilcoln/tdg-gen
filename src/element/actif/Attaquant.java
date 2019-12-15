package element.actif;

import element.Element;
import jeu.Partie;
import utils.Position;
import utils.Positionable;

import java.util.ArrayList;
import java.util.List;

public abstract class Attaquant extends Positionable implements Element {
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
		Attaquant cible;
		if(getType().equals(AttaquantType.MOBILE))
			 cible = getEnemiPrio(partie.getObstaclesPresents());
		else
			cible = getEnemiPrio(partie.getMobilesPresents());

		if(cible != null){
			for (Projectile p: getProjectiles()) {
				if(p.getPortee() >= getPosition().distanceTo(cible.getPosition())){
					Projectile pc= p.clone();
					pc.setDepart(getPosition());
					pc.setArrive(cible.getPosition());
					pc.setTypeCible(cible.getType());
					partie.getProjectilesLances().add(pc);
					// TODO E24 diminuer energieMaxActuelle ou energieDispo ??? => OK
					diminuerEnergieMaxActuelle(p.getMasse());
				}
			}
		}
	}
	public void evoluer(Partie partie){
		if(!isElimine()){
			this.energieDispo = this.energieMaxActuelle; // Activation
			lancerProjectileSiNecessaire(partie);
		}
	}
	public int getEnergieMaxActuelle() {
		return energieMaxActuelle;
	}
	public void setEnergieMaxActuelle(int energieMaxActuelle) {
		this.energieMaxActuelle = energieMaxActuelle;
	}
	public boolean isElimine() {
		return energieMaxActuelle <= 0;
	}

	public Attaquant getEnemiPrio(List<Attaquant> enemisPresents){
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

	public String getEtat(){
		return	"nom : " + getNom() + "\n" +
				"Est Vivant :" + !isElimine() + "\n" +
				"Position : " + getPosition() + "\n" +
				"energieMax :" + energieMax + "\n" +
				"energieMaxActuelle : " + energieMaxActuelle + "\n" +
				"energieDispo : " + energieDispo + "\n" +
				"tactique : " + tactique;
	}
}
