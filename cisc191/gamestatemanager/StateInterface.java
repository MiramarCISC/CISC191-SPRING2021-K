// Christopher Diep
package edu.sdccd.cisc191.gamestatemanager;

public interface StateInterface { //Used as a basis for all interfaces and a general guide
    public void enter(StateManager man);
    public void exit();
    public StateInterface nextState(Condition c);
    public void setSelection(Condition c);
}
