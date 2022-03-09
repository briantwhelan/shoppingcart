package com.xgen.interview;

import java.util.HashMap;

/**
 * A stub implementation - for this exercise, you may disregard that this is incomplete.
 */
public class Pricer
{
    HashMap<String, Integer> pricingDatabase = new HashMap<>(); // stub

    /**
     * Creates a {@Pricer} which contains the pricing information for various items
     */
    public Pricer()
    {
        pricingDatabase.put("apple", 100);
        pricingDatabase.put("banana", 200);
    }

    /**
     * Gets the price for the specified item according to the data in the
     * {@code Pricer}
     *
     * @param itemType the type of item for which a price is required
     * @return the price of the {@code itemType} in euro-cent according 
     * to the {@code Pricer} (or 0 if the {@code Pricer} has no price
     * information for the {@code itemType}
     */
    public Integer getPrice(String itemType)
    {
        if(!pricingDatabase.containsKey(itemType))
        {
            return 0;
        }
        return pricingDatabase.get(itemType);
    }
}

