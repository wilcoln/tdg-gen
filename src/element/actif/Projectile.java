package element.actif;

import element.Element;
import jeu.Partie;
import utils.Position;
import utils.Positionable;

public class Projectile extends Positionable implements Element {
	private String name;
	private int masse;
	private int vitesse;
	private int portee;
	private int energie;
	private boolean elimine;

	private Position depart;
	private Position arrive;

	public Projectile(String name, int masse, int vitesse, int portee, int energie) {
		super();
		this.name = name;
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getMasse() {
		return masse;
	}

	public void setMasse(int masse) {
		this.masse = masse;
	}

	public int getVitesse() {
		return vitesse;
	}

	public void setVitesse(int vitesse) {
		this.vitesse = vitesse;
	}

	public int getPortee() {
		return portee;
	}

	public void setPortee(int portee) {
		this.portee = portee;
	}

	public int getEnergie() {
		return energie;
	}

	public void setEnergie(int energie) {
		this.energie = energie;
	}

	public void activer() {
		// TODO : implement
	}

	public void avancer() {
		if (getPosition().isUndefined())
			setPosition(depart);
		else
			setPosition(getPosition().gauche());
	}

	public void croiserAttaquant(Attaquant attq) {
		if (this.energie > attq.getEnergieMaxActuelle()) {
			this.energie -= attq.getEnergieMaxActuelle();
			this.poursuivreChemin();
		} else {
			this.setElimine(true);
			attq.setEnergieMaxActuelle(attq.getEnergieMaxActuelle() - this.energie);
		}
	}

	private void poursuivreChemin() {
		// TODO Auto-generated method stub

	}

	public boolean isElimine() {
		return elimine;
	}

	public void setElimine(boolean elimine) {
		this.elimine = elimine;
	}

	@Override
	public Projectile clone() {
		return new Projectile(name, masse, vitesse, portee, energie);
	}

	@Override
	public String getEtat() {
		/*
		 * return "Projectile \n nom : " + name + "\n" + "Est Vivant :" + !elimine +
		 * "energie :" + energie + "masse : " + masse + "vitesse : " + vitesse +
		 * "portee : " + portee;
		 */
		return getName();
	}

	@Override
	public void evoluer(Partie partie) {
		Attaquant at = partie.getAttaquantAt(getPosition());
		if (at != null) {
			croiserAttaquant(at);
		}
		avancer();
	}

}
