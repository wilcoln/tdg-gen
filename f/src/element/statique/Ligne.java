package element.statique;

import java.util.ArrayList;
import java.util.List;

/**
 * Cette classe représente une Ligne d'un terrain dans un jeu de Tower Defense
 *
 * @author Elmontassir Rachid
 * @author Wilfried L. Bounsi
 */

public class Ligne {
	private List<NatureTerrain> cases;

	public Ligne() {
		cases = new ArrayList<>();
	}
	public List<NatureTerrain> getCases() {
		return cases;
	}
	
}
