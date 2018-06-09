import java.util.ArrayList;

public interface Problem {

	public State initialState();

	public State goalState();

	public boolean goalTest(State s);

	public State result(State state, Action action);

	public ArrayList<Action> actions(State state);

	public boolean equal(State s1, State s2);

	public double h(State state);

	public Action reverseAction(Action action);
}