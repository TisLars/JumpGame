import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class RRoofTop2 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class RRoofTop2 extends RScrollingObjecten
{

    private boolean _acted = false;  
    public void act() 

    {
        if(_acted == false) {
            placePowerUps();
            placeDoubleJump();
            _acted = true;

        }
        scrollingNonCollidable(3);
    }
// 
    /**
     * Plaatst powerups boven de gebouwen, maar niet ernaast.
     */
    private void placePowerUps() {

        int randomPlacement = Greenfoot.getRandomNumber(3);
        int randomPlaceOne = Greenfoot.getRandomNumber(100);
        int randomPlaceTwo = Greenfoot.getRandomNumber(50);
        if (randomPlacement == 1) {
            //Random powerup switch
            getWorld().addObject(new RRodeKruis(), this.getX() + (randomPlaceTwo - randomPlaceOne), this.getY() - 235);
        }
    }

    private void placeDoubleJump() {
        int randomPlacementDoubleJump = Greenfoot.getRandomNumber(3);
        int randomPlaceOne = Greenfoot.getRandomNumber(100);
        int randomPlaceTwo = Greenfoot.getRandomNumber(50);
        if (randomPlacementDoubleJump == 1) {
            getWorld().addObject(new RDoubleJump(), this.getX() + (randomPlaceTwo - randomPlaceOne), this.getY() - (350 + randomPlaceOne));

        }
    }
}
