package element.actif;

import element.actif.Mobile;
import element.actif.Obstacle;
import jeu.Partie;
import utils.Position;
import utils.Positionable;

public class ObstacleVague extends Positionable {
	private Obstacle obstacle;
	public ObstacleVague(Obstacle obstacle, Position position) {
		this.obstacle = obstacle;
		this.setPosition(position);
		
	}

	public Obstacle getObstacle() {
		return obstacle;
	}
	public void setObstacle(Obstacle obstacle) {
		this.obstacle = obstacle;
	}
	public void activer() {
		obstacle.activer();
	}

	@Override
	public String getEtat() {
		return obstacle.getName();
	}

	@Override
	public void evoluer(Partie partie) {
		lancerProjectile(partie);
	}

	private void lancerProjectile(Partie partie) {
		Position pos = partie.getPosEnemiPrio(obstacle);
		for (Projectile p: obstacle.getProjectiles()) {
				Projectile pc= p.clone();
				pc.setDepart(getPosition());
				pc.setArrive(pos);
				partie.getProjectilesPresents().add(pc);
		}
	}
}
