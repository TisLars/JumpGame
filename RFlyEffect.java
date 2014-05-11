import greenfoot.*;
/**
 * Creert het effect van Flying powerups. 
 * 
 * @author TECHSETTERS
 */

public class RFlyEffect extends RPowerUpEffects
{
	public RFlyEffect()
	{
		effect = getImage();
		fade = Greenfoot.getRandomNumber(3) + 1;
	}

	public void act()
	{
		removeFly();
	}

	private void removeFly()
	{
		if (getImage().getHeight() < 3) {
			getWorld().removeObject(this);
		} else {
			GreenfootImage img = new GreenfootImage(effect);
			if (getImage().getHeight() > 1 && getImage().getTransparency() > 20) {
				img.scale(getImage().getWidth(), getImage().getHeight() - 1);
				img.setTransparency(getImage().getTransparency() - (fade * 2));
			} else {
				img.setTransparency(getImage().getTransparency() - (fade * 8));
			}

			setImage(img);
		}
	}
}
