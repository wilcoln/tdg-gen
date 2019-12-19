package jeu;

import element.dynamique.Mobile;
import element.dynamique.Obstacle;

import java.util.ArrayList;
import java.util.List;

/**
 * Cette classe représente une Vague d'une partie d'un jeu de Tower Defense
 *
 * @author Wilfried L. Bounsi
 *
 */

public class Vague {
	private int bonusEnergie;
	private List<Obstacle> obstacles;
	private List<Mobile> mobiles;
	private boolean attenteInitailisee;
	private Long debutAttente;
	private boolean lancee;
	private int indiceProchainMobile;

	private void calculCheminDesMobiles(Partie partie) {
		for (Mobile mobile : mobiles) {
			mobile.calculChemin(partie);
		}
	}

	public List<Obstacle> getObstacles() {
		return obstacles;
	}
    public Vague(int bonusEnergie) {

        this.bonusEnergie = bonusEnergie;
        this.mobiles = new ArrayList<>();
        this.obstacles = new ArrayList<>();
        indiceProchainMobile = 0; // Aucun mobile déployé au début
    }


    public void deployerMobiles(Partie partie) {
        if(!attenteInitailisee){
            if(partie.getIndiceVagueActuelle() > 0){
				partie.addNotification("Nouvelle vague dans 1 seconde....");
				partie.afficheur.activerActionsPause();
			}
            attenteInitailisee = true;
            calculCheminDesMobiles(partie);
            debutAttente = System.currentTimeMillis();
			partie.addNotification("Infos Vague:\n" + getInfos());
        }else{
            long attente = System.currentTimeMillis() - debutAttente;
            if (partie.getIndiceVagueActuelle() == 0 || attente >= partie.getNiveauActuel().getDureePause() * 1000) {
                partie.afficheur.desactiverActionsPauseSiNecessaire();
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

    public int getBonusEnergie() {
        return bonusEnergie;
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
		return mobilesTousEliminesOuSortis() && !partie.perdue();
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
