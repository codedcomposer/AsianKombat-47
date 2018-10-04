import greenfoot.*;
import java.awt.Color;

public class World1 extends World
{
    public static String player2DeadOrAlive = "alive";
    public static String player1DeadOrAlive = "alive";
    
    private int player1Health = 500;
    private int player2Health = 500;

    Counter1 counter1 = new Counter1(); // adds counters for player health
    Counter2 counter2 = new Counter2();
    
    /**
     * Constructor for objects of class World1.
     * 
     */
    public World1()
    {    
        super(1000, 550, 1); // resolution is 1000 x 550
        prepare();
    }

    public Counter1 getCounter1() { //accessing counter 1
        return counter1;
    }

    public Counter2 getCounter2() {  //accessing counter 2
        return counter2;
    }

    public int getPlayer1Health () { //player 1 health accessor
        return player1Health;
    }

    public int getPlayer2Health () { //player 2 health accessor
        return player2Health;
    }

    public void setPlayer1Health (int i) { //player 1 health mutator
        player1Health = i;                 //sets player health to whatever i is 
        if (player1Health <= 0) {
            player1Died();
        }
    }

    public void setPlayer2Health (int j) { //player 1 health mutator
        player2Health = j;                  //sets player health to whatever i is 
        if (player2Health <= 0) {
            player2Died();
        }
    }

    public void player1Died() {
        System.out.println("Player1 has been Slain");
        player1DeadOrAlive = "dead";
        removeObjects(getObjects(Player1.class));  //remove player 1 from the world
        Player2Wins player2wins = new Player2Wins(); 
        addObject(player2wins, 450, 300);  // adds player 2 wins icon
    }

    public void player2Died() {
        System.out.println("Player2 has been Slain");
        player2DeadOrAlive = "dead";
        removeObjects(getObjects(Player2.class));  //remove player 1 from the world
        Player1Wins player1wins = new Player1Wins();
        addObject(player1wins, 450, 300);   // adds player 1 wins icon
    }

    /**
     * Prepare the world for the start of the program. That is: create the initial
     * objects and add them to the world.
     */
    private void prepare()
    {
        addObject (counter2, 150, 115);
        addObject (counter1, 850, 115);

        Title title = new Title();
        addObject(title, 499, 47);
        title.setLocation(501, 39);
        Ground ground = new Ground();
        addObject(ground, 57, 506);
        ground.setLocation(49, 499);
        Ground ground2 = new Ground();
        addObject(ground2, 158, 506);
        ground2.setLocation(149, 499);
        Ground ground3 = new Ground();
        addObject(ground3, 256, 506);
        ground3.setLocation(249, 499);
        Ground ground4 = new Ground();
        addObject(ground4, 359, 505);
        ground4.setLocation(349, 499);
        Ground ground5 = new Ground();
        addObject(ground5, 456, 504);
        ground5.setLocation(449, 499);
        Ground ground6 = new Ground();
        addObject(ground6, 954, 508);
        ground6.setLocation(950, 499);
        Ground ground7 = new Ground();
        addObject(ground7, 850, 504);
        ground7.setLocation(850, 499);
        Ground ground8 = new Ground();
        addObject(ground8, 749, 497);
        ground8.setLocation(750, 499);
        Ground ground9 = new Ground();
        addObject(ground9, 649, 500);
        ground9.setLocation(650, 499);
        Ground ground10 = new Ground();
        addObject(ground10, 556, 509);
        ground10.setLocation(550, 499);
        ground10.setLocation(550, 499);
        Player1 player1 = new Player1();
        addObject(player1, 872, 372);
        Player2 player2 = new Player2();
        addObject(player2, 760, 336);
        player1.setLocation(100, 396);
        player2.setLocation(886, 389);
        player1.setLocation(104, 380);
        player2.setLocation(885, 379);
        player2.setLocation(744, 375);
        player2.setLocation(744, 371);
        player1.setLocation(260, 369);
        Wall wall = new Wall();
        addObject(wall, 113, 421);
        wall.setLocation(32, 416);
        Wall wall2 = new Wall();
        addObject(wall2, 23, 353);
        wall2.setLocation(31, 352);
        Wall wall3 = new Wall();
        addObject(wall3, 32, 294);
        wall3.setLocation(31, 288);
        Wall wall4 = new Wall();
        addObject(wall4, 26, 221);
        wall4.setLocation(31, 224);
        Wall wall5 = new Wall();
        addObject(wall5, 972, 415);
        wall5.setLocation(968, 416);
        Wall wall6 = new Wall();
        addObject(wall6, 907, 340);
        wall6.setLocation(968, 352);
        Wall wall7 = new Wall();
        addObject(wall7, 970, 279);
        wall7.setLocation(968, 288);
        Wall wall8 = new Wall();
        addObject(wall8, 957, 215);
        wall8.setLocation(968, 224);
    }
}
