package jeu;

import element.actif.Obstacle;
import element.passif.Campement;
import utils.Position;

public class Joueur {
    private int energie;
    private Partie partie;
    public Joueur(Partie partie){
        this.partie = partie;
        this.energie = partie.getNiveaux().get(0).getEnergieInitiale();
    }
    // TODO P15--P17
    public void acheterObstacle(Obstacle obstacle) {

    }

    // TODO P15--P17
    public void vendreObstacle(Obstacle obstacle) {

    }
    // TODO P9
    public void reparerObstacle(Obstacle obstacle) {
        obstacle.augmenterEnergieMaxActuelle(1);
    }
    // TODO P15--P17
    // a priori la methode deplacer() fera aussi placer()

    public void deplacerObstacle(Obstacle o, Position p) {
        if(partie.getTerrain().getNatureTerrainAtPosition(p) instanceof Campement){
            o.setPosition(p);
        }
    }

    public void gagneBonusEnergie(int bonusEnergie) {
        this.energie += bonusEnergie;
    }

    public void setEnergieInitialeDuNiveau(Niveau niveau){
        this.energie = niveau.getEnergieInitiale();
    }

    public int getEnergie() {
        return energie;
    }
}
