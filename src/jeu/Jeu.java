package jeu;

import element.Element;

import java.util.ArrayList;
import java.util.List;

public class Jeu {
	private String name;
	private List<Element> elements;
	private List<Partie> parties;

	public Jeu(String name) {
		this.setName(name);
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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
