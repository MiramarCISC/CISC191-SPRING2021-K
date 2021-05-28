// Christopher Diep
package edu.sdccd.cisc191.GameEntities;

public class GemSlime extends Monster{ //Most difficult slime except for the boss
    public final int range = 9;

    public GemSlime() {
        super("Gem Slime");
        levelScale();
    }

    public GemSlime(int level) {
        super("Gem Slime");
        levelScale();
    }

    public void levelScale() { //Originally had a level system, repurposed to apply basic stats
        setMaxHealth(50);
        setHealth(50);
        setAttack(20);
        setDefense(20);
        setSpeed(16);

    }

    public int attack() {
        return super.attack(range);
    }
}
