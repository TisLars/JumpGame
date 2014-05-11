import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class RDoubleJump here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class RDoubleJump extends RPowerUps
{
    /**
     * Act - do whatever the RDoubleJump wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
	private boolean _pickedUp = false;

	public void act()
	{
		Actor Player = getOneObjectAtOffset(0, 0, RPlayer.class);
		if (Player != null) {
			Greenfoot.playSound("coin.wav");
			_pickedUp = true;
		}

		if (_pickedUp == false) {
			scrollingNonCollidable(3);
		}
	}
}

