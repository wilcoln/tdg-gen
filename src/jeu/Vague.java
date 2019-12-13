package jeu;

import element.actif.MobileVague;
import element.actif.ObstacleVague;

import java.util.ArrayList;
import java.util.List;

public class Vague {
    private int energieJoueur;
    private List<ObstacleVague> obstaclesVague;
    private List<MobileVague> mobilesVague;

	public int getIndiceProchainMobile() {
		return indiceProchainMobile;
	}

	public void setIndiceProchainMobile(int indiceProchainMobile) {
		this.indiceProchainMobile = indiceProchainMobile;
	}

	private int indiceProchainMobile;

    public Vague(int energieJoueur) {

        this.energieJoueur = energieJoueur;
        this.mobilesVague = new ArrayList<>();
        this.obstaclesVague = new ArrayList<>();
        indiceProchainMobile = 0; // Aucun mobile déployé au début
    }

    // P5,P6
    public void lancerTour(Partie partie) {
        if (indiceProchainMobile < mobilesVague.size()) {
            if (mobilesVague.get(indiceProchainMobile).peutEntrer(partie)) {
                mobilesVague.get(indiceProchainMobile).entrer();
                indiceProchainMobile++;
            }
        }
        partie.activerMobilesPresents();
        partie.activerObstaclesPresents();
        partie.activerProjectilesPresents();
    }

    public List<ObstacleVague> getObstaclesVague() {
        return obstaclesVague;
    }

    public List<MobileVague> getMobilesVague() {
        return mobilesVague;
    }

    public int getEnergieJoueur() {
        return energieJoueur;
    }

    public boolean echouee(Partie partie){
    	return mobilesTousElimines() || (mobilesTousSortis() && !partie.defaiteJoueur());
	}
    public boolean deploye(Partie partie) {
        return partie.defaiteJoueur() || mobilesTousElimines() || mobilesTousSortis();
    }

    public boolean mobilesTousElimines(){
		for (MobileVague mv: mobilesVague) {
			if(!mv.getMobile().isElimine())
				return false;
		}
		return true;
	}
	public boolean mobilesTousSortis(){
		for (MobileVague mv: mobilesVague) {
			if(!mv.isSorti())
				return false;
		}
		return true;
	}

    public void setEnergieJoueur(int energieJoueur) {
        this.energieJoueur = energieJoueur;
    }
}
