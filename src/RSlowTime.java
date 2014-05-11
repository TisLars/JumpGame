import greenfoot.*; // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Deze klasse kijkt of de powerup opgepakt is door de speler.
 * Zo nee, dan blijft deze scrollen en bestaan.
 */
public class RSlowTime extends RPowerUps
{
    private boolean _pickedUp = false;

    public void act()
    {
        Actor Player = getOneObjectAtOffset(0, 0, RPlayer.class);
        if (Player != null) {
            _pickedUp = true;
        }

        if (_pickedUp == false) {
            scrollingNonCollidable(3);
        }
    }
}
