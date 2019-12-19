package jeu;

import element.dynamique.Obstacle;
import utils.Position;

import java.util.ArrayList;
import java.util.List;

/**
 * Cette classe représente le Joueur d'un jeu de Tower Defense
 *
 * @author Elmontassir Rachid
 * @author Wilfried L. Bounsi
 *
 */

public class Joueur {
	private int energie;
	private Partie partie;

	private List<Obstacle> obstacles;

	public Joueur(Partie partie) {
		this.partie = partie;
		this.energie = partie.getNiveaux().get(0).getEnergieInitiale();
		this.obstacles = new ArrayList<>();
	}

	public void acheterObstacle(Obstacle obstacle) {
		if (energie >= obstacle.getEnergieDispo()) {
			obstacles.add(obstacle);
			energie -= obstacle.getEnergieDispo();
		} else {
			this.partie.afficheur.afficherMessage("L'energie du joueur n'est pas suffisante pour effectuer cet achat");
		}
	}


	public void vendreObstacle(Obstacle obstacle) {
			obstacles.remove(obstacle);
			energie += obstacle.getEnergieDispo();
	}

	public void reparerObstacle(Obstacle obstacle) {
		if (energie >= obstacle.coutReparation()) {
			obstacle.reparer();
			energie -= obstacle.coutReparation();
		} else {
			partie.afficheur.afficherMessage("L'energie du joueur n'est pas suffisante pour effectuer cet reparation");
		}
	}

	public void deplacerObstacle(Obstacle obstacle, Position p) {
		if (energie >= obstacle.coutDeplacement()) {
				obstacle.setPosition(p);
				partie.getJoueur().energie -= obstacle.coutDeplacement();
		} else {
			partie.afficheur.afficherMessage("L'energie du joueur n'est pas suffisante pour effectuer ce déplacement");
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

    public String getJaugeEnergieJoueur(){
        String msg = "Jauge Energie Joueur : ";
        for(int i = 0; i < energie; i++){
            msg+="#";
        }
        msg += " : " + energie + "\n" ;
        return msg;
    }
}
