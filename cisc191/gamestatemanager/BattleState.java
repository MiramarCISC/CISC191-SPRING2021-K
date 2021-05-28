// Christopher Diep
package edu.sdccd.cisc191.gamestatemanager;

import edu.sdccd.cisc191.CombatEncounter.AttackAction;
import edu.sdccd.cisc191.CombatEncounter.CombatAction;
import edu.sdccd.cisc191.CombatEncounter.DefenseAction;
import edu.sdccd.cisc191.CombatEncounter.RunAction;
import edu.sdccd.cisc191.Equipment.*;
import edu.sdccd.cisc191.GameEntities.*;
import edu.sdccd.cisc191.Location;
import edu.sdccd.cisc191.MenuPanel;
import edu.sdccd.cisc191.WordButton;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class BattleState implements StateInterface{
    private Location map;
    private StateManager sm;
    private StateInterface battleState;
    private MenuPanel menu;
    private Condition selection;
    private Player player;
    private Monster enemy;
    private boolean boss;
    private ArrayList<Item> rewards; //Rewards to input into the results screen

    public BattleState(Location am, GameState ps, Player player){
        menu = new MenuPanel();
        battleState = ps;
        map = am;
        this.player = player;
        boss = false;
        enemy = encounterRate();
        player.setEscape(false); //Set escape for every encounter
        rewards = new ArrayList<>();

    }

    @Override
    public void enter(StateManager man) {
        sm = man;
        sm.getWindow().getContentPane().removeAll();

        String enemyStats = enemy.getName() + ": " + enemy.getHealth() + "/" + enemy.getMaxHealth();
        String message = "Encounter";
        WordButton attack = new WordButton("Attack");
        WordButton defend = new WordButton("Defend");
        WordButton run = new WordButton("Run");
        String playerStats = player.getName() + ": " + player.getHealth() + "/" + player.getMaxHealth();

        attack.addActionListener(new ActionListener() {//Attack action
            @Override
            public void actionPerformed(ActionEvent e) {
                playerTurn(new AttackAction());
                enemyDies();
                if(selection == null) {
                    enemyTurn();
                    playerLoses();
                }
                menu.getText(0).setText(enemy.getName() + ": " + enemy.getHealth() + "/" + enemy.getMaxHealth());
                menu.getText(2).setText(player.getName() + ": " + player.getHealth() + "/" + player.getMaxHealth());
            }
        });
        defend.addActionListener(new ActionListener() {//Defend action
            @Override
            public void actionPerformed(ActionEvent e) {
                playerTurn(new DefenseAction());
                enemyDies();
                if(selection == null) { //Checks if another conditional has been met
                    enemyTurn();
                    playerLoses();
                }
                menu.getText(0).setText(enemy.getName() + ": " + enemy.getHealth() + "/" + enemy.getMaxHealth());
                menu.getText(2).setText(player.getName() + ": " + player.getHealth() + "/" + player.getMaxHealth());
            }
        });
        run.addActionListener(new ActionListener() {//Run action to try to escape encounter
            @Override
            public void actionPerformed(ActionEvent e) {
                playerTurn(new RunAction());
                enemyDies();
                if(selection == null) {
                    enemyTurn();
                    playerLoses();
                }
                menu.getText(0).setText(enemy.getName() + ": " + enemy.getHealth() + "/" + enemy.getMaxHealth());
                menu.getText(2).setText(player.getName() + ": " + player.getHealth() + "/" + player.getMaxHealth());
            }
        });

        //adds buttons to screen
        this.menu.add(enemyStats);
        this.menu.add(message);
        this.menu.add(attack);
        this.menu.add(defend); //Adds all buttons and strings to the menu
        if(!boss) //Cannot run from boss encounter
            this.menu.add(run);
        this.menu.add(playerStats);

        sm.getWindow().add(this.menu);

        sm.getWindow().getContentPane().revalidate();
        sm.getWindow().getContentPane().repaint();
    }

    @Override
    public void exit() {


        if(selection == Condition.DEAD){//returns to main menu

            //deletes panel and state since they are not needed if we are not resuming the game
            map = null;
            battleState = new NullState();
        }
        else if(selection == Condition.VICTORY){//resumes game and adds items to player inventory
            dropItems();
        }
        else if(selection == Condition.WIN) {
        }
        sm.setCurrentState(nextState(selection));
    }

    @Override
    public StateInterface nextState(Condition c) {
        return switch (c) {//The player can run, which resumes to game, win, which they get rewards, or dies, which initiates end game state
            case VICTORY -> new ResultState(map, battleState, rewards);
            case RUN -> battleState;
            case WIN -> new EndGameState();
            case DEAD -> new DeadState();
            default -> new NullState();
        };
    }
    @Override
    public void setSelection(Condition c){
        this.selection = c;

    }

    private Monster encounterRate() { //Randomly generates monster type
        if(map.checkBoss()) {
            boss = true;
            return new DemonSlime();
        }
        int encountered = (int) ((Math.random() * ((100) - 1)) + 1);
        if(encountered <= 45)
            return new Slime();
        else if(encountered <= 75)
            return new BiggerSlime();
        else
            return new GemSlime();
    }

    public void enemyTurn() { //Does enemy turn
        enemy.enemyTurn(player);
    }

    public void playerLoses() { //Check player losing conditional
        if(player.getHealth() <= 0) {
            selection = Condition.DEAD;
            exit();
        }
    }

    public void enemyDies() { //Checks enemy losing conditional

        if(enemy.getHealth() <= 0) {
            sm.getWindow().removeKeyListener(map);
            if(boss) { //Sets different conditional for the boss victory
                selection = Condition.WIN;
            }
            else
                selection = Condition.VICTORY;
            map.battlePause();
            exit();
        }
    }

    public void playerTurn(CombatAction playerAction) { //Player action choice
        player.playerTurn(playerAction, enemy);
        if(player.isEscape()) { //If the player successfully escapes
            sm.getWindow().removeKeyListener(map);
            selection = Condition.RUN;
            map.battlePause();
            exit();
        }
    }

    public void dropItems() { //Enemy item drops
        int items = (int) ((Math.random() * ((100) - 1)) + 1);
        if(enemy.getName().equals(new Slime().getName())) {
            if(items < 65) { //Rate of drop for healing items
                Consumables drop = new PotionOfHealing();
                player.addToInventory(drop);
                rewards.add(drop);
            }
            else {
                Consumables drop = new PotionOfGreaterHealing();
                player.addToInventory(drop);
                rewards.add(drop);
            }

            if(items > 65) {
                Weapon newWeapon = new SteelSword();
                if(player.getWeapon().getDamageBonus() < newWeapon.getDamageBonus()) {
                    player.equipWeapon(newWeapon);
                    rewards.add(newWeapon);
                }
            }

            if(items > 85) {
                Armor newArmor = new SteelArmor();
                if(player.getArmor().getDefenseBonus() < newArmor.getDefenseBonus()) {
                    player.equipArmor(newArmor);
                    rewards.add(newArmor);
                }
            }
        }
        else if(enemy.getName().equals(new BiggerSlime().getName())) { //BiggerSlime droprate
            if(items < 45) { //Rate of drop for healing items
                Consumables drop = new PotionOfHealing();
                player.addToInventory(drop);
                rewards.add(drop);
            }
            else {
                Consumables drop = new PotionOfGreaterHealing();
                player.addToInventory(drop);
                rewards.add(drop);
            }

            if(items > 65) {
                Weapon newWeapon = new Weapon("Mithril Sword", 15, 10);
                if(player.getWeapon().getDamageBonus() < newWeapon.getDamageBonus()) {
                    player.equipWeapon(newWeapon);
                    rewards.add(newWeapon);
                }
            }

            if(items > 85) {
                Armor newArmor = new Armor("Plate Armor", 15);
                if(player.getArmor().getDefenseBonus() < newArmor.getDefenseBonus()) {
                    player.equipArmor(newArmor);
                    rewards.add(newArmor);
                }
            }
        }
        else if(enemy.getName().equals(new GemSlime().getName())) { //Gemslime drop rate
            Consumables drop = new PotionOfGreaterHealing();
            player.addToInventory(drop);
            rewards.add(drop);

            if(items > 65) {
                Weapon newWeapon = new Excalibur();
                if(player.getWeapon().getDamageBonus() < newWeapon.getDamageBonus()) {
                    player.equipWeapon(newWeapon);
                    rewards.add(newWeapon);
                }
            }

            if(items > 85) {
                Armor newArmor = new Armor("Mithril Armor", 20);
                if(player.getArmor().getDefenseBonus() < newArmor.getDefenseBonus()) {
                    player.equipArmor(newArmor);
                    rewards.add(newArmor);
                }
            }
        }
    }


}
