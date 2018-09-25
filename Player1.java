import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.Random;
import java.awt.*;
import java.awt.Color;

// PLEASE READ THE README.TXT IN THE FILE FOR MOVING INSTRUCTIONS
// PLEASE READ THE README.TXT IN THE FILE FOR MOVING INSTRUCTIONS
// PLEASE READ THE README.TXT IN THE FILE FOR MOVING INSTRUCTIONS
/**
 * Write a description of class Player1 here.
 * 
 * @author Daniel
 */
public class Player1 extends Actor
{
    private int moveSpeed = 5;
    private double fallSpeed = 0;
    private int frame = 1;
    private int animationCounter = 0;
    private double acceleration = 0.8;

    public boolean isOnGround = false;
    public boolean isOnWall = false;

    public static String player1Direction = "";

    private boolean isPunching = false;
    private boolean isKicking = false;
    private boolean isWalking = false;

    public static int punchTimer = 0;
    private int kickTimer = 0;
    private int shootTimer = 0;

    //Loads images into variables for use
    GreenfootImage p1Punching1 = new GreenfootImage("p1punch01.gif");
    GreenfootImage p1Punching2 = new GreenfootImage("p1punch02.gif");
    GreenfootImage p1Kicking1 = new GreenfootImage("p1kick01.gif");
    GreenfootImage p1Kicking2 = new GreenfootImage("p1kick02.gif");
    GreenfootImage p1Shooting1 = new GreenfootImage("p1shoot01.gif");
    GreenfootImage p1Shooting2 = new GreenfootImage("p1shoot02.gif");
    
    public void act() 
    {
        checkKeys();
        fall();
        onGround();
        onWall();
        animatePlayer1Wins();

        animationCounter++;
    }

    //Checks for keys pressed
    private void checkKeys()
    {
        String key = Greenfoot.getKey();

        //Jump 
        if (Greenfoot.isKeyDown("w")) {
            if (isOnGround == true) { // jumps if character is on the ground
                fallSpeed = -15;

            }
        }

        //Move Left
        if (Greenfoot.isKeyDown("a"))
        {
            setLocation(getX()-moveSpeed, getY());  // moves player 1 x units left
            if (isTouching(Wall.class)) {   // stops movement if player touches wall
                setLocation (getX()+moveSpeed, getY());
            }

            if (animationCounter % 4 == 0) { 
                player1Direction = "left";
                animateLeft();

            }
        }    

        //Move Right
        if (Greenfoot.isKeyDown("d"))
        {
            setLocation(getX()+moveSpeed, getY()); //moves player 2 x units right
            if (isTouching(Wall.class)) {   // stops movement if player touches wall
                setLocation (getX()-moveSpeed, getY());
            }
            if (animationCounter % 4 == 0) {
                player1Direction = "right";
                animateRight();
            }
        }    

        //Attempts to Punch
        if (isPunching == false && Greenfoot.isKeyDown("q")) {
            //activates method punch if player isnt punching and "q" is pressed
            punch();
            setImage(p1Punching1);
            punchTimer++;
        }
        if (isPunching == false && getImage() == p1Punching1 && Greenfoot.isKeyDown("q") && punchTimer == 4)
        {  //displays 2nd animation if timer equals to 4
            setImage(p1Punching2);
            isPunching = true;
        }
        if (isPunching == true && Greenfoot.isKeyDown("q") == false)
        {  //sets display back to normal if "q" isnt being pressed
            setImage("p1walk01.gif");
            isPunching = false;
            punchTimer = punchTimer - 4;
        }

        //Attempts to Kick
        if (isKicking == false && Greenfoot.isKeyDown("e"))
        { //activates method punch if player isnt kicking and "q" is pressed
            kick();
            setImage(p1Kicking1);
            kickTimer++;
        }  
        if (isKicking == false && getImage() == p1Kicking1 && Greenfoot.isKeyDown("e") && kickTimer == 6)
        { //displays 2nd animation if timer equals to 6
            setImage(p1Kicking2);
            isKicking = true;
        }
        if (isKicking == true && Greenfoot.isKeyDown("e") == false)
        { //sets display back to normal if "e" isnt being pressed
            setImage("p1walk01.gif");
            isKicking = false;
            kickTimer = kickTimer - 6;
        }

        //Attempts to shoot a fireball
        if (Greenfoot.isKeyDown("f"))
        { // if the shoot timer reaches 30, the player shoots a fireball
            shootTimer ++;  // shoot timer is increased by holding down "f"
            if(shootTimer == 5)
                setImage("p1shoot01.gif");
            if(shootTimer == 10)
                setImage("p1shoot02.gif");
            if(shootTimer == 15)
            { // shoots the fireball 
                setImage("p1shoot03.gif");
                getWorld().addObject(new Fireball1(), getX() + 50, getY());
            }
            if(shootTimer == 20)
                setImage("p1walk01.gif");
        }
        else {
            if (Greenfoot.isKeyDown("f") == false){ // if not pressing f, shoot timer is 0
                shootTimer = 0;
            }
        }
    }

    public void fall() { // fall speed gradually increases
        setLocation (getX(), (int)(getY() + fallSpeed));
        fallSpeed = fallSpeed + acceleration ; // adds acceleration onto fall speed
    }

    public void shoot() { //shoots fireball
        Fireball1 fireball = new Fireball1();
        getWorld().addObject(fireball, getX()+75, getY());
    }

    public void punch() 
    {
        Actor player2;
        player2 = getOneIntersectingObject(Player2.class); 
        if (player2 != null) {
            World1 world1 = (World1)getWorld();

            world1.setPlayer2Health(world1.getPlayer2Health()-10); //minuses 10 health from player 2
            System.out.println(world1.getPlayer2Health());

            Counter1 counter1 = world1.getCounter1();
            counter1.addScore(); // decreases player health on screen

            if (world1.getPlayer2Health () <= 0) {
                world1.player2Died();
            }
        }
    }

    public void kick() 
    {
        Actor player2;
        player2 = getOneIntersectingObject(Player2.class); 
        if (player2 != null) { 
            World1 world1 = (World1)getWorld();

            world1.setPlayer2Health(world1.getPlayer2Health()-10); //minuses 10 health from player 2
            System.out.println(world1.getPlayer2Health());

            Counter1 counter1 = world1.getCounter1();
            counter1.addScore(); // decreases player health on screen

            if (world1.getPlayer2Health () <= 0) {
                world1.player2Died();
            }
        }
    }

    public boolean onGround() {
        Actor ground;
        ground = getOneObjectAtOffset (0, getImage().getHeight()/2, Ground.class);

        if (ground != null) { // stops character from falling if on the ground
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
            isOnWall = true;
        } else {
            isOnWall = false;
        }
        return isOnWall;
    }

    public  void animatePlayer1Wins() { // Victory pose animations when player 1 wins
        if (World1.player2DeadOrAlive == "dead") {
            this.setImage("p1win03.gif");
            Greenfoot.delay(100);
            World1.player2DeadOrAlive = "gameover";
        }
    }

    public void animateRight() { // animations for moving right
        if (frame == 1) { 
            this.setImage("p1walk01.gif");
        } else if (frame == 2) {
            this.setImage("p1walk02.gif");
        } else if (frame == 3) {
            this.setImage("p1walk03.gif");
        } else if (frame == 4) {
            this.setImage("p1walk04.gif");
        } else if (frame == 5) {
            this.setImage("p1walk05.gif");
            frame = 1;
        }
        frame++;
    }

    public void animateLeft() { // animations for moving left
        if (frame == 1) {
            this.setImage("p1walk05.gif");
        } else if (frame == 2) {
            this.setImage("p1walk04.gif");
        } else if (frame == 3) {
            this.setImage("p1walk03.gif");
        } else if (frame == 4) {
            this.setImage("p1walk02.gif");
        } else if (frame == 5) {
            this.setImage("p1walk01.gif");
            frame = 1;
        }
        frame++;
    }
}
