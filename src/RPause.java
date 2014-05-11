import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Roept de pauzefunctie in de World aan als er op dit object wordt geklikt.
 */
public class RPause extends RScrollingObjecten
{
    public void act() 
    {
        if(Greenfoot.mouseClicked(this))
        {  
            RunningWorld world = (RunningWorld) getWorld();
            world.createPauseScherm();
            //Check of het spel op pauze staat
            if(world.getPaused()){
                //Zo ja, unpause
                world.setPaused(false);
                //world.removePauseMenu();
            }else{
                //Zo nee, pause.
                world.setPaused(true);
                //world.createPauseMenu();
            }
        }  
        scrollingNonCollidable(0);
    }    
}
