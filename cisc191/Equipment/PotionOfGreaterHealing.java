// Christopher Diep
package edu.sdccd.cisc191.Equipment;

import edu.sdccd.cisc191.GameEntities.Combatant;

public class PotionOfGreaterHealing extends Consumables{ //Better healing potion to use outside of combat

    public PotionOfGreaterHealing() {
        super("Potion of Greater Healing");
    }

    public void effect(Combatant effected) {
        int currentHealth = effected.getHealth();
        effected.setHealth(currentHealth + 40); //Heals 40 health
    }
}
