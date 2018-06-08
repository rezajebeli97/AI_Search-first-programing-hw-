import java.util.ArrayList;
import java.util.Scanner;

public class Problem {
	private ArrayList<int[]> walls = new ArrayList<int[]>();
	private int n, m;

	public Problem() {
		Scanner scr = new Scanner(System.in);
		n = scr.nextInt();
		m = scr.nextInt();
		int numberOfWalls = scr.nextInt();
		for (int i = 0; i < numberOfWalls; i++) {
			int[] wall = new int[4];
			int x1 = scr.nextInt();
			wall[0] = x1;
			int y1 = scr.nextInt();
			wall[1] = y1;
			int x2 = scr.nextInt();
			wall[2] = x2;
			int y2 = scr.nextInt();
			wall[3] = y2;
			walls.add(wall);
		}
	}

	public State initialState() {
		State s = new State(1, 1);
		return s;
	}

	public boolean goalTest(State s) {
		if (s.getX() == n && s.getY() == m) {
			return true;
		}
		return false;
	}

	public State result(State state, Action action) {
		State s;
		switch (action.name) {
		case 'r':
			s = new State(state.getX() + 1, state.getY());
			break;

		case 'u':
			s = new State(state.getX(), state.getY() - 1);
			break;
			
		case 'l':
			s = new State(state.getX() - 1, state.getY());
			break;
			
		case 'd':
			s = new State(state.getX(), state.getY() + 1);
			break;

		default:
			s = null;
			break;
		}
		return s;
	}

	public ArrayList<Action> actions(State state) {
		boolean hasRight = true;
		boolean hasLeft = true;
		boolean hasUp = true;
		boolean hasDown = true;
		
		if (state.getX() == 1) {
			hasLeft = false;
		}
		if (state.getX() == n) {
			hasRight = false;
		}
		if (state.getY() == 1) {
			hasUp = false;
		}
		if (state.getY() == m) {
			hasDown = false;
		}
		
		for (int i = 0; i < walls.size(); i++) {
			int[] w = walls.get(i);
			if (w[0] == state.getX() && w[1] == state.getY()) {
				if (w[2] == state.getX() - 1) {
					hasLeft =false;
				}
				if (w[2] == state.getX() + 1) {
					hasRight =false;
				}
				if (w[3] == state.getY() - 1) {
					hasUp =false;
				}
				if (w[3] == state.getY() + 1) {
					hasDown =false;
				}
			}
			if (w[2] == state.getX() && w[3] == state.getY()) {
				if (w[0] == state.getX() - 1) {
					hasLeft =false;
				}
				if (w[0] == state.getX() + 1) {
					hasRight =false;
				}
				if (w[1] == state.getY() - 1) {
					hasUp =false;
				}
				if (w[1] == state.getY() + 1) {
					hasDown =false;
				}
			}
		}
		
		ArrayList<Action> actions = new ArrayList<>();
		if (hasRight) {
			actions.add(new Action('r'));
		}
		if (hasLeft) {
			actions.add(new Action('l'));
		}
		if (hasDown) {
			actions.add(new Action('d'));
		}
		if (hasUp) {
			actions.add(new Action('u'));
		}
		return actions;
	}

	public boolean equal(State s, State child) {
		if (s.getX() == child.getX() && s.getY() == child.getY()) {
			return true;
		}
		return false;
	}

}