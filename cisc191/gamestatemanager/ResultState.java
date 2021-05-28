// Christopher Diep
package edu.sdccd.cisc191.gamestatemanager;

import edu.sdccd.cisc191.Equipment.Item;
import edu.sdccd.cisc191.Location;
import edu.sdccd.cisc191.MenuPanel;
import edu.sdccd.cisc191.WordButton;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

//Shows the player what they obtain from winning a fight
public class ResultState implements StateInterface{
    private Location map;
    private StateManager sm;
    private StateInterface pausedState;
    private MenuPanel menu;
    private Condition selection;
    private ArrayList<Item> rewards;

    public ResultState(Location am, StateInterface ps, ArrayList<Item> rewards){
        menu = new MenuPanel();
        pausedState = ps;
        map = am;
        this.rewards = rewards;
    }

    @Override
    public void enter(StateManager man) {
        sm = man;
        sm.getWindow().getContentPane().removeAll();


        String message = "YOU GAIN:";
        String itemMessage;
        this.menu.add(message);
        for(Item item: rewards) { //Shows each individual rewards
            itemMessage = item.getItemName();
            this.menu.add(itemMessage);
        }
        WordButton aReturn = new WordButton("RETURN");
        aReturn.addActionListener(new ActionListener() {//returns to game
            @Override
            public void actionPerformed(ActionEvent e) {
                sm.getWindow().removeKeyListener(map);
                map.battlePause();
                map.togglePause();
                selection = Condition.EXIT;
                exit();
            }
        });


        //adds buttons to screen
        this.menu.add(aReturn);

        sm.getWindow().add(this.menu);

        sm.getWindow().getContentPane().revalidate();
        sm.getWindow().getContentPane().repaint();

    }

    @Override
    public void exit() {
        sm.setCurrentState(nextState(selection));
    }

    @Override
    public StateInterface nextState(Condition c) {
        return switch (c) {//player can only resume game
            case EXIT -> pausedState;
            default -> new NullState();
        };
    }
    @Override
    public void setSelection(Condition c){
        this.selection = c;

    }
}
