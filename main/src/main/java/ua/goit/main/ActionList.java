package ua.goit.main;

import ua.goit.dataPrice.InMemoryStorage;
import ua.goit.dataPrice.StorageWithAction;
import ua.goit.dataPrice.StorageWithoutAction;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ActionList {
    private final InMemoryStorage storageWithoutAction = new StorageWithoutAction().createStorage();
    private final InMemoryStorage storageWithAction = new StorageWithAction().createStorage();
    HashMap<String, Integer> actionMap;

    public ActionList(HashMap<String, Integer> actionMap) {
        this.actionMap = actionMap;
    }

    public InMemoryStorage getStorageWithoutAction(){ return storageWithoutAction;}
    public InMemoryStorage getStorageWithAction(){return storageWithAction;}

    public BigDecimal calculateTotalCost(String shoppingList) {
        if(shoppingList == null){
            throw new IllegalArgumentException("incorrect input data");
        }
        List <String> nameItems = new ArrayList<>();
        List<Integer> amountItems = new ArrayList<>();
        List<BigDecimal> costAction = new ArrayList<>();
        for (Map.Entry<String, Integer> entry: actionMap.entrySet()) {
            nameItems.add(entry.getKey());
            amountItems.add(entry.getValue());
            costAction.add(storageWithAction.get(entry.getKey()));
        }

        int size = costAction.size();
        BigDecimal result = BigDecimal.ZERO;
        int amountItemsInCart;
        int count = 0;

        for (Map.Entry<String, BigDecimal> entry : storageWithoutAction.getItemsMap().entrySet()) {
            if(count < size && entry.getKey().equals(nameItems.get(count))) {
                amountItemsInCart = getCounter(shoppingList, nameItems.get(count));
                result = result
                        .add(getItemSum(entry.getValue(), costAction.get(count),
                                amountItems.get(count), amountItemsInCart));
                count++;
            }else{
                int amount = (getCounter(shoppingList, entry.getKey()));
                result = result.add(entry.getValue()
                                .multiply(new BigDecimal(amount)))
                        .setScale(2, RoundingMode.HALF_EVEN);
            }
        }
        return result;
    }


    private int getCounter(String shoppingList, String nameItems) {
        int indexItem;
        int currentIndex = 0;
        int counter = 0;
        while ((indexItem = shoppingList.indexOf(nameItems, currentIndex)) != -1){
            counter++;
            currentIndex = indexItem + 1;
        }
        return counter;
    }

    private BigDecimal getItemSum(BigDecimal cost, BigDecimal costAction, int amountForAction, int counter) {
        BigDecimal result;
        if(counter >= amountForAction){
            int remainder = counter - amountForAction;
            result = costAction.multiply(new BigDecimal(amountForAction))
                    .add(cost.multiply(new BigDecimal(remainder)))
                    .setScale(2, RoundingMode.HALF_EVEN);
        }else {
            result = cost.multiply(new BigDecimal(counter))
                    .setScale(2, RoundingMode.HALF_EVEN);
        }
        return result;
    }

}

