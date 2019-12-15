package jeu;

import element.actif.Mobile;
import element.actif.Obstacle;

import java.util.ArrayList;
import java.util.List;

public class Vague {
    private int energieJoueur;
    private List<Obstacle> obstacles;
    private List<Mobile> mobiles;
    private boolean attenteInitailisee;
    private long debutAttente;
    private boolean lancee;

    public int getIndiceProchainMobile() {
		return indiceProchainMobile;
	}

	public void setIndiceProchainMobile(int indiceProchainMobile) {
		this.indiceProchainMobile = indiceProchainMobile;
	}

	private int indiceProchainMobile;

    public Vague(int energieJoueur) {

        this.energieJoueur = energieJoueur;
        this.mobiles = new ArrayList<>();
        this.obstacles = new ArrayList<>();
        indiceProchainMobile = 0; // Aucun mobile déployé au début
    }

    // P5,P6
    public void deployerMobiles(Partie partie) {
        if(!attenteInitailisee){
            if(partie.indiceVagueActuelle > 0)
                partie.afficheur.activerPause();
            attenteInitailisee = true;
            calculCheminDesMobiles(partie);
            debutAttente = System.currentTimeMillis();
        }else{
            long attente = System.currentTimeMillis() - debutAttente;
            if (partie.indiceVagueActuelle == 0 || attente >= partie.getNiveaux().get(partie.indiceNiveauActuel).getDureePause() * 1000) {
                partie.afficheur.desactiverPauseIfActivee();
                lancee = true;
                if (indiceProchainMobile < mobiles.size()) {
                    if (mobiles.get(indiceProchainMobile).peutEntrer(partie)) {
                        mobiles.get(indiceProchainMobile).entrer();
                        indiceProchainMobile++;
                    }
                }
            }
        }

    }

    private void calculCheminDesMobiles(Partie partie) {
        for (Mobile mobile: mobiles) {
            mobile.calculChemin(partie);
        }
    }

    public List<Obstacle> getObstacles() {
        return obstacles;
    }

    public List<Mobile> getMobiles() {
        return mobiles;
    }

    public int getEnergieJoueur() {
        return energieJoueur;
    }

    public boolean echouee(Partie partie){
    	return mobilesTousEliminesOuSortis() && !partie.defaiteJoueur();
	}
    public boolean deploye(Partie partie) {
        return partie.defaiteJoueur() || mobilesTousEliminesOuSortis();
    }

    public boolean mobilesTousEliminesOuSortis(){
		for (Mobile m: mobiles) {
			if(!m.isElimine() && !m.isSorti())
				return false;
		}
		return true;
	}

    public void setEnergieJoueur(int energieJoueur) {
        this.energieJoueur = energieJoueur;
    }

    public boolean isLancee() {
        return lancee;
    }
}
