// Fernando Palencia
// Create action classes
package edu.sdccd.cisc191.CombatEncounter;


public class Actions{

//    public static CombatAction getCombatAction() { //Originally got input for selecting action, replaced with button inputs
//        int i = 1;
//
//        switch (i) {
//            case 1 -> {
//                return new AttackAction(); //Player Attacks
//            }
//            case 2 -> {
//                return new DefenseAction(); //Player defends
//            }
//            case 3 -> {
//                return new ItemAction(); //Player uses an item
//            }
//            case 4 -> {
//                return new RunAction(); //Players run away
//            }
//            default -> {
//                return null;
//            }
//        }
//    }

    public static CombatAction getEnemyAction() { //For now the enemy only attacks
        return new EnemyAttackAction();
    } //Enemy Attack turn

//    public static void displayActionResult(String resultText){ //Used in testing actions before adding to game
//        System.out.printf("%s",resultText);
//        System.out.println();
//    }

}