// Christopher Diep
package edu.sdccd.cisc191.gamestatemanager;

import edu.sdccd.cisc191.GameEntities.Fighter;
import edu.sdccd.cisc191.GameEntities.Player;
import edu.sdccd.cisc191.Location;

public class GameState implements StateInterface{
    private StateManager sm;
    private Location map = new Location();
    private Condition selection;
    private Player player;

    public GameState(){
        player = new Fighter("John");
    }
    @Override
    public void enter(StateManager man) {
            sm = man;
            sm.getWindow().getContentPane().removeAll();

            sm.getWindow().add(map);
            sm.getWindow().addKeyListener(map);
            this.paintMaze();

            map.setState(this);
    }

    //paints the game to the screen
    public void paintMaze(){

        sm.getWindow().getContentPane().removeAll();
        sm.getWindow().add(map);

        sm.getWindow().getContentPane().revalidate();
        sm.getWindow().getContentPane().repaint();
    }
    @Override
    public void exit() {
        sm.setCurrentState(nextState(selection));
    }

    @Override
    public StateInterface nextState(Condition c) {
        switch(c){//Conditions for pausing or entering a battle
            case PAUSE:
                return new PauseState(map, this, player);
            case BATTLE:
                return new BattleState(map, this, player);
            default:
                return new NullState();
        }
    }

    @Override
    public void setSelection(Condition c){
        this.selection = c;
    }
}
