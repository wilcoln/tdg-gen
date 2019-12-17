package graphics.swing;

import jeu.Partie;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuGraphique extends JPanel {
    private JButton deplacer;
    private JButton vendre;
    private JButton reparer;
    private JButton acheter;
    private JButton pause;

    public MenuGraphique(Partie partie){
        GridLayout layout = new GridLayout(1,6);
        layout.setHgap(2);
        this.setLayout(layout);

        pause = new JButton("Pause");
       deplacer = new JButton("Deplacer");
       vendre = new JButton("Vendre");
       reparer = new JButton("Reparer");
       acheter = new JButton("Acheter");

        this.add(this.pause);
        this.add(this.acheter);
        this.add(this.deplacer);
        this.add(this.reparer);
        this.add(this.vendre);
       attacherListeners(partie);
       desactiverActionsPause();

        this.pause.setVisible(true);
        this.acheter.setVisible(true);
        this.deplacer.setVisible(true);
        this.vendre.setVisible(true);
        this.reparer.setVisible(true);

    }

    private void attacherListeners(Partie partie) {
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
        this.pause.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                if(partie.enPause()){
                    partie.setEnPause(false);
                    pause.setText("Pause");
                }else{
                    partie.setEnPause(true);
                    pause.setText("Resume");
                }
            }
        });
    }

    public void activerActionsPause() {
        this.pause.setEnabled(false);
        this.acheter.setEnabled(true);
        this.deplacer.setEnabled(true);
        this.vendre.setEnabled(true);
        this.reparer.setEnabled(true);
    }

    public void desactiverActionsPause() {
        this.pause.setEnabled(true);
        this.acheter.setEnabled(false);
        this.deplacer.setEnabled(false);
        this.vendre.setEnabled(false);
        this.reparer.setEnabled(false);
    }
}
