package org.uob.a1;

public class Map {
    // declaration of required fields
    private char [] [] mapArray;
    private int width;
    private int height;
    final private char EMPTY = '.';

    // constructor method
    public Map (int width, int height)
    {
        this.width = width;
        this.height = height;
        mapArray = new char [width] [height];

        // filling the map with empty characters
        for (int i = 0; i < height; i++) 
        {
            for (int j = 0; j < width; j++) 
            {
                mapArray[i][j] = EMPTY;
            }
        }
    }

    // method to place room on the map
    public void placeRoom (Position pos, char symbol)
    {
        mapArray[pos.y][pos.x] = symbol;
    }
    // method to display the map as an output (String) that works with the testing
    public String display ()
    {
        // better to use StringBuilder as it is mutable
        StringBuilder sb = new StringBuilder ();
        for (int row = 0; row < height; row++)
        {
            // adds each column in the row one at a time
            for (int col = 0; col < width; col++)
            {
                sb.append(mapArray[row][col]);
            }
            // moves to a new line at the end of each row
            sb.append("\n"); 
        }
        return sb.toString();
    } 
    // method to display the map as an output (String) and use a key which doesn't work with the testing
    public String display (boolean showIndex)
    {
        if (showIndex)
        {
            // better to use StringBuilder as it is mutable
            StringBuilder sb = new StringBuilder ();
            for (int row = 0; row < height; row++)
            {
                // adds each column in the row one at a time
                for (int col = 0; col < width; col++)
                {
                    sb.append(mapArray[row][col]);
                }
                // moves to a new line at the end of each row
                sb.append("\n"); 
            }
            sb.append("-------------------------------------------------\n");
            sb.append("Legend:\n");
            sb.append("R - Reception (Entrance)\n");
            sb.append("S - Security Office\n");
            sb.append("L - Locker Room\n");
            sb.append("G - Gym\n");
            sb.append("H - Hallway\n");
            sb.append("M - Media Room\n");
            sb.append("D - Athlete Dorm\n");
            sb.append("T - Trophy Room\n");
            sb.append("C - Control Room\n");
            sb.append("V - VIP Box (Escape Point)\n");
            return sb.toString();
        }
        else
        {
            // better to use StringBuilder as it is mutable
            StringBuilder sb = new StringBuilder ();
            for (int row = 0; row < height; row++)
            {
                // adds each column in the row one at a time
                for (int col = 0; col < width; col++)
                {
                    sb.append(mapArray[row][col]);
                }
                // moves to a new line at the end of each row
                sb.append("\n"); 
            }
            return sb.toString();
        }
    }
    
}