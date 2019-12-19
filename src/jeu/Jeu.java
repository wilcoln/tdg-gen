package jeu;

import element.Element;

import java.util.ArrayList;
import java.util.List;

/**
 * Cette classe contient les éléments qui composent un jeu de Tower Defense
 *
 * @author Elmontassir Rachid
 * @author Wilfried L. Bounsi
 *
 */
public class Jeu {
	private String nom;
	private List<Element> elements;
	private List<Partie> parties;

	public Jeu(String nom) {
		this.nom = nom;
		this.elements = new ArrayList<Element>();
		this.parties = new ArrayList<Partie>();
	}
	public List<Element> getElements() {
		return elements;
	}

	public List<Partie> getParties() {
		return parties;
	}
	public String getNom() {
		return nom;
	}
}
