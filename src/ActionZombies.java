
public class ActionZombies extends Action{
	int zombieTransfer;
	int humanTransfer;
	public ActionZombies(int zombieTransfer, int humanTransfer) {
		this.zombieTransfer = zombieTransfer;
		this.humanTransfer = humanTransfer;
		this.cost = 1;
	}
	@Override
	public String print() {
		String str = "( z=" + zombieTransfer +" , h=" + humanTransfer + " )";
		return str;
	}

}
