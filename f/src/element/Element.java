package element;

import jeu.Partie;
import utils.Position;

/**
 * Cette classe représente un élément d'un jeu de TowerDefense,
 * Elle possède un attribut {@link Position} car chaque élément est positionable sur le terrain
 *
 * @author Wilfried L. Bounsi
 *
 */

public abstract class Element {
	private String nom;
	private Position position;


	public Element(String nom){
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
}
