package jeu;

import config.Config;
import element.actif.*;
import utils.Position;
import element.passif.Terrain;
import element.passif.Chemin;
import graphics.AfficheurGraphique;
import utils.Timer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

public class Partie {
	private Terrain terrain;
	private List<Niveau> niveaux;
	private List<Vague> vagues; // P14
	private int nbMobilesSortis;
	public int indiceNiveauActuel;
	public int indiceVagueActuelle;
	private int energieJoueur;
	List<Projectile> projectilesPresents;
	AfficheurGraphique afficheurGraphique;

	// Utile à l'affichage textuelle
	private String etatCourant;
	private String etatPrec;
	public Partie() {
		etatCourant = "";
		etatPrec = "";
		this.niveaux = new ArrayList<>();
		this.vagues = new ArrayList<>();
		this.projectilesPresents = new ArrayList<>();
	}

	public void commencer() {
		try {
			afficheurGraphique = new AfficheurGraphique(this);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		etatCourant = "";
		etatPrec = "";
		while (!isOver()) {
			vagues.get(indiceVagueActuelle).lancerTour(this);
			System.out.println(this.getMobilesPresents().get(0).getPosition());
			System.out.println("-> ");
			afficherTerrain();
			System.out.println("\n\n\n\n");
			afficheurGraphique.affiche();
			evoluer();
			Timer.attendre(Config.STEP_INTERVAL);
			if (vagues.get(indiceVagueActuelle).echouee(this)) {
				Timer.attendre(niveaux.get(indiceNiveauActuel).getDureePause() * 1000);
				indiceVagueActuelle++;
			}
			if (indiceVagueActuelle == vagues.size()) {
				indiceVagueActuelle = 0;
				indiceNiveauActuel++;
			}
		}
		affichageFinPartie();

	}

	public void affichageDebutPartie() {
		System.out.println("Début Partie");
		System.out.println("Nombre de niveaux : " + niveaux.size());
		for (Niveau n : niveaux) {
			System.out.println("Tolérance niveau " + n.getName() + ": " + n.getToleranceMobiles());
		}
		System.out.println("Nombre de vagues : " + vagues.size());
		System.out.println("\n----");
		afficherTerrain();
	}

	private void affichageFinPartie() {
		System.out.println("\n----");
		System.out.println("Partie terminée");
		indiceNiveauActuel -= indiceNiveauActuel < niveaux.size() ? 0 : 1;
		afficherTerrain();
		if (defaiteJoueur()) {
			System.out.println("Le joueur a perdu!");
			JOptionPane.showMessageDialog(null, "Le joueur a perdu!");
		} else {
			System.out.println("Le joueur a gagné!");
			JOptionPane.showMessageDialog(null, "Le joueur a gagné!");
		}
	}

	public void evoluer() {
		if (terrain.getNatureTerrainAtPosition(this.getMobilesPresents().get(0).getPosition()) instanceof Chemin) 
		((Chemin) terrain.getNatureTerrainAtPosition(this.getMobilesPresents().get(0).getPosition())).setEstVisite(true);
		for (ObstacleVague ov : getObstaclesPresents()) {
			ov.evoluer(this);
		}
		for (MobileVague mv : getMobilesPresents()) {
			mv.evoluer(this);
		}
		for (Projectile proj : getProjectilesPresents()) {
			proj.evoluer(this);
		}
	}

	// P10 P11 P12
	public boolean isOver() {
		return indiceNiveauActuel == niveaux.size() || defaiteJoueur();
	}

	// P15--P17
	public void acheterObstacle(Obstacle obstacle) {

	}

	// P15--P17
	public void vendreObstacle(Obstacle obstacle) {

	}

	// P15--P17
	// a priori la methode deplacer() fera aussi placer()

	public void deplacer(Obstacle obstacle, Position position) {

	}

	public Terrain getTerrain() {
		return terrain;
	}

	public void setTerrain(Terrain terrain) {
		this.terrain = terrain;
	}

	public List<Niveau> getNiveaux() {
		return niveaux;
	}

	public void ajouterNiveau(Niveau niveau) {
		this.niveaux.add(niveau);
	}

	public List<Vague> getVagues() {
		return vagues;
	}

	public void ajouterVague(Vague vague) {
		this.vagues.add(vague);
	}

	public void activerMobilesPresents() {
		for (MobileVague mv : getMobilesPresents()) {
			mv.activer();
		}
	}

	public void activerObstaclesPresents() {
		for (ObstacleVague ov : getObstaclesPresents()) {
			ov.activer();
		}
	}

	public List<MobileVague> getMobilesPresents() {
		List<MobileVague> resultats = new ArrayList<>();
		Vague vagueActuel = vagues.get(indiceNiveauActuel);
		for (MobileVague mv : vagueActuel.getMobilesVague()) {
			if (!mv.isSorti() && !mv.getMobile().isElimine() && terrain.contientPosition(mv.getPosition()))
				resultats.add(mv);
		}
		return resultats;
	}

	public List<ObstacleVague> getObstaclesPresents() {
		// TODO à implémenter
		return vagues.get(indiceNiveauActuel).getObstaclesVague();
	}

	public void activerProjectilesPresents() {
		for (Projectile p : getProjectilesPresents()) {
			p.activer();
		}
	}

	public List<Projectile> getProjectilesPresents() {
		return projectilesPresents;
	}

	public boolean defaiteJoueur() {
		return getNbMobilesSortis() > getToleranceActuelle();
	}

	private int getToleranceActuelle() {
		if (indiceNiveauActuel < niveaux.size()) {
			int toleranceActuelle = 0;
			for (int i = 0; i <= indiceNiveauActuel; i++)
				toleranceActuelle += niveaux.get(i).getToleranceMobiles();
			return toleranceActuelle;
		}
		return Integer.MAX_VALUE;
	}

	public boolean victoirejoueur() {
		boolean vaguePassee = true;
		int compteurVague = 0;
		while (vaguePassee && compteurVague < vagues.size()) {
			vaguePassee = vagues.get(compteurVague).deploye(this);
		}
		return vaguePassee && !defaiteJoueur();
	}

	public int getNbMobilesSortis() {
		return nbMobilesSortis;
	}

	public void ajouterNbMobileSortis() {
		nbMobilesSortis++;
	}

	public void setNbMobilesSortis(int nbMobilesSortis) {
		this.nbMobilesSortis = nbMobilesSortis;
	}

	public String getEtatAtPosition(Position pos) {
		StringBuilder etat = new StringBuilder();
		if (terrain.contientPosition(pos)) {
			// Ajout de l'état du terrain en la position
			etat.append(terrain.getNatureTerrainAtPosition(pos).getLabel() + ": ");
			// Ajout des états des obstacles sur la position
			for (ObstacleVague ov : getObstaclesPresents()) {
				if (ov.getPosition().equals(pos))
					etat.append(ov.getEtat() + " ");
			}
			// Ajout des états des mobiles sur la position
			for (MobileVague mv : getMobilesPresents()) {
				if (mv.getPosition().equals(pos))
					etat.append(mv.getEtat() + " ");

			}
			// Ajout des états des projectiles sur la position
			for (Projectile proj : getProjectilesPresents()) {
				if (proj.getPosition().equals(pos))
					etat.append(proj.getEtat() + " ");
			}
		}
		return etat.toString();
	}

	public void afficherTerrain() {
		etatCourant = "";
		for (int i = 0; i < terrain.getLignes().size(); i++) {
			for (int j = 0; j < terrain.getLignes().get(i).getCases().size(); j++) {
				int nbCarRestants = 20;
				Position posCase = new Position(i + 1, j + 1);
				etatCourant += getEtatAtPosition(posCase) + " ";
				nbCarRestants -= getEtatAtPosition(posCase).length();
				for (int c = 0; c < nbCarRestants; c++) {
					etatCourant += " ";
				}
			}
			etatCourant += "\n";
		}
		if (!etatPrec.equals(etatCourant)) {
			System.out.println(etatCourant);
			etatPrec = etatCourant;
		}
	}

	public Position getPosEnemiPrio(Attaquant attaquant) {
		if (getMobilesPresents().size() > 0)
			return getMobilesPresents().get(0).getPosition();
		return null;

	}

	public Attaquant getAttaquantAt(Position position) {
		for (MobileVague mobileVague : getMobilesPresents()) {
			if (mobileVague.getPosition().equals(position))
				return mobileVague.getMobile();
		}
		return null;
	}

	public void recommencer() {
		// TODO
		
	}
}
