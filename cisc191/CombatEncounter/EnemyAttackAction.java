// Fernando Palencia
// Enemy attack
package edu.sdccd.cisc191.CombatEncounter;

import edu.sdccd.cisc191.GameEntities.Monster;
import edu.sdccd.cisc191.GameEntities.Player;

public class EnemyAttackAction implements CombatAction{ //Attack action for the enemy

    String actionResult;

    public void execute(Player player, Monster enemy){
        int damage = damageCalculation(enemy.attack(), player.getDefense());
        player.takeDamage(damage);
    }

    public int damageCalculation(int damage, int defense) { //Damage calculation based on defense of player and attack of enemy
        int totalDamage;
        totalDamage = damage - (defense/2);
        if(damage < 1)
            totalDamage = 1;
        return totalDamage;
    }

    public String toString(){
        return actionResult;
    }

}
