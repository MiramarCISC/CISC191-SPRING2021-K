// Christopher Diep
package edu.sdccd.cisc191.gamestatemanager;


import edu.sdccd.cisc191.MenuPanel;
import edu.sdccd.cisc191.WordButton;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InstructionsState implements StateInterface { //Shows the instructions for the game
    private StateManager sm;
    private Condition selected;
    private MenuPanel menu;

    public InstructionsState(){
        menu = new MenuPanel();
    }

    @Override
    public void enter(StateManager man) {
        sm = man;
        sm.getWindow().getContentPane().removeAll();
//        this.clearMenu();

        //how to play the game
        String message = "How To Play: ";
        String up = "Press \"w\" or Up to move up";
        String down = "Press \"s\" or Down to move down";
        String left = "Press \"a\" or Left to move left";
        String right = "Press \"d\" or Right to move right";
        String pause = "Press Escape to pause the game";

        WordButton back = new WordButton("Return");

        back.addActionListener(new ActionListener() {//returns to main menu
            @Override
            public void actionPerformed(ActionEvent e) { //reload the main menu
                selected = Condition.MENU;
                exit();
            }
        });

        //adds the text
        this.menu.add(message);
        this.menu.add(up);
        this.menu.add(down);
        this.menu.add(left);
        this.menu.add(right);
        this.menu.add(pause);
        this.menu.add(back);

        sm.getWindow().add(menu);

        sm.getWindow().getContentPane().revalidate();
        sm.getWindow().getContentPane().repaint();

    }

    @Override
    public void exit() {
        sm.setCurrentState(nextState(selected));
        menu = null;
    }

    @Override
    public StateInterface nextState(Condition c) {//the player can only press the return button on this state
        switch (c){
            case MENU:
                return new MainMenuState();
            default:
                return new NullState();
        }
    }

    @Override
    public void setSelection(Condition c){
    }
}
