package element.dynamique;

import element.Element;
import jeu.Partie;
import utils.Position;

/**
 * Cette classe factorise le code dun élément dynamique (i.e capable d'évoluer durant une {@link Partie})
 * Un élément dynamique possède une {@link Position} qui évolue aussi avec le temps
 *
 * @author Wilfried L. Bounsi
 *
 */
public abstract class ElementDynamique extends Element {
    private Position position;


    public ElementDynamique(String nom){
       super(nom);
       this.position = new Position(-1, -1);
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public abstract  boolean isElimine();

    public boolean isSorti(Partie partie){
        return !partie.getTerrain().contientPosition(position);
    }
    public abstract void evoluer(Partie partie);
}
