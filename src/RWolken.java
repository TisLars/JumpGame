import greenfoot.*; // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Klasse zet de wolk images in een static array en wordt in de world
 * constructor in een switch aangeroepen. Hierdoor worden Bij elke game random
 * wolken gegenereert.
 */

public class RWolken extends RScrollingObjecten
{
    static GreenfootImage[] wolkImages = { new GreenfootImage("wolk.png"),
            new GreenfootImage("wolk2.png"), new GreenfootImage("wolk3.png") };

    int wolkNummer;

    public void act()
    {
        scrollingNonCollidable(2);
    }

    public RWolken(int wNum)
    {
        wolkNummer = wNum;
        setImage(wolkImages[wNum]);
    }
    
}
