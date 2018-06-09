public abstract class State {
	public State parentState;
	public Action parentAction;
	private double g;
	private int depth;
	public abstract String print();
	public double getG() {
		return g;
	}
	public void setG(double g) {
		this.g = g;
	}
	public int getDepth() {
		return depth;
	}
	public void setDepth(int depth) {
		this.depth = depth;
	}
}