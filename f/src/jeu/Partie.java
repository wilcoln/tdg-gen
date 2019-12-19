package jeu;

import config.Config;
import element.dynamique.*;
import utils.Position;
import element.statique.Terrain;
import utils.Timer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Cette classe représente une Partie d'un jeu de Tower Defense
 *
 * @author Wilfried L. Bounsi
 *
 */

public class Partie {
    private int indiceNiveauActuel;
    private int indiceVagueActuelle;
    public Afficheur afficheur;
    private Terrain terrain;
    private List<Niveau> niveaux;
    private List<Vague> vagues; // P14
    private int nbMobilesSortis;
    private List<Projectile> projectilesLances;
    private Joueur joueur;
	private List<Obstacle> obstaclesDispoPourVente;
	private List<String> notifications;
	private int nbTour;

    private boolean enPause;

    public Partie() {
        this.niveaux = new ArrayList<>();
        this.vagues = new ArrayList<>();
        this.projectilesLances = new ArrayList<>();
        this.obstaclesDispoPourVente = new ArrayList<>();
        this.notifications = new ArrayList<>();
        this.enPause = false;
        this.nbTour = 0;
    }

    public void commencer() throws IOException {
        joueur = new Joueur(this);
        afficheur = new Afficheur(this);
        afficheur.affichageDebutPartie();
        while (!terminee()) {
            if(!enPause){
                nbTour++;
                evoluer();
            }
            afficheur.afficherTerrain();
            afficheur.afficherNotifs();
            Timer.attendre(Config.STEP_INTERVAL);
        }

        afficheur.affichageFinPartie();
    }

    public void evoluer() {
        notifications.add("Tour de jeu n°" + nbTour + "\n================");

        for (Projectile proj : getProjectilesPresents()) {
            proj.evoluer(this);
        }
        for (Attaquant m : getMobilesPresents()) {
            m.evoluer(this);
        }
        for (Attaquant o : getObstaclesPresents()) {
            o.evoluer(this);
        }

        getVagueActuelle().deployerMobiles(this);

        if (getVagueActuelle().echouee(this)) {
            notifications.add("Vague terminée!");
            joueur.gagneBonusEnergie(getVagueActuelle().getBonusEnergie());
            indiceVagueActuelle++;
        }
        if (indiceVagueActuelle == vagues.size()) {
            joueur.setEnergieInitialeDuNiveau(getNiveauActuel());
            indiceNiveauActuel++;
            if (!terminee()) {
                indiceVagueActuelle = 0;
            }
        }
        notifications.add(joueur.getJaugeEnergieJoueur());
        notifications.add("nb mobiles sortis : " + nbMobilesSortis + " (tolerance = " + getToleranceActuelle() + ")");
        if(indiceVagueActuelle < vagues.size() && !getVagueActuelle().isLancee()){
            notifications.clear();
            notifications.add("...\r");
        }

    }

    public int getIndiceVagueActuelle() {
        return indiceVagueActuelle;
    }

    public Niveau getNiveauActuel(){
        return niveaux.get(getIndiceNiveauActuel());
    }

    // P10 P11 P12
    public boolean terminee() {
        return (indiceNiveauActuel == niveaux.size()) || perdue();
    }

    public Terrain getTerrain() {
        return terrain;
    }

    public void setTerrain(Terrain terrain) {
        this.terrain = terrain;
    }

    public List<Niveau> getNiveaux() {
        return niveaux;
    }

    public List<Vague> getVagues() {
        return vagues;
    }

    public List<Attaquant> getMobilesPresents() {
        List<Attaquant> resultats = new ArrayList<>();
        if (!terminee()) {
            Vague vagueActuel = getVagueActuelle();
            for (Mobile m : vagueActuel.getMobiles()) {
                if (!m.isSorti() && !m.isElimine() && terrain.contientPosition(m.getPosition()))
                    resultats.add(m);
            }
        }
        return resultats;
    }

    public List<Attaquant> getObstaclesPresents() {
        List<Attaquant> resultats = new ArrayList<>();
        if (!terminee()) {
            if (getVagueActuelle().isLancee()) {
                for (Obstacle obstacle : getVagueActuelle().getObstacles()) {
                    if (!obstacle.isElimine()) {
                        resultats.add(obstacle);
                    }
                }
            }

            for (Obstacle obstacle : joueur.getObstacles()) {
                if (terrain.contientPosition(obstacle.getPosition())) {
                    resultats.add(obstacle);
                }
            }
        }
        return resultats;
    }

    private Vague getVagueActuelle() {
        if(indiceVagueActuelle < vagues.size())
            return vagues.get(getIndiceVagueActuelle());
        return null;
    }

    public List<Projectile> getProjectilesPresents() {
        List<Projectile> resultats = new ArrayList<>();
        if (!terminee()) {
            for (Projectile p : projectilesLances) {
                if (!p.isElimine())
                    resultats.add(p);
            }
        }
        return resultats;
    }

    public Joueur getJoueur() {
        return joueur;
    }

    public boolean perdue() {
        return getNbMobilesSortis() > getToleranceActuelle();
    }

    private int getToleranceActuelle() {
        int toleranceActuelle = 0;
        for (int i = 0; i <= getIndiceNiveauActuel(); i++)
            toleranceActuelle += niveaux.get(i).getToleranceMobiles();
        return toleranceActuelle;
    }

    private int getIndiceNiveauActuel() {
        if(indiceNiveauActuel < niveaux.size())
            return indiceNiveauActuel;
        return indiceNiveauActuel - 1;
    }

    public int getNbMobilesSortis() {
        return nbMobilesSortis;
    }

    public void incrementerNbMobileSortis() {
        nbMobilesSortis++;
    }

    public String getEtatAtPositionVerbose(Position pos) {
        StringBuilder etat = new StringBuilder();
        if (terrain.contientPosition(pos)) {
            // Ajout de l'état du terrain en la position
            etat.append("Case " + terrain.getNatureTerrainAtPosition(pos).getEtat() + "\n------\n");
            // Ajout des états des obstacles sur la position
            for (Attaquant o : getObstaclesPresents()) {
                if (o.getPosition().equals(pos))
                    etat.append("-> " + o.getEtat());
            }
            // Ajout des états des mobiles sur la position
            for (Attaquant m : getMobilesPresents()) {
                if (m.getPosition().equals(pos))
                    etat.append("-> " + m.getEtat());

            }
            // Ajout des états des projectiles sur la position
            for (Projectile proj : getProjectilesPresents()) {
                if (proj.getPosition().equals(pos))
                    etat.append("-> " + proj.getEtat());
            }
        }
        return etat.toString();
    }
    public String getEtatAtPosition(Position pos) {
        StringBuilder etat = new StringBuilder();
        if (terrain.contientPosition(pos)) {
            // Ajout de l'état du terrain en la position
            etat.append(terrain.getNatureTerrainAtPosition(pos).getSymbol() + ": ");
            // Ajout des états des obstacles sur la position
            for (Attaquant o : getObstaclesPresents()) {
                if (o.getPosition().equals(pos))
                    etat.append(o.getNom() + " ");
            }
            // Ajout des états des mobiles sur la position
            for (Attaquant m : getMobilesPresents()) {
                if (m.getPosition().equals(pos))
                    etat.append(m.getNom() + " ");

            }
            // Ajout des états des projectiles sur la position
            for (Projectile proj : getProjectilesPresents()) {
                if (proj.getPosition().equals(pos))
                    etat.append(proj.getNom() + " ");
            }
        }
        return etat.toString();
    }

    public List<Attaquant> getMobilesAt(Position position) {
        List<Attaquant> mobiles = new ArrayList<>();
        for (Attaquant mobile : getMobilesPresents()) {
            if (mobile.getPosition().equals(position))
                mobiles.add(mobile);
        }
        return mobiles;
    }

    public List<Attaquant> getObstaclesAt(Position position) {
        List<Attaquant> obstacles = new ArrayList<>();
        for (Attaquant obstacle : getObstaclesPresents()) {
            if (obstacle.getPosition().equals(position))
                obstacles.add(obstacle);
        }
        return obstacles;
    }

    public void donneBonusEnergieJoueur(int bonusEnergie) {
        getJoueur().gagneBonusEnergie(bonusEnergie);
    }

    public List<Projectile> getProjectilesLances() {
        return projectilesLances;
    }

    public void addNotification(String notif) {
        notifications.add(notif);
    }

    public int getVolumeOccupeAt(Position pos) {
        int resultat = 0;
        for (Attaquant mobile: getMobilesAt(pos)) {
            resultat += ((Mobile)mobile).getVolume();
        }
        return resultat;
    }

    public void setEnPause(boolean enPause) {
        this.enPause = enPause;
    }
	public List<Obstacle> getObstaclesDispoPourVente() {
		return this.obstaclesDispoPourVente;
	}

    public List<String> getNotifications() {
        return notifications;
    }

    public boolean enPause() {
        return enPause;
    }
}
