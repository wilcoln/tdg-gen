package element.dynamique;

import element.statique.*;
import jeu.Partie;
import utils.Position;

import java.util.ArrayList;
import java.util.List;

/**
 * Cette classe représente un Mobile d'un jeu de Tower Defense
 *
 * @author Wilfried L. Bounsi
 */

public class Mobile extends Attaquant{

	private int volume;
	private int vitesse;
	private boolean sorti;
	private Position posEntree;
	private Position posSortie;
	private List<Position> chemin;
	private int indiceProchainePosition;

	public Mobile(String nom, int volume, int energieMax, int energieDispo, int vitesse, TactiqueType tactique) {
		super(nom, energieMax, energieDispo, tactique);
		this.volume = volume;
		this.vitesse = vitesse;
		this.sorti = false;
		chemin = new ArrayList<>();
		indiceProchainePosition = 1;
	}

	public int getVolume() {
		return volume;
	}

	public int getVitesse() {
		return vitesse;
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
	private void sortir(Partie partie){
		sorti = true;
		partie.incrementerNbMobileSortis();
	}
	public boolean isSorti() {
		return sorti;
	}

	@Override
	public void evoluer(Partie partie) {
		super.evoluer(partie);
		if (!isElimine() && !isSorti()) {
			if(getPosition().equals(posSortie)) {
				sortir(partie);
				partie.addNotification("un mobile " + getNom() + " vient de sortir");
			}
			else{
				if(peutAvancer(partie)){
					NatureTerrain nt = partie.getTerrain().getNatureTerrainAtPosition(chemin.get(indiceProchainePosition));
					if(nt instanceof Chemin){
						int moinsEnergie = ((Chemin)nt).getEnergie();
						// TODO E15 diminuer energieMaxActuelle ou energieDispo ???
						diminuerEnergieMaxActuelle(moinsEnergie);
					}
					setPosition(chemin.get(indiceProchainePosition));
					if(indiceProchainePosition+vitesse < chemin.size())
						indiceProchainePosition+=vitesse;
					else indiceProchainePosition++;
				}
			}
			if(isElimine())
				partie.addNotification("un mobile " + getNom() + " éliminé");
		}
	}
	public boolean peutEntrer(Partie partie) {
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
				"\tVitesse : " + vitesse + "\n" +
				"\tVolume : " + volume + "\n" +
				"\tPosEntree : " + posEntree + "\n" +
				"\tPosSortie : " + posSortie + "\n";

	}

	public boolean peutAvancer(Partie partie) {
		// Chemin, Entree, Sortie
		NatureTerrain nt = partie.getTerrain().getNatureTerrainAtPosition(chemin.get(indiceProchainePosition));
		return nt instanceof Entree ||
				nt instanceof Sortie ||
				nt instanceof Chemin && (((Chemin) nt).getVolume() - partie.getVolumeOccupeAt(chemin.get(indiceProchainePosition)) >= volume);
	}

	public void calculChemin(Partie partie) {
		chemin = partie.getTerrain().calculPlusCourtChemin(posEntree, posSortie);
	}
}
