import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class RStartKnop here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class RStartKnop extends Actor
{
    /**
     * Startknop voor de Wie niet Springt-game.
     */
    public void act() 
    {
        if (Greenfoot.mouseClicked(this))
        {
            RunningWorld world = (RunningWorld) getWorld();
            world.setPaused(false);
            world.removeStartScherm();
        }
    }    
}
