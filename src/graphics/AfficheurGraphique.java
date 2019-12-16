package graphics;
import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.ScrollPane;
import java.io.IOException;

import javax.swing.*;

import jeu.Partie;

public class AfficheurGraphique {

	private JFrame frameTerrain;
	private JFrame frameNotif;
	private TerrainGraphique terrainGraphique;
	private JTextArea notif;
	private JScrollPane sp = new JScrollPane();
	private static Partie partie;
	
	/**
	 * Launch the application.
	 */

	/**
	 * Create the application.
	 * 
	 * @throws IOException
	 */
	public AfficheurGraphique(Partie p) throws IOException {
		frameTerrain = new JFrame("TowerDefense Game");
		frameNotif = new JFrame("Notifications");
		partie = p;
		frameTerrain.setBounds(0, 0, partie.getTerrain().getLongueur() * 100, partie.getTerrain().getLargeur() * 108);
		frameNotif.setBounds(100, 100, partie.getTerrain().getLongueur() * 100, partie.getTerrain().getLargeur() * 108);
		frameTerrain.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		terrainGraphique = new TerrainGraphique(p);
		notif = new JTextArea("=======>NOTIFICATIONS<=====");
		notif.setBounds(0, 0, partie.getTerrain().getLongueur() * 100, partie.getTerrain().getLargeur() * 108);
		
	}

	/**
	 * Initialize the contents of the frame.
	 */

	public void afficheTerrain() {
		afficherNotif();
		frameTerrain.setContentPane(terrainGraphique);
		frameTerrain.setVisible(true);
	}
	public void afficherNotif() {
		notif.setText("xxzcz");
		sp.setViewportView(notif);
		frameNotif.setContentPane(sp);
		frameNotif.setVisible(true);
	}

	public void activerPause() {
		terrainGraphique.activerPause();
	}

	public void afficheDialog(String message) {
		JOptionPane.showMessageDialog(null, message);
	}

	public void close() {
		System.exit(0);
	}

	public TerrainGraphique getTerrainGraphique() {
		return this.terrainGraphique;
	}

	public void desactiverPause() {
		this.terrainGraphique.desactiverPause();
	}
}