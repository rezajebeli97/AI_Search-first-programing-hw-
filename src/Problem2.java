//مسیله ی پازل ۸ تایی

import java.util.ArrayList;
import java.util.Scanner;

public class Problem2 implements Problem {
	int[][] input = new int[3][3];

	public Problem2() {
		Scanner scr = new Scanner(System.in);
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				input[i][j] = scr.nextInt();
			}
		}
	}

	@Override
	public State initialState() {
		return new State2(input);
	}

	@Override
	public State goalState() {
		int[][] goal = new int[3][3];
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				goal[i][j] = i * 3 + j;
			}
		}
		return new State2(goal);
	}

	@Override
	public boolean goalTest(State s_inter) {
		State2 s = (State2) s_inter;
		int[][] puzzle = s.getPuzzle();
		for (int i = 0; i < 3; i++)
			for (int j = 0; j < 3; j++)
				if (puzzle[i][j] != i * 3 + j)
					return false;

		return true;
	}

	@Override
	public State result(State state_inter, Action action_inter) {
		Action2 action = (Action2) action_inter;
		State2 state = (State2) state_inter;
		int[][] puzzle = new int[3][3];
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				puzzle[i][j] = state.getPuzzle()[i][j];
			}
		}
		int x0 = 0, y0 = 0;
		for (int i = 0; i < 3; i++)
			for (int j = 0; j < 3; j++)
				if (puzzle[i][j] == 0) {
					x0 = j;
					y0 = i;
				}

		switch (action.name) {
		case 'r':
			puzzle[y0][x0] = puzzle[y0][x0 + 1];
			puzzle[y0][x0 + 1] = 0;
			break;
		case 'l':
			puzzle[y0][x0] = puzzle[y0][x0 - 1];
			puzzle[y0][x0 - 1] = 0;
			break;
		case 'd':
			puzzle[y0][x0] = puzzle[y0 + 1][x0];
			puzzle[y0 + 1][x0] = 0;
			break;
		case 'u':
			puzzle[y0][x0] = puzzle[y0 - 1][x0];
			puzzle[y0 - 1][x0] = 0;
			break;
		default:
			break;
		}

		return new State2(puzzle);
	}

	@Override
	public ArrayList<Action> actions(State state_inter) {
		State2 state = (State2) state_inter;
		int[][] puzzle = state.getPuzzle();
		int x0 = 0, y0 = 0;
		for (int i = 0; i < 3; i++)
			for (int j = 0; j < 3; j++)
				if (puzzle[i][j] == 0) {
					x0 = j;
					y0 = i;
				}
		boolean canRight = true, canLeft = true, canUp = true, canDown = true;
		if (x0 == 0)
			canLeft = false;
		if (x0 == 2)
			canRight = false;
		if (y0 == 0)
			canUp = false;
		if (y0 == 2)
			canDown = false;

		ArrayList<Action> actions = new ArrayList<>();
		if (canRight) {
			actions.add(new Action2('r', 1));
		}
		if (canLeft) {
			actions.add(new Action2('l', 1));
		}
		if (canDown) {
			actions.add(new Action2('d', 1));
		}
		if (canUp) {
			actions.add(new Action2('u', 1));
		}
		return actions;
	}

	@Override
	public boolean equal(State s1_inter, State s2_inter) {
		State2 s1 = (State2) s1_inter;
		State2 s2 = (State2) s2_inter;
		int[][] puzzle1 = s1.getPuzzle();
		int[][] puzzle2 = s2.getPuzzle();

		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if (puzzle1[i][j] != puzzle2[i][j]) {
					return false;
				}
			}
		}
		return true;
	}

	@Override
	public double h(State state_inter) {
		State2 state = (State2) state_inter;
		int[][] puzzle = state.getPuzzle();
		double sumOfDistances = 0;
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				sumOfDistances += Math.sqrt(Math.pow(puzzle[i][j], 2) - Math.pow((i * 3 + j), 2));
			}
		}
		return sumOfDistances;
	}

	@Override
	public Action reverseAction(Action action_inter) {
		Action2 action = (Action2) action_inter;
		Action2 reverseAction;
		switch (action.name) {
		case 'r':
			reverseAction = new Action2('l', 1);
			break;

		case 'l':
			reverseAction = new Action2('r', 1);
			break;

		case 'd':
			reverseAction = new Action2('u', 1);
			break;

		case 'u':
			reverseAction = new Action2('d', 1);
			break;

		default:
			reverseAction = null;
			break;
		}
		return reverseAction;
	}

}
