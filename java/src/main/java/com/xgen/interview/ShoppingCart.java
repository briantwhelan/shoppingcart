package com.xgen.interview;

import java.lang.reflect.Array;
import java.util.*;


/**
 * This is the current implementation of ShoppingCart.
 * Please write a replacement
 */
public class ShoppingCart implements IShoppingCart
{
    HashMap<String, Integer> contents;
    Pricer pricer;

    /**
     * Creates a {@code ShoppingCart} with the specified {@Pricer}.
     *
     * @param pricer the {@code Pricer} which holds the information
     * regarding the prices of items
     */
    public ShoppingCart(Pricer pricer)
    {
        this.contents = new HashMap<String, Integer>();
        this.pricer = pricer;
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
        if(!contents.containsKey(itemType))
        {
            contents.put(itemType, number);
        }
        else
        {
            int existing = contents.get(itemType);
            contents.put(itemType, existing + number);
        }
    }

    /**
     * Prints the receipt for the current state of the {@code ShoppingCart}.
     * Items are printed in the format <item> - <quantity> - <price>.
     * The total is printed at the end of the receipt.
     */
    public void printReceipt()
    {
        Object[] keys = contents.keySet().toArray();

        float totalPrice = 0;
        for(int i = 0; i < Array.getLength(keys) ; i++)
        {
            Integer price = pricer.getPrice((String)keys[i]) * contents.get(keys[i]);
            Float priceFloat = new Float(new Float(price) / 100);
            totalPrice += priceFloat;
            String priceString = String.format("€%.2f", priceFloat);

            System.out.println(keys[i] + " - " + contents.get(keys[i]) + " - " + priceString);
        }
        String totalPriceString = String.format("€%.2f", totalPrice);
        System.out.println("Total: " + totalPriceString);
    }
}
