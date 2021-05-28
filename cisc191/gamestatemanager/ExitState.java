// Christopher Diep
package edu.sdccd.cisc191.gamestatemanager;


//this state is just for closing the game so it only needs to dispose of the window
public class ExitState implements StateInterface {
    private StateManager sm;


    @Override
    public void enter(StateManager man) {
        man.getWindow().dispose();
    }

    @Override
    public void exit() {//the game is closed already so nothing goes here

    }

    @Override
    public StateInterface nextState(Condition c) {
        return null;
    }

    @Override
    public void setSelection(Condition c){

    }
}
