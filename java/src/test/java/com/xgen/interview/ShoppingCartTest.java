package com.xgen.interview;

import com.xgen.interview.Pricer;
import com.xgen.interview.ShoppingCart;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertEquals;

public class ShoppingCartTest
{
    @Test
    public void canAddAnItem()
    {
        ShoppingCart sc = new ShoppingCart(new Pricer());

        sc.addItem("apple", 1);

        final ByteArrayOutputStream myOut = new ByteArrayOutputStream();
        System.setOut(new PrintStream(myOut));

        sc.printReceipt();
        assertEquals(String.format("apple - 1 - €1.00%nTotal: €1.00%n"), myOut.toString());
    }

    @Test
    public void canAddMoreThanOneItem()
    {
        ShoppingCart sc = new ShoppingCart(new Pricer());

        sc.addItem("apple", 2);

        final ByteArrayOutputStream myOut = new ByteArrayOutputStream();
        System.setOut(new PrintStream(myOut));

        sc.printReceipt();
        assertEquals(String.format("apple - 2 - €2.00%nTotal: €2.00%n"), myOut.toString());
    }

    @Test
    public void canAddDifferentItems()
    {
        ShoppingCart sc = new ShoppingCart(new Pricer());

        sc.addItem("apple", 2);
        sc.addItem("banana", 1);

        final ByteArrayOutputStream myOut = new ByteArrayOutputStream();
        System.setOut(new PrintStream(myOut));

        sc.printReceipt();

        String result = myOut.toString();

        if(result.startsWith("apple"))
        {
            assertEquals(String.format("apple - 2 - €2.00%nbanana - 1 - €2.00%nTotal: €4.00%n"), result);
        }
        else
        {
            assertEquals(String.format("banana - 1 - €2.00%napple - 2 - €2.00%nTotal: €4.00%n"), result);
        }
    }

    @Test
    public void doesntExplodeOnMysteryItem()
    {
        ShoppingCart sc = new ShoppingCart(new Pricer());

        sc.addItem("crisps", 2);

        final ByteArrayOutputStream myOut = new ByteArrayOutputStream();
        System.setOut(new PrintStream(myOut));

        sc.printReceipt();
        assertEquals(String.format("crisps - 2 - €0.00%nTotal: €0.00%n"), myOut.toString());
    }

    @Test
    public void canRetrieveItemFields()
    {
        Item item = new Item("apple", 2, 100);
        assertEquals("apple", item.getName());
        assertEquals(2, item.getQuantity());
        assertEquals(100, item.getPrice());
    }

    @Test
    public void canAdjustItemQuantity()
    {
        Item item = new Item("apple", 2, 100);
        item.adjustQuantity(-3);
        assertEquals("Testing invalid quantity adjustment...", 2, item.getQuantity());
        item.adjustQuantity(5);
        assertEquals("Testing valid positive quantity adjustment...", 7, item.getQuantity());
        item.adjustQuantity(-2);
        assertEquals("Testing valid negative quantity adjustment...", 5, item.getQuantity());
    }
}

