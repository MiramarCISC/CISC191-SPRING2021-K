// Christopher Diep
package edu.sdccd.cisc191.Equipment;

import edu.sdccd.cisc191.GameEntities.Combatant;

public class PotionOfHealing extends Consumables { //Basic healing potion that can only be used outside of combat

    public PotionOfHealing() {
        super("Potion of Healing");
    }

    @Override
    public void effect(Combatant effected) {
        int currentHealth = effected.getHealth();
        effected.setHealth(currentHealth + 10); //Heals 10 hp
    }
}
