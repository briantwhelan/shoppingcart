package com.xgen.interview;

import java.lang.reflect.Array;
import java.util.*;

/**
 * This is the current implementation of ShoppingCart.
 * Please write a replacement
 */
public class ShoppingCart implements IShoppingCart
{
    private HashMap<String, Item> contents;
    private LinkedList<String> scanOrder;
    private Pricer pricer;
    
    /**
     * Creates a {@code ShoppingCart} with the specified {@Pricer}.
     *
     * @param pricer the {@code Pricer} which holds the information
     * regarding the prices of items
     */
    public ShoppingCart(Pricer pricer)
    {
        this.contents = new HashMap<String, Item>();
        this.scanOrder = new LinkedList<String>();
        this.pricer = pricer;
    }

    /**
     * Checks whether the specified {@code Item} is in the {@ShoppingCart}.
     *
     * @param item the item to check whether it exists
     * @return {@code true} if {@code item} is already exists and 
     * {@code false} otherwise 
     */
    private boolean isInShoppingCart(String item)
    {
        return contents.containsKey(item);
    }

    /**
     * Adds an item to the {@code ShoppingCart}.
     *
     * @param itemType the type of item being added
     * @param number the quantity of the {@code itemType} 
     * being added
     */
    public void addItem(String itemType, int number)
    {
        if(isInShoppingCart(itemType))
        {
            Item item = contents.get(itemType);
            item.adjustQuantity(number);
        }
        else
        {
            Item newItem = new Item(itemType, number, pricer.getPrice(itemType));
            contents.put(itemType, newItem);
            scanOrder.add(itemType);
        }
    }

    /**
     * Prints the receipt for the current state of the {@code ShoppingCart}.
     * Items are printed in the format <type> - <quantity> - <price>.
     * (This can be easily adjusted by changing the method called to print 
     * each item e.g. use item.printPriceFirst() to print the price first).
     * The total is printed at the end of the receipt.
     */
    public void printReceipt()
    {
        float totalPrice = 0;
        while(scanOrder.size() > 0)
        {
            Item item = contents.get(scanOrder.remove());
            item.printTypeFirst();    
            totalPrice += item.getTotalPriceInEuro();
        }
        String totalPriceString = String.format("€%.2f", totalPrice);
        System.out.println("Total: " + totalPriceString);
    }
}
