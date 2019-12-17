import java.io.IOException;
import javax.swing.JOptionPane;
import config.Config;
import element.actif.*;
import graphics.AfficheurGraphique;
import jeu.*;
import element.passif.*;
import utils.Position;

public class EnigmeSujet {

	public static void main(String[] args) throws IOException {

		// Définition du jeu
		Jeu jeu = new Jeu("ExempleSujet");

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

		Projectile p = new Projectile("p",1,1,1,1);
		jeu.getElements().add(p.clone());

		Mobile mb = new Mobile("mb",1,1,1,1,TactiqueType.attaquePlusProche);
		jeu.getElements().add(mb.clone());

		Obstacle ob = new Obstacle("ob",1,1,TactiqueType.attaquePlusFaible); 
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
		Mobile mv1 = mb.clone();
		mv1.setNom("mv1");
		mv1.setPosEntree(new Position(2, 1));
		mv1.setPosSortie(new Position(2, 5));
		// Ajout des éléments dans la vague
		v.getMobiles().add(mv1);
		Mobile mv2 = mb.clone();
		mv2.setNom("mv2");
		mv2.setPosEntree(new Position(2, 1));
		mv2.setPosSortie(new Position(2, 5));
		// Ajout des éléments dans la vague
		v.getMobiles().add(mv2);
		Mobile mv3 = mb.clone();
		mv3.setNom("mv3");
		mv3.setPosEntree(new Position(2, 1));
		mv3.setPosSortie(new Position(2, 5));
		// Ajout des éléments dans la vague
		v.getMobiles().add(mv3);

		Obstacle ov = ob.clone();
		ov.setNom("ov");
		ov.setPosition(new Position(3,2));
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
		terrain.getLignes().add(l1);

		Ligne l2 = new Ligne();
		l2.getCases().add(entree.clone());
		l2.getCases().add(route3.clone());
		l2.getCases().add(route2.clone());
		l2.getCases().add(route1.clone());
		l2.getCases().add(sortie.clone());
		terrain.getLignes().add(l2);

		Ligne l3 = new Ligne();
		l3.getCases().add(garage5.clone());
		l3.getCases().add(garage4.clone());
		l3.getCases().add(garage3.clone());
		l3.getCases().add(garage2.clone());
		l3.getCases().add(garage1.clone());
		terrain.getLignes().add(l3);


		partie.setTerrain(terrain);
		partie.commencer();
	}
}

