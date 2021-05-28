// Christopher Diep
package edu.sdccd.cisc191.gamestatemanager;

import edu.sdccd.cisc191.GameEntities.Player;
import edu.sdccd.cisc191.Location;
import edu.sdccd.cisc191.MenuPanel;
import edu.sdccd.cisc191.WordButton;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//Pauses the game and freezes movement behind the screen
public class PauseState implements StateInterface {
    private Location map;
    private StateManager sm;
    private StateInterface pausedState;
    private MenuPanel menu;
    private Condition selection;
    private Player player;

    public PauseState(Location am, GameState ps, Player player){
        menu = new MenuPanel();
        pausedState = ps;
        map = am;
        this.player = player;
    }

    @Override
    public void enter(StateManager man) {
        sm = man;
        sm.getWindow().getContentPane().removeAll();


        String message = "GAME PAUSED";
        WordButton resume = new WordButton("RESUME");
        WordButton itemMenu = new WordButton("ITEMS");
        WordButton menu = new WordButton("MENU");

        resume.addActionListener(new ActionListener() {//resumes game
            @Override
            public void actionPerformed(ActionEvent e) {
                sm.getWindow().removeKeyListener(map);
                map.togglePause();
                selection = Condition.EXIT;
                exit();
            }
        });
        itemMenu.addActionListener(new ActionListener() {//goes to item screen
            @Override
            public void actionPerformed(ActionEvent e) {
                selection = Condition.ITEM;
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
        this.menu.add(resume);
        this.menu.add(itemMenu);
        this.menu.add(menu);

        sm.getWindow().add(this.menu);

        sm.getWindow().getContentPane().revalidate();
        sm.getWindow().getContentPane().repaint();

    }

    @Override
    public void exit() {

        sm.setCurrentState(nextState(selection));

        if(selection == Condition.MENU){//returns to main menu
            //deletes game and state since they are not needed if we are not resuming the game
            map = null;
            pausedState = new NullState();
        }
        else if(selection == Condition.EXIT){//resumes game
        }


    }

    @Override
    public StateInterface nextState(Condition c) {
        return switch (c) {//player can either game maze, open item menu, or return to main menu
            case ITEM -> new ItemState(map, pausedState, player);
            case EXIT -> pausedState;
            case MENU -> new MainMenuState();
            default -> new NullState();
        };
    }
    @Override
    public void setSelection(Condition c){
        this.selection = c;

    }
}
