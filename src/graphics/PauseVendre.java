package graphics;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;

import element.actif.Obstacle;
import jeu.Partie;

public class PauseVendre{

	private JFrame frame;
	private JComboBox obstaclesCb;
	private Partie partie;

	/**
	 * Create the application.
	 */
	public PauseVendre(Partie p) {
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

		JButton bouton = new JButton("Valider");
		bouton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				partie.getJoueur().vendreObstacle(((Obstacle) obstaclesCb.getSelectedItem()));
				frame.setVisible(false);
			}
		});
		panel.add(bouton);
		frame.setContentPane(panel);
		frame.setVisible(true);

	}
}
