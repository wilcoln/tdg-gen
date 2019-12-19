package exemples;

import element.dynamique.Mobile;
import element.dynamique.Obstacle;
import element.dynamique.Projectile;
import element.dynamique.TactiqueType;
import element.statique.*;
import jeu.Jeu;
import jeu.Niveau;
import jeu.Partie;
import jeu.Vague;
import utils.Position;

import java.io.IOException;

public class ExempleObstacleTactiquePlusProche {

    public static void main(String[] args) throws IOException {

        // Définition du jeu
        Jeu jeu = new Jeu("ObsAttaquePlusFort");

        // Définition des élements du  jeu
        // Ajout des élements dans la liste des élts du jeu
        Sortie sortie = new Sortie("S","sortie");
        jeu.getElements().add(sortie.clone());

        Entree entree = new Entree("E","entree");
        jeu.getElements().add(entree.clone());

        Decoration montagne1 = new Decoration("M","montagne1");
        jeu.getElements().add(montagne1.clone());

        Decoration montagne2 = new Decoration("M","montagne2");
        jeu.getElements().add(montagne2.clone());

        Decoration montagne3 = new Decoration("M","montagne3");
        jeu.getElements().add(montagne3.clone());

        Decoration montagne4 = new Decoration("M","montagne4");
        jeu.getElements().add(montagne4.clone());

        Decoration montagne5 = new Decoration("M","montagne5");
        jeu.getElements().add(montagne5.clone());

        Projectile p = new Projectile("p",1,1,10,40);
        jeu.getElements().add(p.clone());

        Mobile mb1 = new Mobile("mb",1,10,1,1,TactiqueType.attaquePlusProche);
        jeu.getElements().add(mb1.clone());

        Mobile mb2 = new Mobile("mb",1,5,1,1,TactiqueType.attaquePlusProche);
        jeu.getElements().add(mb2.clone());

        Mobile mb3 = new Mobile("mb",1,7,1,1,TactiqueType.attaquePlusProche);
        jeu.getElements().add(mb2.clone());

        Obstacle ob = new Obstacle("ob",50,1,TactiqueType.attaquePlusProche);
        jeu.getElements().add(ob.clone());

        Chemin route1 = new Chemin("R","route1",1,1);
        jeu.getElements().add(route1.clone());

        Chemin route2 = new Chemin("R","route2",1,1);
        jeu.getElements().add(route2.clone());

        Chemin route3 = new Chemin("R","route3",1,1);
        jeu.getElements().add(route3.clone());

        Campement garage1 = new Campement("G","garage1");
        jeu.getElements().add(garage1.clone());

        Campement garage2 = new Campement("G","garage2");
        jeu.getElements().add(garage2.clone());

        Campement garage3 = new Campement("G","garage3");
        jeu.getElements().add(garage3.clone());

        Campement garage4 = new Campement("G","garage4");
        jeu.getElements().add(garage4.clone());

        Campement garage5 = new Campement("G","garage5");
        jeu.getElements().add(garage5.clone());


        //Affectation des projectiles aux obstacles et mobiles
        ob.getProjectiles().add(p.clone());

        // Définition des parties
        Partie partie = new Partie();
        partie.getObstaclesDispoPourVente().add(ob.clone());
        Niveau unique = new Niveau("unique", 0, 1, 2);
        partie.getNiveaux().add(unique);

        Vague v  = new Vague(0);

        Mobile mv3 = mb3.clone();
        mv3.setNom("mv3");
        mv3.setPosEntree(new Position(6, 6));
        mv3.setPosSortie(new Position(3, 6));
        // Ajout des éléments dans la vague
        v.getMobiles().add(mv3);

        Mobile mv1 = mb1.clone();
        mv1.setNom("mv1");
        mv1.setPosEntree(new Position(2, 1));
        mv1.setPosSortie(new Position(2, 5));
        // Ajout des éléments dans la vague
        v.getMobiles().add(mv1);
        Mobile mv2 = mb2.clone();
        mv2.setNom("mv2");
        mv2.setPosEntree(new Position(2, 11));
        mv2.setPosSortie(new Position(2, 7));
        // Ajout des éléments dans la vague
        v.getMobiles().add(mv2);

        Obstacle ov = ob.clone();
        ov.setNom("ov");
        ov.setPosition(new Position(2,6));
        // Ajout des éléments dans la vague
        v.getObstacles().add(ov);

        // ajout de la vague à la partie
        partie.getVagues().add(v);
        Vague v1  = new Vague(1);


        // ajout de la vague à la partie
        partie.getVagues().add(v1);

        // Définition du element.terrain de la partie
        Terrain terrain = new Terrain();

        // Définition des lignes du terrain
        Ligne l1 = new Ligne();
        l1.getCases().add(montagne5.clone());
        l1.getCases().add(montagne4.clone());
        l1.getCases().add(montagne3.clone());
        l1.getCases().add(montagne2.clone());
        l1.getCases().add(montagne1.clone());
        l1.getCases().add(montagne5.clone());
        l1.getCases().add(montagne4.clone());
        l1.getCases().add(montagne3.clone());
        l1.getCases().add(montagne2.clone());
        l1.getCases().add(montagne1.clone());
        l1.getCases().add(montagne1.clone());
        terrain.getLignes().add(l1);

        Ligne l2 = new Ligne();
        l2.getCases().add(entree.clone());
        l2.getCases().add(route3.clone());
        l2.getCases().add(route2.clone());
        l2.getCases().add(route1.clone());
        l2.getCases().add(sortie.clone());
        l2.getCases().add(garage1.clone());
        l2.getCases().add(sortie.clone());
        l2.getCases().add(route3.clone());
        l2.getCases().add(route2.clone());
        l2.getCases().add(route1.clone());
        l2.getCases().add(entree.clone());
        terrain.getLignes().add(l2);

        Ligne l3 = new Ligne();
        l3.getCases().add(montagne5.clone());
        l3.getCases().add(montagne4.clone());
        l3.getCases().add(montagne3.clone());
        l3.getCases().add(montagne2.clone());
        l3.getCases().add(montagne1.clone());
        l3.getCases().add(sortie.clone());
        l3.getCases().add(montagne4.clone());
        l3.getCases().add(montagne3.clone());
        l3.getCases().add(montagne2.clone());
        l3.getCases().add(montagne1.clone());
        l3.getCases().add(montagne1.clone());
        terrain.getLignes().add(l3);

        Ligne l4 = new Ligne();
        l4.getCases().add(montagne5.clone());
        l4.getCases().add(montagne4.clone());
        l4.getCases().add(montagne3.clone());
        l4.getCases().add(montagne2.clone());
        l4.getCases().add(montagne1.clone());
        l4.getCases().add(route1.clone());
        l4.getCases().add(montagne4.clone());
        l4.getCases().add(montagne3.clone());
        l4.getCases().add(montagne2.clone());
        l4.getCases().add(montagne1.clone());
        l4.getCases().add(montagne1.clone());
        terrain.getLignes().add(l4);

        Ligne l45 = new Ligne();
        l45.getCases().add(montagne5.clone());
        l45.getCases().add(montagne4.clone());
        l45.getCases().add(montagne3.clone());
        l45.getCases().add(montagne2.clone());
        l45.getCases().add(montagne1.clone());
        l45.getCases().add(route1.clone());
        l45.getCases().add(montagne4.clone());
        l45.getCases().add(montagne3.clone());
        l45.getCases().add(montagne2.clone());
        l45.getCases().add(montagne1.clone());
        l45.getCases().add(montagne1.clone());
        terrain.getLignes().add(l45);

        Ligne l5 = new Ligne();
        l5.getCases().add(montagne5.clone());
        l5.getCases().add(montagne4.clone());
        l5.getCases().add(montagne3.clone());
        l5.getCases().add(montagne2.clone());
        l5.getCases().add(montagne1.clone());
        l5.getCases().add(entree.clone());
        l5.getCases().add(montagne4.clone());
        l5.getCases().add(montagne3.clone());
        l5.getCases().add(montagne2.clone());
        l5.getCases().add(montagne1.clone());
        l5.getCases().add(montagne1.clone());
        terrain.getLignes().add(l5);


        partie.setTerrain(terrain);
        partie.commencer();
    }
}

