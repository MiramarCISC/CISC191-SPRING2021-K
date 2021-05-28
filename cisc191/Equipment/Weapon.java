// Christopher Diep
package edu.sdccd.cisc191.Equipment;

public class Weapon extends Item { //Originally intended to have multiple weapon types, basis of all weapons
    private int damageBonus;
    private int damageRange;

    public Weapon() {
        super();
        damageBonus = 0;
        damageRange = 0;
    }

    public Weapon(String name, int damageBonus, int damageRange) {
        super(name);
        this.damageBonus = damageBonus;
        this.damageRange = damageRange;
    }

    public int getDamageBonus() {
        return damageBonus;
    }

    public int getDamageRange() {
        return damageRange;
    }

}
