package graphics;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImagesJeu {
    public static BufferedImage chemin;
    public static BufferedImage decoration;
    public static BufferedImage entree;
    public static BufferedImage sortie;
    public static BufferedImage campement;
    public static Image obstacle;
    public static Image mobile;
    public static Image projectile;

    static {
        try {
            chemin = ImageIO.read(new File("../images/marbre.png"));

            File img3 = new File("../images/montagne.png");
            decoration = ImageIO.read(img3);

            File img4 = new File("../images/anthill.png");
            entree = ImageIO.read(img4);

            File img5 = new File("../images/algue.png");
            sortie = ImageIO.read(img5);

            File img6 = new File("../images/garage.png");
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
