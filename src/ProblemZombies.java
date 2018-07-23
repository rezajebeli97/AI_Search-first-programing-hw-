import java.util.ArrayList;

public class ProblemZombies implements Problem{

	@Override
	public StateZombies initialState() {
		return new StateZombies(3 , 3 , 0 , 0 , true);
	}

	@Override
	public StateZombies goalState() {
		return new StateZombies(0 , 0 , 3 , 3 , false);
	}

	@Override
	public boolean goalTest(State s_inter) {
		StateZombies s = (StateZombies)s_inter; 
		if (s.leftZombies == 0 && s.leftHuman == 0 && s.rightZombies == 3 && s.rightHuman == 3 && s.boatIsLeft == false) {
			return true;
		}
		return false;
	}

	@Override
	public StateZombies result(State state_inter, Action action_inter) {
		StateZombies state = (StateZombies)state_inter;
		ActionZombies action = (ActionZombies)action_inter;
		
		int leftZombies , leftHuman , rightZombies , rightHuman; 
		if (state.boatIsLeft) {
			rightZombies = state.rightZombies + action.zombieTransfer;
			rightHuman = state.rightHuman + action.humanTransfer;
			leftZombies = state.leftZombies - action.zombieTransfer;
			leftHuman = state.leftHuman - action.humanTransfer;
		}
		else {
			rightZombies = state.rightZombies - action.zombieTransfer;
			rightHuman = state.rightHuman - action.humanTransfer;
			leftZombies = state.leftZombies + action.zombieTransfer;
			leftHuman = state.leftHuman + action.humanTransfer;
		}
		return new StateZombies(leftZombies, leftHuman, rightZombies, rightHuman, !state.boatIsLeft);
	}

	@Override
	public ArrayList<Action> actions(State state_inter) {
		StateZombies state = (StateZombies)state_inter;
		ArrayList<Action> actions = new ArrayList<Action>();
		
		if (state.boatIsLeft) {
			for (int i = 0; i <= Math.min ( state.leftZombies , 2 ) ; i++) {
				for (int j = 0; j <= Math.min (state.leftHuman , 2 ); j++) {
					if ((i + j) > 0  && (i+j) <=2) {
						ActionZombies action = new ActionZombies(i , j);
						StateZombies nxtState = this.result(state, action);
						if ((nxtState.leftZombies <= nxtState.leftHuman || nxtState.leftHuman == 0)  &&  (nxtState.rightZombies <= nxtState.rightHuman || nxtState.rightHuman == 0) ) {
							actions.add(action);
						}
					}
					
				}
			}
		}
		else {
			for (int i = 0; i <= Math.min(state.rightZombies , 2 ); i++) {
				for (int j = 0; j <= Math.min(state.rightHuman , 2); j++) {
					if ((i + j) > 0  && (i+j) <=2) {
						ActionZombies action = new ActionZombies(i , j);
						StateZombies nxtState = this.result(state, action);
						if ((nxtState.leftZombies <= nxtState.leftHuman || nxtState.leftHuman == 0)  &&  (nxtState.rightZombies <= nxtState.rightHuman || nxtState.rightHuman == 0) ) {
							actions.add(action);
						}
					}
					
				}
			}
		}
		return actions;
	}

	@Override
	public boolean equal(State s1_inter, State s2_inter) {
		StateZombies s1 = (StateZombies)s1_inter;
		StateZombies s2 = (StateZombies)s2_inter;
		if (s1.leftZombies == s2.leftZombies && s1.leftHuman == s2.leftHuman && s1.rightZombies == s2.rightZombies && s1.rightHuman == s2.rightHuman && s1.boatIsLeft == s2.boatIsLeft) {
			return true;
		}
		return false;
	}

	@Override
	public double h(State state) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public ActionZombies reverseAction(Action action_inter) {
		ActionZombies action = (ActionZombies)action_inter;
		return action;
	}
	
}
