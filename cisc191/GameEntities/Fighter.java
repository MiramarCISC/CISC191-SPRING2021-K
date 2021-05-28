// Christopher Diep
package edu.sdccd.cisc191.GameEntities;

public class Fighter extends Player { //Originally intended to have multiple classes, couldn't add due to time constraints

    public Fighter() {
        super();
        classScale();
    }

    public Fighter(String name) {
        super(name);
        classScale();
    }

    public void classScale() { //Originally had a level system, repurposed to apply basic stats
        setMaxHealth(100);
        setHealth(100);
        setAttack(5);
        setSpeed(5);
    }
}
