import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Creert het effect van SlowTime powerups. 
 * 
 * @author TECHSETTERS
 */
public class RSlowTimeEffect extends RPowerUpEffects
{
    public RSlowTimeEffect()
    {
        effect = getImage();
        fade = Greenfoot.getRandomNumber(3) + 1;
    }

    public void act()
    {
        removeSlowTime();
    }

    private void removeSlowTime()
    {
        if (getImage().getTransparency() < 20) {
            getWorld().removeObject(this);
        } else {
            GreenfootImage img = new GreenfootImage(effect);
            if (getImage().getTransparency() < 20) {
                //img.scale(getImage().getWidth() - 1, getImage().getHeight() - 1);
                img.setTransparency(getImage().getTransparency() - (fade * 2));
            } 
            else {
                img.setTransparency(getImage().getTransparency() - (fade * 8));
            }
            setImage(img);
        }
    }
}
