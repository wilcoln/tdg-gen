import java.io.IOException;
import javax.swing.JOptionPane;
import config.Config;
import element.actif.*;
import graphics.AfficheurGraphique;
import jeu.*;
import element.passif.*;
import utils.Position;

public class EnigmePlusieursChemins {

	public static void main(String[] args) throws IOException {

		// Définition du jeu
		Jeu jeu = new Jeu("EnigmePlusieursChemins");

		// Définition des élements du  jeu
		// Ajout des élements dans la liste des élts du jeu
		Entree entree = new Entree("E","entree"); 
		jeu.getElements().add(entree.clone());

		Projectile P = new Projectile("P",1,1,1,1);
		jeu.getElements().add(P.clone());

		Mobile MB = new Mobile("MB",1,10,1,1,TactiqueType.attaquePlusFaible);
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
		Niveau unique = new Niveau("unique", 1, 4, 3);
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
		mv2.setPosSortie(new Position(6, 1));
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
		mv4.setPosSortie(new Position(6, 1));
		// Ajout des éléments dans la vague
		v1.getMobiles().add(mv4);

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
		l6.getCases().add(sortie.clone());
		l6.getCases().add(route3.clone());
		l6.getCases().add(route4.clone());
		l6.getCases().add(route1.clone());
		l6.getCases().add(montagne2.clone());
		l6.getCases().add(montagne1.clone());
		terrain1.getLignes().add(l6);


		partie.setTerrain(terrain1);
		partie.commencer();
	}
}

