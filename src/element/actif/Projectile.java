package element.actif;

import jeu.Partie;
import utils.Position;
import utils.Positionable;

import java.util.List;

public class Projectile extends Positionable implements ElementMobile {

	private int masse;
	private int vitesse;
	private int portee;
	private int energie;
	private boolean elimine;

	private AttaquantType typeCible;
	private Position depart;
	private Position arrive;

	public Projectile(String nom, int masse, int vitesse, int portee, int energie) {
		super(nom);
		this.masse = masse;
		this.vitesse = vitesse;
		this.portee = portee;
		this.energie = energie;
	}

	public Position getDepart() {
		return depart;
	}

	public void setDepart(Position depart) {
		this.depart = depart;
	}

	public Position getArrive() {
		return arrive;
	}

	public void setArrive(Position arrive) {
		this.arrive = arrive;
	}

	public int getMasse() {
		return masse;
	}

	public int getVitesse() {
		return vitesse;
	}

	public int getPortee() {
		return portee;
	}

	public int getEnergie() {
		return energie;
	}

	public void setEnergie(int energie) {
		this.energie = energie;
	}

	public void avancer() {
		//TODO E24, P8 => OK , pas de déplacement en diag
		for (int i = 0; i < vitesse ; i++) {
			if(depart.estADroiteDe(arrive)){
				setPosition(getPosition().gauche());
			}else if(depart.estAGaucheDe(arrive)){
				setPosition(getPosition().droite());
			}else if(depart.estEnHautDe(arrive)){
				setPosition(getPosition().bas());
			}else if(depart.estEnBasDe(arrive)){
				setPosition(getPosition().haut());
			}
		}
	}

	public AttaquantType getTypeCible() {
		return typeCible;
	}

	public void setTypeCible(AttaquantType typeCible) {
		this.typeCible = typeCible;
	}

	public void croiserAttaquant(Attaquant cible) {
		if (this.energie > cible.getEnergieMaxActuelle()) {
			this.energie -= cible.getEnergieMaxActuelle();
			this.avancer();
		} else {
			this.setElimine(true);
			cible.setEnergieMaxActuelle(cible.getEnergieMaxActuelle() - this.energie);
		}
	}

	public boolean isElimine() {
		return elimine;
	}

	public void setElimine(boolean elimine) {
		this.elimine = elimine;
	}

	@Override
	public Projectile clone() {
		return new Projectile(getNom(), masse, vitesse, portee, energie);
	}

	@Override
	public String getEtat() {
		/*
		 * return "Projectile \n nom : " + name + "\n" + "Est Vivant :" + !elimine +
		 * "energie :" + energie + "masse : " + masse + "vitesse : " + vitesse +
		 * "portee : " + portee;
		 */
		return getNom();
	}

	@Override
	public void evoluer(Partie partie) {
		if (getPosition().isUndefined()){
			setPosition(depart);
		}
		else{
			List<Attaquant> victimesPossibles;
			if(typeCible == AttaquantType.MOBILE)
				victimesPossibles = partie.getMobilesAt(getPosition());
			else
				victimesPossibles = partie.getObstaclesAt(getPosition());

			if (victimesPossibles.size() > 0) {
				Attaquant victime = victimesPossibles.get(0);
				croiserAttaquant(victime);
				if(victime.isElimine() && (victime instanceof Mobile)){
					partie.getJoueur().gagneBonusEnergie(victime.getEnergieMax());
				}
			}
			if (getPosition().distanceTo(depart) < portee)
				avancer();
			else
				setElimine(true);
		}
	}

	@Override
	public List<Position> positionsAccessibles(Partie partie) {
		// TODO E14 Décoration, Campement, Chemin, Entree, Sortie
		return null;
	}
}
