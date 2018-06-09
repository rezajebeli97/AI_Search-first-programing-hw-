public class Action2 extends Action{

	public char name;
	
	@Override
	public String print() {
		return name + "";
	}
	
	public Action2(char name , int cost) {
		this.name = name;
		this.cost = cost;
	}
	
}