import java.io.IOException;
import javax.swing.JOptionPane;
import config.Config;
import element.actif.*;
import graphics.AfficheurGraphique;
import jeu.*;
import element.passif.*;
import utils.Position;

public class Enigme2 {

	public static void main(String[] args) throws IOException {

		// Définition du jeu
		Jeu jeu = new Jeu("Enigme2");

		// Définition des élements du  jeu
		// Ajout des élements dans la liste des élts du jeu
		Entree entree = new Entree("E","entree"); 
		jeu.getElements().add(entree.clone());

		Projectile P = new Projectile("P",1,1,1,1);
		jeu.getElements().add(P.clone());

		Mobile MB = new Mobile("MB",1,1,1,1,TactiqueType.attaquePlusFaible);
		jeu.getElements().add(MB.clone());

		Sortie sortie = new Sortie("S","sortie"); 
		jeu.getElements().add(sortie.clone());

		Campement garage = new Campement("G","garage"); 
		jeu.getElements().add(garage.clone());

		Chemin route = new Chemin("R","route",1,1); 
		jeu.getElements().add(route.clone());

		Decoration montagne = new Decoration("M","montagne"); 
		jeu.getElements().add(montagne.clone());

		Obstacle O = new Obstacle("O",1,1,TactiqueType.attaquePlusFaible); 
		jeu.getElements().add(O.clone());


		//Affectation des projectiles aux obstacles et mobiles
		MB.getProjectiles().add(P.clone());
		O.getProjectiles().add(P.clone());

		// Définition des parties
		Partie partie = new Partie();
		partie.getObstaclesDispoPourVentre(O);
		Niveau unique = new Niveau("unique", 1, 4, 1);
		partie.getNiveaux().add(unique);
	
		Vague v1  = new Vague(3);
		Mobile mv1 = MB.clone();
		mv1.setNom("mv1");
		mv1.setPosEntree(new Position(3, 1));
		mv1.setPosSortie(new Position(6, 1));
		// Ajout des éléments dans la vague
		v1.getMobiles().add(mv1);
		Mobile mv2 = MB.clone();
		mv2.setNom("mv2");
		mv2.setPosEntree(new Position(3, 1));
		mv2.setPosSortie(new Position(6, 1));
		// Ajout des éléments dans la vague
		v1.getMobiles().add(mv2);

		Obstacle ov = ov.clone();
		ov.setNom("ov");
		ov.setPosition(new Position(3,3));
		// Ajout des éléments dans la vague
		v1.getObstacles().add(ov);
		
		// ajout de la vague à la partie
		partie.getVagues().add(v1);	
		
		// Définition du element.terrain de la partie
		Terrain terrain1 = new Terrain();
		
		// Définition des lignes du terrain
		Ligne l1 = new Ligne();
		l1.getCases().add(entree.clone());
		l1.getCases().add(route.clone());
		terrain1.getLignes().add(l1);

		Ligne l2 = new Ligne();
		l2.getCases().add(montagne.clone());
		terrain1.getLignes().add(l2);

		Ligne l3 = new Ligne();
		l3.getCases().add(montagne.clone());
		l3.getCases().add(garage.clone());
		l3.getCases().add(sortie.clone());
		terrain1.getLignes().add(l3);


		partie.setTerrain(terrain1);
		partie.commencer();
	}
}

