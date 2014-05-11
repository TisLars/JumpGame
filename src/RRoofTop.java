import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Klasse daken. Methodes om de power-ups alleen op de daken te plaatsen.
 */
public class RRoofTop extends RScrollingObjecten
{
    private boolean _acted = false;  
    public void act() 

    {
        if(_acted == false) {
            placePowerUps();
            _acted = true;

        }
        scrollingNonCollidable(3);
    }

    /**
     * Plaatst powerups random op/boven de daken maar niet ernaast.
     */
    public void placePowerUps() {

        int randomPlacement = Greenfoot.getRandomNumber(5);
        int randomPlaceOne = Greenfoot.getRandomNumber(100);
        int randomPlaceTwo = Greenfoot.getRandomNumber(50);

        if (randomPlacement == 1) {
            //Random powerup switch
            getWorld().addObject(new RRodeKruis(), this.getX() + (randomPlaceTwo - randomPlaceOne), this.getY() - 115);
        }
        else if (randomPlacement == 2) {
            getWorld().addObject(new RDoubleJump(), this.getX() + (randomPlaceTwo - randomPlaceOne), this.getY() - (200 + randomPlaceOne));
        }
        else if (randomPlacement == 3) {
            getWorld().addObject(new RSlowTime(), this.getX() + (randomPlaceTwo - randomPlaceOne), this.getY() - 350 + randomPlaceTwo);
        }
    }
}
