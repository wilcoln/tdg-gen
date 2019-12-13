package element.passif;

import utils.Position;

import java.util.ArrayList;
import java.util.List;

public class Terrain {
	private List<Ligne> lignes;
	
	public Terrain() {
		lignes = new ArrayList<>();
	}

	public List<Ligne> getLignes() {
		return lignes;
	}

	public void setLignes(List<Ligne> lignes) {
		this.lignes = lignes;
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
	public List<Position> sontAccessiblesDepuis(Position pos) {
		List<Position> positions = null;
		NatureTerrain nt = getNatureTerrainAtPosition(pos);
		if(nt instanceof Chemin || nt instanceof Entree){
			positions = new ArrayList<>();
			if(getNatureTerrainAtPosition(pos.haut()) instanceof Chemin)
				positions.add(pos.haut());
			if(getNatureTerrainAtPosition(pos.bas()) instanceof Chemin)
				positions.add(pos.bas());
			if(getNatureTerrainAtPosition(pos.gauche()) instanceof Chemin)
				positions.add(pos.gauche());
			if(getNatureTerrainAtPosition(pos.droite()) instanceof Chemin)
				positions.add(pos.droite());
		}
		return positions;
	}

	public Position caseSuivante(Position position) {
		return sontAccessiblesDepuis(position).get(0);
	}
}
