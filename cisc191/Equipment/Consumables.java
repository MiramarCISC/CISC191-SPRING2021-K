// Christopher Diep
package edu.sdccd.cisc191.Equipment;

import edu.sdccd.cisc191.GameEntities.Combatant;

public abstract class Consumables extends Item { //Class for all the potions, which have effects on the player

    public Consumables() {
        super();
    }

    public Consumables(String name) {
        super(name);
    }

    public abstract void effect(Combatant effected);

}
