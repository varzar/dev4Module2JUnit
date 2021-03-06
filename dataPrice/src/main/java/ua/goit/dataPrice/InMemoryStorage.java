package ua.goit.dataPrice;

import java.math.BigDecimal;
import java.util.HashMap;

public class InMemoryStorage {
    private HashMap<String, BigDecimal> itemsMap = new HashMap<>();
    public BigDecimal put(Item item){
        return itemsMap.put(item.getName(), item.getCost());
    }

    public BigDecimal get(String name){
        return itemsMap.get(name);
    }

    public HashMap<String, BigDecimal> getItemsMap(){
        return itemsMap;
    }

    //please commented and unused code

}
