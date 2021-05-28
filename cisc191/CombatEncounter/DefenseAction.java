// Fernando Palencia
// Player Defense
package edu.sdccd.cisc191.CombatEncounter;

import edu.sdccd.cisc191.GameEntities.Monster;
import edu.sdccd.cisc191.GameEntities.Player;

public class DefenseAction implements CombatAction{
    public void execute(Player player, Monster enemy){
        player.defendAction();
    } //Increases defense for one turn

    public String toString(){
        return "Defends";
    }

}
