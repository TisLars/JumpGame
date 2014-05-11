import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class RVogel here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class RVogel extends RScrollingObjecten
{
    /**
     * Act - do whatever the RVogel wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public RVogel()
    {
        int random = Greenfoot.getRandomNumber(100);
        if (random <= 2)
        {
            setImage("unicorn.png");
        }
    }
    public void act() 
    {
        scrollingNonCollidable(6);
    }

}    

