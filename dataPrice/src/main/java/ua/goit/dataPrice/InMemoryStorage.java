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

    public BigDecimal getValue(int index){
        return itemsMap.get(index);
    }

    public BigDecimal remove(String name){
        return itemsMap.remove(name);
    }

    public int size(){
        return itemsMap.size();
    }

    public HashMap<String, BigDecimal> getItemsMap(){
        return itemsMap;
    }

    public void clear(){
        itemsMap.clear();
    }

//    public String keySet() {
//        itemsMap.keySet();
//    }
}
