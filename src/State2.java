
public class State2 extends State{
	private int[][] puzzle = new int[3][3];

	public State2(int[][] x) {
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				getPuzzle()[i][j] = x[i][j];
			}
		}
	}
	
	@Override
	public String print() {
		String s = "\n" + getPuzzle()[0][0] + " " + getPuzzle()[0][1] + " " + getPuzzle()[0][2] + "\n";
		s 		+= 		  getPuzzle()[1][0] + " " + getPuzzle()[1][1] + " " + getPuzzle()[1][2] + "\n";
		s 		+= 		  getPuzzle()[2][0] + " " + getPuzzle()[2][1] + " " + getPuzzle()[2][2] + "\n";
		return s;
	}

	public int[][] getPuzzle() {
		return puzzle;
	}

	public void setPuzzle(int[][] puzzle) {
		this.puzzle = puzzle;
	}
	
}
