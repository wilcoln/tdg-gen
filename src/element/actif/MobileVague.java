package element.actif;

import element.actif.Mobile;
import jeu.Partie;
import utils.Position;
import utils.Positionable;

import java.util.List;

public class MobileVague extends Positionable {
	private Mobile mobile;
	private int ordre;
	private Position posEntree;
	private Position posSortie;

	private boolean sorti;
	
	public MobileVague(Mobile mobile, int ordre, Position posEntree, Position posSortie) {
		this.mobile = mobile;
		this.ordre = ordre;
		this.posEntree = posEntree;
		this.posSortie = posSortie;
		this.sorti = false;
	}
	public boolean peutEntrer(Partie partie) {
		List<Position > posAccessibles = partie.getTerrain().sontAccessiblesDepuis(posEntree);
		return posAccessibles != null && posAccessibles.size() > 0;
	}
	public Mobile getMobile() {
		return mobile;
	}
	public void setMobile(Mobile mobile) {
		this.mobile = mobile;
	}
	public int getOrdre() {
		return ordre;
	}
	public void setOrdre(int ordre) {
		this.ordre = ordre;
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
		partie.ajouterNbMobileSortis();
	}
	public void activer() {
		mobile.activer();
	}
	public boolean isSorti() {
		return sorti;
	}
	public void evoluer(Partie partie){
		if(!mobile.isElimine() && !isSorti()){
			faireUnPas(partie);
		}
	}
	public void faireUnPas(Partie partie){
		if(getPosition().equals(posSortie))
			sortir(partie);
		else
			setPosition(partie.getTerrain().caseSuivante(getPosition()));
	}
	public void setSorti(boolean sorti) {
		this.sorti = sorti;
	}

	@Override
	public String getEtat() {
		return mobile.getName();
	}
}
