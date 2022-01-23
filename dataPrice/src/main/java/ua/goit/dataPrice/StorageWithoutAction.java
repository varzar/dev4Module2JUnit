package ua.goit.dataPrice;
import java.math.BigDecimal;

public class StorageWithoutAction {
    private  InMemoryStorage storage= new InMemoryStorage();
    public InMemoryStorage createStorage(){
        Item A = new Item("A", BigDecimal.valueOf(1.25));
        Item B = new Item("B", BigDecimal.valueOf(4.25));
        Item C = new Item("C", BigDecimal.valueOf(1));
        Item D = new Item("D", BigDecimal.valueOf(0.75));
        storage.put(A);
        storage.put(B);
        storage.put(C);
        storage.put(D);
        return storage;
    }
}
