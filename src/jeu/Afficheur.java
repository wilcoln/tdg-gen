package jeu;

import element.actif.Mobile;
import graphics.AfficheurGraphique;
import utils.Position;

import javax.swing.*;
import java.io.IOException;

public class Afficheur {
    // Utile à l'affichage textuelle
    private String etatCourant = "";
    private String etatPrec = "";
    private Partie partie;
    private AfficheurGraphique afficheurGraphique;

    public Afficheur(Partie partie) throws IOException {
        this.partie = partie;
        afficheurGraphique = new AfficheurGraphique(partie);
    }
    public void afficherTerrain() {
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
            System.out.println("-> ");
            System.out.println(etatCourant);
            etatPrec = etatCourant;
        }
        afficheurGraphique.afficheTerrain();
    }
    public void affichageDebutPartie() {
        System.out.println("Début Partie");
        System.out.println("Nombre de niveaux : " + partie.getNiveaux().size());
        for (Niveau n : partie.getNiveaux()) {
            System.out.println("Tolérance niveau " + n.getName() + ": " + n.getToleranceMobiles());
        }
        System.out.println("Nombre de vagues : " + partie.getVagues().size());
        System.out.println("\n----");
        afficherTerrain();
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
    public void afficheDialog(String msg){
        afficheurGraphique.afficheDialog(msg);
    }

    public void afficherVagues(){
        for(int i = 0; i < partie.getVagues().size(); i++) {
            System.out.println("Vague N°" + (i+1) );
            System.out.println("===============");
            afficherMobilesDeLaVague(partie.getVagues().get(i));
        }
    }

    public void afficherMobilesDeLaVague(Vague vague) {
        for (Mobile m : vague.getMobiles()) {
            System.out.println(m.getEtat());
        }
    }
    public void afficherEnergieJoueur(){
        String msg ="Energie Joueur : ";
        for(int i = 0; i < partie.getJoueur().getEnergie(); i++){
            msg+="#";
        }
        msg += "\n";
        System.out.println(msg);
    }
}
