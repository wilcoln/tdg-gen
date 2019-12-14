package element.actif;

import element.Element;
import jeu.Partie;
import utils.Position;

import java.util.List;

public interface ElementMobile extends Element {
    List<Position> positionsAccessibles(Partie partie);
}
