// Christopher Diep
package edu.sdccd.cisc191.gamestatemanager;

import javax.swing.*;
import java.awt.*;

//Manages and changes the state of all the State classes
public class StateManager{

    private final int PANELSIZE = 1000;
    private StateInterface currentState;
    private JFrame window;

    public StateManager(){
        window = new JFrame("RPG Game");
        currentState = new MainMenuState();
        window.setSize(PANELSIZE, PANELSIZE);
        window.setBackground(Color.LIGHT_GRAY);
        window.setResizable(false);

        currentState.enter(this);

        window.setVisible(true);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    public JFrame getWindow(){
        return this.window;
    }
    public void enterNextState(){
        currentState.enter(this);
    }
    public void setCurrentState(StateInterface s){
        this.currentState = s;
        this.enterNextState();
    }
}
