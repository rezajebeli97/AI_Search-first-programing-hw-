import java.util.ArrayList;
import java.util.Scanner;

public class Problem1 implements Problem{
	private ArrayList<int[]> walls = new ArrayList<int[]>();
	private int n, m;

	public Problem1() {
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

	@Override
	public State1 initialState() {
		State1 s = new State1(1, 1);
		return s;
	}

	@Override
	public State1 goalState() {
		State1 s = new State1(n, m);
		return s;
	}

	@Override
	public boolean goalTest(State s) {
		State1 s1 = (State1)s;
		if (s1.getX() == n && s1.getY() == m) {
			return true;
		}
		return false;
	}

	@Override
	public State1 result(State state_inter, Action action_inter) {
		State1 state = (State1)state_inter;
		Action1 action = (Action1)action_inter;
		State1 s;
		switch (action.name) {
		case 'r':
			s = new State1(state.getX() + 1, state.getY());
			break;

		case 'u':
			s = new State1(state.getX(), state.getY() - 1);
			break;

		case 'l':
			s = new State1(state.getX() - 1, state.getY());
			break;

		case 'd':
			s = new State1(state.getX(), state.getY() + 1);
			break;

		default:
			s = null;
			break;
		}
		return s;
	}

	@Override
	public ArrayList<Action> actions(State state_inter) {
		State1 state = (State1)state_inter;
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
					hasLeft = false;
				}
				if (w[2] == state.getX() + 1) {
					hasRight = false;
				}
				if (w[3] == state.getY() - 1) {
					hasUp = false;
				}
				if (w[3] == state.getY() + 1) {
					hasDown = false;
				}
			}
			if (w[2] == state.getX() && w[3] == state.getY()) {
				if (w[0] == state.getX() - 1) {
					hasLeft = false;
				}
				if (w[0] == state.getX() + 1) {
					hasRight = false;
				}
				if (w[1] == state.getY() - 1) {
					hasUp = false;
				}
				if (w[1] == state.getY() + 1) {
					hasDown = false;
				}
			}
		}

		ArrayList<Action> actions = new ArrayList<>();
		if (hasRight) {
			actions.add(new Action1('r', 1));
		}
		if (hasLeft) {
			actions.add(new Action1('l', 1));
		}
		if (hasDown) {
			actions.add(new Action1('d', 1));
		}
		if (hasUp) {
			actions.add(new Action1('u', 1));
		}
		return actions;
	}

	@Override
	public boolean equal(State s1_inter, State s2_inter) {
		State1 s1 = (State1)s1_inter;
		State1 s2 = (State1)s2_inter;
		if (s1.getX() == s2.getX() && s1.getY() == s2.getY()) {
			return true;
		}
		return false;
	}

	@Override
	public double h(State state_inter) {
		State1 state = (State1)state_inter;
		return Math.sqrt(Math.pow(n - state.getX(), 2) - Math.pow(m - state.getY(), 2));
	}

	@Override
	public Action reverseAction(Action action_inter) {
		Action1 action = (Action1)action_inter;
		Action1 reverseAction;
		switch (action.name) {
		case 'r':
			reverseAction = new Action1('l', 1);
			break;

		case 'l':
			reverseAction = new Action1('r', 1);
			break;
			
		case 'd':
			reverseAction = new Action1('u', 1);
			break;
			
		case 'u':
			reverseAction = new Action1('d', 1);
			break;

		default:
			reverseAction = null;
			break;
		}
		return reverseAction;
	}

}