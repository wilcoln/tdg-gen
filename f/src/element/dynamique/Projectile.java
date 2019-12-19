package element.dynamique;

import jeu.Partie;
import utils.Position;

import java.util.List;

/**
 * Cette classe représente un Projectile d'un jeu de Tower Defense
 *
 * @author Wilfried L. Bounsi
 */

public class Projectile extends ElementDynamique {

    private int masse;
    private int vitesse;
    private int portee;
    private int energie;
    private boolean elimine;

    private AttaquantType typeCible;
    private Position depart;
    private Position destination;

    public Projectile(String nom, int masse, int vitesse, int portee, int energie) {
        super(nom);
        this.masse = masse;
        this.vitesse = vitesse;
        this.portee = portee;
        this.energie = energie;
    }

    public Position getDepart() {
        return depart;
    }

    public void setDepart(Position depart) {
        this.depart = depart;
    }

    public Position getDestination() {
        return destination;
    }

    public void setDestination(Position destination) {
        this.destination = destination;
    }

    public int getMasse() {
        return masse;
    }

    public int getVitesse() {
        return vitesse;
    }

    public int getPortee() {
        return portee;
    }

    public int getEnergie() {
        return energie;
    }

    public void avancer() {
        //TODO E24, P8 => OK , pas de déplacement en diag
        for (int i = 0; i < vitesse; i++) {
            if (depart.estADroiteDe(destination)) {
                setPosition(getPosition().gauche());
            } else if (depart.estAGaucheDe(destination)) {
                setPosition(getPosition().droite());
            } else if (depart.estEnHautDe(destination)) {
                setPosition(getPosition().bas());
            } else if (depart.estEnBasDe(destination)) {
                setPosition(getPosition().haut());
            }
        }
    }

    public AttaquantType getTypeCible() {
        return typeCible;
    }

    public void setTypeCible(AttaquantType typeCible) {
        this.typeCible = typeCible;
    }

    public void croiserAttaquant(Attaquant cible) {
        cible.setEnergieMaxActuelle(cible.getEnergieMaxActuelle() - this.energie);
        if (this.energie > cible.getEnergieMaxActuelle()) {
            this.energie -= cible.getEnergieMaxActuelle();
            this.avancer();
        } else {
            this.setElimine(true);
        }
    }

    @Override
    public boolean isElimine() {
        return elimine;
    }

    public void setElimine(boolean elimine) {
        this.elimine = elimine;
    }

    @Override
    public Projectile clone() {
        return new Projectile(getNom(), masse, vitesse, portee, energie);
    }

    @Override
    public String getEtat() {
        return "Projectile \n" +
                "\tnom : " + getNom() + "\n" +
                "\tenergie :" + energie + "\n" +
                "\tmasse : " + masse + "\n" +
                "\tvitesse : " + vitesse + "\n" +
                "\tportee : " + portee;
    }

    @Override
    public void evoluer(Partie partie) {
        if (getPosition().isUndefined()) {
            setPosition(depart);

        }else{
            List<Attaquant> victimesPossibles;
            if (typeCible == AttaquantType.MOBILE)
                victimesPossibles = partie.getMobilesAt(getPosition());
            else
                victimesPossibles = partie.getObstaclesAt(getPosition());

            if (victimesPossibles.size() > 0) {
                Attaquant victime = victimesPossibles.get(0);
                croiserAttaquant(victime);
                if (victime.isElimine() && (victime instanceof Mobile)) {
                    partie.donneBonusEnergieJoueur(victime.getEnergieMax());
                }
            }
            if (getPosition().distanceTo(depart) < portee && !isSorti(partie))
                avancer();
            else{
                setElimine(true);
                partie.getProjectilesLances().remove(this);
            }
        }
    }
}
