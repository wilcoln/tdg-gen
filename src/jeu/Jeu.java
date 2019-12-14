package jeu;

import element.Element;

import java.util.ArrayList;
import java.util.List;

public class Jeu {
	private String nom;
	private List<Element> elements;
	private List<Partie> parties;

	public Jeu(String nom) {
		this.setNom(nom);
		this.elements = new ArrayList<Element>();
		this.parties = new ArrayList<Partie>();
	}
	public List<Element> getElements() {
		return elements;
	}

	public void setElements(List<Element> elements) {
		this.elements = elements;
	}

	public List<Partie> getParties() {
		return parties;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
}
