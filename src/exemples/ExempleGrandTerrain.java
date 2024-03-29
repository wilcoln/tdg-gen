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

public class ExempleGrandTerrain {
    public static void main(String[] args) throws IOException {

            // Définition du jeu
            Jeu jeu = new Jeu("tests.EnigmeGrand");

            // Définition des élements du  jeu
            // Ajout des élements dans la liste des élts du jeu
            Entree entree = new Entree("E","entree");
            jeu.getElements().add(entree.clone());

            Projectile P = new Projectile("P",1,1,2,5);
            jeu.getElements().add(P.clone());

            Mobile MB = new Mobile("MB",1,25,1,1, TactiqueType.attaquePlusFaible);
            jeu.getElements().add(MB.clone());

            Sortie sortie = new Sortie("S","sortie");
            jeu.getElements().add(sortie.clone());

            Obstacle O = new Obstacle("O",25,1,TactiqueType.attaquePlusFaible);
            jeu.getElements().add(O.clone());

            Decoration montagne1 = new Decoration("M","montagne1");
            jeu.getElements().add(montagne1.clone());

            Decoration montagne2 = new Decoration("M","montagne2");
            jeu.getElements().add(montagne2.clone());

            Chemin route1 = new Chemin("R","route1",1,3);
            jeu.getElements().add(route1.clone());

            Chemin route4 = new Chemin("R","route4",1,3);
            jeu.getElements().add(route4.clone());

            Chemin route3 = new Chemin("R","route3",1,3);
            jeu.getElements().add(route3.clone());

            Chemin route6 = new Chemin("R","route6",1,3);
            jeu.getElements().add(route6.clone());

            Campement garage1 = new Campement("G","garage1");
            jeu.getElements().add(garage1.clone());

            Campement garage2 = new Campement("G","garage2");
            jeu.getElements().add(garage2.clone());


            //Affectation des projectiles aux obstacles et mobiles
            MB.getProjectiles().add(P.clone());
            O.getProjectiles().add(P.clone());

            // Définition des parties
            Partie partie = new Partie();
            partie.getObstaclesDispoPourVente().add(O.clone());
            Niveau unique = new Niveau("unique", 1, 4, 5);
            partie.getNiveaux().add(unique);

            Vague v1  = new Vague(3);
            Mobile mv1 = MB.clone();
            mv1.setNom("mv1");
            mv1.setPosEntree(new Position(2, 1));
            mv1.setPosSortie(new Position(2, 11));
            // Ajout des éléments dans la vague
            v1.getMobiles().add(mv1);
            Mobile mv2 = MB.clone();
            mv2.setNom("mv2");
            mv2.setPosEntree(new Position(2, 1));
            mv2.setPosSortie(new Position(7, 9));
            // Ajout des éléments dans la vague
            v1.getMobiles().add(mv2);
            Mobile mv3 = MB.clone();
            mv3.setNom("mv3");
            mv3.setPosEntree(new Position(5, 1));
            mv3.setPosSortie(new Position(8, 5));
            // Ajout des éléments dans la vague
            v1.getMobiles().add(mv3);
            Mobile mv4 = MB.clone();
            mv4.setNom("mv4");
            mv4.setPosEntree(new Position(2, 1));
            mv4.setPosSortie(new Position(4, 11));
            // Ajout des éléments dans la vague
            v1.getMobiles().add(mv4);
            Mobile mv5 = MB.clone();
            mv5.setNom("mv5");
            mv5.setPosEntree(new Position(4, 4));
            mv5.setPosSortie(new Position(2, 11));
            // Ajout des éléments dans la vague
            v1.getMobiles().add(mv5);
            Mobile mv6 = MB.clone();
            mv6.setNom("mv6");
            mv6.setPosEntree(new Position(1, 7));
            mv6.setPosSortie(new Position(8, 3));
            // Ajout des éléments dans la vague
            v1.getMobiles().add(mv6);
            Mobile mv7 = MB.clone();
            mv7.setNom("mv7");
            mv7.setPosEntree(new Position(2, 1));
            mv7.setPosSortie(new Position(7, 9));
            // Ajout des éléments dans la vague
            v1.getMobiles().add(mv7);
            Mobile mv8 = MB.clone();
            mv8.setNom("mv8");
            mv8.setPosEntree(new Position(1, 7));
            mv8.setPosSortie(new Position(7, 9));
            // Ajout des éléments dans la vague
            v1.getMobiles().add(mv8);

            Obstacle ov1 = O.clone();
            ov1.setNom("ov1");
            ov1.setPosition(new Position(2,4));
            // Ajout des éléments dans la vague
            v1.getObstacles().add(ov1);
            Obstacle ov2 = O.clone();
            ov2.setNom("ov2");
            ov2.setPosition(new Position(6,6));

            // Ajout des éléments dans la vague
            v1.getObstacles().add(ov2);
            Obstacle ov3 = O.clone();
            ov3.setNom("ov3");
            ov3.setPosition(new Position(4,8));

            // Ajout des éléments dans la vague
            v1.getObstacles().add(ov3);
            Obstacle ov4 = O.clone();
            ov4.setNom("ov4");
            ov4.setPosition(new Position(4,6));

            // Ajout des éléments dans la vague
            v1.getObstacles().add(ov4);
            Obstacle ov5 = O.clone();
            ov5.setNom("ov5");
            ov5.setPosition(new Position(7,2));

            // Ajout des éléments dans la vague
            v1.getObstacles().add(ov5);
            Obstacle ov6 = O.clone();
            ov6.setNom("ov6");
            ov6.setPosition(new Position(6,8));
            // Ajout des éléments dans la vague
            v1.getObstacles().add(ov6);

            // ajout de la vague à la partie
            partie.getVagues().add(v1);

            // Définition du element.terrain de la partie
            Terrain terrain1 = new Terrain();

            // Définition des lignes du terrain
            Ligne l1 = new Ligne();
            l1.getCases().add(garage1.clone());
            l1.getCases().add(route6.clone());
            l1.getCases().add(route3.clone());
            l1.getCases().add(route4.clone());
            l1.getCases().add(route1.clone());
            l1.getCases().add(montagne1.clone());
            l1.getCases().add(entree.clone());
            l1.getCases().add(montagne2.clone());
            l1.getCases().add(montagne2.clone());
            l1.getCases().add(montagne2.clone());
            l1.getCases().add(montagne2.clone());
            terrain1.getLignes().add(l1);

            Ligne l2 = new Ligne();
            l2.getCases().add(entree.clone());
            l2.getCases().add(route4.clone());
            l2.getCases().add(garage1.clone());
            l2.getCases().add(garage2.clone());
            l2.getCases().add(route6.clone());
            l2.getCases().add(route6.clone());
            l2.getCases().add(route6.clone());
            l2.getCases().add(route6.clone());
            l2.getCases().add(route6.clone());
            l2.getCases().add(route6.clone());
            l2.getCases().add(sortie.clone());
            terrain1.getLignes().add(l2);

            Ligne l3 = new Ligne();
            l3.getCases().add(garage2.clone());
            l3.getCases().add(route1.clone());
            l3.getCases().add(garage1.clone());
            l3.getCases().add(montagne1.clone());
            l3.getCases().add(garage1.clone());
            l3.getCases().add(route4.clone());
            l3.getCases().add(route6.clone());
            l3.getCases().add(route3.clone());
            l3.getCases().add(route4.clone());
            l3.getCases().add(route6.clone());
            l3.getCases().add(route3.clone());
            l3.getCases().add(route4.clone());
            terrain1.getLignes().add(l3);

            Ligne l4 = new Ligne();
            l4.getCases().add(garage2.clone());
            l4.getCases().add(route1.clone());
            l4.getCases().add(garage1.clone());
            l4.getCases().add(entree.clone());
            l4.getCases().add(garage1.clone());
            l4.getCases().add(garage1.clone());
            l4.getCases().add(garage1.clone());
            l4.getCases().add(garage1.clone());
            l4.getCases().add(route4.clone());
            l4.getCases().add(garage1.clone());
            l4.getCases().add(sortie.clone());
            terrain1.getLignes().add(l4);

            Ligne l5 = new Ligne();
            l5.getCases().add(entree.clone());
            l5.getCases().add(route3.clone());
            l5.getCases().add(route4.clone());
            l5.getCases().add(route1.clone());
            l5.getCases().add(route3.clone());
            l5.getCases().add(route4.clone());
            l5.getCases().add(route1.clone());
            l5.getCases().add(route1.clone());
            l5.getCases().add(route4.clone());
            l5.getCases().add(montagne1.clone());
            l5.getCases().add(montagne2.clone());
            l5.getCases().add(montagne1.clone());
            terrain1.getLignes().add(l5);

            Ligne l6 = new Ligne();
            l6.getCases().add(garage2.clone());
            l6.getCases().add(garage2.clone());
            l6.getCases().add(route3.clone());
            l6.getCases().add(route4.clone());
            l6.getCases().add(route1.clone());
            l6.getCases().add(garage2.clone());
            l6.getCases().add(garage2.clone());
            l6.getCases().add(garage2.clone());
            l6.getCases().add(montagne2.clone());
            l6.getCases().add(montagne2.clone());
            l6.getCases().add(montagne1.clone());
            terrain1.getLignes().add(l6);

            Ligne l7 = new Ligne();
            l7.getCases().add(montagne1.clone());
            l7.getCases().add(garage2.clone());
            l7.getCases().add(route3.clone());
            l7.getCases().add(route4.clone());
            l7.getCases().add(route1.clone());
            l7.getCases().add(route3.clone());
            l7.getCases().add(route4.clone());
            l7.getCases().add(route1.clone());
            l7.getCases().add(sortie.clone());
            l7.getCases().add(montagne2.clone());
            l7.getCases().add(montagne1.clone());
            terrain1.getLignes().add(l7);

            Ligne l8 = new Ligne();
            l8.getCases().add(montagne1.clone());
            l8.getCases().add(garage2.clone());
            l8.getCases().add(sortie.clone());
            l8.getCases().add(route4.clone());
            l8.getCases().add(sortie.clone());
            l8.getCases().add(garage2.clone());
            l8.getCases().add(garage2.clone());
            l8.getCases().add(garage2.clone());
            l8.getCases().add(montagne2.clone());
            l8.getCases().add(montagne2.clone());
            l8.getCases().add(montagne1.clone());
            terrain1.getLignes().add(l8);


            partie.setTerrain(terrain1);
            partie.commencer();
        }
    }
