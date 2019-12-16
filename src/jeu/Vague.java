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
	private Long debutAttente;
	private boolean lancee;
	private int indiceProchainMobile;

	// P5,P6


	private void calculCheminDesMobiles(Partie partie) {
		for (Mobile mobile : mobiles) {
			mobile.calculChemin(partie);
		}
	}

	public List<Obstacle> getObstacles() {
		return obstacles;
	}
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
				partie.getNotifications().add("Nouvelle vague dans 1 seconde....");
                partie.afficheur.activerPause();
            attenteInitailisee = true;
            calculCheminDesMobiles(partie);
            debutAttente = System.currentTimeMillis();
			partie.getNotifications().add("Infos Vague:\n" + getInfos());
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

    public List<Mobile> getMobiles() {
        return mobiles;
    }

    public int getEnergieJoueur() {
        return energieJoueur;
    }
	public String getInfos(){
    	String result = "";
		for (Mobile m :getMobiles()) {
			result += m.getEtat();
		}
		for (Obstacle o :getObstacles()) {
			result += o.getEtat();
		}
		return result;
	}

	public boolean echouee(Partie partie) {
		return mobilesTousEliminesOuSortis() && !partie.defaiteJoueur();
	}

	public boolean mobilesTousEliminesOuSortis() {
		for (Mobile m : mobiles) {
			if (!m.isElimine() && !m.isSorti())
				return false;
		}
		return true;
	}

	public boolean isLancee() {
		return lancee;
	}
}
