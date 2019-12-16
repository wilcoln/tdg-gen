package graphics;

import element.actif.Attaquant;
import element.actif.Mobile;
import element.actif.Obstacle;
import element.actif.Projectile;
import element.actif.TactiqueType;
import element.passif.Campement;
import element.passif.Chemin;
import element.passif.Decoration;
import element.passif.Entree;
import element.passif.Sortie;
import element.passif.Terrain;
import jeu.Partie;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import utils.Position;

public class TerrainGraphique extends JPanel {
	private BufferedImage chemin;
	private BufferedImage decoration;
	private BufferedImage entree;
	private BufferedImage sortie;
	private BufferedImage campement;
	private Terrain terrain;
	private Partie partie;
	private Image obstacle;
	private Image mobile;
	private Image projectile;
	private JButton deplacer = new JButton("Deplacer");
	private JButton vendre = new JButton("Vendre");
	private JButton reparer = new JButton("Reparer");
	private JButton acheter = new JButton("Acheter");
	// private String path = "/home/relmonta/annee_2/idm/gls-java";
	private String path = "..";
	private Position positionDeDep;

	public TerrainGraphique(Partie partie) throws IOException {

		this.setLayout(new GridBagLayout());
		this.add(this.acheter);
		this.add(this.deplacer);
		this.add(this.reparer);
		this.add(this.vendre);
		JTextArea notif = new JTextArea("=====> Notification <===== \n");
		notif.setBackground(Color.white);
		notif.setEditable(false);
		JScrollPane actionScroll = new JScrollPane(notif, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		this.add(notif);
		this.add(actionScroll);

		this.acheter.setVisible(false);
		this.deplacer.setVisible(false);
		this.vendre.setVisible(false);
		this.reparer.setVisible(false);

		File img2 = new File(path + "/images/route.jpg");
		chemin = ImageIO.read(img2);

		File img3 = new File(path + "/images/montagne.png");
		decoration = ImageIO.read(img3);

		File img4 = new File(path + "/images/route.jpg");
		entree = ImageIO.read(img4);

		File img5 = new File(path + "/images/route.jpg");
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

	public void activerPause() {
		this.acheter.setVisible(true);
		this.deplacer.setVisible(true);
		this.vendre.setVisible(true);
		this.reparer.setVisible(true);

		this.acheter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				PauseAcheter pause = new PauseAcheter(partie);
			}
		});
		this.deplacer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				PauseDeplacer pause = new PauseDeplacer(partie);
			}
		});
		this.vendre.addActionListener((ActionListener) new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				PauseVendre pause = new PauseVendre(partie);
			}
		});
		this.reparer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				PauseReparer pause = new PauseReparer(partie);
			}
		});
	}

	public void desactiverPause() {
		this.acheter.setVisible(false);
		this.deplacer.setVisible(false);
		this.vendre.setVisible(false);
		this.reparer.setVisible(false);
		for (ActionListener l : this.acheter.getActionListeners()) {
			this.acheter.removeActionListener(l);
		}
		for (ActionListener l : this.deplacer.getActionListeners()) {
			this.deplacer.removeActionListener(l);
		}
		for (ActionListener l : this.reparer.getActionListeners()) {
			this.reparer.removeActionListener(l);
		}
		for (ActionListener l : this.vendre.getActionListeners()) {
			this.vendre.removeActionListener(l);
		}
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics g2d = (Graphics2D) g;
		int next = 100;
		for (int i = 0; i < terrain.getLargeur(); i++) {
			for (int j = 0; j < terrain.getLongueur(); j++) {
				if (terrain.getNatureTerrainAtIndices(i, j) instanceof Chemin) {
					g2d.drawImage(this.chemin, j * next, i * next, null);
				} else if (terrain.getNatureTerrainAtIndices(i, j) instanceof Decoration) {
					g2d.drawImage(this.decoration, j * next, i * next, null);
				} else if (terrain.getNatureTerrainAtIndices(i, j) instanceof Entree) {
					g2d.drawImage(this.chemin, j * next, i * next, null);
					g2d.drawImage(this.entree, j * next, i * next, null);
				} else if (terrain.getNatureTerrainAtIndices(i, j) instanceof Sortie) {
					g2d.drawImage(this.chemin, j * next, i * next, null);
					g2d.drawImage(this.sortie, j * next, i * next, null);
				} else if (terrain.getNatureTerrainAtIndices(i, j) instanceof Campement) {
					g2d.drawImage(this.campement, j * next, i * next, null);
				}
			}
		}

		for (Attaquant ob : partie.getObstaclesPresents()) {
			g2d.drawImage(this.obstacle, (ob.getPosition().getY() - 1) * next, (ob.getPosition().getX() - 1) * next,
					null);
		}
		for (Attaquant m : partie.getMobilesPresents()) {
			g2d.drawImage(this.mobile, (m.getPosition().getY() - 1) * next, (m.getPosition().getX() - 1) * next, null);
		}
		for (Projectile p : partie.getProjectilesPresents()) {
			g2d.drawImage(this.projectile, (p.getPosition().getY() - 1) * next + 30,
					(p.getPosition().getX() - 1) * next + 30, null);

		}
	}
}