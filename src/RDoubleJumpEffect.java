import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Creert het effect van DoublEjump powerups. 
 * 
 * @author TECHSETTERS
 */
public class RDoubleJumpEffect extends RPowerUpEffects
{
    public RDoubleJumpEffect()
    {
        effect = getImage();
        fade = Greenfoot.getRandomNumber(3) + 1;
    }

    public void act()
    {
        removeDoubleJump();
    }

    private void removeDoubleJump()
    {
        if (getImage().getHeight() < 20) {
            getWorld().removeObject(this);
        } else {
            GreenfootImage img = new GreenfootImage(effect);
            if (getImage().getHeight() > 2 && getImage().getTransparency() > 20) {
                img.scale(getImage().getWidth() - 1, getImage().getHeight() - 1);
                img.setTransparency(getImage().getTransparency() - (fade * 2));
            } else {
                img.setTransparency(getImage().getTransparency() - (fade * 8));
            }

            setImage(img);
        }
    }
}
