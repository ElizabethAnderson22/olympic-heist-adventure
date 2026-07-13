package org.uob.a1;

public class Room {
    // declaration of required fields
    private String name;
    private String description;
    private char symbol;
    private Position position;

    // constructor method
    public Room (String name, String description, char symbol, Position position)
    {
        this.name = name;
        this.description = description;
        this.symbol = symbol;
        this.position = position;
    }

    // accessor method for the name field
    public String getName ()
    {
        return this.name;
    }

    // accessor method for the description field
    public String getDescription ()
    {
        return this.description;
    }

    // accessor method for the symbol field
    public char getSymbol ()
    {
        return this.symbol;
    }

    // accessor method for the position field
    public Position getPosition ()
    {
        return this.position;
    }
}