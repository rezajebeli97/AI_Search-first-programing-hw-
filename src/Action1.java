
public class Action1 extends Action{

	public char name;
//	public int cost;
	
	@Override
	public String print() {
		return name + "";
	}
	
	public Action1(char name , int cost) {
		this.name = name;
		this.cost = cost;
	}
	
}