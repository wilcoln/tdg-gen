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
import graphics.TerrainGraphique;
import jeu.Partie;

public class PauseVendreReparer {

	private JFrame frame;
	private JComboBox obstacles;
	private Partie partie;

	/**
	 * Create the application.
	 */
	public PauseVendreReparer(Partie p) {
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

		obstacles = new JComboBox(partie.getObstaclesPresents().toArray());
		panel.add(obstacles);

		JButton bouton = new JButton("Valider");
		bouton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				partie.afficheur.getAfficheurGraphique().getTerrainGraphique()
						.setObstaclePause(((Obstacle) obstacles.getSelectedItem()));
				frame.setVisible(false);
			}
		});
		panel.add(bouton);
		frame.setVisible(true);

	}
}
