public class Action3 extends Action{
	
	public String name;
	
	public Action3(String name , int cost) {
		this.name = name;
		this.cost = cost;
	}
	
	@Override
	public String print() {
		return name;
	}

}
