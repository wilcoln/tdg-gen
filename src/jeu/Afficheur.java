package jeu;

import graphics.AfficheurGraphique;
import utils.Position;

import javax.swing.*;
import java.io.IOException;

public class Afficheur {
    // Utile à l'affichage textuelle
    private String etatCourant = "";
    private String etatPrec = "";
    private Partie partie;
    private boolean pauseActivee = false;

    private AfficheurGraphique afficheurGraphique;

    public Afficheur(Partie partie) throws IOException {
        this.partie = partie;
        afficheurGraphique = new AfficheurGraphique(partie);
    }
    public void afficherTerrain() {
        affichageTerrainConsole();
        afficheurGraphique.afficheTerrain();
    }

    private void affichageTerrainConsole() {
        etatCourant = "";
        for (int i = 0; i < partie.getTerrain().getLargeur(); i++) {
            for (int j = 0; j < partie.getTerrain().getLongueur(); j++) {
                int nbCarRestants = 20;
                Position posCase = new Position(i + 1, j + 1);
                etatCourant += partie.getEtatAtPosition(posCase) + " ";
                nbCarRestants -= partie.getEtatAtPosition(posCase).length();
                for (int c = 0; c < nbCarRestants; c++) {
                    etatCourant += " ";
                }
            }
            etatCourant += "\n";
        }
        if (!etatPrec.equals(etatCourant)) {
            System.out.println();
            System.out.println(etatCourant);
            System.out.println();
            etatPrec = etatCourant;
        }
    }

    public void affichageDebutPartie() {
        System.out.println("Début Partie");
        System.out.println("Nombre de niveaux : " + partie.getNiveaux().size());
        for (Niveau n : partie.getNiveaux()) {
            System.out.println("Tolérance niveau " + n.getName() + ": " + n.getToleranceMobiles());
        }
        System.out.println("Nombre de vagues : " + partie.getVagues().size());
        System.out.println("\n----");
    }

    public void affichageFinPartie() {
        System.out.println("\n----");
        System.out.println("Partie terminée");
        System.out.println("Nombre de mobiles sortis : " + partie.getNbMobilesSortis());
        partie.indiceNiveauActuel -= partie.indiceNiveauActuel < partie.getNiveaux().size() ? 0 : 1;
        if (partie.defaiteJoueur()) {
            System.out.println("Le joueur a perdu!");
            afficheurGraphique.afficheDialog("Le joueur a perdu!");
        } else {
            System.out.println("Le joueur a gagné!");
            JOptionPane.showMessageDialog(null, "Le joueur a gagné!");
        }
    }

	public void afficherMenuPause() {
		this.afficheurGraphique.afficherMenuPause();
		pauseActivee = true;
	}

    public void cacherMenuPauseSiAffiche() {
        if(pauseActivee){
            afficheurGraphique.cacherMenuPause();
            pauseActivee = false;
        }
    }

    public void afficherNotifs() {
        for(String notif: partie.getNotifications())
            System.out.println(notif);
        partie.getNotifications().clear();
    }
}
