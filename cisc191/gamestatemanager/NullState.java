// Christopher Diep
package edu.sdccd.cisc191.gamestatemanager;


//null state for pause menu and battle menu
public class NullState implements StateInterface {
    private StateManager sm;
    private StateInterface nextState;

    @Override
    public void enter(StateManager man) {
        this.nextState = this;
    }

    @Override
    public void exit() {

    }

    @Override
    public StateInterface nextState(Condition c) {
        return null;
    }

    @Override
    public void setSelection(Condition c){
    }
}
