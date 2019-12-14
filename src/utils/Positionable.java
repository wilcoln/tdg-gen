package utils;

import jeu.Partie;
import utils.Position;

public abstract class Positionable {
	private String nom;
	private Position position;


	public Positionable(String nom){
		this.nom = nom;
		this.position = new Position(-1, -1);
	}

	public Position getPosition() {
		return position;
	}

	public void setPosition(Position position) {
		this.position = position;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

    public abstract String getEtat();

    public abstract void evoluer(Partie partie);
}
