// Christopher Diep
package edu.sdccd.cisc191.GameEntities;

import edu.sdccd.cisc191.CombatEncounter.CombatAction;

import static edu.sdccd.cisc191.CombatEncounter.Actions.getEnemyAction;

public abstract class Monster extends Combatant { //Basis for all monster entities in the game, all slimes
    private CombatAction combatAction;

    public Monster() {
        super();
        combatAction = null;
    }

    public Monster(String name) {
        super(name);
        combatAction = null;
    }


    public int attack() {
        return super.attack(0);
    }

    public void enemyTurn(Player player){
        if(getHealth() > 0){
            setCombatAction(getEnemyAction());
        }
        else{
        }
        performAction(player);
    }

    public void performAction(Player player){
        combatAction.execute(player, this);
    } //Does an action in combat

    public void setCombatAction(CombatAction selAction){
        combatAction = selAction;
    }

}
