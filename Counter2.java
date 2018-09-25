import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

import java.awt.Color;

/**
 * Write a description of class Counter1 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Counter2 extends Actor
{
    int score = 500;
    public void act() {
        setImage (new GreenfootImage("Player 1 Health : " + score, 24, Color.WHITE, Color.BLACK));

    }

    public void addScore() {
        score = score - 10;

    }
        public void fireballDamage () {
        score= score -50;
    }
}