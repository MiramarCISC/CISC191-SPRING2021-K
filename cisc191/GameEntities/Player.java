// Christopher Diep
package edu.sdccd.cisc191.GameEntities;

import edu.sdccd.cisc191.CombatEncounter.CombatAction;
import edu.sdccd.cisc191.Equipment.*;

import java.util.Hashtable;


public class Player extends Combatant { //Basis for the player, planned to have multiple classes
    private Weapon weapon; //What weapon the player has
    private Armor armor; //What armor they have equipped
    private Hashtable<String, Integer> inventory; //Consumable items such as potions
    private CombatAction combatAction; //What actions they take in combat
    private boolean escape; //To escape during combat

    public Player() {
        super();
        inventory = new Hashtable<>();
        combatAction = null;
        equipWeapon(new Sword());
        equipArmor(new IronArmor());
        escape = false;
        createPotionList();
        addToInventory(new PotionOfHealing());
    }

    public Player(String name) {
        super(name);
        inventory = new Hashtable<>();
        combatAction = null;
        equipWeapon(new Sword());
        equipArmor(new IronArmor());
        escape = false;
        createPotionList();
        addToInventory(new PotionOfHealing());
    }

    public int attack() {
        int min, max;
        if (weapon == null) {
            return super.attack(getAttack() + 5);
        } else {
            min = getAttack() + weapon.getDamageBonus(); //Character's attack with the very lowest the damage can do.
            max = getAttack() + weapon.getDamageBonus() + weapon.getDamageRange();  //Character's attack with the maximum the damage can do
            return (int) ((Math.random() * ((max) - min)) + min);
        }
    }

    private void createPotionList() {
        inventory.put(new PotionOfHealing().getItemName(), 0);
        inventory.put(new PotionOfGreaterHealing().getItemName(), 0);
     }

    public void equipWeapon(Weapon weapon) {
        this.weapon = weapon;
    }

    public void equipArmor(Armor armor) {
        this.armor = armor;
    }

    @Override
    public int getDefense() {
        return super.getDefense() + armor.getDefenseBonus();
    }

    public void playerTurn(CombatAction selAction, Monster enemy){
        if(getHealth() > 0){
            setCombatAction(selAction);
        }
        else{
        }
        performAction(enemy);
    }

    public void performAction(Monster enemy){
        combatAction.execute(this, enemy);
    }

    public void setCombatAction(CombatAction selAction){
        combatAction = selAction;
    }

    public CombatAction getCombatAction() {
        return combatAction;
    }


    public void addToInventory(Consumables addition) { //Check to see that item is in inventory and add one to amount val
        if(inventory.containsKey(addition.getItemName())) {
            int amount = inventory.get(addition.getItemName()) + 1;
            inventory.put(addition.getItemName(), amount);
        }
        else
            System.out.println("Failure");
    }

    public void useItem(Consumables usedItem) {
        if(inventory.containsKey(usedItem.getItemName()) && inventory.get(usedItem.getItemName()) > 0) { //Makes sure you at least have 1 of the time to use it
            int amount = inventory.get(usedItem.getItemName()) - 1;
            usedItem.effect(this);
            inventory.put(usedItem.getItemName(), amount);
        }
        else
            System.out.println("Failure");
    }

    public int getItemAmount(Consumables item) {
        if(inventory.containsKey(item.getItemName())) {
            return inventory.get(item.getItemName());
        }
        return -1;
    }


    public boolean isEscape() {
        return escape;
    }

    public void setEscape(boolean escape) {
        this.escape = escape;
    }

    public Weapon getWeapon() {
        return weapon;
    }

    public Armor getArmor() {
        return armor;
    }
}
