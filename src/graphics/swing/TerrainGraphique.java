package graphics.swing;

import element.actif.Attaquant;
import element.actif.Projectile;
import element.passif.*;
import jeu.Partie;

import java.awt.*;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JPanel;

import utils.Position;

public class TerrainGraphique extends JPanel {
	private Terrain terrain;
	private Partie partie;

	public TerrainGraphique(Partie partie) throws IOException {
		this.partie = partie;
		this.terrain = partie.getTerrain();
		this.setLayout(new GridLayout(partie.getTerrain().getLargeur(), partie.getTerrain().getLongueur()));
		for (int i = 0; i < partie.getTerrain().getLargeur(); i++) {
			for (int j = 0; j < partie.getTerrain().getLongueur(); j++) {
				Position pos  = new Position(i+1, j+1);
				CaseGraphique caseG = new CaseGraphique(partie, pos);
				this.add(caseG);
			}
		}
	}

	public void refresh() {
		this.revalidate();
		this.repaint();
	}

	public void paintComponent(Graphics g) {

		super.paintComponent(g);

		Graphics g2d = (Graphics2D) g;
		int next = 100;

		for (int i = 0; i < terrain.getLargeur(); i++) {
			for (int j = 0; j < terrain.getLongueur(); j++) {
				if (terrain.getNatureTerrainAtIndices(i, j) instanceof Chemin) {
					g2d.drawImage(ImagesJeu.chemin, j * next, i * next, null);
				} else if (terrain.getNatureTerrainAtIndices(i, j) instanceof Decoration) {
					g2d.drawImage(ImagesJeu.decoration, j * next, i * next, null);
				} else if (terrain.getNatureTerrainAtIndices(i, j) instanceof Entree) {
					g2d.drawImage(ImagesJeu.chemin, j * next, i * next, null);
					g2d.drawImage(ImagesJeu.entree, j * next, i * next, null);
				} else if (terrain.getNatureTerrainAtIndices(i, j) instanceof Sortie) {
					g2d.drawImage(ImagesJeu.chemin, j * next, i * next, null);
					g2d.drawImage(ImagesJeu.sortie, j * next, i * next, null);
				} else if (terrain.getNatureTerrainAtIndices(i, j) instanceof Campement) {
					g2d.drawImage(ImagesJeu.campement, j * next, i * next, null);
				}
			}
		}

		for (Attaquant ob : partie.getObstaclesPresents()) {
			g2d.drawImage(ImagesJeu.obstacle, (ob.getPosition().getY() - 1) * next, (ob.getPosition().getX() - 1) * next,
					null);
		}
		for (Attaquant m : partie.getMobilesPresents()) {
			g2d.drawImage(ImagesJeu.mobile, (m.getPosition().getY() - 1) * next, (m.getPosition().getX() - 1) * next, null);
		}
		for (Projectile p : partie.getProjectilesPresents()) {
			g2d.drawImage(ImagesJeu.projectile, (p.getPosition().getY() - 1) * next + 30,
					(p.getPosition().getX() - 1) * next + 30, null);

		}
	}

}