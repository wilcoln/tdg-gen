package element.passif;

import utils.DijkstraAlgo;
import utils.Position;
import utils.Utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Terrain {
	private List<Ligne> lignes;
	
	public Terrain() {
		lignes = new ArrayList<>();
	}

    public List<Position> calculPlusCourtChemin(Position posDep, Position posDest) {

		HashMap<Position, Integer> casesAccessibles = new HashMap<>();
		for(int i = 0; i < getLargeur(); i++)
			for(int j = 0; j < getLongueur(); j++){
				NatureTerrain nnt = getNatureTerrainAtIndices(i,j);
				if(nnt instanceof Entree || nnt instanceof Sortie)
					casesAccessibles.put(new Position(i+1,j+1), 0);
				else if(nnt instanceof Chemin)
					casesAccessibles.put(new Position(i+1,j+1), ((Chemin) nnt).getEnergie());
			}

		return Utils.calculPlusCourtChemin(posDep, posDest, casesAccessibles);
    }

    public List<Ligne> getLignes() {
		return lignes;
	}

	public NatureTerrain getNatureTerrainAtIndices(int x, int y){
		return getNatureTerrainAtPosition(new Position(x+1, y+1));
	}
	public NatureTerrain getNatureTerrainAtPosition(Position position){
		if(contientPosition(position))
			return getLignes().get(position.getX() - 1).getCases().get(position.getY() - 1);
		return null;
	}

    public boolean contientPosition(Position position) {
		return position != null &&
				position.getX() >= 1 &&
				position.getY() >= 1 &&
				position.getX() <= getLignes().size()
				&& position.getY() <= getLignes().get(0).getCases().size();

    }

    public int getLongueur() {
		return getLignes().get(0).getCases().size();
    }
    public int getLargeur() {
		return getLignes().size();
	}
}
