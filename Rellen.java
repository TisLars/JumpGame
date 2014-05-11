
/**
 * Write a description of class brand here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Rellen extends Calimiteiten
{ 
    public Rellen(boolean fake) {
        super();
    	_IsFake = fake;
        
        if (!_IsFake)
        {
        	_Fakes = new Rellen[3];
        	_Fakes[0] = new Rellen(true);
        	_Fakes[1] = new Rellen(true);
        	_Fakes[2] = new Rellen(true);
        }
    }
    
    public void act() {	
    	super.act();
    }
}
