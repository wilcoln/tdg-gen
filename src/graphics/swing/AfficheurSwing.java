package graphics.swing;
import java.awt.*;
import java.io.IOException;

import javax.swing.*;

import graphics.AfficheurGraphique;
import jeu.Partie;

public class AfficheurSwing implements AfficheurGraphique {

	private JFrame appFrame;
	private TerrainGraphique terrainGraphique;
	private MenuGraphique menuGraphique;
	private static Partie partie;

	public AfficheurSwing(Partie p) throws IOException {
		appFrame = new JFrame("TowerDefense Game");
		partie = p;
		appFrame.setBounds(100, 100, partie.getTerrain().getLongueur() * 100, partie.getTerrain().getLargeur() * 108);
		appFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		terrainGraphique = new TerrainGraphique(p);
		menuGraphique = new MenuGraphique(p);
		JPanel mainPanel = new JPanel();
		GridBagLayout gridBagLayout = new GridBagLayout();
		GridBagConstraints c = new GridBagConstraints();
		mainPanel.setLayout(gridBagLayout);
		appFrame.setContentPane(mainPanel);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.ipadx = 0;
		c.ipady = 0;

		c.weightx = 0.0;
		c.gridx = 0;
		c.gridy = 0;
		c.anchor = GridBagConstraints.CENTER;
		gridBagLayout.setConstraints(terrainGraphique, c);
		appFrame.getContentPane().add(terrainGraphique);

		c.weightx = 0.0;
		c.gridheight = 1;
		c.gridwidth = 3;
		c.gridx = 0;
		c.gridy = 1;
		c.insets = new Insets(10, 0, 10, 0);
		gridBagLayout.setConstraints(menuGraphique, c);
		appFrame.getContentPane().add(menuGraphique);
	}

	@Override
	public void afficherTerrain() {
		terrainGraphique.refresh();
		appFrame.pack();
		appFrame.setVisible(true);
	}

	@Override
	public void afficherMenuPause() {
		menuGraphique.activerActionsPause();
	}

	@Override
	public void cacherMenuPause() {
		menuGraphique.desactiverActionsPause();
	}

	@Override
	public void afficheDialog(String message) {
		JOptionPane.showMessageDialog(null, message);
	}
}