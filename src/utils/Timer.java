package utils;

/**
 * Cette classe fournit les méthodes permettant de gérer l'écoulement du temps dans le jeu
 * @author Wilfried L. Bounsi
 */

public final class Timer {
    public static void attendre(int millis){
        try {
			Thread.sleep(millis);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
    }
}
