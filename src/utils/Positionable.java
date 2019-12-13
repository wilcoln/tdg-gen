package utils;

import jeu.Partie;
import utils.Position;

public abstract class Positionable {
	private Position position;
	public Positionable(){
		this.position = new Position(-1, -1);
	}
	public Position getPosition() {
		return position;
	}

	public void setPosition(Position position) {
		this.position = position;
	}


    public abstract String getEtat();

    public abstract void evoluer(Partie partie);
}
