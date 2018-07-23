
public class StateZombies extends State{
	int leftZombies;
	int leftHuman;
	int rightZombies;
	int rightHuman;
	boolean boatIsLeft;
	public StateZombies(int leftZombies, int leftHuman, int rightZombies, int rightHuman , boolean boatIsLeft) {
		this.leftZombies = leftZombies;
		this.leftHuman = leftHuman;
		this.rightZombies = rightZombies;
		this.rightHuman = rightHuman;
		this.boatIsLeft = boatIsLeft;
	}
	@Override
	public String print() {
		String str = "";
		if (boatIsLeft) {
			str = "( z="+leftZombies + " h=" + leftHuman + " ~ , z="+rightZombies + " h=" + rightHuman + ")";
		}
		else {
			str = "( z="+leftZombies + " h=" + leftHuman + " , z="+rightZombies + " h=" + rightHuman + " ~ )";
		}
		return str;
	}

}
