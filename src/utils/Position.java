package utils;

import java.util.ArrayList;
import java.util.List;

public class Position {

	private int x = -1;
	private int y = -1;


	public Position(int x, int y) {
		this.setX(x);
		this.setY(y);
	}


	public int getX() {
		return x;
	}


	public void setX(int x) {
		this.x = x;
	}


	public int getY() {
		return y;
	}


	public void setY(int y) {
		this.y = y;
	}

	public double distanceTo(Position pos) {
		if(pos == null)
			return Double.POSITIVE_INFINITY;
		double dx = this.x - pos.x;
		double dy = this.y - pos.y;
		return Math.sqrt(dx*dx + dy*dy);
	}

	public Position droite() {
		return new Position(getX(), getY() + 1);
	}

	public Position gauche() {
		return new Position(getX(), getY() - 1);
	}

	public Position bas() {
		return new Position(getX() + 1, getY());
	}

	public Position haut() {
		return new Position(getX() - 1, getY());
	}

	public String toString() {
		return "[ " + getX() + ", " + getY() + " ]";
	}

	public boolean equals(Position that) {
		return that != null && this.getX() == that.getX() && this.getY() == that.getY();
	}

	public boolean isUndefined() {
		return this.equals(new Position(-1, -1));
	}

	public boolean estAGaucheDe(Position that) {
		return that!= null && this.y < that.y;
	}

	public boolean estADroiteDe(Position that) {
		return that!= null && this.y > that.y;
	}

	public boolean estEnBasDe(Position that) {
		return that!= null && this.x > that.x;
	}

	public boolean estEnHautDe(Position that) {
		return that!= null && this.x < that.x;
	}

	public List<Position> getAdjacents() {
		List<Position> adjacents = new ArrayList<>();
		adjacents.add(this.gauche());
		adjacents.add(this.droite());
		adjacents.add(this.haut());
		adjacents.add(this.bas());
		return adjacents;
	}
}
