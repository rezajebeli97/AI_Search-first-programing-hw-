import java.util.ArrayList;

public class Search {

	public void bfs(Problem problem) {
		ArrayList<State> f = new ArrayList<State>();
		ArrayList<State> e = new ArrayList<State>();
		State initState = problem.initialState();
		if (problem.goalTest(initState)) {
			printDetails(problem, initState, f, e, 0, 0);
		}

		f.add(initState);

		while (!f.isEmpty()) {
			State p = f.remove(0);
			e.add(p);
			System.out.println(p.print() + " : removed from f array and added to e array(expand p)") ;
			for (Action action : problem.actions(p)) {
				State child = problem.result(p, action);
				child.parentAction = action;
				child.parentState = p;
				child.setG(p.getG() + action.cost);
				boolean eContainsChild = false;
				for (State s : e) {
					if (problem.equal(s, child))
						eContainsChild = true;
				}
				if (!eContainsChild) {
					System.out.println("check goal test for state : " + child.print());
					if (problem.goalTest(child)) {
						System.out.println("found final node!");
						printDetails(problem, child, f, e, 0, 0);
						return;
					}
					boolean fContainsChild = false;
					for (State s : f) {
						if (problem.equal(s, child))
							fContainsChild = true;
					}
					if (!fContainsChild) {
						f.add(child);
						System.out.println(child.print() + " : added to f array") ;
					}
				}
			}
		}
		int[] x = printDetails_noAnswer(problem, initState, f, e);
		System.out.println("numOfObservedNodes: " + x[0]);
		System.out.println("numOfExtendedNodes: " + x[1]);
		System.out.println("max memory: " + x[2]);
	}

	//////////////////////////////////////////////////////////////////////////////////////////////////////////

	public void dfs_tree(Problem problem) {
		ArrayList<State> f = new ArrayList<State>();
		State initState = problem.initialState();
		if (problem.goalTest(initState)) {
			printDetails(problem, initState, f, new ArrayList<>(), 0, 0);
		}

		f.add(initState);

		while (!f.isEmpty()) {
			State p = f.remove(f.size() - 1);
			for (Action action : problem.actions(p)) {
				State child = problem.result(p, action);
				child.parentAction = action;
				child.parentState = p;
				child.setG(p.getG() + action.cost);

				if (problem.goalTest(child)) {
					printDetails(problem, child, f, new ArrayList<>(), 0, 0);
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
		int[] x = printDetails_noAnswer(problem, initState, f, new ArrayList<>());
		System.out.println("numOfObservedNodes: " + x[0]);
		System.out.println("numOfExtendedNodes: " + x[1]);
		System.out.println("max memory: " + x[2]);
	}

	//////////////////////////////////////////////////////////////////////////////////////////////////////////

	public void dfs_limitedDepth_tree(Problem problem, int maxDepth) {
		int[] x = dfs_limitedDepth_tree_(problem, maxDepth, 0);
		System.out.println("numOfObservedNodes: " + x[0]);
		System.out.println("numOfExtendedNodes: " + x[1]);
		System.out.println("max memory: " + x[2]);
	}

	//////////////////////////////////////////////////////////////////////////////////////////////////////////

	public int[] dfs_limitedDepth_tree_(Problem problem, int maxDepth, int numOfObservedNodes_past) { // ba farze anke
																										// avalin state
																										// depth = 0
		ArrayList<State> f = new ArrayList<State>();
		State initState = problem.initialState();
		if (problem.goalTest(initState)) {
			printDetails_dfs_tree(problem, initState, 0, numOfObservedNodes_past);
		}

		initState.setG(0);
		initState.setDepth(0);
		f.add(initState);

		int maxMemory = 1;
		while (!f.isEmpty()) {
			State p = f.remove(f.size() - 1);
			for (Action action : problem.actions(p)) {
				State child = problem.result(p, action);
				child.parentAction = action;
				child.parentState = p;
				child.setG(p.getG() + action.cost);
				child.setDepth(p.getDepth() + 1);
				if (problem.goalTest(child)) {
					printDetails_dfs_tree(problem, child, maxMemory, numOfObservedNodes_past);
				}
				boolean fContainsChild = false;
				for (State s : f) {
					if (problem.equal(s, child))
						fContainsChild = true;
				}
				if (!fContainsChild && (child.getDepth() < maxDepth)) {
					f.add(child);
				}
				maxMemory = Math.max(maxMemory, f.size());

			}
		}
		int[] x = new int[3];
		x[0] = maxMemory + 1;
		x[2] = maxMemory;
		return x;
	}

	//////////////////////////////////////////////////////////////////////////////////////////////////////////

	public void dfs_depthIncremental_tree(Problem problem, int maxDepth) {
		int numOfObservedNodes = 0;
		for (int i = 0; i < maxDepth; i++) {
			int[] x = dfs_limitedDepth_tree_(problem, i, numOfObservedNodes);
			numOfObservedNodes += x[0];
		}
	}

	//////////////////////////////////////////////////////////////////////////////////////////////////////////

	public void dfs_graph(Problem problem) {
		ArrayList<State> f = new ArrayList<State>();
		ArrayList<State> e = new ArrayList<State>();
		State initState = problem.initialState();
		if (problem.goalTest(initState)) {
			printDetails(problem, initState, f, e, 0, 0);
		}

		f.add(initState);

		while (!f.isEmpty()) {
			State p = f.remove(f.size() - 1);
			e.add(p);
			for (Action action : problem.actions(p)) {
				State child = problem.result(p, action);
				child.parentAction = action;
				child.parentState = p;
				child.setG(p.getG() + action.cost);
				boolean eContainsChild = false;
				for (State s : e) {
					if (problem.equal(s, child))
						eContainsChild = true;
				}
				if (!eContainsChild) {
					if (problem.goalTest(child)) {
						printDetails(problem, child, f, e, 0, 0);
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
		int[] x = printDetails_noAnswer(problem, initState, f, e);
		System.out.println("numOfObservedNodes: " + x[0]);
		System.out.println("numOfExtendedNodes: " + x[1]);
		System.out.println("max memory: " + x[2]);
	}

	//////////////////////////////////////////////////////////////////////////////////////////////////////////

	public void dfs_limitedDepth_graph(Problem problem, int maxDepth) {
		int[] x = dfs_limitedDepth_graph_(problem, maxDepth, 0, 0);
		System.out.println("numOfObservedNodes: " + x[0]);
		System.out.println("numOfExtendedNodes: " + x[1]);
		System.out.println("max memory: " + x[2]);
	}

	//////////////////////////////////////////////////////////////////////////////////////////////////////////

	public int[] dfs_limitedDepth_graph_(Problem problem, int maxDepth, int numOfObservedNodes_past,
			int numOfExtendedNodes_past) { // ba farze anke avalin state depth = 0
		ArrayList<State> f = new ArrayList<State>();
		ArrayList<State> e = new ArrayList<State>();
		State initState = problem.initialState();
		if (problem.goalTest(initState)) {
			printDetails(problem, initState, f, e, numOfObservedNodes_past, numOfExtendedNodes_past);
		}

		initState.setG(0);
		initState.setDepth(0);
		f.add(initState);

		while (!f.isEmpty()) {
			State p = f.remove(f.size() - 1);
			e.add(p);
			for (Action action : problem.actions(p)) {
				State child = problem.result(p, action);
				child.parentAction = action;
				child.parentState = p;
				child.setG(p.getG() + action.cost);
				child.setDepth(p.getDepth() + 1);
				boolean eContainsChild = false;
				for (State s : e) {
					if (problem.equal(s, child))
						eContainsChild = true;
				}
				if (!eContainsChild) {
					if (problem.goalTest(child)) {
						printDetails(problem, child, f, e, numOfObservedNodes_past, numOfExtendedNodes_past);
					}
					boolean fContainsChild = false;
					for (State s : f) {
						if (problem.equal(s, child))
							fContainsChild = true;
					}
					if (!fContainsChild && (child.getDepth() < maxDepth)) {
						f.add(child);
					}
				}
			}
		}
		int[] x = printDetails_noAnswer(problem, initState, f, e);
		return x;
	}

	//////////////////////////////////////////////////////////////////////////////////////////////////////////

	public void dfs_depthIncremental_graph(Problem problem, int maxDepth) {
		int numOfObservedNodes = 0, numOfExtendedNodes = 0;
		for (int i = 0; i < maxDepth; i++) {
			int[] x = dfs_limitedDepth_graph_(problem, i, numOfObservedNodes, numOfExtendedNodes);
			numOfObservedNodes += x[0];
			numOfExtendedNodes += x[1];
		}
	}

	//////////////////////////////////////////////////////////////////////////////////////////////////////////

	public void uniformCost(Problem problem) {
		ArrayList<State> f = new ArrayList<State>();
		ArrayList<State> e = new ArrayList<State>();
		State initState = problem.initialState();

		initState.setG(0);
		f.add(initState);

		while (!f.isEmpty()) {
			int index = 0;
			double minCost = 100000000;
			for (int i = 0; i < f.size(); i++) { // choose minimal cost state to expand
				if (minCost > f.get(i).getG()) {
					minCost = f.get(i).getG();
					index = i;
				}
			}
			State p = f.remove(index);
			if (problem.goalTest(p)) {
				printDetails(problem, p, f, e, 0, 0);
				return;
			}
			e.add(p);

			for (Action action : problem.actions(p)) {
				State child = problem.result(p, action);
				child.parentAction = action;
				child.parentState = p;
				child.setG(p.getG() + action.cost);
				boolean eContainsChild = false;
				for (State s : e) {
					if (problem.equal(s, child))
						eContainsChild = true;
				}
				if (!eContainsChild) {

					boolean fContainsChild = false;
					for (State s : f) {
						if (problem.equal(s, child)) {
							fContainsChild = true;
							s.setG(Math.min(child.getG(), s.getG()));
						}
					}
					if (!fContainsChild) {
						f.add(child);
					}
				}
			}
		}
	}

	//////////////////////////////////////////////////////////////////////////////////////////////////////////

	public void A_Star(Problem problem) {
		ArrayList<State> f = new ArrayList<State>();
		ArrayList<State> e = new ArrayList<State>();
		State initState = problem.initialState();

		initState.setG(0);
		f.add(initState);

		while (!f.isEmpty()) {
			int index = 0;
			double minCost = 100000000;
			for (int i = 0; i < f.size(); i++) { // choose minimal cost state to expand
				if (minCost > f.get(i).getG() + problem.h(f.get(i))) {
					minCost = f.get(i).getG() + problem.h(f.get(i));
					index = i;
				}
			}
			State p = f.remove(index);
			if (problem.goalTest(p)) {
				printDetails(problem, p, f, e, 0, 0);
				return;
			}
			e.add(p);

			for (Action action : problem.actions(p)) {
				State child = problem.result(p, action);
				child.parentAction = action;
				child.parentState = p;
				child.setG(p.getG() + action.cost);
				boolean eContainsChild = false;
				for (State s : e) {
					if (problem.equal(s, child))
						eContainsChild = true;
				}
				if (!eContainsChild) {

					boolean fContainsChild = false;
					for (State s : f) {
						if (problem.equal(s, child)) {
							fContainsChild = true;
							s.setG(Math.min(child.getG(), s.getG()));
						}
					}
					if (!fContainsChild) {
						f.add(child);
					}
				}
			}
		}
	}
	
	//////////////////////////////////////////////////////////////////////////////////////////////////////////

	public void bidirectional(Problem problem) {
		ArrayList<State> f1 = new ArrayList<State>();
		ArrayList<State> e1 = new ArrayList<State>();
		State initState1 = problem.initialState();
		if (problem.goalTest(initState1)) {
			printDetails(problem, initState1, f1, e1, 0, 0);
		}

		f1.add(initState1);

		ArrayList<State> f2 = new ArrayList<State>();
		ArrayList<State> e2 = new ArrayList<State>();
		State initState2 = problem.goalState();
		f2.add(initState2);

		while (!f1.isEmpty() && !f2.isEmpty()) {
			if (!f1.isEmpty()) {

				State p = f1.remove(0);
				e1.add(p);
				for (Action action : problem.actions(p)) {
					State child = problem.result(p, action);
					child.parentAction = action;
					child.parentState = p;
					child.setG(p.getG() + action.cost);
					boolean eContainsChild = false;
					for (State s : e1) {
						if (problem.equal(s, child))
							eContainsChild = true;
					}
					if (!eContainsChild) {
						State commonStateInF2 = goalTestBidirectional(problem, child, f2);
						if (commonStateInF2 != null) {
							printDetails(problem, child, commonStateInF2, f1, e1, f2, e2);
							return;
						}
						
						boolean fContainsChild = false;
						for (State s : f1) {
							if (problem.equal(s, child))
								fContainsChild = true;
						}
						if (!fContainsChild) {
							f1.add(child);
						}
					}
				}
			}
			if (!f2.isEmpty()) {

				State p = f2.remove(0);
				e2.add(p);
				for (Action action : problem.actions(p)) {
					State child = problem.result(p, action);
					child.parentAction = action;
					child.parentState = p;
					child.setG(p.getG() + action.cost);
					boolean eContainsChild = false;
					for (State s : e2) {
						if (problem.equal(s, child))
							eContainsChild = true;
					}
					if (!eContainsChild) {
						State commonStateInF1 = goalTestBidirectional(problem, child, f1);
						if (commonStateInF1 != null) {
							printDetails(problem, commonStateInF1 , child ,  f1, e1, f2, e2);
							return;
						}
						boolean fContainsChild = false;
						for (State s : f2) {
							if (problem.equal(s, child))
								fContainsChild = true;
						}
						if (!fContainsChild) {
							f2.add(child);
						}
					}
				}
			}

		}
		// int[] x = printDetails_noAnswer(problem, initState, f1, e1);
		// System.out.println("numOfObservedNodes: " + x[0]);
		// System.out.println("numOfExtendedNodes: " + x[1]);
		// System.out.println("max memory: " + x[2]);
	}

	//////////////////////////////////////////////////////////////////////////////////////////////////////////

	private void printDetails(Problem problem, State p1, State p2, ArrayList<State> f1, ArrayList<State> e1,
			ArrayList<State> f2, ArrayList<State> e2) {

		int numOfObservedNodes = f1.size() + e1.size() + f2.size() + e2.size() + 1;
		int numOfExtendedNodes = e1.size() + e2.size();
		int maxMemory = f1.size() + e1.size() + f2.size() + e2.size();
		System.out.println("numOfObservedNodes: " + numOfObservedNodes);
		System.out.println("numOfExtendedNodes: " + numOfExtendedNodes);
		System.out.println("max memory: " + maxMemory);
		State q1 = p1;
		ArrayList<State> pathStates1 = new ArrayList<State>();
		pathStates1.add(q1);
		ArrayList<State> pathStates2 = new ArrayList<State>();
		
		ArrayList<Action> pathActions1 = new ArrayList<Action>();
		ArrayList<Action> pathActions2 = new ArrayList<Action>();
		
		while (q1.parentState != null) {
			pathStates1.add(q1.parentState);
			pathActions1.add(q1.parentAction);
			q1 = q1.parentState;
		}
		State q2 = p2;
		while (q2.parentState != null) {
			pathStates2.add(q2.parentState);
			pathActions2.add(problem.reverseAction(q2.parentAction));
			q2 = q2.parentState;
		}
		System.out.print("pathStates : ");
		for (int i = pathStates1.size() - 1; i >= 0; i--) {
			System.out.print(pathStates1.get(i).print() + "  ");
		}
		for (int i = 0 ; i < pathStates2.size() ; i++) {
			System.out.print(pathStates2.get(i).print() + "  ");
		}
		System.out.println();
		
		System.out.print("pathActions : ");
		for (int i = pathActions1.size() - 1; i >= 0; i--) {
			System.out.print(pathActions1.get(i).print() + " ");
		}
		for (int i = 0 ; i < pathActions2.size() ; i++) {
			System.out.print(pathActions2.get(i).print() + " ");
		}
		System.out.println();
		
		int cost = pathActions1.size() + pathActions2.size();
		System.out.println("pathCost : " + cost);
		System.exit(0);
	}

	//////////////////////////////////////////////////////////////////////////////////////////////////////////

	private State goalTestBidirectional(Problem problem, State p, ArrayList<State> f2) {
		for (State s : f2) {
			if (problem.equal(s, p)) {
				return s;
			}
		}
		return null;
	}

	//////////////////////////////////////////////////////////////////////////////////////////////////////////

	private void printDetails(Problem problem, State p, ArrayList<State> f, ArrayList<State> e,
			int numOfObservedNodes_past, int numOfExtendedNodes_past) {
		int numOfObservedNodes = f.size() + e.size() + 1 + numOfObservedNodes_past;
		int numOfExtendedNodes = e.size() + numOfExtendedNodes_past;
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
		System.out.println("pathCost : " + p.getG() + problem.h(p));
		System.exit(0);
	}

	//////////////////////////////////////////////////////////////////////////////////////////////////////////

	private void printDetails_dfs_tree(Problem problem, State p, int maxMemory, int numOfObservedNodes_past) {

		int numOfObservedNodes = maxMemory + 1 + numOfObservedNodes_past;
		System.out.println("numOfObservedNodes: " + numOfObservedNodes);
		System.out.println("numOfExtendedNodes: " + 0);
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
		System.out.println("pathCost : " + p.getG() + problem.h(p));
		System.exit(0);
	}

	//////////////////////////////////////////////////////////////////////////////////////////////////////////

	private int[] printDetails_noAnswer(Problem problem, State p, ArrayList<State> f, ArrayList<State> e) {
		int[] output = new int[3];
		int numOfObservedNodes = f.size() + e.size() + 1;
		int numOfExtendedNodes = e.size();
		int maxMemory = f.size() + e.size();
		output[0] = numOfObservedNodes;
		output[1] = numOfExtendedNodes;
		output[2] = maxMemory;
		return output;
	}
}