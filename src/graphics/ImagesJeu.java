package graphics;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

/**
 * Cette classe charge et détient les images utilisées dans le jeu
 *
 * @author Elmontassir Rachid
 * @author Wilfried L. Bounsi
 *
 */
public final class ImagesJeu {
    public static Image chemin;
    public static Image decoration;
    public static Image entree;
    public static Image sortie;
    public static Image campement;
    public static Image obstacle;
    public static Image mobile;
    public static Image projectile;

    static {
        try {
            chemin = ImageIO.read(new File("../images/chemin.png"));

            File img3 = new File("../images/montagne.png");
            decoration = ImageIO.read(img3);

            File img4 = new File("../images/entree.png");
            entree = ImageIO.read(img4);

            File img5 = new File("../images/sortie.png");
            sortie = ImageIO.read(img5);

            File img6 = new File("../images/campement.png");
            campement = ImageIO.read(img6);

            File img7 = new File("../images/obstacle.png");
            obstacle = ImageIO.read(img7);

            File img8 = new File("../images/mobile.png");
            mobile = ImageIO.read(img8);

            File img9 = new File("../images/projectile.jpg");
            projectile = ImageIO.read(img9);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
