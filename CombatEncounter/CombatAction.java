// Fernando Palencia
package edu.sdccd.cisc191.CombatEncounter;

import edu.sdccd.cisc191.GameEntities.Monster;
import edu.sdccd.cisc191.GameEntities.Player;

public interface CombatAction { //Bases for all the combat action

    void execute(Player player, Monster enemy); //Calls all effected parties in combat

    String toString();

}


