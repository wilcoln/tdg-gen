package jeu;

import javax.swing.JOptionPane;

import config.Config;
import element.actif.Obstacle;
import utils.Position;

import java.util.ArrayList;
import java.util.List;

public class Joueur {
	private int energie;
	private Partie partie;

	private List<Obstacle> obstacles;

	public Joueur(Partie partie) {
		this.partie = partie;
		this.energie = partie.getNiveaux().get(0).getEnergieInitiale();
		this.obstacles = new ArrayList<>();
	}
	// TODO P15--P17

	// TODO P15--P17
	public void acheterObstacle(Obstacle obstacle) {
		if (energie >= obstacle.getEnergieDispo()) {
			obstacles.add(obstacle);
			energie -= obstacle.getEnergieDispo();
			//System.out.println("<++++> un obstacle acheté : "+obstacle.getNom());
		} else {
			//TODO show notifications plutôt
			JOptionPane.showMessageDialog(null, "L'energie du joueur n'est pas suffisante pour effectuer cet achat");
		}
	}

	// TODO P15--P17
	public void vendreObstacle(Obstacle obstacle) {
			obstacles.remove(obstacle);
			energie += obstacle.getEnergieDispo();
			// System.out.println("<----> un obstacle vendu : "+obstacle.getNom());
	}

	// TODO P9
	public void reparerObstacle(Obstacle obstacle) {
		if (energie >= obstacle.coutReparation()) {
			obstacle.reparer();
			energie -= obstacle.coutReparation();
		} else {
			JOptionPane.showMessageDialog(null,
					"L'energie du joueur n'est pas suffisante pour effectuer cet reparation");
		}
	}
	// TODO P15--P17

	public void deplacerObstacle(Obstacle obstacle, Position p) {
		if (energie >= obstacle.coutReparation()) {
				obstacle.setPosition(p);
				partie.getJoueur().energie -= obstacle.coutReparation();
		} else {
			JOptionPane.showMessageDialog(null,
					"L'energie du joueur n'est pas suffisante pour effectuer ce deplacement");
		}
	}

	public List<Obstacle> getObstacles() {
		return obstacles;
	}

	public void gagneBonusEnergie(int bonusEnergie) {
		this.energie += bonusEnergie;
	}

	public void setEnergieInitialeDuNiveau(Niveau niveau) {
		this.energie = niveau.getEnergieInitiale();
	}

	public int getEnergie() {
		return energie;
	}
}
