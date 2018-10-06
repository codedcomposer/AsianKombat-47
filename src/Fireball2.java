import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class Fireball2 extends Actor
{
    private String collided="no";
    /**
     * Act - do whatever the Fireball2 wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        move(-8);
        atWorldEdge();
        if(collided!="yes"){
            collisionPlayer1();
        }
    }

    public void collisionPlayer1()
    {
        Actor player1;
        player1 = getOneIntersectingObject(Player1.class);
        if (player1 !=null) {
            World world;
            world = getWorld();

            World1 world1 = (World1)getWorld();
            world1.setPlayer1Health(world1.getPlayer1Health()-50);
            System.out.println(world1.getPlayer1Health());

            Counter2 counter2 = world1.getCounter2();
            counter2.fireballDamage();

            world.removeObject(this);

        }
    }

    public void atWorldEdge() 
    {
        if (getX() <= 20 || getX() >= getWorld().getWidth() - 20) {
            collided = "yes";
            getWorld().removeObject(this);
        }
    }
}

