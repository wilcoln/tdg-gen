package element.passif;

import java.util.ArrayList;
import java.util.List;

public class Ligne {
	private List<NatureTerrain> cases;

	public Ligne() {
		cases = new ArrayList<>();
	}
	public List<NatureTerrain> getCases() {
		return cases;
	}

	public void setCases(List<NatureTerrain> cases) {
		this.cases = cases;
	}
	
}
