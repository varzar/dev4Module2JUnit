package ua.goit.dataPrice;

import java.math.BigDecimal;
import java.util.Objects;

public class Item {
    private String name;
    private BigDecimal cost;

    public Item(String name, BigDecimal cost){
        this.name = name;
        this.cost = cost;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Item items = (Item) o;
        return Objects.equals(name, items.name) && Objects.equals(cost, items.cost);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, cost);
    }

    @Override
    public String toString() {
        return "Item{" +
                "name='" + name + '\'' +
                ", cost=" + cost +
                '}';
    }
}
