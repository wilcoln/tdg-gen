package graphics.swing;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;

import element.dynamique.Obstacle;
import element.statique.Campement;
import jeu.Partie;
import utils.Position;

public class PauseDeplacer {

	private JFrame frame;
	private JComboBox<Obstacle> obstaclesCb;
	private JComboBox<Position> positionsCb;
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
		frame.setBounds(100, 100, 200, 100);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel panel = new JPanel();

		obstaclesCb = new JComboBox(partie.getJoueur().getObstacles().toArray());
		panel.add(obstaclesCb);

		positionsCb = new JComboBox();
		for (int i = 0; i < partie.getTerrain().getLargeur(); i++) {
			for (int j = 0; j < partie.getTerrain().getLongueur(); j++) {
				if (partie.getTerrain().getNatureTerrainAtIndices(i, j) instanceof Campement) {
					positionsCb.addItem(new Position(i+1, j+1));
				}
			}
		}
		panel.add(positionsCb);
		JButton bouton = new JButton("Deplacer");
		bouton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				partie.getJoueur().deplacerObstacle(((Obstacle) obstaclesCb.getSelectedItem()),
						((Position) positionsCb.getSelectedItem()));
				frame.setVisible(false);
			}
		});
		panel.add(bouton);
		frame.setContentPane(panel);
		frame.setVisible(true);

	}
}
