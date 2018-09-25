import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Fireball here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Fireball1 extends Actor
{
    private String collided= "no";

    /**
     * Act - do whatever the Fireball2 wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        move(8); // moves at 8 pixels speed
        atWorldEdge();
        if(collided!="yes"){
            collisionPlayer2();
        }
    }

    public void collisionPlayer2()
    {
        Actor player2;
        player2 = getOneIntersectingObject(Player2.class);
        if (player2 !=null) {
            World world;
            world = getWorld();

            World1 world1 = (World1)getWorld();
            world1.setPlayer2Health(world1.getPlayer2Health()-50); // decreases player health by 50
            System.out.println(world1.getPlayer2Health());

            Counter1 counter1 = world1.getCounter1();
            counter1.fireballDamage (); // decreases player health by damage 

            world.removeObject(this);
        }
    }

    public void atWorldEdge() { // determines if fireball hits the edge of the world
        if (getX() <= 20 || getX() >= getWorld().getWidth() - 20) {
            collided = "yes";
            getWorld().removeObject(this);
        }
    }
}
