// Christopher Diep
package edu.sdccd.cisc191.Equipment;

public class Item { //Original entity of all items in the game
    private String itemName;
//    private int price; Originally had shops in the game but could not add due to member limitation

    public Item() {
        itemName = "";
    }

    public Item(String name) {
        itemName = name;
//        this.price = price;
    }

    public String getItemName() {
        return itemName;
    }

}
