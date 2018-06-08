import java.util.ArrayList;

public class Search {

	public void bfs_graph(Problem problem) {
		ArrayList<State> f = new ArrayList<State>();
		ArrayList<State> e = new ArrayList<State>();
		State initState = problem.initialState();
		if (problem.goalTest(initState)) {
			printDetails(initState, f, e);
		}

		f.add(initState);

		while (!f.isEmpty()) {
			State tmpState = f.remove(0);
			e.add(tmpState);
			for (Action a : problem.actions(tmpState)) {
				State child = problem.result(tmpState, a);
				child.parentAction = a;
				child.parentState = tmpState;
				boolean eContainsChild = false;
				for (State s : e) {
					if (problem.equal(s, child))
						eContainsChild = true;
				}
				if (!eContainsChild) {
					if (problem.goalTest(child)) {
						printDetails(child, f, e);
						return;
					}
					boolean fContainsChild = false;
					for (State s : f) {
						if (problem.equal(s, child))
							fContainsChild = true;
					}
					if (!fContainsChild) {
						f.add(child);
					}
				}
			}
		}
	}

	//////////////////////////////////////////////////////////////////////////////////////////////////////////

	public void dfs_tree(Problem problem, State p) {
		if (problem.goalTest(p)) {
			System.out.println(p.print());
		}
		for (Action action : problem.actions(p)) {
			dfs_tree(problem, problem.result(p, action));
		}
	}

	//////////////////////////////////////////////////////////////////////////////////////////////////////////

	public void dfs_graph(Problem problem, State p) {
		if (problem.goalTest(p)) {
			System.out.println(p.print());
		}
		for (Action action : problem.actions(p)) {
			dfs_graph(problem, problem.result(p, action));
		}
	}

	//////////////////////////////////////////////////////////////////////////////////////////////////////////

	public void uniformCost_graph(Problem problem) {
		ArrayList<State> f = new ArrayList<State>();
		ArrayList<State> e = new ArrayList<State>();
		State initState = problem.initialState();

		f.add(initState);

		while (!f.isEmpty()) {
			State p = f.remove(0);
			if (problem.goalTest(p)) {
				printDetails(p, f, e);
				return;
			}
			e.add(p);
			System.out.println(p.print());
			for (Action a : problem.actions(p)) {
				State child = problem.result(p, a);
				child.parentAction = a;
				child.parentState = p;
				boolean eContainsChild = false;
				for (State s : e) {
					if (problem.equal(s, child))
						eContainsChild = true;
				}
				if (!eContainsChild) {

					boolean fContainsChild = false;
					for (State s : f) {
						if (problem.equal(s, child))
							fContainsChild = true;
					}
					if (!fContainsChild) {
						f.add(child);
					}
				}
			}
		}
	}
	
//////////////////////////////////////////////////////////////////////////////////////////////////////////

	private void printDetails(State p, ArrayList<State> f, ArrayList<State> e) {
		int numOfObservedNodes = f.size() + e.size() + 1;
		int numOfExtendedNodes = e.size();
		int maxMemory = f.size() + e.size();
		System.out.println("numOfObservedNodes: " + numOfObservedNodes);
		System.out.println("numOfExtendedNodes: " + numOfExtendedNodes);
		System.out.println("max memory: " + maxMemory);
		State q = p;
		ArrayList<State> pathStates = new ArrayList<State>();
		pathStates.add(q);
		ArrayList<Action> pathActions = new ArrayList<Action>();
		while (q.parentState != null) {
			pathStates.add(q.parentState);
			pathActions.add(q.parentAction);
			q = q.parentState;
		}
		System.out.print("pathStates : ");
		for (int i = pathStates.size() - 1; i >= 0; i--) {
			System.out.print(pathStates.get(i).print() + "  ");
		}
		System.out.println();
		System.out.print("pathActions : ");
		for (int i = pathActions.size() - 1; i >= 0; i--) {
			System.out.print(pathActions.get(i).print() + " ");
		}
		System.out.println();
		System.out.println("pathCost : " + pathActions.size());
	}
}