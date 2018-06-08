public class State {
	public State parentState;
	public Action parentAction;
	private int x,y;
	public State(int x , int y) {

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
	public String print() {
		return "( " + getX() + " , " + getY() + " )" ;
	}
}
