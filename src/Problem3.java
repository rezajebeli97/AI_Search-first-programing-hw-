import java.util.ArrayList;
import java.util.Scanner;

public class Problem3 implements Problem {
	private char[] input = new char[24];

	public Problem3() {
		Scanner scr = new Scanner(System.in);
		String str = scr.nextLine();
		input = str.replaceAll(" ", "").toCharArray();
	}

	@Override
	public State initialState() {
		return new State3(input);
	}

	@Override
	public State goalState() { // many
		return null;
	}

	@Override
	public boolean goalTest(State s_inter) {
		State3 s = (State3) s_inter;
		boolean goal = true;
		for (int i = 0; i < 6; i++) {
			if ((s.getRubik()[i * 4 + 0] == s.getRubik()[i * 4 + 1])
					&& (s.getRubik()[i * 4 + 1] == s.getRubik()[i * 4 + 2])
					&& (s.getRubik()[i * 4 + 2] == s.getRubik()[i * 4 + 3]))
				;
			else
				goal = false;
		}

		if (goal)
			return true;
		else
			return false;
	}

	@Override
	public State result(State state_inter, Action action_inter) {
		State3 state = (State3) state_inter;
		Action3 action = (Action3) action_inter;
		char[] t = new char[24];
		for (int i = 0; i < 24; i++) {
			t[i] = state.getRubik()[i];
		}

		switch (action.name) {
		case "T":
			t[21] = state.getRubik()[14];
			t[20] = state.getRubik()[15];
			t[5] = state.getRubik()[21];
			t[4] = state.getRubik()[20];
			t[17] = state.getRubik()[5];
			t[16] = state.getRubik()[4];
			t[14] = state.getRubik()[17];
			t[15] = state.getRubik()[16];
			t[0] = state.getRubik()[2];
			t[1] = state.getRubik()[0];
			t[3] = state.getRubik()[1];
			t[2] = state.getRubik()[3];

			break;
		case "TC":
			t[14] = state.getRubik()[21];
			t[15] = state.getRubik()[21];
			t[21] = state.getRubik()[5];
			t[20] = state.getRubik()[4];
			t[5] = state.getRubik()[17];
			t[4] = state.getRubik()[16];
			t[17] = state.getRubik()[14];
			t[16] = state.getRubik()[15];
			t[2] = state.getRubik()[0];
			t[0] = state.getRubik()[1];
			t[1] = state.getRubik()[3];
			t[3] = state.getRubik()[2];
			break;

		case "F":
			t[3] = state.getRubik()[17];
			t[2] = state.getRubik()[19];
			t[20] = state.getRubik()[2];
			t[22] = state.getRubik()[3];
			t[9] = state.getRubik()[20];
			t[8] = state.getRubik()[22];
			t[19] = state.getRubik()[9];
			t[17] = state.getRubik()[8];
			t[4] = state.getRubik()[6];
			t[5] = state.getRubik()[4];
			t[7] = state.getRubik()[5];
			t[6] = state.getRubik()[7];
			break;

		case "FC":
			t[2] = state.getRubik()[20];
			t[3] = state.getRubik()[22];
			t[17] = state.getRubik()[3];
			t[19] = state.getRubik()[2];
			t[8] = state.getRubik()[17];
			t[9] = state.getRubik()[19];
			t[22] = state.getRubik()[8];
			t[20] = state.getRubik()[9];
			t[4] = state.getRubik()[5];
			t[5] = state.getRubik()[7];
			t[7] = state.getRubik()[6];
			t[6] = state.getRubik()[4];
			break;
			
		case "R":
			t[3] = state.getRubik()[7];
			t[1] = state.getRubik()[5];
			t[15] = state.getRubik()[3];
			t[13] = state.getRubik()[1];
			t[11] = state.getRubik()[15];
			t[9] = state.getRubik()[13];
			t[7] = state.getRubik()[11];
			t[5] = state.getRubik()[9];
			t[20] = state.getRubik()[22];
			t[22] = state.getRubik()[23];
			t[23] = state.getRubik()[21];
			t[21] = state.getRubik()[20];
			break;

		case "RC":
			t[7] = state.getRubik()[3];
			t[5] = state.getRubik()[1];
			t[3] = state.getRubik()[15];
			t[1] = state.getRubik()[13];
			t[15] = state.getRubik()[11];
			t[13] = state.getRubik()[9];
			t[11] = state.getRubik()[7];
			t[9] = state.getRubik()[5];
			t[22] = state.getRubik()[20];
			t[23] = state.getRubik()[22];
			t[21] = state.getRubik()[23];
			t[20] = state.getRubik()[21];
			break;

		default:
			break;
		}
		return new State3(t);
	}

	@Override
	public ArrayList<Action> actions(State state) {
		ArrayList<Action> actions = new ArrayList<Action>();
		actions.add(new Action3("T", 1));
		actions.add(new Action3("TC", 1));
		actions.add(new Action3("F", 1));
		actions.add(new Action3("FC", 1));
		actions.add(new Action3("R", 1));
		actions.add(new Action3("RC", 1));
		return actions;
	}

	@Override
	public boolean equal(State s1_inter, State s2_inter) {
		State3 s1 = (State3)s1_inter;
		State3 s2 = (State3)s2_inter;
		for (int i = 0; i < 24; i++) {
			if (s1.getRubik()[i] != s2.getRubik()[i]) {
				return false;
			}
		}
		return true;
	}

	@Override
	public double h(State state) {		//question has'nt asked
		
		return 0;
	}

	@Override
	public Action reverseAction(Action action) {		//question has'nt asked
		return null;
	}

}
