package jeu;

import graphics.AfficheurGraphique;
import graphics.swing.AfficheurSwing;
import utils.Position;

import javax.swing.*;
import java.io.IOException;

/**
 * Cette classe est l'afficheur utilisé pour le jeu, elle se charge à la fois de l'affichage graphique et de l'affichage console.
 * Elle a donc besoin d'un attribut {@link AfficheurGraphique}, ici initialisé à un  {@link AfficheurSwing} dans le constructeur
 *
 * @author Wilfried L. Bounsi
 *
 */

public class Afficheur {
    // Utile à l'affichage textuelle
    private String etatCourant = "";
    private String etatPrec = "";
    private Partie partie;
    private boolean pauseActivee = false;

    private AfficheurGraphique afficheurGraphique;

    public Afficheur(Partie partie) throws IOException {
        this.partie = partie;
        afficheurGraphique = new AfficheurSwing(partie);
    }
    public void afficherTerrain() {
        affichageTerrainConsole();
        afficheurGraphique.afficherTerrain();
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
        if (partie.perdue()) {
            String msg = "=== FIN ==> Le joueur a perdu!";
            System.out.println(msg);
            JOptionPane.showMessageDialog(null, msg);
        } else {
            String msg = "=== FIN ==> Le joueur a gagné!";
            System.out.println(msg);
            JOptionPane.showMessageDialog(null, msg);
        }
    }

	public void activerActionsPause() {
		this.afficheurGraphique.activerActionsPause();
		pauseActivee = true;
	}

    public void desactiverActionsPauseSiNecessaire() {
        if(pauseActivee){
            afficheurGraphique.desactiverActionsPause();
            pauseActivee = false;
        }
    }

    public void afficherNotifs() {
        for(String notif: partie.getNotifications())
            System.out.println(notif);
        partie.getNotifications().clear();
    }

    public void afficherMessage(String msg) {
        afficheurGraphique.afficherMessage(msg);
        System.out.println(msg);
    }
}
