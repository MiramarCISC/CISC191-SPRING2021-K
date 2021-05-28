// Christopher Diep
package edu.sdccd.cisc191.Equipment;

public class Sword extends Weapon { //Bases of all swords and basic weapon the player starts with

    public Sword() {
        super("Basic Sword", 2, 3);
    }

    public Sword(String name, int damageBonus, int damageRange) {
        super(name, damageBonus, damageRange);
    }

}
