package graphics;

import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;

import element.actif.Obstacle;
import element.passif.Campement;
import jeu.Partie;
import utils.Position;

public class PauseDeplacer {

	private JFrame frame;
	private JComboBox<Obstacle> obstaclesPlaces;
	private JComboBox<Position> positions;
	private Partie partie;

	/**
	 * Create the application.
	 */
	public PauseDeplacer(Partie p) {
		this.partie = p;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 100, 50);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel panel = new JPanel();
		panel.setLayout(new FlowLayout());

		obstaclesPlaces = new JComboBox(partie.getObstaclesPresents().toArray());
		panel.add(obstaclesPlaces);

		positions = new JComboBox();
		for (int i = 0; i < partie.getTerrain().getLargeur(); i++) {
			for (int j = 0; j < partie.getTerrain().getLongueur(); j++) {
				if (partie.getTerrain().getNatureTerrainAtIndices(i, j) instanceof Campement) {
					positions.addItem(new Position(i, j));
				}
			}
		}
		panel.add(positions);
		JButton bouton = new JButton("Valider");
		bouton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				partie.afficheur.getAfficheurGraphique().getTerrainGraphique()
						.setPositonDeplacement((Position) positions.getSelectedItem());
				partie.afficheur.getAfficheurGraphique().getTerrainGraphique()
						.setObstaclePause((Obstacle) obstaclesPlaces.getSelectedItem());
				frame.setVisible(false);
			}
		});
		panel.add(bouton);
		frame.setVisible(true);

	}
}
