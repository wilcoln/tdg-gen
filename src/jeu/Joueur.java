package jeu;

import javax.swing.JOptionPane;

import config.Config;
import element.actif.Obstacle;
import element.passif.Campement;
import utils.Position;

public class Joueur {
	private int energie;
	private Partie partie;

	public Joueur(Partie partie) {
		this.partie = partie;
		this.energie = partie.getNiveaux().get(0).getEnergieInitiale();
	}
	// TODO P15--P17

	// TODO P15--P17
	public void acheterObstacle(Obstacle obstacle) {
		if (partie.getJoueur().energie >= obstacle.getEnergieDispo()) {
			partie.getObstaclesPlaces().add(obstacle);
			partie.getJoueur().energie -= obstacle.getEnergieDispo();
			System.out.println("<++++> un obstacle acheté : "+obstacle.getNom());
		} else {
			JOptionPane.showMessageDialog(null, "L'energie du joueur n'est pas suffisante pour effectuer cet achat");
		}
	}

	// TODO P15--P17
	public void vendreObstacle(Obstacle obstacle) {
		if (partie.getVagues().get(partie.indiceVagueActuelle).getObstacles().contains(obstacle)) {
			partie.getObstaclesPlaces().remove(obstacle);
			partie.getJoueur().energie += obstacle.getEnergieDispo();
			System.out.println("<----> un obstacle vendu : "+obstacle.getNom());
		} else {
			JOptionPane.showMessageDialog(null, "Le joueur ne dispose plus de cet article");
		}
	}

	// TODO P9
	public void reparerObstacle(Obstacle obstacle) {
		if (partie.getJoueur().energie >= Config.PRIX_REPARATION) {
			obstacle.augmenterEnergieMaxActuelle(Config.PRIX_REPARATION);
			partie.getJoueur().energie -= Config.PRIX_REPARATION;
		} else {
			JOptionPane.showMessageDialog(null,
					"L'energie du joueur n'est pas suffisante pour effectuer cet reparation");
		}
	}
	// TODO P15--P17

	public void deplacerObstacle(Obstacle obstacle, Position p) {
		if (partie.getJoueur().energie >= Config.PRIX_DEPLACEMENT) {
				obstacle.setPosition(p);
				partie.getJoueur().energie -= Config.PRIX_DEPLACEMENT;
			if (!partie.getObstaclesPlaces().contains(obstacle)) {
				partie.getObstaclesPlaces().add(obstacle);
			} 
		} else {
			JOptionPane.showMessageDialog(null,
					"L'energie du joueur n'est pas suffisante pour effectuer ce deplacement");
		}
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
