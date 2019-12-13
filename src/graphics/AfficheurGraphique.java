package graphics;

import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JPanel;
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
		frame.setBounds(0, 0, partie.getTerrain().getLignes().get(0).getCases().size()*100+200,partie.getTerrain().getLignes().size()*100 + 200);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		terrainGraphique = new TerrainGraphique(p);
	}

	/**
	 * Initialize the contents of the frame.
	 */

	public void affiche() {
				frame.setContentPane(terrainGraphique);
				frame.setVisible(true);
	}

	public void close() {
		System.exit(0);
	}
}
