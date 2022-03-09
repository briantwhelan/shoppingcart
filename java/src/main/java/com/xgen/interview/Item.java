package com.xgen.interview;

public class Item
{
    private static final Integer CUSTOMER_QUANTITY_LIMIT = 100;

    private final String name;
    private int quantity;
    private final int priceInCents;

    /**
     * Creates an {@code Item} with the specified name and 
     * initial quantity.
     *
     * @param name the name of the item
     * @param quantity the initial quantity of the item (if this
     * is not a valid quantity, the initial quanity is set to 1)
     * @param priceInCents the price of the item in Euro-cents
     */
    public Item(String name, int quantity, int priceInCents)
    {
        this.name = name;
        this.quantity = (isValidQuantity(quantity) ? quantity : 1);
        this.priceInCents = priceInCents; 
    }

    /**
     * Adjusts the quantity of {@code Item} by the specified adjustment
     * (provided the adjustment results in a valid quantity).
     *
     * @param adjustment the adjustment to make to the quantity (positive 
     * values increase the quantity, negative values reduce the quantity)
     */
    public void adjustQuantity(int adjustment)
    {
        if(isValidQuantity(quantity + adjustment))
        {
            quantity += adjustment;
        }
    }

    /**
     * Checks whether the specified quantity is valid.
     * Quantities between 1 and the {@code CUSTOMER_QUANTITY_LIMIT}
     * are considered valid.
     *
     * @param quantity the quantity to check the validity of
     * @return {@code true} if {@code quantity} is valid and 
     * {@code false} otherwise 
     */
    private boolean isValidQuantity(int quantity)
    {
        boolean isValidQuantity = false;
        if((quantity > 0) && (quantity < CUSTOMER_QUANTITY_LIMIT))
        {
            isValidQuantity = true;
        }

        return isValidQuantity;
    }

    /**
     * Gets the name of the {@code Item}.
     *
     * @return the name
     */
    public String getName()
    {
        return name;
    }
    
    /**
     * Gets the current quantity of the {@code Item}.
     *
     * @return the quantity
     */
    public int getQuantity()
    {
        return quantity;
    }
    
    /**
     * Gets the price, in Euro-cent, of the {@code Item}.
     *
     * @return the price in Euro-cent
     */
    public int getPrice()
    {
        return priceInCents;
    }
}

