package jeu;

import config.Config;
import element.actif.*;
import utils.Position;
import element.passif.Terrain;
import utils.Timer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Partie {
	public int indiceNiveauActuel;
	public int indiceVagueActuelle;
	public Afficheur afficheur;
	private Terrain terrain;
	private List<Niveau> niveaux;
	private List<Vague> vagues; // P14
	private int nbMobilesSortis;
	private List<Projectile> projectilesLances;
	private List<Obstacle> obstaclesPlaces;
	private Joueur joueur;

	public Partie() {
		this.niveaux = new ArrayList<>();
		this.vagues = new ArrayList<>();
		this.projectilesLances = new ArrayList<>();
		this.obstaclesPlaces = new ArrayList<>();
	}

	public void commencer() throws IOException {
		joueur = new Joueur(this);
		afficheur = new Afficheur(this);

		afficheur.affichageDebutPartie();
		while (!isOver()) {
			evoluer();
			afficheur.afficherTerrain();
			afficheur.afficherEnergieJoueur();
			Timer.attendre(Config.STEP_INTERVAL);
		}

		afficheur.affichageFinPartie();
	}

	public void evoluer() {

		for (Projectile proj : getProjectilesPresents()) {
			proj.evoluer(this);
		}
		for (Attaquant m : getMobilesPresents()) {
			m.evoluer(this);
		}
		for (Attaquant o : getObstaclesPresents()) {
			o.evoluer(this);
		}

		vagues.get(indiceVagueActuelle).deployerMobiles(this);

		if (vagues.get(indiceVagueActuelle).echouee(this)) {
			afficheur.activerPause();
			System.out.println("Vague terminée!, début prochaine dans 1 seconde");
			joueur.gagneBonusEnergie(vagues.get(indiceVagueActuelle).getEnergieJoueur());
			try {
				Thread.sleep(this.niveaux.get(this.indiceNiveauActuel).getDureePause() * 10000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			afficheur.desactiverPause();
			indiceVagueActuelle++;
		}
		if (indiceVagueActuelle == vagues.size()) {
			joueur.setEnergieInitialeDuNiveau(niveaux.get(indiceNiveauActuel));
			indiceNiveauActuel++;
			if (!isOver()) {
				indiceVagueActuelle = 0;
			}
		}

	}

	// P10 P11 P12
	public boolean isOver() {
		return (indiceNiveauActuel == niveaux.size()) || defaiteJoueur();
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

	public List<Vague> getVagues() {
		return vagues;
	}

	public List<Obstacle> getObstaclesPlaces() {
		return this.obstaclesPlaces;
	}

	public List<Attaquant> getMobilesPresents() {
		List<Attaquant> resultats = new ArrayList<>();
		if (!isOver()) {
			Vague vagueActuel = vagues.get(indiceVagueActuelle);
			for (Mobile m : vagueActuel.getMobiles()) {
				if (!m.isSorti() && !m.isElimine() && terrain.contientPosition(m.getPosition()))
					resultats.add(m);
			}
		}
		return resultats;
	}

	public List<Attaquant> getObstaclesPresents() {
		List<Attaquant> resultats = new ArrayList<>();
		if (!isOver()) {
			if (vagues.get(indiceVagueActuelle).isLancee()) {
				for (Obstacle obstacle : vagues.get(indiceVagueActuelle).getObstacles()) {
					if (!obstacle.isElimine()) {
						resultats.add(obstacle);
					}
				}
			}

			for (Obstacle obstacle : obstaclesPlaces) {
				if (!obstacle.isElimine()) {
					resultats.add(obstacle);
				}
			}
		}
		return resultats;
	}

	public List<Projectile> getProjectilesPresents() {
		List<Projectile> resultats = new ArrayList<>();
		if (!isOver()) {
			for (Projectile p : projectilesLances) {
				if (!p.isElimine())
					resultats.add(p);
			}
		}
		return resultats;
	}

	public Joueur getJoueur() {
		return joueur;
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

	public int getNbMobilesSortis() {
		return nbMobilesSortis;
	}

	public void incrementerNbMobileSortis() {
		nbMobilesSortis++;
	}

	public String getEtatAtPosition(Position pos) {
		StringBuilder etat = new StringBuilder();
		if (terrain.contientPosition(pos)) {
			// Ajout de l'état du terrain en la position
			etat.append(terrain.getNatureTerrainAtPosition(pos).getSymbol() + ": ");
			// Ajout des états des obstacles sur la position
			for (Attaquant o : getObstaclesPresents()) {
				if (o.getPosition().equals(pos))
					etat.append(o.getNom() + " ");
			}
			// Ajout des états des mobiles sur la position
			for (Attaquant m : getMobilesPresents()) {
				if (m.getPosition().equals(pos))
					etat.append(m.getNom() + " ");

			}
			// Ajout des états des projectiles sur la position
			for (Projectile proj : getProjectilesPresents()) {
				if (proj.getPosition().equals(pos))
					etat.append(proj.getNom() + " ");
			}
		}
		return etat.toString();
	}

	public List<Attaquant> getMobilesAt(Position position) {
		List<Attaquant> mobiles = new ArrayList<>();
		for (Attaquant mobile : getMobilesPresents()) {
			if (mobile.getPosition().equals(position))
				mobiles.add(mobile);
		}
		return mobiles;
	}

	public List<Attaquant> getObstaclesAt(Position position) {
		List<Attaquant> obstacles = new ArrayList<>();
		for (Attaquant obstacle : getObstaclesPresents()) {
			if (obstacle.getPosition().equals(position))
				obstacles.add(obstacle);
		}
		return obstacles;
	}

	public List<Projectile> getProjectilesLances() {
		return projectilesLances;
	}

	public int getVolumeOccupeAt(Position pos) {
		int resultat = 0;
		for (Attaquant mobile : getMobilesAt(pos)) {
			resultat += ((Mobile) mobile).getVolume();
		}
		return resultat;
	}
}
