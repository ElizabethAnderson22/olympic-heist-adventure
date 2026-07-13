package org.uob.a1;

import java.util.Scanner; 

public class Game 
{  
    // declaring these variables outside of the main method so they can be used by all helper methods
    // creating the map
    private static Map gameMap = new Map (10, 10);

    // creating inventory and score
    private static Inventory inventory = new Inventory ();
    private static Score score = new Score (0);
    
    // creating the array of rooms and storing information about the rooms in the array
    // integer value storing the number of rooms
    private static int size = 10;
    private static Room [] rooms = new Room [size];
    // loop control testing if the user still wants to play
    private static boolean playing = true;
    // initializing Scanner device to receive use input
    private static Scanner inputDevice = new Scanner(System.in);
    // variable to hold the current room of the player
    private static Room currentRoom;
    // Creating player's position
    private static Position playerPos;
    //booleans indicating whether the puzzles have been solved
    private static boolean puzzle1Solved = false;
    private static boolean puzzle2Solved = false;
    private static boolean puzzle3Solved = false;
    // checking if the user has visited a new room
    private static boolean [] visited = new boolean [size]; // parallel array to that of rooms, indicating if they have been visited or not
    
    public static void main(String args[]) 
    {
        // creating the rooms
        rooms [0] = new Room ("Reception", "You are standing in the main entrance area with marble floors and a desk. A volunteer’s badge lies on the counter. This might come in handy later. The desk is a feature in this room. ", 'R', new Position (2, 8));

    rooms [1] = new Room ("Security office", "You are standing in the security office, filled with CCTV monitors and blinking red lights. The main security system is controlled here. The monitors are a feature in this room.", 'S', new Position (3, 7));

    rooms [2] = new Room ("Locker room", "You are standing in the locker room with rows of lockers — some locked, others open. A keycard could be hidden here. The locker if a feature in this room.", 'L', new Position (5, 7));

    rooms [3] = new Room ("Gym", "You are standing in the gym. Equipment clutters the space. There’s a toolbox near the dumbbells. The toolbox is a feature in this room.", 'G', new Position (6, 6));

    rooms [4] = new Room ("Hallway", "You are standing in a long hallway that connects the central parts of the stadium. The poster is a feature in this room.", 'H', new Position (4, 6));

    rooms [5] = new Room ("Media room", "You are standing in the media room, filled with cameras, laptops, and microphones — a journalist might have left something behind. The camera is a feature in this room", 'M', new Position (3, 5));

    rooms [6] = new Room ("Athlete dorm", "You are standing in the athlete dorm, where bunk beds line the walls. It smells faintly of sweat and adrenaline. The bunk is a feature in this room.", 'D', new Position (5, 5));

    rooms [7] = new Room ("Trophy room", "You are standing in the trophy room with glittering trophies and gold medals on display. The stand is a feature in this room.", 'T', new Position (6, 4));

    rooms [8] = new Room ("Control room", "You are standing in the control room with panels of blinking switches. The security lockdown can be disabled here. The panel is a feature in this room.", 'C', new Position (7, 4));

    rooms [9] = new Room ("VIP box", "You are standing in the VIP box that overlooks the field. The window is a feature in this room and is the final escape point once the medal is recovered.", 'V', new Position (8, 3));

        // setting start point to be at reception
        currentRoom = rooms[0];
        playerPos = new Position(currentRoom.getPosition().x, currentRoom.getPosition().y);
     // placing the rooms on the map
    for (int i = 0 ; i < size; i++)
    {
        gameMap.placeRoom(rooms[i].getPosition(), rooms[i].getSymbol());
    }
        // printing the opening message to inform the use about the game. Making using of a text block to format more aesthetically. 
        System.out.println("""
================================================== 

        OLYMPIC HEIST: THE GOLDEN MISSION 

================================================== 

You are an undercover agent sent to steal the 
Olympic gold medal and exit via the VIP box (where your getaway awaits) before the closing ceremony begins. 

  

Explore the stadium, uncover clues, collect items (Volunteer badge, Keycard, Wire cutters and finally the Gold Medal), 
and solve puzzles to steal the medal before it's too late. 

  

Type your commands carefully and pay attention to details — 
every room, item, and clue could bring you closer to victory. 

  

Possible commands: 

- move <direction>    (north, south, east, west) 

- look                (describes your surroundings) 

- look <feature/item> (inspect a feature or item) 

- take <item>         (pick up an item to add it to your inventory) 

- use <item>          (use an item in your inventory to solve a puzzle)

- drop <item>         (drop/remove an item in your inventory)

- inventory           (view collected items) 

- score               (check your current score) 

- map                 (view a map of the olympic stadium. be sharp though as you will need to figure out whereabouts you are on the map.) 

- help                (show this list again) 

- hint                (receive a dynamic gameplay hint) 

- quit                (exit the game) 

  

-------------------------------------------------- 

Enter "help" anytime for a reminder of possible commands. 

Your mission begins now. Good luck, Agent. 

================================================== 
                           """);
        
        // while loop to control running of game
        while (playing)
        {
            // showing a prompt to user, indicating they should enter their input
            System.out.println("Enter your input below. Enter help for a reminder of possible commands.");
            System.out.print("> ");
            // creating s String out of the users input, using the trim() method to remove extra spaces at the start and end
            String command = inputDevice.nextLine().trim();

            // if statements to check commands (as sometimes the command won't be directly equal to what it means - may contain additional phrases such as direction) and perform actions depending on what the command entered is
            // help
            if (command.equalsIgnoreCase("help"))
            {
                showHelp();
            }
            // hint
            else if (command.equalsIgnoreCase("hint"))
            {
                showHint(inventory);
            }
            //move
            else if (command.startsWith("move "))
            {
                // get the direction of movement from the command
                String direction = command.substring(5).toLowerCase();
                movePlayer(direction);
            }
            // look general
            else if (command.equalsIgnoreCase("look"))
            {
                if (playerPos.x == rooms[0].getPosition().x && playerPos.y == rooms[0].getPosition().y)
                {
                    if (puzzle1Solved)
                    {
                        System.out.print(currentRoom.getDescription());
                        System.out.println("A door leading east has a sign on it saying 'unlocked'. You can now move north and then east into the security office.");
                    }
                    else 
                    {
                        System.out.print(currentRoom.getDescription());
                        System.out.println("A door leading east has a sign on it saying 'locked'.");
                    }
                }
                if (playerPos.x != currentRoom.getPosition().x || playerPos.y != currentRoom.getPosition().y)
                {
                    System.out.println("You are not currently in a room. Empty corridor surrounds you. Try using the map to move to a room in order to look around.");
                }
                else if (!(playerPos.x == rooms[0].getPosition().x && playerPos.y == rooms[0].getPosition().y))
                {
                    System.out.println(currentRoom.getDescription());
                }
            }
            // look at specific feature or item
            else if (command.startsWith("look "))
            {
                // get the exact feature/item out of the input
                String featureOrItem = command.substring(5);
                lookAtFeatureOrItem(featureOrItem);
            }
            // take an item
            else if (command.startsWith("take "))
            {
                // get the item out of the input
                String itemName = command.substring(5);
                takeItem(itemName);
            }
            // use an item
            else if (command.startsWith("use "))
            {
                // get the item out of the input
                String itemName = command.substring(4);
                useItem(itemName);
            }
            // drop an item
            else if (command.startsWith("drop "))
            {
                // get the item out of the input
                String itemName = command.substring(5);
                inventory.removeItem(itemName);
            }
            // view inventory
            else if (command.equalsIgnoreCase("inventory"))
            {
                System.out.println("Inventory:");
                System.out.println(inventory.displayInventory());
            }
            // see score
            else if (command.equalsIgnoreCase("score"))
            {
                System.out.println("Score: " + score.getScore());
            }
            // view map
            else if (command.equalsIgnoreCase("map"))
            {
                System.out.println(gameMap.display(true));
            }
            // quit
            else if (command.equalsIgnoreCase("quit"))
            {
                System.out.println("Mission aborted. Goodbye, Agent.");
                playing = false; // exit the loop
            }
            // invalid command entered
            else
            {
                System.out.println("Invalid command entered. Enter 'help' for a list of valid commands.");
            }

        } // end game while loop
        
    } // end main method

    // method to be performed when the help command is called
    public static void showHelp()
    {
        System.out.println("""
=== HELP === 

Need a reminder, Agent? 

  

Here are the commands you can use: 

- move <direction>    → Travel to another room. Direction is north, south, east or west. 

- look                → Get a description of your current location. 

- look <feature/item> → Examine a specific object or item. 

- take <item>         → Pick up an item and add it to your inventory. 

- use <item>          → Use an item in your inventory to solve a puzzle.

- drop <item>         → Drop/remove an item in your inventory.

- inventory           → See which items you’ve collected. 

- score               → Check your current score. 

- map                 → Display explored parts of the stadium. 

- help                → Receive a detailed help description, including a list of possible commands.

- hint                → Receive a subtle gameplay clue. 

- quit                → End the mission. 


Here are items you can look at, take, use or drop:
                           
- Volunteer badge
- Keycard
- Wire cutters
- Gold medal

Mission advice: 

• Some rooms are locked until you find certain items. 

• Clues are often hidden in room descriptions or features. 

• Not every item is useful right away — think creatively. 

• Keep your eyes open. Timing and logic are everything. 

  

Remember: to recover the gold medal, you’ll need to explore, 
solve puzzles and connect the dots. 

  

Stay sharp, Agent — the ceremony clock is ticking. 

============================= 
                           """);
    } // end showHelp method

    // method to be performed when the hint command is called
    public static void showHint (Inventory inventory)
    {
        if (!(puzzle1Solved))
        {
            // encourages user to pick up and use the Volunteer badge in the security office in order to gain access to the hallway and thus all the additional rooms
            System.out.println("There was a volunteer badge lying around in reception. That will definitely give you access to the restricted areas that you need!");
        }
        else if ((puzzle1Solved) && (!(puzzle2Solved)))
        {
            // encourages the user to use the wire cutters to solve puzzle 2 if they have completed puzzle 1, but havent solved puzzle 2
            System.out.println("You have solved puzzle 1. In order to solve puzzle 2, you'll need the wire cutters. I think the maintenance staff mentioned leaving their toolbox in the gym? Maybe you can use this tool to deactivate some alarms in the Trophy Room?");
        }
        else if ((puzzle1Solved) && (puzzle2Solved) && (!(puzzle3Solved)))
        {
            // encourages the user to use the keycard to open the display case in the trophy room if they have completed puzzles 1 and 2
            System.out.println("You have completed the first two puzzles. You're nearly there! In order to complete the final puzzle, you'll need the keycard to shut down the security systems, in order to open the medal's display case. I heard the security staff mention that they might've lost their card somewhere in the locker room...");
        }
        else if ((puzzle1Solved) && (!(puzzle2Solved)) && (puzzle3Solved))
        {
            // encourage the user to solve puzzle 2 if they have already completed puzzles 1 and 3 as this can accidentally happen out of order
            System.out.println("You have completed puzzles 1 and 3! You're close! In order to complete puzzle 2, you'll need the wire cutters to deactivate the display case's laser beams. I think the maintenance staff mentioned leaving their toolbox in the gym?");
        }
        // default general hint
        else
        {
            System.out.println("Apologies! We are unable to provide you with a specialised hint. Here is a general message that should be able to help you.");
            showHelp();
        }
    } // end showHint method

    // method to be performed when the move command is called
    public static void movePlayer (String direction)
    {
        int newX = playerPos.x;
        int newY = playerPos.y;
        switch (direction.toLowerCase()) 
        {
            case "north": newY--; break;
            case "south": newY++; break;
            case "east":  newX++; break;
            case "west":  newX--; break;
            default: 
                System.out.println("You have entered an invalid direction. You can only move north, south, east or west. Please try again.");
                return;
        }
        
        // checking if the user has visited a new room
        boolean [] visited = new boolean [size]; // parallel array to that of rooms, indicating if they have been visited or not
        // initialising all rooms but reception to false
        visited[0] = true;
        for (int i = 1; i < size; i++)
        {
            visited[i] = false;
        }
        
        // if the room has been visited for the first time, it is added to their score. if they enter the room, currentRoom is set to that room
        String narrative = "You move " + direction + ".";
        // security office
        if (newX == 3 && newY == 7 && (puzzle1Solved))
        {
            currentRoom = rooms[1];
            if (!(visited[1]))
            {
                visited[1] = true;
                score.visitRoom();
            }
            narrative = "You go through the doors " + direction + " and enter the security office.";
        }
        // locker room
        else if (newX == 5 && newY == 7)
        {
            currentRoom = rooms[2];
            if (!(visited[2]))
            {
                visited[2] = true;
                score.visitRoom();
            }
            narrative = "You go through the doors " + direction + " and enter the locker room.";
        }
        // training gym
        else if (newX == 6 && newY == 6 )
        {
            currentRoom = rooms[3];
            if (!(visited[3]))
            {
                visited[3] = true;
                score.visitRoom();
            }
            narrative = "You go through the doors " + direction + " and enter the gym.";
        }
        // hallway
        else if (newX == 4 && newY == 6)
        {
            currentRoom = rooms[4];
            if (!(visited[4]))
            {
                visited[4] = true;
                score.visitRoom();
            }
            narrative = "You go through the doors " + direction + " and enter the hallway junction.";
        }
        // media room 
        else if (newX == 3 && newY == 5)
        {
            currentRoom = rooms[5];
            if (!(visited[5]))
            {
                visited[5] = true;
                score.visitRoom();
            }
            narrative = "You go through the doors " + direction + " and enter the media room.";
        }
        // athlete dorm
        else if (newX == 5 && newY == 5)
        {
            currentRoom = rooms[6];
            if (!(visited[6]))
            {
                visited[6] = true;
                score.visitRoom();
            }
            narrative = "You go through the doors " + direction + " and enter the athlete dorm.";
        }
        // trophy room
        else if (newX == 6 && newY == 4)
        {
            currentRoom = rooms[7];
            if (!(visited[7]))
            {
                visited[7] = true;
                score.visitRoom();
            }
            narrative = "You go through the doors " + direction + " and enter the trophy room.";
        }
        // control room
        else if (newX == 7 && newY == 4)
        {
            currentRoom = rooms[8];
            if (!(visited[8]))
            {
                visited[8] = true;
                score.visitRoom();
            }
            narrative = "You go through the doors " + direction + " and enter the control room.";
        }
        // if the user visits the VIP box below and they have the gold medal, they win the game
        else if (newX == 8 && newY == 3)
        {
            currentRoom = rooms[9];
            narrative = "You go through the doors " + direction + " and enter the VIP box.";
            if (!(visited[9]))
            {
                visited[9] = true;
                score.visitRoom();
                if (inventory.hasItem("Gold medal") != -1)
                {
                    winGame();
                }
            }
        }
        
        // checking to see if the user is trying to enter restricted rooms
        if (newX == 3 && newY == 7 && (!(puzzle1Solved))) // entering security room
        {
            System.out.println("You haven't unlocked this area yet. Try returning to reception to see if you can find any items to aid you. If you have already taken this item, perhaps try using it first. ");
        }
        else
        {
            playerPos.x = newX;
            playerPos.y = newY;
            System.out.println(narrative);
        }
    } // end movePlayer method

    // method to be performed when the look <feature/item> command is called
    public static void lookAtFeatureOrItem(String featureOrItem)
    {
        if (featureOrItem.equalsIgnoreCase("Volunteer badge") && inventory.hasItem("Volunteer badge") != -1)
        {
            System.out.println("A laminated badge with the Olympic logo and 'Volunteer Access' printed beneath a smiling photo. This can help give you access to restricted areas.");
        }
        else if (featureOrItem.equalsIgnoreCase("Keycard") && inventory.hasItem("Keycard") != -1)
        {
            System.out.println("A plastic keycard with a magnetic strip and the words ‘SECURITY ACCESS: LEVEL 2’. It feels slightly warm, as if recently used. Perhaps this could unlock the Security Room door?");
        }
        else if (featureOrItem.equalsIgnoreCase("Wire cutters") && inventory.hasItem("Wire cutters") != -1)
        {
            System.out.println("A pair of heavy-duty wire cutters, slightly rusted but still sharp enough to cut through thick cables, such as the laser cables in the trophy room");
        }
        else if (featureOrItem.equalsIgnoreCase("Gold medal") && inventory.hasItem("Gold medal") != -1)
        {
            System.out.println("The missing Olympic gold medal — gleaming under the dim emergency lights, its ribbon perfectly folded as if waiting to be claimed. Taking it will win you the game!");
        }
        else if (featureOrItem.equalsIgnoreCase("desk") && currentRoom.getName().equalsIgnoreCase("Reception"))
        {
            System.out.println("A sleek marble desk with a visitor’s logbook. The pages list the names of volunteers — one of the badges must have been left behind.");
        }
        else if (featureOrItem.equalsIgnoreCase("locker") && currentRoom.getName().equalsIgnoreCase("Locker room"))
        {
            System.out.println("Most lockers are empty, but one seems to have a keycard lying in front of it.");
        }
        else if (featureOrItem.equalsIgnoreCase("monitors") && currentRoom.getName().equalsIgnoreCase("Security office"))
        {
            System.out.println("Rows of CCTV monitors flicker with feeds from every corner of the stadium. One camera seems to show the trophy room’s security system.");
        }
        else if (featureOrItem.equalsIgnoreCase("toolbox") && currentRoom.getName().equalsIgnoreCase("Gym"))
        {
            System.out.println("A sturdy metal toolbox sits beside the weights bench. It looks heavy, and the latch is slightly bent — maybe there’s something inside.");
        }
        else if (featureOrItem.equalsIgnoreCase("poster") && currentRoom.getName().equalsIgnoreCase("Hallway"))
        {
            System.out.println("A motivational poster reads: ‘Champions earn their glory through effort.’ Someone’s scribbled a small arrow pointing toward the Trophy Room.");
        }
        else if (featureOrItem.equalsIgnoreCase("camera") && currentRoom.getName().equalsIgnoreCase("Media room"))
        {
            System.out.println("A professional camera sits on a tripod, still recording. Be careful to avoid getting caught on camera.");
        }
        else if (featureOrItem.equalsIgnoreCase("bunk") && currentRoom.getName().equalsIgnoreCase("Athlete dorm"))
        {
            System.out.println("The bottom bunk has a half-open drawer underneath — inside, you spot a discarded towel and an empty energy drink can.");
        }
        else if (featureOrItem.equalsIgnoreCase("stand") && currentRoom.getName().equalsIgnoreCase("Trophy room"))
        {
            System.out.println("The stand’s plaque reads: ‘Olympic Gold Medal – Men’s 100m Sprint.’ The desirable gold medal gleams under the lights, but the display case sits between you and the medal.");
        }
        else if (featureOrItem.equalsIgnoreCase("panel") && currentRoom.getName().equalsIgnoreCase("Control room"))
        {
            System.out.println("A glowing control panel hums softly. One button is flashing red — it’s labelled ‘Lockdown Override.’");
        }
        else if (featureOrItem.equalsIgnoreCase("window") && currentRoom.getName().equalsIgnoreCase("VIP box"))
        {
            System.out.println("A wide glass window overlooks the entire stadium. From here, you can see every entrance — and maybe your way out.");
        }
        else 
        {
            System.out.println("That is an invalid feature or item. Try looking at something else.");
        }
    } // end lookAtFeatureOrItem

    // method to be performed whenever the take command is called
    public static void takeItem (String item)
    {
        if (item.equalsIgnoreCase("Volunteer badge") && currentRoom.getName().equalsIgnoreCase("Reception") && inventory.hasItem("Volunteer badge") == -1)
        {
            inventory.addItem(item);
        }
        else if (item.equalsIgnoreCase("Keycard") && currentRoom.getName().equalsIgnoreCase("Locker Room") && (puzzle1Solved) && inventory.hasItem("Keycard") == -1)
        {
            inventory.addItem(item);
        }
        else if (item.equalsIgnoreCase("Wire cutters") && currentRoom.getName().equalsIgnoreCase("Gym") && inventory.hasItem("Wire cutters") == -1)
        {
            inventory.addItem(item);
        }
        else if (item.equalsIgnoreCase("Gold medal") && currentRoom.getName().equalsIgnoreCase("Trophy Room") && (puzzle1Solved && puzzle2Solved && puzzle3Solved) && inventory.hasItem("Gold medal") == -1)
        {
            inventory.addItem(item);
        }
        else 
        {
            System.out.println("Remember, you can only take certain items when you are in certain rooms and you cannot take the same item more than once! The only valid items you can take are: Volunteer badge, Keycard, Wire cutter, Gold medal.");
        }
    } // end takeItem method

    // method to be performed whenever the use command is called
    public static void useItem(String item)
    {
        if (item.equalsIgnoreCase("Volunteer badge") && (currentRoom.getName().equalsIgnoreCase("Reception") || currentRoom.getName().equalsIgnoreCase("Security office")) && inventory.hasItem(item) != -1)
        {
            puzzle1Solved = true;
            score.solvePuzzle();
            System.out.println("You used to volunteer badge to unlock the additional rooms.");
            System.out.println("Congratulations, you have solved the first puzzle!");
        }
        else if (item.equalsIgnoreCase("Keycard") && currentRoom.getName().equalsIgnoreCase("Trophy room") && inventory.hasItem(item) != -1)
        {
            puzzle3Solved = true;
            score.solvePuzzle();
            System.out.println("You used the keycard to open the gold medal's display case, making it now able to take.");
            System.out.println("Congratulations, you have solved the third and final puzzle! You're really close now!");
        }
        else if (item.equalsIgnoreCase("Wire cutters") && currentRoom.getName().equalsIgnoreCase("Trophy room") && inventory.hasItem(item) != -1)
        {
            puzzle2Solved = true;
            score.solvePuzzle();
            System.out.println("You used the wire cutters to disable the trophy room's laser alarm's power line. You can now move undetected in the room.");
            System.out.println("Congratulations, you have solved the second puzzle.");
        }
        else 
        {
            System.out.println("Remember, you can only use certain items when you are in certain rooms and only if they are in your inventory! The only valid items you can use are: Volunteer badge, Keycard, Wire cutter, Gold medal.");
        }
    } // end useItem method
    
    // method to be performed once the Gold Medal has been taken and the user has reached the VIP box, and thus the game has been won
    public static void winGame()
    {
        System.out.println("Congratulations, Agent! You have completed the task by taking the Gold Medal and exiting through the VIP Box! We are so proud of you.");
        System.out.println("Your score upon game completion: " + score.getScore());
        playing = false;
    } 
    
} // end Game class