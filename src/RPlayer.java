import greenfoot.*; // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Player here.
 * 
 * @author (your name)
 * @version (a version number or a date)
 */
public class RPlayer extends RScrollingObjecten
{
    private int mSpeed = 5;
    private int fSpeed = 0;
    private int acceleration = 1;
    private int jumpStr = -20;

    private int timerLeft = 0;
    private int timerRight = 0;

    private boolean jumped = false;
    private boolean pickUp;
    private boolean flyActive;
    private boolean slowActive;

    private int flyTimer;

    // public int useEffect;

    private boolean canFly = false;
    private boolean canDoubleJump = false;
    private boolean hasSlowTime = false;

    private Actor rodeKruis;
    private Actor geleKlok;
    private Actor doubleJump;
    private Actor bird;
    private Actor golf;

    private GreenfootSound jumpSound = new GreenfootSound("jump.mp3");

    public RPlayer()
    {
        GreenfootImage image = getImage();
        image.scale(image.getWidth(), image.getHeight());
        setImage("player_right0.png");
    }

    /**
     * In de act worden de methodes uit de klasse uitgevoerd (als de game niet op pauze staat) om de state van de RPlayer te bepalen of te veranderen.
     */
    
    public void act()
    {
        RunningWorld theWorld = (RunningWorld) getWorld();
        if (!theWorld.isPaused) {
            checkPowerUp();
            checkInput();
            checkFall();
            GameOver();
            scrollingNonCollidable(3);
            checkBorderCollision();
        }
    }

    public void checkBorderCollision()
    {
        if (this.getX() <= 0) {
            scrolling(-3);
        }
        if (this.getX() >= 760) {
            scrolling(0);
        }
    }

    /**
     * checkt per powerup of deze wordt opgepakt.
     */
    public void checkPowerUp()
    {
        rodeKruis = getOneIntersectingObject(RRodeKruis.class);
        doubleJump = getOneIntersectingObject(RDoubleJump.class);
        geleKlok = getOneIntersectingObject(RSlowTime.class);

        RunningWorld theWorld = (RunningWorld) getWorld();

        if (rodeKruis != null) {
            pickUp = true;
            theWorld.removeObject(rodeKruis);
            // Variabele wordt true als het object is opgepakt & object
            // verwijderd
            canFly = true;
            theWorld.setGolfSnelheid(1);
        }
        if (doubleJump != null) {
            pickUp = true;
            // Variabele wordt true als het object is opgepakt & object
            // verwijderd
            theWorld.removeObject(doubleJump);
            canDoubleJump = true;
            theWorld.setGolfSnelheid(1);
        }
        if (geleKlok != null) {
            pickUp = true;
            // Variabele wordt true als het object is opgepakt & object
            // verwijderd
            theWorld.removeObject(geleKlok);
            hasSlowTime = true;
            theWorld.setGolfSnelheid(1);
        }

        if (pickUp == true && canFly == true) {
            if (flyActive == false && pickUp == true && canFly == true) {
                // Zet de flytimer variabele naar 100 voor ~3 seconde vliegtijd
                // Zet de flyActive variabele naar true om de timer te laten
                // lopen
                setFlyTimer(100);
                flyActive = true;
            }
            if (flyActive == true && getFlyTimer() > 0) {
                // Zo lang de flyTimer groter is dan 0, blijft deze aflopen
                setFlyTimer(getFlyTimer() - 1);
                if (getFlyTimer() <= 0) {
                    // Als de flyTimer op 0 komt, wordt de powerup gedeactiveerd
                    flyActive = false;
                    pickUp = false;
                    canFly = false;
                }
            }
        }

        if (pickUp == true && hasSlowTime == true) {
            if (slowActive == false && pickUp == true && hasSlowTime == true) {
                slowActive = true;
            }
        }
    }

    /**
     * De checkInput() methode beheert de controls van de speler en start de animatie van de speler.
     */
    public void checkInput()
    {
        //Naar links lopen
        if (Greenfoot.isKeyDown("left") || Greenfoot.isKeyDown("a")) {
            timerLeft++;
            timerRight = 0;
            if (timerLeft < 5) {
                setImage("player_left0.png");
            }
            if (timerLeft == 10) {
                setImage("player_left1.png");
            }
            if (timerLeft == 15) {
                setImage("player_left2.png");
                timerLeft = 0;
            }

            moveLeft();
        }
        
        //Naar rechts lopen
        if (Greenfoot.isKeyDown("right") || Greenfoot.isKeyDown("d")) {
            timerRight++;
            timerLeft = 0;
            if (timerRight < 5) {
                setImage("player_right0.png");
            }
            if (timerRight == 10) {
                setImage("player_right1.png");
            }
            if (timerRight == 15) {
                setImage("player_right2.png");
                timerRight = 0;
            }
            moveRight();
        }
        
        //Springen (kan niet springen als er al gesprongen is maar nog niet geland)
        if (Greenfoot.isKeyDown("w") && jumped == false
                || Greenfoot.isKeyDown("up") && jumped == false) {
            jumpSound.setVolume(50);
            jumpSound.play();
            jump();
        }
        
        //Het gebruik van de powerup (altijd de spatiebalk als input van de speler).
        if (Greenfoot.isKeyDown("space") && pickUp == true) {
            if (flyActive == true && canFly == true) {
                showFlyEffect();
                moveUp();
            }
            if (canDoubleJump == true) {
                jumped = false;
                showDoubleJumpEffect();
                jumpSound.setVolume(50);
                jumpSound.play();
                jump();
                canDoubleJump = false;
                pickUp = false;
            }

        }
    }

    /**
     * moveLeft(), moveRight(), moveUp() en jump() zijn de methodes die worden aangeroepen om het karakter echt te laten bewegen.
     * Deze worden aangeroepen in de checkInput()
     */
    public void moveLeft()
    {
        Actor building = getOneObjectAtOffset(-25, 0, RRoofTop.class);
        Actor building2 = getOneObjectAtOffset(-25, 0, RRoofTop2.class);
        if (building == null && building2 == null) {
            setLocation(getX() - mSpeed, getY());
        }
    }

    public void moveRight()
    {
        Actor building = getOneObjectAtOffset(25, 0, RRoofTop.class);
        Actor building2 = getOneObjectAtOffset(25, 0, RRoofTop2.class);
        if (building == null && building2 == null) {
            setLocation(getX() + mSpeed, getY());
        }
    }

    public void moveUp()
    {
        setLocation(getX(), getY() - 3);
        fSpeed = 0;
    }

    public void jump()
    {
        setImage("player_jump.png");
        jumped = true;
        fSpeed = jumpStr;
        timerLeft = 0;
        timerRight = 0;
        fall();
    }

    /**
     * De volgende methodes zijn de checks van de states van de RPlayer die uitgevoerd worden in de act.
     */
    
    public void checkFall()
    {
        if (onBuilding() || onBuildingHigh()) {
            jumped = false;
            fSpeed = 0;
        } else if (hasSlowTime == true) {
            slowFall();
            showSlowTimeEffect();
        } else {
            fall();
        }
    }

    public void fall()
    {
        fSpeed += acceleration; // Elke tick zal je sneller vallen (fSpeed + 1)
        int dir = (int) Math.signum(fSpeed); // Wanneer 'int dir' afwijkt van 1,
        // wordt deze terug gezet naar 1
        for (int tick = 0; tick != fSpeed; tick += dir) {
            setLocation(getX(), getY() + dir);
            if (getOneIntersectingObject(RRoofTop.class) != null || getOneIntersectingObject(RRoofTop2.class) != null) {
                jumped = false;
                setLocation(getX(), getY() - dir);
                fSpeed = 0;
                break;
            }
        }
    }

    public void slowFall()
    {
        if (getOneObjectAtOffset(0, 20, RRoofTop.class) != null || getOneObjectAtOffset(0, 20, RRoofTop2.class) != null) {
            hasSlowTime = false;
            slowActive = false;
            fSpeed = 0;
            pickUp = false;
        } else {
            fSpeed = 1;
            setLocation(getX(), getY() + fSpeed);
        }
    }

    /**
     * De Effect methodes worden aangeroepen nadat de RPlayer een power-up oppakt.
     * Een Effect methode maakt een nieuw effect object aan en plaatst deze in de wereld
     */
    public void showFlyEffect()
    {
        // useEffect = 1;
        getWorld().addObject(new RFlyEffect(), getX(), getY());
    }

    public void showDoubleJumpEffect()
    {
        // useEffect = 1;
        getWorld().addObject(new RDoubleJumpEffect(), getX(), getY());
    }

    public void showSlowTimeEffect()
    {
        // useEffect = 1;
        getWorld().addObject(new RSlowTimeEffect(), getX(), getY() - 25);
    }

    /**
     * Methode om de voorwaardes van game over te bepalen. 
     * Bij game over wordt de game altijd gepauzeerd.
     */
    public void GameOver()
    {
        //Als speler van gebouw af valt.
        RunningWorld theWorld = (RunningWorld) getWorld();
        if (this.getY() >= 755) {
            this.setImage("player_dead.png");
            theWorld.setPaused(true);
            createGameOver();
        }
        //Als speler tegen een vogel aan komt.
        bird = getOneObjectAtOffset(0, 0, RVogel.class);
        if (bird != null) {
            this.setImage("player_dead.png");
            theWorld.setPaused(true);
            createGameOver();
        }
        //Als speler te ver in de golf komt.
        golf = getOneObjectAtOffset(225, 0, RGolf.class);
        if (golf != null) {
            this.setImage("player_dead.png");
            theWorld.setPaused(true); 
            createGameOver();
        }
        //Als de speler op de finish komt -> successcherm
        Actor finish = getOneIntersectingObject(RFinish.class);
        if(finish != null){
            theWorld.setPaused(true);
            createSuccesScherm();
        }

    }

    /**
     * Creeren van het game over en succes scherm.
     */
    public void createGameOver(){
        RunningWorld theWorld = (RunningWorld) getWorld();
        theWorld.addObject(new RGameOver(), theWorld.getWidth() / 2,
                theWorld.getHeight() / 2);
        theWorld.addObject(new RRetryKnop(), 245, 645);
        theWorld.addObject(new RStopKnop(), 535, 645);
    }
    
        public void createSuccesScherm(){
        RunningWorld theWorld = (RunningWorld) getWorld();
        theWorld.addObject(new RSuccesScherm(), theWorld.getWidth() / 2,
                theWorld.getHeight() / 2);
        theWorld.addObject(new RDoorgaanKnop(1), 370, 645);
    }
    
    /**
     * Getters and Setters
     */
    public boolean onBuilding()
    {
        Actor under = getOneIntersectingObject(RRoofTop.class);
        return under != null;
    }

    public boolean onBuildingHigh()
    {
//         Actor under = getOneIntersectingObject(RRoofTop2.class);
        Actor under = getOneObjectAtOffset(0, 5, RRoofTop2.class);
        return under != null;
    }

    public int getFlyTimer()
    {
        return flyTimer;
    }

    public void setFlyTimer(int flyTimer)
    {
        this.flyTimer = flyTimer;
    }
}
