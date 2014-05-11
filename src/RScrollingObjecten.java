import greenfoot.*; // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)


public class RScrollingObjecten extends Actor
/*
 * Klasse die aangeroepen wordt door elk object in de game dat scrollt als het spel actief is.
 */
{
    public void scrolling(int snelheid)
    {
        RunningWorld theWorld = (RunningWorld) getWorld();
        if(!theWorld.isPaused)  {
            setLocation(getX() - snelheid, getY());
            Actor actor = getOneObjectAtOffset(0, 0, null);

            if (actor != null) {
                actor.setLocation(actor.getX() - snelheid, actor.getY());
            }
        }
    }

    public void scrollingNonCollidable(int snelheid)
    {
        RunningWorld theWorld = (RunningWorld) getWorld();
        if(!theWorld.isPaused)  {
            setLocation(getX() - snelheid, getY());
            getOneObjectAtOffset(0, 0, null);
        }
    }
}
