// Christopher Diep
package edu.sdccd.cisc191.gamestatemanager;

import edu.sdccd.cisc191.MenuPanel;
import edu.sdccd.cisc191.WordButton;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainMenuState implements StateInterface{ //Main menu for the game

    private MenuPanel menu;
    private Condition selected;
    private StateManager sm;
    public MainMenuState(){
        menu = new MenuPanel();
    }
    @Override
    public void enter(StateManager man) {
        sm = man;
        sm.getWindow().getContentPane().removeAll();

        String message = "Welcome To";
        String message1 = "    A RPG Game    ";
        WordButton start = new WordButton("PLAY"); //Starting the game
        WordButton help = new WordButton("INSTRUCTIONS"); //tutorial
        WordButton exit = new WordButton("EXIT"); //exit

        //now we add action listeners to the WordButtons that operate their respective tasks
        start.addActionListener(new ActionListener() { //switch to game screen
            @Override
            public void actionPerformed(ActionEvent e) {
                selected = Condition.PLAY;
                exit();
            }
        });
        help.addActionListener(new ActionListener() {// switch to tutorial menu
            @Override
            public void actionPerformed(ActionEvent e) {
                selected = Condition.INSTRUCTIONS;
                exit();
            }
        });
        //close the game
        exit.addActionListener(e -> {
            selected = Condition.EXIT;
            exit();
        });

        //add each item
        this.menu.add(message);
        this.menu.add(message1);

        this.menu.add(start);
        this.menu.add(help);
        this.menu.add(exit);

        man.getWindow().add(menu);

        man.getWindow().getContentPane().revalidate();
        man.getWindow().getContentPane().repaint();
    }

    @Override
    public void exit() {
        sm.setCurrentState(nextState(selected));
        menu = null;
    }

    @Override
    public StateInterface nextState(Condition c){
        return switch (c) {
            case PLAY -> new GameState();
            case INSTRUCTIONS -> new InstructionsState();
            case EXIT -> new ExitState();
            default -> new NullState();
        };
    }

    @Override
    public void setSelection(Condition c){

    }

}
