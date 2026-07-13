package org.uob.a1;

public class Inventory {
    // declaring required fields
    private final int MAX_ITEMS = 10;
    private int currentSize; // integer to store the current number of items stored
    private String [] inventory; // array to store the items in

    // constructor method !!!!!
    public Inventory ()
    {
        inventory = new String [MAX_ITEMS];
    }

    // method to add an item to the array if there is space
    public void addItem (String item)
    {
        if ((currentSize +1) != MAX_ITEMS)
        {
            inventory[currentSize] = item;
            System.out.println("You have taken item: " + item + " and added it to your inventory.");
            currentSize++;
        }
        else
        {
            System.out.println("You are unable to add another item to your inventory as it is already full. You must first drop an item before you can try add this one.");
        }
    }

    // method to return the position of the item in the array if it is in the array. If it is not in the array, returns -1
    public int hasItem (String item)
    {
        int index = -1;
        for (int i = 0; i < currentSize; i++)
        {
            if (inventory[i] != null && inventory[i].equalsIgnoreCase(item))
            {
                index = i;
            }
        }
        return index;
    }

    // method to remove a specified item while ensuring there are no empty elements in the array.
    public void removeItem (String item)
    {
        int pos = hasItem(item);
        if (pos != -1)
        {
            // for loop to perform a shiftLess method, moving each element in the array after the position of the removed element, down one position, ensuring no empty elements
            for (int i = pos; i < currentSize-1; i++)
            {
                inventory[i] = inventory[i+1];
            }
            inventory[currentSize-1] = null;
            currentSize--;
            System.out.println("You have dropped item: " + item + " and removed it from your inventory.");
        }
        else
        {
            System.out.println("We cannot find that item in your inventory, so you were unable to drop it. Please try again.");
        }
    }

    // method to display the inventory by returning a String
    public String displayInventory ()
    {
        // StringBuilder is best to use here as it is mutable
        StringBuilder sb = new StringBuilder ();
        for (int i = 0; i < currentSize; i++)
        {
            sb.append(inventory[i] + "\n");
        }
        return sb.toString();
    }
    
}