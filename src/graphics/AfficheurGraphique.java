package graphics;

import java.io.IOException;

import javax.swing.*;

import jeu.Partie;

public class AfficheurGraphique {

	private JFrame frame;
	private TerrainGraphique terrainGraphique;
	private static Partie partie;

	/**
	 * Launch the application.
	 */

	/**
	 * Create the application.
	 * @throws IOException
	 */
	public AfficheurGraphique(Partie p) throws IOException {
		frame = new JFrame("TowerDefense Game");
		partie = p;
		frame.setBounds(0, 0, partie.getTerrain().getLongueur()*100,partie.getTerrain().getLargeur()*108);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		terrainGraphique = new TerrainGraphique(p);
	}

	/**
	 * Initialize the contents of the frame.
	 */

	public void afficheTerrain() {
		frame.setContentPane(terrainGraphique);
		frame.setVisible(true);
	}

	public void activerPause() {
		terrainGraphique.activerPause();
	}

	public void afficheDialog(String message){
		JOptionPane.showMessageDialog(null, message);
	}

	public void close() {
		System.exit(0);
	}

	public TerrainGraphique getTerrainGraphique() {
		// TODO Auto-generated method stub
		return this.terrainGraphique;
	}

	public void desactiverPause() {
		this.terrainGraphique.desactiverPause();
	}
}