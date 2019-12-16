import java.io.IOException;
import javax.swing.JOptionPane;
import config.Config;
import element.actif.*;
import graphics.AfficheurGraphique;
import jeu.*;
import element.passif.*;
import utils.Position;

public class Enigme5 {

	public static void main(String[] args) throws IOException {

		// Définition du jeu
		Jeu jeu = new Jeu("Enigme5");

		// Définition des élements du  jeu
		// Ajout des élements dans la liste des élts du jeu
		Entree entree = new Entree("E","entree"); 
		jeu.getElements().add(entree.clone());

		Projectile P = new Projectile("P",1,1,1,1);
		jeu.getElements().add(P.clone());

		Mobile MB = new Mobile("MB",1,20,1,1,TactiqueType.attaquePlusFaible);
		jeu.getElements().add(MB.clone());

		Sortie sortie = new Sortie("S","sortie"); 
		jeu.getElements().add(sortie.clone());

		Obstacle O = new Obstacle("O",1,1,TactiqueType.attaquePlusFaible); 
		jeu.getElements().add(O.clone());

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

		Chemin route1 = new Chemin("R","route1",1,1); 
		jeu.getElements().add(route1.clone());

		Chemin route4 = new Chemin("R","route4",1,1); 
		jeu.getElements().add(route4.clone());

		Chemin route3 = new Chemin("R","route3",1,1); 
		jeu.getElements().add(route3.clone());

		Chemin route5 = new Chemin("R","route5",1,1); 
		jeu.getElements().add(route5.clone());

		Chemin route6 = new Chemin("R","route6",1,1); 
		jeu.getElements().add(route6.clone());

		Chemin route2 = new Chemin("R","route2",1,1); 
		jeu.getElements().add(route2.clone());

		Campement garage = new Campement("G","garage"); 
		jeu.getElements().add(garage.clone());


		//Affectation des projectiles aux obstacles et mobiles
		MB.getProjectiles().add(P.clone());
		O.getProjectiles().add(P.clone());

		// Définition des parties
		Partie partie = new Partie();
		//partie.getObstaclesDispoPourVentre(O);
		Niveau unique = new Niveau("unique", 1, 4, 1);
		partie.getNiveaux().add(unique);
	
		Vague v1  = new Vague(3);
		Mobile mv1 = MB.clone();
		mv1.setNom("mv1");
		mv1.setPosEntree(new Position(1, 1));
		mv1.setPosSortie(new Position(2, 7));
		// Ajout des éléments dans la vague
		v1.getMobiles().add(mv1);
		Mobile mv2 = MB.clone();
		mv2.setNom("mv2");
		mv2.setPosEntree(new Position(1, 1));
		mv2.setPosSortie(new Position(2, 7));
		// Ajout des éléments dans la vague
		v1.getMobiles().add(mv2);
		Mobile mv3 = MB.clone();
		mv3.setNom("mv3");
		mv3.setPosEntree(new Position(1, 1));
		mv3.setPosSortie(new Position(2, 7));
		// Ajout des éléments dans la vague
		v1.getMobiles().add(mv3);
		Mobile mv4 = MB.clone();
		mv4.setNom("mv4");
		mv4.setPosEntree(new Position(1, 1));
		mv4.setPosSortie(new Position(2, 7));
		// Ajout des éléments dans la vague
		v1.getMobiles().add(mv4);
		Mobile mv5 = MB.clone();
		mv5.setNom("mv5");
		mv5.setPosEntree(new Position(1, 1));
		mv5.setPosSortie(new Position(2, 7));
		// Ajout des éléments dans la vague
		v1.getMobiles().add(mv5);
		Mobile mv6 = MB.clone();
		mv6.setNom("mv6");
		mv6.setPosEntree(new Position(1, 1));
		mv6.setPosSortie(new Position(2, 7));
		// Ajout des éléments dans la vague
		v1.getMobiles().add(mv6);
		Mobile mv7 = MB.clone();
		mv7.setNom("mv7");
		mv7.setPosEntree(new Position(1, 1));
		mv7.setPosSortie(new Position(2, 7));
		// Ajout des éléments dans la vague
		v1.getMobiles().add(mv7);
		Mobile mv8 = MB.clone();
		mv8.setNom("mv8");
		mv8.setPosEntree(new Position(1, 1));
		mv8.setPosSortie(new Position(2, 7));
		// Ajout des éléments dans la vague
		v1.getMobiles().add(mv8);

		Obstacle ov1 = O.clone();
		ov1.setNom("ov1");
		ov1.setPosition(new Position(1,4));
		// Ajout des éléments dans la vague
		v1.getObstacles().add(ov1);
		Obstacle ov2 = O.clone();
		ov2.setNom("ov2");
		ov2.setPosition(new Position(5,4));
		// Ajout des éléments dans la vague
		v1.getObstacles().add(ov2);
		
		// ajout de la vague à la partie
		partie.getVagues().add(v1);	
		
		// Définition du element.terrain de la partie
		Terrain terrain1 = new Terrain();
		
		// Définition des lignes du terrain
		Ligne l1 = new Ligne();
		l1.getCases().add(entree.clone());
		l1.getCases().add(montagne4.clone());
		l1.getCases().add(montagne3.clone());
		l1.getCases().add(garage.clone());
		l1.getCases().add(montagne5.clone());
		l1.getCases().add(montagne2.clone());
		l1.getCases().add(montagne1.clone());
		terrain1.getLignes().add(l1);

		Ligne l2 = new Ligne();
		l2.getCases().add(route1.clone());
		l2.getCases().add(route4.clone());
		l2.getCases().add(route5.clone());
		l2.getCases().add(montagne3.clone());
		l2.getCases().add(route2.clone());
		l2.getCases().add(route6.clone());
		l2.getCases().add(sortie.clone());
		terrain1.getLignes().add(l2);

		Ligne l3 = new Ligne();
		l3.getCases().add(montagne2.clone());
		l3.getCases().add(montagne1.clone());
		l3.getCases().add(route3.clone());
		l3.getCases().add(montagne3.clone());
		l3.getCases().add(route4.clone());
		l3.getCases().add(montagne5.clone());
		l3.getCases().add(montagne4.clone());
		terrain1.getLignes().add(l3);

		Ligne l4 = new Ligne();
		l4.getCases().add(montagne3.clone());
		l4.getCases().add(montagne2.clone());
		l4.getCases().add(route2.clone());
		l4.getCases().add(montagne1.clone());
		l4.getCases().add(route6.clone());
		l4.getCases().add(route5.clone());
		l4.getCases().add(montagne4.clone());
		terrain1.getLignes().add(l4);

		Ligne l5 = new Ligne();
		l5.getCases().add(montagne2.clone());
		l5.getCases().add(montagne1.clone());
		l5.getCases().add(route2.clone());
		l5.getCases().add(garage.clone());
		l5.getCases().add(montagne3.clone());
		l5.getCases().add(route6.clone());
		l5.getCases().add(montagne4.clone());
		terrain1.getLignes().add(l5);

		Ligne l6 = new Ligne();
		l6.getCases().add(montagne2.clone());
		l6.getCases().add(montagne1.clone());
		l6.getCases().add(route2.clone());
		l6.getCases().add(route6.clone());
		l6.getCases().add(montagne4.clone());
		l6.getCases().add(route5.clone());
		l6.getCases().add(montagne5.clone());
		terrain1.getLignes().add(l6);

		Ligne l7 = new Ligne();
		l7.getCases().add(montagne3.clone());
		l7.getCases().add(montagne2.clone());
		l7.getCases().add(montagne1.clone());
		l7.getCases().add(route2.clone());
		l7.getCases().add(route6.clone());
		l7.getCases().add(route5.clone());
		l7.getCases().add(montagne5.clone());
		terrain1.getLignes().add(l7);


		partie.setTerrain(terrain1);
		partie.commencer();
	}
}

