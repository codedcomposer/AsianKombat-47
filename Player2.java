import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.Random;
import java.awt.Color;

// PLEASE READ THE README.TXT IN THE FILE FOR MOVING INSTRUCTIONS
// PLEASE READ THE README.TXT IN THE FILE FOR MOVING INSTRUCTIONS
// PLEASE READ THE README.TXT IN THE FILE FOR MOVING INSTRUCTIONS
/**
 * Write a description of class here.
 * 
 * @author Daniel Zhao
 */
public class Player2 extends Actor
{
    private int moveSpeed = 5;
    private double fallSpeed = 0;
    private int frame = 1;
    private int animationCounter = 0;
    private double acceleration = 0.8;

    private boolean isOnWall = false;
    private boolean isOnGround = false;

    private boolean isPunching = false;
    private boolean isKicking = false;
    private boolean isWalking = false;

    public static int punchTimer = 0;
    private int kickTimer = 0;
    private int shootTimer = 0;

    GreenfootImage p2Punching1 = new GreenfootImage("p2punch01.gif");
    GreenfootImage p2Punching2 = new GreenfootImage("p2punch02.gif");
    GreenfootImage p2Kicking1 = new GreenfootImage("p2kick01.gif");
    GreenfootImage p2Kicking2 = new GreenfootImage("p2kick02.gif");
    GreenfootImage p2Shooting1 = new GreenfootImage("p2shoot01.gif");
    GreenfootImage p2Shooting2 = new GreenfootImage("p2shoot02.gif");
    /**
     * Act - do whatever the Joshua wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        checkKeys();
        fall();
        onGround();
        onWall();

        animationCounter++;
    }    

    public void checkKeys() 
    {
        // Jump
        if (Greenfoot.isKeyDown("up")) { 
            if (isOnGround == true) {  // jumps if character is on the ground
                fallSpeed = -15;
            }
        }

        //Move Left
        if (Greenfoot.isKeyDown("left")) // left
        { 
            setLocation(getX()-moveSpeed, getY()); //moves player 1 x units right
            if (isTouching(Wall.class)) { // stops movement if player touches wall
                setLocation (getX()+moveSpeed, getY());
            }

            if (animationCounter % 4 == 0) {
                animateLeft();

            }
        }    

        //Move Right
        if (Greenfoot.isKeyDown("right"))
        {
            setLocation(getX()+moveSpeed, getY()); //moves player 1 x units right

            if (isTouching(Wall.class)) { // stops movement if player touches wall
                setLocation (getX()-moveSpeed, getY());
            }
            if (animationCounter % 4 == 0) {
                animateRight();
            }
        }    

        //Attempts to Punch
        if (isPunching == false && Greenfoot.isKeyDown(",")) {
                        //activates method punch if player isnt punching and "q" is pressed
            punch();
            setImage(p2Punching1);
            punchTimer++;
        }

        if (isPunching == false && getImage() == p2Punching1 && Greenfoot.isKeyDown(",") && punchTimer == 4)
        {  //displays 2nd animation if timer equals to 4
            setImage(p2Punching2); 
            isPunching = true;
            punchTimer++;
        }

        if (isPunching == true && Greenfoot.isKeyDown(",") == false)
        { //sets display back to normal if "q" isnt being pressed
            setImage("p2walk01.gif");
            isPunching = false;
            punchTimer = punchTimer - 4;
        }

        //Attempts to Kick
        if (isKicking == false && Greenfoot.isKeyDown("."))
        { //activates method punch if player isnt kicking and "q" is pressed
            kick();
            setImage(p2Kicking1);
            kickTimer++;
        }  
        if (isKicking == false && getImage() == p2Kicking1 && Greenfoot.isKeyDown(".") && kickTimer == 6)
        { //displays 2nd animation if timer equals to 8
            setImage(p2Kicking2);
            isKicking = true;
        }
        if (isKicking == true && Greenfoot.isKeyDown(".") == false)
        { //sets display back to normal if "." isnt being pressed
            setImage("p2walk01.gif");
            isKicking = false;
            kickTimer = kickTimer - 6;
        }

        //Attempts to shoot a fireball
        if (Greenfoot.isKeyDown("/"))
        {
            shootTimer ++; // if the shoot timer reaches 30, the player shoots a fireball
            // shoot timer is increased by holding down "/"

            if(shootTimer == 5)
                setImage("p2shoot01.gif");
            if(shootTimer == 10)
                setImage("p2shoot02.gif");
            if(shootTimer == 15)
            { // shoots the fireball 
                setImage("p2shoot03.gif");
                getWorld().addObject(new Fireball2(), getX() - 50, getY());
            }
            if(shootTimer == 20)
                setImage("p2walk01.gif");
        }
        else {
            if (Greenfoot.isKeyDown("f") == false){ // if not pressing f, shoot timer is 0
                shootTimer = 0;
            }
        }
    }

    public void fall() {  // fall speed gradually increases
        setLocation (getX(), (int)(getY() + fallSpeed));
        fallSpeed = fallSpeed + acceleration ; // adds acceleration onto fall speed
    }

    public void punch() //shoots fireball
    {
        Actor player1;
        player1 = getOneIntersectingObject(Player1.class); 
        if (player1 != null) { 
            World1 world1 = (World1)getWorld();

            world1.setPlayer1Health(world1.getPlayer1Health()-10); //minuses 10 health from player 1
            System.out.println(world1.getPlayer1Health());

            Counter2 counter2 = world1.getCounter2();   // decreases player health on screen
            counter2.addScore();
            if (world1.getPlayer1Health () <= 0) {
                world1.player1Died();
            }
        }
    }

    public void kick() 
    {
        Actor player1;
        player1 = getOneIntersectingObject(Player1.class); 
        if (player1 != null) { 
            World1 world1 = (World1)getWorld();

            world1.setPlayer1Health(world1.getPlayer1Health()-10);  //minuses 10 health from player 1
            System.out.println(world1.getPlayer1Health());

            Counter2 counter2 = world1.getCounter2(); // decreases player health on screen
            counter2.addScore();

            if (world1.getPlayer1Health () <= 0) {
                world1.player1Died();
            }
        }
    }

    public boolean onGround() { // stops character from falling if on the ground
        Actor ground;
        ground = getOneObjectAtOffset (0, getImage().getHeight()/2, Ground.class);

        if (ground != null) {
            fallSpeed = 0;
            isOnGround = true;
        } else {
            isOnGround = false;
        }
        return isOnGround;
    }

    public boolean onWall() {
        Actor wall;
        wall = getOneObjectAtOffset (0, getImage().getHeight()/2, Wall.class);

        if (wall != null) { // determines if player is hitting wall
            fallSpeed = 0;
            isOnWall = true;
        } else {
            isOnWall = false;
        }
        return isOnWall;
    }

    public void animateRight() {   // animations for moving right
        if (frame == 1) {
            this.setImage("p2walk01.gif");
        } else if (frame == 2) {
            this.setImage("p2walk02.gif");
        } else if (frame == 3) {
            this.setImage("p2walk03.gif");
        } else if (frame == 4) {
            this.setImage("p2walk04.gif");
        } else if (frame == 5) {
            this.setImage("p2walk05.gif");
            frame = 1;
        }
        frame++;
    }

    public  void animatePlayer2Wins() { //Victory pose animations when player 2 wins
        if (World1.player1DeadOrAlive == "dead") {
            this.setImage("p2win03.gif");
            Greenfoot.delay(100);
            World1.player1DeadOrAlive = "gameover";

        }
    }

    public void animateLeft() {
        if (frame == 1) {
            this.setImage("p2walk05.gif");
        } else if (frame == 2) {
            this.setImage("p2walk04.gif");
        } else if (frame == 3) {
            this.setImage("p2walk03.gif");
        } else if (frame == 4) {
            this.setImage("p2walk02.gif");
        } else if (frame == 5) {
            this.setImage("p2walk01.gif");
            frame = 1;
        }
        frame++;
    }
}
