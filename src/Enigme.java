import java.io.IOException;
import javax.swing.JOptionPane;
import config.Config;
import element.actif.*;
import graphics.AfficheurGraphique;
import jeu.*;
import element.passif.*;
import utils.Position;

@SuppressWarnings("unused")
public class Enigme {

	public static void main(String[] args) {
		// Définition du jeu
		Jeu jeu = new Jeu("Enigme");
		// Définition des élements du jeu
		Projectile p = new Projectile("p", 1, 1, 1, 1);
		Mobile mb = new Mobile("mb", 1, 1, 1, 1, TactiqueType.attaquePlusProche);
		Obstacle o = new Obstacle("o", 1, 1, TactiqueType.attaquePlusFaible);
		Chemin r = new Chemin("R", "route", 1, 1);
		Decoration m = new Decoration("M", "montagne");
		Campement g = new Campement("G", "garage");
		Entree e = new Entree("E", "entree");
		Sortie s = new Sortie("S", "sortie");

		// Affectation des projectiles aux attaquants
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
		Niveau niveau = new Niveau("Unique Level", 1, 0, 2);
		// Ajout du niveau à la partie
		partie.getNiveaux().add(niveau);

		// Définition de la vague
		Vague vague = new Vague(3);

		// Définition des éléments de la vague
		ObstacleVague ov = new ObstacleVague(o.clone(), new Position(2, 3));
		MobileVague mv1 = new MobileVague(mb.clone(), 1, new Position(2, 1), new Position(2, 10));
		MobileVague mv2 = new MobileVague(mb.clone(), 2, new Position(2, 1), new Position(2, 10));
		MobileVague mv3 = new MobileVague(mb.clone(), 3, new Position(2, 1), new Position(2,10));

		// Ajout des éléments dans la vague

		// vous pouvez essayer d'enlever l'obstacle et voir le resultat
		//vague.getObstaclesVague().add(ov);
		vague.getMobilesVague().add(mv1);
		vague.getMobilesVague().add(mv2);
		vague.getMobilesVague().add(mv3);
		// ajout de la vague à la partie
		partie.getVagues().add(vague);

		// Définition du element.terrain de la partie
		Terrain terrain = new Terrain();

		// Définition des lignes de la partie
		Ligne ligne1 = new Ligne();
		ligne1.getCases().add(m.clone());
		ligne1.getCases().add(m.clone());
		ligne1.getCases().add(m.clone());
		ligne1.getCases().add(m.clone());
		ligne1.getCases().add(m.clone());
		ligne1.getCases().add(m.clone());
		ligne1.getCases().add(m.clone());
		ligne1.getCases().add(m.clone());
		ligne1.getCases().add(m.clone());
		ligne1.getCases().add(m.clone());

		Ligne ligne2 = new Ligne();
		ligne2.getCases().add(e.clone());
		ligne2.getCases().add(r.clone());
		ligne2.getCases().add(m.clone());
		ligne2.getCases().add(m.clone());
		ligne2.getCases().add(m.clone());
		ligne2.getCases().add(m.clone());
		ligne2.getCases().add(r.clone());
		ligne2.getCases().add(r.clone());
		ligne2.getCases().add(r.clone());
		ligne2.getCases().add(s.clone());

		Ligne ligne3 = new Ligne();
		ligne3.getCases().add(g.clone());
		ligne3.getCases().add(r.clone());
		ligne3.getCases().add(g.clone());
		ligne3.getCases().add(g.clone());
		ligne3.getCases().add(g.clone());
		ligne3.getCases().add(g.clone());
		ligne3.getCases().add(r.clone());
		ligne3.getCases().add(g.clone());
		ligne3.getCases().add(g.clone());
		ligne3.getCases().add(g.clone());
		
		Ligne ligne4 = new Ligne();
		ligne4.getCases().add(g.clone());
		ligne4.getCases().add(r.clone());
		ligne4.getCases().add(r.clone());
		ligne4.getCases().add(r.clone());
		ligne4.getCases().add(r.clone());
		ligne4.getCases().add(r.clone());
		ligne4.getCases().add(r.clone());
		ligne4.getCases().add(g.clone());
		ligne4.getCases().add(g.clone());
		ligne4.getCases().add(g.clone());
		
		terrain.getLignes().add(ligne1);
		terrain.getLignes().add(ligne2);
		terrain.getLignes().add(ligne3);
		terrain.getLignes().add(ligne4);
		// Set element.terrain de la partie
		partie.setTerrain(terrain);
		int i = 2;
		java.util.Scanner sc = new java.util.Scanner(System.in);
		while (i != 1) {
			System.out.println("tapez :\n" 
					+ "1 pour commencer \n" 
					+ "2 pour afficher le terrain \n"
					+ "3 pour afficher les informations sur la partie \n");
			try {
				i = sc.nextInt();
			} catch (Exception e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
			switch (i) {
			case 1:
				partie.commencer();
				break;
				case 3:
				partie.affichageDebutPartie();
				break;
				case 2:
				default:
				partie.afficherTerrain();
				break;
			}
		}
	}

}
