import java.io.IOException;
import java.util.Scanner;

import element.actif.*;
import jeu.*;
import element.passif.*;
import utils.Position;

@SuppressWarnings("unused")
public class Enigme {

	public static void main(String[] args) throws IOException {
		// Définition du jeu
		Jeu jeu = new Jeu("Enigme");
		// Définition des élements du  jeu
		Projectile p = new Projectile("p", 1, 1, 1, 4);
		Mobile mb = new Mobile("mb", 1, 10, 1, 1, TactiqueType.attaquePlusProche);
		Obstacle o = new Obstacle("o", 10, 1, TactiqueType.attaquePlusFaible);
		Chemin r = new Chemin("R", "route", 1, 1);
		Decoration m = new Decoration("M", "montagne");
		Campement g = new Campement("G", "garage");
		Entree e = new Entree("E", "entree");
		Sortie s = new Sortie("S", "sortie");

		//Affectation des projectiles aux attaquants
		o.getProjectiles().add(p.clone());
		// Ajout des élements dans la liste des élts du jeu
		jeu.getElements().add(p.clone());
		jeu.getElements().add(mb.clone());
		jeu.getElements().add(o.clone());
		jeu.getElements().add(r.clone());
		jeu.getElements().add(m.clone());
		jeu.getElements().add(g.clone());
		jeu.getElements().add(e.clone());
		jeu.getElements().add(s.clone());


		// Définition d'une partie
		Partie partie = new Partie();

		// Définition du niveau
		Niveau niveau = new Niveau("Unique Level", 2, 0, 4);
		// Ajout du niveau à la partie
		partie.getNiveaux().add(niveau);

		// Définition de la vague
		Vague vague1 = new Vague(3);
		Vague vague2 = new Vague(3);

		// Définition des éléments de la vague
		Obstacle ov1 = o.clone();
		ov1.setNom("o1");
		ov1.setPosition(new Position(3,4));

		Mobile mv1 = mb.clone();
		mv1.setNom("mb1");
		mv1.setPosEntree(new Position(2, 1));
		mv1.setPosSortie(new Position(2, 5));

		Mobile mv2 = mb.clone();
		mv2.setNom("mb2");
		mv2.setPosEntree(new Position(1, 1));
		mv2.setPosSortie(new Position(2, 5));

		Mobile mv3 = mb.clone();
		mv3.setNom("mb3");
		mv3.setPosEntree(new Position(2, 1));
		mv3.setPosSortie(new Position(2, 5));

		// Ajout des éléments dans la vague
		vague1.getObstacles().add(ov1);
		vague1.getMobiles().add(mv1);
		vague1.getMobiles().add(mv2);
		vague1.getMobiles().add(mv3);
		// ajout de la vague à la partie
		partie.getVagues().add(vague1);

		// Définition des éléments de la vague
		Obstacle ov2 = o.clone();
		ov2.setNom("o2");
		ov2.setPosition(new Position(3,4));

		Obstacle ov3 = o.clone();
		ov3.setNom("o3");
		ov3.setPosition(new Position(2,2));

		Mobile mv4 = mb.clone();
		mv4.setNom("mb4");
		mv4.setPosEntree(new Position(1, 1));
		mv4.setPosSortie(new Position(2, 5));

		Mobile mv5 = mb.clone();
		mv5.setNom("mb5");
		mv5.setPosEntree(new Position(2, 1));
		mv5.setPosSortie(new Position(2, 5));

		Mobile mv6 = mb.clone();
		mv6.setNom("mb6");
		mv6.setPosEntree(new Position(1, 1));
		mv6.setPosSortie(new Position(2, 5));

		Mobile mv7 = mb.clone();
		mv7.setNom("mb6");
		mv7.setPosEntree(new Position(2, 1));
		mv7.setPosSortie(new Position(2, 5));

		Mobile mv8 = mb.clone();
		mv8.setNom("mb6");
		mv8.setPosEntree(new Position(1, 1));
		mv8.setPosSortie(new Position(2, 5));



		// Ajout des éléments dans la vague
		vague2.getObstacles().add(ov2);
		vague2.getObstacles().add(ov3);
		vague2.getMobiles().add(mv4);
		vague2.getMobiles().add(mv5);
		vague2.getMobiles().add(mv6);
		vague2.getMobiles().add(mv7);
		vague2.getMobiles().add(mv8);
		// ajout de la vague à la partie
		partie.getVagues().add(vague2);

		// Définition du element.terrain de la partie
		Terrain terrain = new Terrain();

		// Définition des lignes de la partie
		Ligne ligne1 = new Ligne();
		ligne1.getCases().add(r.clone());
		ligne1.getCases().add(r.clone());
		ligne1.getCases().add(m.clone());
		ligne1.getCases().add(m.clone());
		ligne1.getCases().add(m.clone());

		Ligne ligne2 = new Ligne();
		ligne2.getCases().add(e.clone());
		ligne2.getCases().add(g.clone());
		ligne2.getCases().add(r.clone());
		ligne2.getCases().add(r.clone());
		ligne2.getCases().add(s.clone());

		Ligne ligne3 = new Ligne();
		ligne3.getCases().add(r.clone());
		ligne3.getCases().add(r.clone());
		ligne3.getCases().add(r.clone());
		ligne3.getCases().add(g.clone());
		ligne3.getCases().add(g.clone());

		terrain.getLignes().add(ligne1);
		terrain.getLignes().add(ligne2);
		terrain.getLignes().add(ligne3);

		// Set element.terrain de la partie
		partie.setTerrain(terrain);
		Scanner sc = new Scanner(System.in);
		partie.commencer();
//		while (i != 1) {
//			System.out.println("tapez :\n"
//					+ "1 pour commencer \n"
//					+ "2 pour afficher le terrain \n"
//					+ "3 pour afficher les informations sur la partie \n");
//			try {
//				i = sc.nextInt();
//			} catch (Exception e2) {
//				e2.printStackTrace();
//			}
//			switch (i) {
//			case 1:
//				partie.commencer();
//				break;
//				case 3:
//				partie.affichageDebutPartie();
//				break;
//				case 2:
//				default:
//				partie.afficherTerrain();
//				break;
//			}
//		}
	}

}
