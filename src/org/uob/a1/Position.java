package org.uob.a1;

public class Position {
    // declaration of x and y fields as public as required so other classes can access directly
    public int x;
    public int y;

    // constructor method accepting integers for x and y position
    public Position (int x, int y)
    {
        this.x = x;
        this.y = y;
    }
}