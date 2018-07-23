public class State3 extends State{
	private char[] rubik = new char[24];
	
	public State3(char[] x) {
		for (int i = 0; i < 24; i++) {
			getRubik()[i] = x[i];
		}
	}
	
	@Override
	public String print() {
		String s = "(";
		for (int i = 0; i < 24; i++) {
			s += getRubik()[i] + " ";
		}
		s+=")";
		return s;
	}

	public char[] getRubik() {
		return rubik;
	}

	public void setRubik(char[] rubik) {
		this.rubik = rubik;
	}
	public void setRubik(char c , int i) {
		this.rubik[i] = c;
	}
}
