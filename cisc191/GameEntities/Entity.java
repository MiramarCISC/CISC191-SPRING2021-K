// Christopher Diep
package edu.sdccd.cisc191.GameEntities;

public abstract class Entity { //Originator for all entities, originally had NPCS but never got used due to time constraints
    private String name;

    public Entity() {
        name = "";
    }

    public Entity(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

//    public abstract void interaction(); originally had for NPC interaction
}
