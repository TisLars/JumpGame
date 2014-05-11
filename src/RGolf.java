

/**
 * Zet de snelheid van de golf
 */
public class RGolf extends RScrollingObjecten
{
    
    private int _snelheid = -1;

    public void act() 
    {
        getSpeed();
        scrollingNonCollidable(_snelheid);
    }    
    
    public void getSpeed(){
        RunningWorld theWorld = (RunningWorld) getWorld();
        if(theWorld.getGolfSnelheid() != -1){
            
            if (this.getX() <= -200) {
                _snelheid = -1;
                theWorld.setGolfSnelheid(-1);
            }
            else {
                _snelheid = 2;
            }
        }
    }
}
