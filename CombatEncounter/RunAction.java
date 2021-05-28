// Fernando Palencia
// Player Run attempt
package edu.sdccd.cisc191.CombatEncounter;

import edu.sdccd.cisc191.GameEntities.Monster;
import edu.sdccd.cisc191.GameEntities.Player;

public class RunAction implements CombatAction{

    String actionResult;

    public void execute(Player player, Monster enemy){ //Used to escape an enemy encounter, cannot use in final battle
        if(runChance() <= 8) {
            player.setEscape(true);
            actionResult = "You have successfully escaped";
        }
        else {
            player.setEscape(false);
            actionResult = "You have failed to escape";
        }
    }

    public int runChance() {
        return (int) ((Math.random() * ((10) - 1)) + 1);
    } //20% chance to fail escape attempt

    public String toString(){
        return actionResult;
    }
}

