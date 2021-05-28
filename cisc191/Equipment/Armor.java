// Christopher Diep
package edu.sdccd.cisc191.Equipment;

public class Armor extends Item {
    private int defenseBonus;

    public Armor(String name, int defenseBonus) { //Basis of the armor for the player
        super(name);
        this.defenseBonus = defenseBonus;
    }

    public int getDefenseBonus() {
        return defenseBonus;
    }
}
