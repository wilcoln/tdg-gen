package utils;

public final class Timer {
    public static void attendre(int millis){
        try {
			Thread.sleep(millis);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
    }
}
