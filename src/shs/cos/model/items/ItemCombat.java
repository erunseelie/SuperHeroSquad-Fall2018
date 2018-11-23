package shs.cos.model.items;

import java.util.Map;

import shs.cos.model.entities.Monster;


public class ItemCombat extends Item{
    String s = "";{
    	
    for (Map.Entry<String, Item> entryItem : Item.getItemIDList().entrySet()) {
        Item i = entryItem.getValue();
        if (i.getItemType() != null) {
            if (i.getItemType().equals("combat")) {
                s = i.getItemType();
                break;
            } 
        }
    }
  // return Monster.getMonSpeed()==0;
}

}