// Christopher Diep
package edu.sdccd.cisc191.GameEntities;

public class Combatant extends Entity {
    private int maxHealth;
    private int health;
    private int attack;
    private int defense;
    private int speed;
    private boolean isDefending;

    public Combatant() {
        super();
        maxHealth = 0;
        health = 0;
        attack = 0;
        defense = 0;
        speed = 0;
        isDefending = false;
    }

    public Combatant(String name) {
        super(name);
        maxHealth = 0;
        this.health = 0;
        this.attack = 0;
        this.defense = 0;
        this.speed = 0;
        isDefending = false;
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public int getHealth() {
        return health;
    }

    public int getAttack() {
        return attack;
    }

    public int getDefense() {
        if(isDefending) {
            return defense * 2;
        }
        return defense;
    }

    public int getSpeed() {
        return speed;
    }

    public void setMaxHealth(int maxHealth) {
        this.maxHealth = maxHealth;
    }

    public void setHealth(int health) {
        this.health = health;
        if(health > maxHealth)
            this.health = maxHealth;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public void setDefense(int defense) {
        this.defense = defense;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public void takeDamage(int damage) {
        health -= damage;
        if(health < 0) {
            health = 0;
        }
    }

    public void defendAction() {
        isDefending = true;
    }

    public int attack(int range) {
        int min, max;
        min = getAttack(); //Character's attack with the very lowest the damage can do.
        max = getAttack() + range;  //Character's attack with the maximum the damage can do
        return (int) ((Math.random() * ((max) - min)) + min);
    }
}
