// Christopher Diep
package edu.sdccd.cisc191.gamestatemanager;

import edu.sdccd.cisc191.Equipment.PotionOfGreaterHealing;
import edu.sdccd.cisc191.Equipment.PotionOfHealing;
import edu.sdccd.cisc191.GameEntities.Player;
import edu.sdccd.cisc191.Location;
import edu.sdccd.cisc191.MenuPanel;
import edu.sdccd.cisc191.WordButton;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ItemState implements StateInterface { //Opens the item menu and allows you to use items
    private Location map;
    private StateManager sm;
    private StateInterface pausedState;
    private MenuPanel menu;
    private Condition selection;
    private Player player;

    public ItemState(Location am, StateInterface ps, Player player){
        menu = new MenuPanel();
        pausedState = ps;
        map = am;
        this.player = player;
    }

    @Override
    public void enter(StateManager man) {
        sm = man;
        sm.getWindow().getContentPane().removeAll();


        String message = "ITEM MENU";
        WordButton potion = new WordButton("Potion x" + player.getItemAmount(new PotionOfHealing()));
        WordButton greaterPotion = new WordButton("Greater Potion x" + player.getItemAmount(new PotionOfGreaterHealing()));
        WordButton aReturn = new WordButton("RETURN");
        potion.addActionListener(new ActionListener() {//uses normal potion
            @Override
            public void actionPerformed(ActionEvent e) {
                player.useItem(new PotionOfHealing());
                sm.getWindow().removeKeyListener(map);
                map.togglePause();
                selection = Condition.EXIT;
                exit();
            }
        });
        greaterPotion.addActionListener(new ActionListener() {//uses greater potion
            @Override
            public void actionPerformed(ActionEvent e) {
                player.useItem(new PotionOfGreaterHealing());
                sm.getWindow().removeKeyListener(map);
                map.togglePause();
                selection = Condition.EXIT;
                exit();
            }
        });
        aReturn.addActionListener(new ActionListener() {//returns to the game
            @Override
            public void actionPerformed(ActionEvent e) {
                sm.getWindow().removeKeyListener(map);
                map.togglePause();
                selection = Condition.EXIT;
                exit();
            }
        });


        //adds buttons to screen
        this.menu.add(message);
        this.menu.add(potion);
        this.menu.add(greaterPotion);
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
        return switch (c) {//Once player does any action on this menu they return to the game
            case EXIT -> pausedState;
            default -> new NullState();
        };
    }
    @Override
    public void setSelection(Condition c){
        this.selection = c;

    }
}
