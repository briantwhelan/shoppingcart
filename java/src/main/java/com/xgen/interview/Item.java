package com.xgen.interview;

public class Item
{
    private static final Integer NUMBER_OF_CENTS_IN_A_EURO = 100;
    private static final Integer CUSTOMER_QUANTITY_LIMIT = 100;

    private final String type;
    private int quantity;
    private final int priceInCents;

    /**
     * Creates an {@code Item} with the specified name and 
     * initial quantity.
     *
     * @param type the type of the item
     * @param quantity the initial quantity of the item (if this
     * is not a valid quantity, the initial quanity is set to 1)
     * @param priceInCents the price of the item in Euro-cents
     */
    public Item(String type, int quantity, int priceInCents)
    {
        this.type = type;
        this.quantity = (isValidQuantity(quantity) ? quantity : 1);
        this.priceInCents = priceInCents; 
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
     * Gets the type of the {@code Item}.
     *
     * @return the item type
     */
    public String getType()
    {
        return type;
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
     * Gets the total price, in Euro, of the {@code Item}.
     *
     * @return the total price in Euro
     */
    public float getTotalPriceInEuro()
    {
        return (((float) priceInCents * (float) quantity) / (float) NUMBER_OF_CENTS_IN_A_EURO);
    }

    /**
     * Gets the total price {@code String} of the {@code Item}.
     *
     * @return the total price {@code String}
     */
    private String getTotalPriceString()
    {
        return String.format("€%.2f", getTotalPriceInEuro());
    }

    /**
     * Prints the {@code Item} in the format <type> - <quantity> - <price>.
     */
    public void printTypeFirst()
    {
        System.out.println(type + " - " + quantity + " - " + getTotalPriceString());
    }
  
    /**
     * Prints the {@code Item} in the format <quantity> - <type> - <price>.
     */
    public void printQuantityFirst()
    {
        System.out.println(quantity + " - " + type + " - " + getTotalPriceString());
    }

     /**
     * Prints the {@code Item} in the format <price> - <type> - <quantity>.
     */
    public void printPriceFirst()
    {
        System.out.println(getTotalPriceString() + " - " + type + " - " + quantity);
    }
}

