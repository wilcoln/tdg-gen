package utils;

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

	public double distanceTo(Position pos){
		return Math.sqrt((this.getX() - pos.getX())^2 - (this.getY() -  pos.getY())^2);
	}
	public Position droite(){
		return new Position(getX(), getY() + 1);
	}
	public Position gauche(){
		return new Position(getX(), getY() - 1);
	}
	public Position bas(){
		return new Position(getX() - 1 , getY());
	}
	public Position haut(){
		return new Position(getX() + 1, getY());
	}

	public String toString(){
		return "[ " + getX() + ", " + getY() + " ]" ;
	}
	public boolean equals(Position that) {
		return that != null && this.getX() == that.getX() && this.getY() == that.getY();
	}

    public boolean isUndefined() {
		return this.equals(new Position(-1,-1));
    }
}
