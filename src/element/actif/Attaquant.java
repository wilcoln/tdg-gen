package element.actif;

import element.Element;

import java.util.ArrayList;
import java.util.List;

public class Attaquant implements Element {
	private String name;
	private int energieMax;
	private int energieMaxActuelle;
	private int energieDispo;
	private TactiqueType tactique;
	private List<Projectile> projectiles;

	public Attaquant(String name, int energieMax, int energieDispo, TactiqueType tactique) {
		this.name = name;
		this.energieMax = energieMax;
		this.energieMaxActuelle = energieMax;
		this.energieDispo = energieDispo;
		this.setTactique(tactique);
		projectiles = new ArrayList<>();
	}
	public int getEnergieMax() {
		return energieMax;
	}
	public void ajouterEnergieMaxActuelle(int plus){
		this.energieMaxActuelle+=plus;
	}
	public void diminuerEnergieMaxActuelle(int moins){
		this.energieMaxActuelle-=moins;
	}
	public void setEnergieMax(int energieMax) {
		this.energieMax = energieMax;
	}

	public int getEnergieDispo() {
		return energieDispo;
	}

	public void setEnergieDispo(int enerieDispo) {
		this.energieDispo = enerieDispo;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public TactiqueType getTactique() {
		return tactique;
	}

	public void setTactique(TactiqueType tactique) {
		this.tactique = tactique;
	}

	public List<Projectile> getProjectiles() {
		return projectiles;
	}

	public void setProjectiles(List<Projectile> projectiles) {
		this.projectiles = projectiles;
	}

	public void activer() {
		this.energieDispo = this.energieMaxActuelle;

	}
	public int getEnergieMaxActuelle() {
		return energieMaxActuelle;
	}
	public void setEnergieMaxActuelle(int energieMaxActuelle) {
		this.energieMaxActuelle = energieMaxActuelle;
	}
	public boolean isElimine() {
		return energieMaxActuelle <= 0;
	}
	public String getEtat(){
		return	"nom : " + name + "\n" +
				"Est Vivant :" + !isElimine() + "\n" +
				"energieMax :" + energieMax + "\n" +
				"energieMaxActuelle : " + energieMaxActuelle + "\n" +
				"energieDispo : " + energieDispo + "\n" +
				"tactique : " + tactique;
	}
}
