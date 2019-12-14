package graphics;

import element.actif.Mobile;
import element.actif.Obstacle;
import element.actif.Projectile;
import element.passif.Campement;
import element.passif.Chemin;
import element.passif.Decoration;
import element.passif.Entree;
import element.passif.Sortie;
import element.passif.Terrain;
import jeu.Partie;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class TerrainGraphique extends JPanel {
	private BufferedImage chemin;
	private BufferedImage decoration;
	private BufferedImage entree;
	private BufferedImage sortie;
	private BufferedImage campement;
	private JLabel label;
	private Terrain terrain;
	private Partie partie;
	private Image obstacle;
	private Image mobile;
	private Image projectile;
	//private String path = "/home/relmonta/annee_2/idm/gls-java";

	private String path = "..";
	public TerrainGraphique(Partie partie) throws IOException {
		File img2 = new File(path + "/images/route.jpg");
		chemin = ImageIO.read(img2);

		File img3 = new File(path + "/images/montagne.png");
		decoration = ImageIO.read(img3);

		File img4 = new File(path + "/images/entree.png");
		entree = ImageIO.read(img4);

		File img5 = new File(path + "/images/sortie.png");
		sortie = ImageIO.read(img5);

		File img6 = new File(path + "/images/garage.png");
		campement = ImageIO.read(img6);

		File img7 = new File(path + "/images/obstacle.png");
		this.obstacle = ImageIO.read(img7);

		File img8 = new File(path + "/images/mobile.png");
		this.mobile = ImageIO.read(img8);

		File img9 = new File(path + "/images/projectile.jpg");
		this.projectile = ImageIO.read(img9);

		this.partie = partie;
		this.terrain = partie.getTerrain();
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics g2d = (Graphics2D) g;
		int next = 100;
		for (int i = 0; i < terrain.getLargeur(); i++) {
			for (int j = 0; j < terrain.getLongueur(); j++) {
				if (terrain.getNatureTerrainAtIndices(i,j) instanceof Chemin) {
					g2d.drawImage(this.chemin, j * next, i * next, null);
				} else if (terrain.getNatureTerrainAtIndices(i,j) instanceof Decoration) {
					g2d.drawImage(this.decoration, j * next, i * next, null);
				} else if (terrain.getNatureTerrainAtIndices(i,j) instanceof Entree) {
					g2d.drawImage(this.chemin, j * next, i * next, null);
					g2d.drawImage(this.entree, j * next, i * next, null);
				} else if (terrain.getNatureTerrainAtIndices(i,j) instanceof Sortie) {
					g2d.drawImage(this.chemin, j * next, i * next, null);
					g2d.drawImage(this.sortie, j * next, i * next, null);
				} else if (terrain.getNatureTerrainAtIndices(i,j) instanceof Campement) {
					g2d.drawImage(this.campement, j * next, i * next, null);
				}
			}
		}

		for (Obstacle ob : partie.getObstaclesPresents()) {
			g2d.drawImage(this.obstacle, (ob.getPosition().getY() - 1) * next, (ob.getPosition().getX() - 1) * next,
					null);
		}
		for (Mobile m : partie.getMobilesPresents()) {
			g2d.drawImage(this.mobile, (m.getPosition().getY() - 1) * next, (m.getPosition().getX() - 1) * next,
					null);
		}
		for (Projectile p : partie.getProjectilesPresents()) {
			g2d.drawImage(this.projectile, (p.getPosition().getY() - 1) * next + 30, (p.getPosition().getX() - 1) * next + 30,
					null);

		}
	}
}
