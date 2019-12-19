package graphics.swing;

import jeu.Partie;
import utils.Position;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class CaseGraphique extends JPanel {
    Partie partie;
    Position pos;

    public CaseGraphique(Partie partie, Position pos){
        this.partie = partie;
        this.pos = pos;
        this.setVisible(true);
        this.setOpaque(false);
        setPreferredSize(new Dimension(100, 100));
        addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent me) {
                JOptionPane.showMessageDialog(null, partie.getEtatAtPositionVerbose(pos));
                System.out.println(partie.getEtatAtPositionVerbose(pos));
            }
        });
    }
}
