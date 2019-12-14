package element.actif;

import element.passif.Chemin;
import element.passif.Entree;
import element.passif.NatureTerrain;
import element.passif.Sortie;
import jeu.Partie;
import utils.Position;

import java.util.ArrayList;
import java.util.List;

public class Mobile extends Attaquant implements ElementMobile {

	private int volume;
	private int vitesse;
	private boolean sorti;
	private Position posEntree;
	private Position posSortie;

	public Mobile(String nom, int volume, int energieMax, int energieDispo, int vitesse, TactiqueType tactique) {
		super(nom, energieMax, energieDispo, tactique);
		this.volume = volume;
		this.vitesse = vitesse;
		this.sorti = false;
	}

	public int getVolume() {
		return volume;
	}
	public void setVolume(int volume) {
		this.volume = volume;
	}

	public int getVitesse() {
		return vitesse;
	}
	public void setVitesse(int vitesse) {
		this.vitesse = vitesse;
	}

	public Position getPosEntree() {
		return posEntree;
	}
	public void setPosEntree(Position posEntree) {
		this.posEntree = posEntree;
	}
	public Position getPosSortie() {
		return posSortie;
	}
	public void setPosSortie(Position posSortie) {
		this.posSortie = posSortie;
	}
	public void entrer() {
		setPosition(getPosEntree());
	}
	public void sortir(Partie partie){
		sorti = true;
		partie.incrementerNbMobileSortis();
	}
	public boolean isSorti() {
		return sorti;
	}

	@Override
	public void evoluer(Partie partie) {
		super.evoluer();
		if (!isElimine() && !isSorti()) {
			if(getPosition().equals(posSortie))
				sortir(partie);
			else{
				Position np = meilleurePositionSuivante(partie);
				if(np != null){
					NatureTerrain nnt = partie.getTerrain().getNatureTerrainAtPosition(np);
					if(nnt instanceof Chemin){
						int moinsEnergie = ((Chemin)nnt).getEnergie();
						// TODO E15 diminuer energieMaxActuelle ou energieDispo ???
						diminuerEnergieMaxActuelle(moinsEnergie);
					}
					setPosition(np);
				}
			}
		}
	}

	private Position meilleurePositionSuivante(Partie partie) {
		//TODO E14, E17, E19, P22, P23  => OK
		if(positionsAccessibles(partie).size() > 0){
			Position meilleurePos = positionsAccessibles(partie).get(0);
			for(Position position: positionsAccessibles(partie)){
				if(posSortie.distanceTo(position) < posSortie.distanceTo(meilleurePos))
					meilleurePos = position;
			}
			return meilleurePos;
		}
		return null;
	}

	public boolean peutEntrer(Partie partie) {
		// P5
		return partie.getMobilesAt(posEntree).size() == 0;
	}

	@Override
	public AttaquantType getType() {
		return AttaquantType.MOBILE;
	}

	@Override
	public Mobile clone(){
		Mobile same = new Mobile(getNom(), getVolume(), getEnergieMax(), getEnergieDispo(), getVitesse(), getTactique());
		for(Projectile p: getProjectiles())
			same.getProjectiles().add(p);
		return same;
	}

	@Override
	public String getEtat(){
		return "Mobile \n" + super.getEtat() + "\n" +
				"Vitesse : " + vitesse + "\n" +
				"Volume : " + volume + "\n" +
				"PosEntree : " + posEntree + "\n" +
				"PosSortie : " + posSortie + "\n";

	}

	@Override
	public List<Position> positionsAccessibles(Partie partie) {
		// Chemin, Entree, Sortie
		List<Position> resultats = new ArrayList<>();
		NatureTerrain nnt;
		for(Position adjacent : getPosition().getAdjacents()){
			nnt = partie.getTerrain().getNatureTerrainAtPosition(adjacent);
			if(nnt instanceof Entree || nnt instanceof Sortie)
				resultats.add(adjacent);
			else if(nnt instanceof Chemin){
				if(((Chemin) nnt).getVolume() - partie.getVolumeOccupeAt(adjacent) >= volume)
					resultats.add(adjacent);
			}
		}
		return resultats;
	}
}
