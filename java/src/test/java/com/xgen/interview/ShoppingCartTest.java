package com.xgen.interview;

import com.xgen.interview.Pricer;
import com.xgen.interview.ShoppingCart;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class ShoppingCartTest
{
    private ShoppingCart shoppingCart;
    private Item item;
    private ByteArrayOutputStream myOut;
    
    @Before
    public void setUp()
    {
        shoppingCart = new ShoppingCart(new Pricer());
        item = new Item("apple", 2, 100);
        myOut = new ByteArrayOutputStream();
        System.setOut(new PrintStream(myOut));
    }

    @After
    public void tearDown()
    {
        try
        {
            myOut.close();  
        }
        catch(IOException e)
        {
            System.out.println("Error closing byte array output stream...");
        }
    }
 
    @Test
    public void canAdjustItemQuantity()
    {
        item.adjustQuantity(-3);
        assertEquals("Testing invalid quantity adjustment...", 2, item.getQuantity());
        item.adjustQuantity(5);
        assertEquals("Testing valid positive quantity adjustment...", 7, item.getQuantity());
        item.adjustQuantity(-2);
        assertEquals("Testing valid negative quantity adjustment...", 5, item.getQuantity());
    }

    @Test
    public void canRetrieveItemFields()
    {
        assertEquals("Testing retrieving the item type...", "apple", item.getType());
        assertEquals("Testing retrieving the item quantity type...", 2, item.getQuantity());
        assertEquals("Testing retrieving the item price (in Euro)...", 2.00, item.getTotalPriceInEuro(), 0.0);
    }

    @Test
    public void canPrintItemWithTypeFirst()
    {
        item.printTypeFirst();
        assertEquals("Testing printing item with type first...", "apple - 2 - €2.00", (myOut.toString()).trim());
    }
    
    @Test
    public void canPrintItemWithQuantityFirst()
    {
        item.printQuantityFirst();
        assertEquals("Testing printing item with quantity first...", "2 - apple - €2.00", (myOut.toString()).trim());
    }
    
    @Test
    public void canPrintItemWithPriceFirst()
    {
        item.printPriceFirst();
        assertEquals("Testing printing item with price first...", "€2.00 - apple - 2", (myOut.toString()).trim());
    }

    @Test
    public void canAddAnItem()
    {
        shoppingCart.addItem("apple", 1);    
        shoppingCart.printReceipt();

        assertEquals("Testing adding a single item...", String.format("apple - 1 - €1.00%nTotal: €1.00%n"), myOut.toString());
    }

    @Test
    public void canAddMoreThanOneItem()
    {
        shoppingCart.addItem("apple", 2);
        shoppingCart.printReceipt();

        assertEquals("Testing adding multiple items...", String.format("apple - 2 - €2.00%nTotal: €2.00%n"), myOut.toString());
    }

    @Test
    public void canAddDifferentItems()
    {
        shoppingCart.addItem("apple", 2);
        shoppingCart.addItem("banana", 1);
        shoppingCart.printReceipt();

        assertEquals("Testing adding different items...", String.format("apple - 2 - €2.00%nbanana - 1 - €2.00%nTotal: €4.00%n"), myOut.toString());
    }
    
    @Test
    public void canAddTheSameItems()
    {
        shoppingCart.addItem("apple", 2);
        shoppingCart.addItem("apple", 1);
        shoppingCart.printReceipt();

        assertEquals("Testing adding the same items...", String.format("apple - 3 - €3.00%nTotal: €3.00%n"), myOut.toString());
    }

    @Test
    public void printsInOrderOfScan()
    {
        shoppingCart.addItem("apple", 2);
        shoppingCart.addItem("banana", 1);
        shoppingCart.addItem("apple", 3);
        shoppingCart.addItem("crisps", 1);
        shoppingCart.printReceipt();
    
        assertEquals("Testing the order of the receipt items...", String.format("apple - 5 - €5.00%nbanana - 1 - €2.00%ncrisps - 1 - €0.00%nTotal: €7.00%n"), myOut.toString());
    }

    @Test
    public void doesntExplodeOnMysteryItem()
    {
        shoppingCart.addItem("crisps", 2);
        shoppingCart.printReceipt();

        assertEquals("Testing adding a mystery item...", String.format("crisps - 2 - €0.00%nTotal: €0.00%n"), myOut.toString());
    }
}

