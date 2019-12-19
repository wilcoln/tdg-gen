package graphics;

/**
 * Cette interface définit les méthodes nécessaires d'une afficheur Graphique (implémentation swing, javafx etc...)
 *
 * @author Wilfried L. Bounsi
 *
 */
public interface AfficheurGraphique {
   void afficherTerrain();
   void activerActionsPause();
   void desactiverActionsPause();
   void afficherMessage(String message);
}
