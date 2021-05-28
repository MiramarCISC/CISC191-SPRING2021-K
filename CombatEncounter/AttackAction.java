// Fernando Palencia
// Player attack
package edu.sdccd.cisc191.CombatEncounter;

import edu.sdccd.cisc191.GameEntities.Monster;
import edu.sdccd.cisc191.GameEntities.Player;

public class AttackAction implements CombatAction { //Player attacks

    public void execute(Player player, Monster enemy) {
        int damage = damageCalculation(player.attack(), enemy.getDefense());
        enemy.takeDamage(damage);
    }

    public int damageCalculation(int damage, int defense) { //Calculates damage based on defense and attack states
        int totalDamage;
        totalDamage = damage - (defense / 2);
        if (totalDamage < 1)
            totalDamage = 1;
        return totalDamage;
    }
}

