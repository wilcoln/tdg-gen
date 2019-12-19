package element.statique;

import utils.Position;
import utils.Utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Cette classe représente un terrain dans un Jeu de Tower Defense
 *
 * @author Elmontassir Rachid
 * @author Wilfried L. Bounsi
 */

public class Terrain {
	private List<Ligne> lignes;
	
	public Terrain() {
		lignes = new ArrayList<>();
	}

	/**
	 * Calcul le plus court chemin entre {@code posDep} et {@code posDest}
	 * @param posDep
	 * @param posDest
	 * @return La liste des positions du plus court chemin entre {@code posDep} et {@code posDest}
	 */
    public List<Position> calculPlusCourtChemin(Position posDep, Position posDest) {

		HashMap<Position, Integer> casesAccessibles = new HashMap<>();
		for(int i = 0; i < getLargeur(); i++)
			for(int j = 0; j < getLongueur(); j++){
				NatureTerrain nt = getNatureTerrainAtIndices(i,j);
				switch (nt.getType()){
					case ENTREE:
					case SORTIE:
						casesAccessibles.put(new Position(i+1,j+1), 0);
						break;
					case CHEMIN:
						casesAccessibles.put(new Position(i+1,j+1), ((Chemin) nt).getEnergie());
						break;
				}
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
