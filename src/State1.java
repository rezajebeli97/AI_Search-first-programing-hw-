public class State1 extends State {
	//public State1 parentState;
	//public Action1 parentAction;
	private int x,y;
	//private double g;
//	private int depth;
	public State1(int x , int y) {
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
	@Override
	public String print() {
		return "( " + getX() + " , " + getY() + " )" ;
	}
	
	
}
