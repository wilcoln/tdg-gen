import java.io.IOException;
import javax.swing.JOptionPane;
import config.Config;
import element.actif.*;
import graphics.AfficheurGraphique;
import jeu.*;
import element.passif.*;
import utils.Position;

public class EnigmePlusieursCheminsEtParties {

	public static void main(String[] args) throws IOException {

		// Définition du jeu
		Jeu jeu = new Jeu("EnigmePlusieursCheminsEtParties");

		// Définition des élements du  jeu
		// Ajout des élements dans la liste des élts du jeu
		Entree entree = new Entree("E","entree"); 
		jeu.getElements().add(entree.clone());

		Projectile P = new Projectile("P",1,1,1,1);
		jeu.getElements().add(P.clone());

		Mobile MB = new Mobile("MB",1,5,1,1,TactiqueType.attaquePlusFaible);
		jeu.getElements().add(MB.clone());

		Sortie sortie = new Sortie("S","sortie"); 
		jeu.getElements().add(sortie.clone());

		Obstacle O = new Obstacle("O",1,1,TactiqueType.attaquePlusFaible); 
		jeu.getElements().add(O.clone());

		Decoration montagne1 = new Decoration("M","montagne1"); 
		jeu.getElements().add(montagne1.clone());

		Decoration montagne2 = new Decoration("M","montagne2"); 
		jeu.getElements().add(montagne2.clone());

		Chemin route1 = new Chemin("R","route1",1,1); 
		jeu.getElements().add(route1.clone());

		Chemin route4 = new Chemin("R","route4",1,1); 
		jeu.getElements().add(route4.clone());

		Chemin route3 = new Chemin("R","route3",1,1); 
		jeu.getElements().add(route3.clone());

		Chemin route6 = new Chemin("R","route6",1,1); 
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
		Niveau unique = new Niveau("unique", 1, 4, 1);
		partie.getNiveaux().add(unique);
	
		Vague v1  = new Vague(3);
		Mobile mv1 = MB.clone();
		mv1.setNom("mv1");
		mv1.setPosEntree(new Position(2, 1));
		mv1.setPosSortie(new Position(2, 6));
		// Ajout des éléments dans la vague
		v1.getMobiles().add(mv1);
		Mobile mv2 = MB.clone();
		mv2.setNom("mv2");
		mv2.setPosEntree(new Position(2, 1));
		mv2.setPosSortie(new Position(2, 6));
		// Ajout des éléments dans la vague
		v1.getMobiles().add(mv2);
		Mobile mv3 = MB.clone();
		mv3.setNom("mv3");
		mv3.setPosEntree(new Position(2, 1));
		mv3.setPosSortie(new Position(2, 6));
		// Ajout des éléments dans la vague
		v1.getMobiles().add(mv3);
		Mobile mv4 = MB.clone();
		mv4.setNom("mv4");
		mv4.setPosEntree(new Position(2, 1));
		mv4.setPosSortie(new Position(2, 6));
		// Ajout des éléments dans la vague
		v1.getMobiles().add(mv4);
		Mobile mv5 = MB.clone();
		mv5.setNom("mv5");
		mv5.setPosEntree(new Position(2, 1));
		mv5.setPosSortie(new Position(2, 6));
		// Ajout des éléments dans la vague
		v1.getMobiles().add(mv5);
		Mobile mv6 = MB.clone();
		mv6.setNom("mv6");
		mv6.setPosEntree(new Position(2, 1));
		mv6.setPosSortie(new Position(2, 6));
		// Ajout des éléments dans la vague
		v1.getMobiles().add(mv6);
		Mobile mv7 = MB.clone();
		mv7.setNom("mv7");
		mv7.setPosEntree(new Position(2, 1));
		mv7.setPosSortie(new Position(2, 6));
		// Ajout des éléments dans la vague
		v1.getMobiles().add(mv7);
		Mobile mv8 = MB.clone();
		mv8.setNom("mv8");
		mv8.setPosEntree(new Position(2, 1));
		mv8.setPosSortie(new Position(2, 6));
		// Ajout des éléments dans la vague
		v1.getMobiles().add(mv8);

		Obstacle ov1 = O.clone();
		ov1.setNom("ov1");
		ov1.setPosition(new Position(2,4));
		// Ajout des éléments dans la vague
		v1.getObstacles().add(ov1);
		Obstacle ov2 = O.clone();
		ov2.setNom("ov2");
		ov2.setPosition(new Position(4,1));
		// Ajout des éléments dans la vague
		v1.getObstacles().add(ov2);
		
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
		l1.getCases().add(montagne2.clone());
		terrain1.getLignes().add(l1);

		Ligne l2 = new Ligne();
		l2.getCases().add(entree.clone());
		l2.getCases().add(route4.clone());
		l2.getCases().add(garage1.clone());
		l2.getCases().add(garage2.clone());
		l2.getCases().add(route6.clone());
		l2.getCases().add(sortie.clone());
		terrain1.getLignes().add(l2);

		Ligne l3 = new Ligne();
		l3.getCases().add(garage2.clone());
		l3.getCases().add(route1.clone());
		l3.getCases().add(garage1.clone());
		l3.getCases().add(route6.clone());
		l3.getCases().add(route3.clone());
		l3.getCases().add(route4.clone());
		terrain1.getLignes().add(l3);

		Ligne l4 = new Ligne();
		l4.getCases().add(garage2.clone());
		l4.getCases().add(route1.clone());
		l4.getCases().add(garage1.clone());
		l4.getCases().add(route4.clone());
		l4.getCases().add(montagne2.clone());
		l4.getCases().add(montagne1.clone());
		terrain1.getLignes().add(l4);

		Ligne l5 = new Ligne();
		l5.getCases().add(garage1.clone());
		l5.getCases().add(route3.clone());
		l5.getCases().add(route4.clone());
		l5.getCases().add(route1.clone());
		l5.getCases().add(montagne2.clone());
		l5.getCases().add(montagne1.clone());
		terrain1.getLignes().add(l5);

		Ligne l6 = new Ligne();
		l6.getCases().add(garage2.clone());
		l6.getCases().add(route3.clone());
		l6.getCases().add(route4.clone());
		l6.getCases().add(route1.clone());
		l6.getCases().add(montagne2.clone());
		l6.getCases().add(montagne1.clone());
		terrain1.getLignes().add(l6);


		partie.setTerrain(terrain1);
		partie.commencer();
		Partie partie2 = new Partie();
			partie2.getObstaclesDispoPourVente().add(O.clone());
		Niveau unique1 = new Niveau("unique1", 1, 4, 1);
		partie2.getNiveaux().add(unique1);
	
		Vague v12  = new Vague(3);
		Mobile mv12 = MB.clone();
		mv12.setNom("mv12");
		mv12.setPosEntree(new Position(2, 1));
		mv12.setPosSortie(new Position(2, 6));
		// Ajout des éléments dans la vague
		v12.getMobiles().add(mv12);
		Mobile mv22 = MB.clone();
		mv22.setNom("mv22");
		mv22.setPosEntree(new Position(2, 1));
		mv22.setPosSortie(new Position(2, 6));
		// Ajout des éléments dans la vague
		v12.getMobiles().add(mv22);
		Mobile mv32 = MB.clone();
		mv32.setNom("mv32");
		mv32.setPosEntree(new Position(2, 1));
		mv32.setPosSortie(new Position(2, 6));
		// Ajout des éléments dans la vague
		v12.getMobiles().add(mv32);
		Mobile mv42 = MB.clone();
		mv42.setNom("mv42");
		mv42.setPosEntree(new Position(2, 1));
		mv42.setPosSortie(new Position(2, 6));
		// Ajout des éléments dans la vague
		v12.getMobiles().add(mv42);
		Mobile mv52 = MB.clone();
		mv52.setNom("mv52");
		mv52.setPosEntree(new Position(2, 1));
		mv52.setPosSortie(new Position(2, 6));
		// Ajout des éléments dans la vague
		v12.getMobiles().add(mv52);
		Mobile mv62 = MB.clone();
		mv62.setNom("mv62");
		mv62.setPosEntree(new Position(2, 1));
		mv62.setPosSortie(new Position(2, 6));
		// Ajout des éléments dans la vague
		v12.getMobiles().add(mv62);
		Mobile mv72 = MB.clone();
		mv72.setNom("mv72");
		mv72.setPosEntree(new Position(2, 1));
		mv72.setPosSortie(new Position(2, 6));
		// Ajout des éléments dans la vague
		v12.getMobiles().add(mv72);
		Mobile mv82 = MB.clone();
		mv82.setNom("mv82");
		mv82.setPosEntree(new Position(2, 1));
		mv82.setPosSortie(new Position(2, 6));
		// Ajout des éléments dans la vague
		v12.getMobiles().add(mv82);

		Obstacle ov12 = O.clone();
		ov12.setNom("ov12");
		ov12.setPosition(new Position(6,1));
		// Ajout des éléments dans la vague
		v12.getObstacles().add(ov12);
		Obstacle ov22 = O.clone();
		ov22.setNom("ov22");
		ov22.setPosition(new Position(4,1));
		// Ajout des éléments dans la vague
		v12.getObstacles().add(ov22);
		
		// ajout de la vague à la partie
		partie2.getVagues().add(v12);	
		
		// Définition du element.terrain de la partie
		Terrain terrain2 = new Terrain();
		
		// Définition des lignes du terrain
		Ligne l12 = new Ligne();
		l12.getCases().add(garage1.clone());
		l12.getCases().add(route6.clone());
		l12.getCases().add(route3.clone());
		l12.getCases().add(route4.clone());
		l12.getCases().add(route1.clone());
		l12.getCases().add(montagne2.clone());
		terrain2.getLignes().add(l12);

		Ligne l22 = new Ligne();
		l22.getCases().add(entree.clone());
		l22.getCases().add(route4.clone());
		l22.getCases().add(garage1.clone());
		l22.getCases().add(garage2.clone());
		l22.getCases().add(route6.clone());
		l22.getCases().add(sortie.clone());
		terrain2.getLignes().add(l22);

		Ligne l32 = new Ligne();
		l32.getCases().add(garage2.clone());
		l32.getCases().add(route1.clone());
		l32.getCases().add(garage1.clone());
		l32.getCases().add(route6.clone());
		l32.getCases().add(route3.clone());
		l32.getCases().add(route4.clone());
		terrain2.getLignes().add(l32);

		Ligne l42 = new Ligne();
		l42.getCases().add(garage2.clone());
		l42.getCases().add(route1.clone());
		l42.getCases().add(garage1.clone());
		l42.getCases().add(route4.clone());
		l42.getCases().add(montagne2.clone());
		l42.getCases().add(montagne1.clone());
		terrain2.getLignes().add(l42);

		Ligne l52 = new Ligne();
		l52.getCases().add(garage1.clone());
		l52.getCases().add(route3.clone());
		l52.getCases().add(route4.clone());
		l52.getCases().add(route1.clone());
		l52.getCases().add(montagne2.clone());
		l52.getCases().add(montagne1.clone());
		terrain2.getLignes().add(l52);

		Ligne l62 = new Ligne();
		l62.getCases().add(garage2.clone());
		l62.getCases().add(route3.clone());
		l62.getCases().add(route4.clone());
		l62.getCases().add(route1.clone());
		l62.getCases().add(montagne2.clone());
		l62.getCases().add(montagne1.clone());
		terrain2.getLignes().add(l62);


		partie2.setTerrain(terrain2);
		partie2.commencer();
	}
}

