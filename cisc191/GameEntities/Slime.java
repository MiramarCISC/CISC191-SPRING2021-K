// Christopher Diep
package edu.sdccd.cisc191.GameEntities;

//Basic enemy, easiest to beat out of all enemies
public class Slime extends Monster {
    public final int range = 5;

    public Slime() {
        super("Slime");
        levelScale();
    }

    public Slime(int level) {
        super("Slime");
        levelScale();
    }
    
    public void levelScale() { //Originally had a level system, repurposed to apply basic stats
        setMaxHealth(15);
        setHealth(15);
        setAttack(5);
        setDefense(5);
        setSpeed(5);
    }

    public int attack() {
        return super.attack(range);
    }

}
