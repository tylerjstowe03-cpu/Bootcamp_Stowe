package org.example;

import java.util.ArrayList;
import java.util.List;

public class AddCart {
    public List<Inventory> cart = new ArrayList<>();

    public void addtoCart(Inventory inventory) {
        cart.add(inventory); }
    public void removeFromCart(Inventory inventory) {
        cart.remove(inventory); }

    public double getCartTotal() {
        double total = 0;
        for (Inventory inventory : cart) {
            total += inventory.getPrice(); }
                return total; }
}
