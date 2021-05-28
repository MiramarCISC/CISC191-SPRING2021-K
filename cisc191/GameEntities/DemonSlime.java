// Christopher Diep
package edu.sdccd.cisc191.GameEntities;

public class DemonSlime extends Monster{ //Final boss enemy
    public final int range = 10;

    public DemonSlime() {
        super("Demon Slime");
        levelScale();
    }

    public DemonSlime(int level) {
        super("Demon Slime");
        levelScale();
    }

    public void levelScale() {
        setMaxHealth(100);
        setHealth(100);
        setAttack(25);
        setDefense(25);
        setSpeed(25);
    }

    public int attack() {
        return super.attack(range);
    }
}
