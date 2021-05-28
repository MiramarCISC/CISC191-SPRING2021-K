// Christopher Diep
package edu.sdccd.cisc191.GameEntities;

public class BiggerSlime extends Monster{ //Slime "Medium" difficulty enemy
    public final int range = 7;

    public BiggerSlime() {
        super("Bigger Slime");
        levelScale();
    }

    public BiggerSlime(int level) {
        super("Bigger Slime");
        levelScale();
    }

    public void levelScale() { //Originally had a level system, repurposed to apply basic stats
        setMaxHealth(25);
        setHealth(25);
        setAttack(10);
        setDefense(10);
        setSpeed(9);

    }

    public int attack() {
        return super.attack(range);
    }
}
