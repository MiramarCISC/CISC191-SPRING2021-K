// Christopher Diep
package edu.sdccd.cisc191.gamestatemanager;

import edu.sdccd.cisc191.MenuPanel;
import edu.sdccd.cisc191.WordButton;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EndGameState implements StateInterface{ //Game victory screen
    private StateManager sm;
    private MenuPanel menu;
    private Condition selection;

    public EndGameState(){
        menu = new MenuPanel();
    }

    @Override
    public void enter(StateManager man) {
        sm = man;
        sm.getWindow().getContentPane().removeAll();


        String message = "YOU BEAT THE GAME";
        WordButton retry = new WordButton("PLAY AGAIN?");
        WordButton menu = new WordButton("EXIT TO MAIN MENU");

        retry.addActionListener(new ActionListener() {//replays game
            @Override
            public void actionPerformed(ActionEvent e) {
                selection = Condition.PLAY;
                exit();
            }
        });
        menu.addActionListener(new ActionListener() {//returns to main menu
            @Override
            public void actionPerformed(ActionEvent e) {
                selection = Condition.MENU;
                exit();
            }
        });

        //adds buttons to screen
        this.menu.add(message);
        this.menu.add(retry);
        this.menu.add(menu);

        sm.getWindow().add(this.menu);

        sm.getWindow().getContentPane().revalidate();
        sm.getWindow().getContentPane().repaint();

    }

    @Override
    public void exit() {

        sm.setCurrentState(nextState(selection));
        menu = null;

    }

    @Override
    public StateInterface nextState(Condition c) {
        return switch (c) {//player can either replay the game or return to the main menu
            case PLAY -> new GameState();
            case MENU -> new MainMenuState();
            default -> new NullState();
        };
    }
    @Override
    public void setSelection(Condition c){
        this.selection = c;

    }
}
