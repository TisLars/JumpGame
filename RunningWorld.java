import greenfoot.*; // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class RunningWorld here.
 * 
 * @author (your name)
 * @version (a version number or a date)
 */
public class RunningWorld extends World
{
    private static final GreenfootImage bgi = new GreenfootImage(
            "background_.png");

    private static final int scrollSpeed = 2;
    private GreenfootImage scrollingImage;
    private int scrollPosition = 0;

    // Snelheid van de gold
    public int _golfSnelheid = -1;
    // Pauze boolean. true = gepauzeerd. false = spelen :)
    public boolean isPaused = false;

    // Actors
    private Actor _player = new RPlayer();
    private Actor _score = new RScore();
    private Actor _pause = new RPause();
    private Actor _golf = new RGolf();

    GreenfootSound backGroundMusic = new GreenfootSound("bgmusic.mp3");
    
    private Actor startScherm;
    private Actor pauzeScherm;
    private Actor startKnop;
    private Actor stopKnop;
    private Actor doorgaanKnop;

    public RunningWorld()
    {
        super(760, 760, 1, false);
        
        Greenfoot.setSpeed(50);
        
        // Achtergrond maken
        GreenfootImage background = new GreenfootImage(7600, 760);
        scrollingImage = getScrollingImage(7600, 760);
        background.drawImage(scrollingImage, 0, 0);
        setBackground(background);
        setPaintOrder(RStartKnop.class, RStopKnop.class, RRetryKnop.class, RDoorgaanKnop.class, RSuccesScherm.class ,RStartScherm.class, RPauseScherm.class, RGameOver.class, RScore.class, RPause.class, RGolf.class,
                RPlayer.class, RFlyEffect.class);

        // Objecten toevoegen
        // addRoofTopsHigh();
        addObject(_golf, -380, getHeight() / 2 + 190);
        addRoofTops();
        addBirds();
        addObject(_player, 150, 580);

        WolkSpawn();
        
        // Knoppen neerzetten
        // addObject(_timer, 100, 60);
        addObject(_score, 75, 25);
        addObject(_pause, 725, 35);
        
        createStartScherm();

    }

    public void act()
    {
        if (!isPaused) {
            if (scrollSpeed > 0 && scrollPosition <= 0) {
                scrollPosition = getWidth();
            }
            scrollPosition -= scrollSpeed;
            paint(scrollPosition);
            backGroundMusic.playLoop();
        } else {
            backGroundMusic.pause();
        }
    }

    private void paint(int position)
    {
        GreenfootImage bg = getBackground();
        bg.drawImage(scrollingImage, position, 0);
        bg.drawImage(scrollingImage, position - scrollingImage.getWidth(), 0);
    }

    private GreenfootImage getScrollingImage(int width, int height)
    {
        GreenfootImage image = new GreenfootImage(width, height);
        for (int x = 0; x < width; x += bgi.getWidth()) {
            for (int y = 0; y < height; y += bgi.getHeight()) {
                image.drawImage(bgi, x, y);
            }
        }
        return image;
    }

    /**
     * Huizen neerzetten
     */
    private void addRoofTops()
    {
        // Plaats sowieso een huis onder de RPlayer
        addObject(new RRoofTop(), 200, 700);
        for (int i = 200; i < 10000; i += 300) {
            if(i < 1500){
                addObject(new RRoofTop(), i + getRandomPlacement(), 700);
            } else{
                int randRooftop = Greenfoot.getRandomNumber(2);
                switch(randRooftop){
                    case 0:
                        addObject(new RRoofTop(), i + getRandomPlacement(), 700);
                        break;
                    case 1:
                        addObject(new RRoofTop2(), i + getRandomPlacement(), 700);
                        break;
                }
                
                addObject(new RFinish(), 10100, 700);
            }
        
        }
    }



    /**
     * Klote vogels.
     */
    private void addBirds()
    {
        for (int i = 300; i < 10000; i += 500) {
            addObject(new RVogel(), i + getRandomXBirds(),
                    getRandomYBirds() + 50);
        }
    }

    private int getRandomXBirds()
    {
        int randomPlace = Greenfoot.getRandomNumber(1000);
        if (randomPlace <= 300 || randomPlace >= 900) {
            return 600;
        } else {
            return randomPlace;
        }
    }

    private int getRandomYBirds()
    {
        int randomPlace = Greenfoot.getRandomNumber(450);
        if (randomPlace <= 200 || randomPlace >= 450) {
            return 325;
        } else {
            return randomPlace;
        }
    }

    /**
     * Plaatst een huis van 250 tot 350 pixels na het vorige
     */
    private int getRandomPlacement()
    {
        int randomPlace = Greenfoot.getRandomNumber(500);
        if (randomPlace <= 250 || randomPlace >= 350) {
            return 305;
        } else {
            return randomPlace;
        }
    }

    /**
     * Wolken neerzetten
     */
    private void WolkSpawn()
    {
        for (int i = 0; i < 10000; i += 500) {
            int randomWolkSpawn = Greenfoot.getRandomNumber(3);
            addObject(new RWolken(randomWolkSpawn), i + getRandomPlacement() + 75, Greenfoot.getRandomNumber(150) + 70);
        }
    }

    private void createStartScherm(){
        
        startScherm = new RStartScherm();
        startKnop = new RStartKnop();
        stopKnop = new RStopKnop();
        
        addObject(startScherm, 387, 371);
        addObject(startKnop, 245, 645);
        addObject(stopKnop, 535, 645);
        
        setPaused(true);
    }
    
    public void removeStartScherm(){
        removeObject(startScherm);
        removeObject(startKnop);
        removeObject(stopKnop);
        
        setPaused(false);
    }
    
    public void createPauseScherm() {
        pauzeScherm = new RPauseScherm();
        stopKnop = new RStopKnop();
        doorgaanKnop = new RDoorgaanKnop(0);
        
        addObject(pauzeScherm, 387, 371);
        addObject(doorgaanKnop, 245, 645);
        addObject(stopKnop, 535, 645);
    }
    
    public void removePauseMenu(){
        removeObject(pauzeScherm);
        removeObject(doorgaanKnop);
        removeObject(stopKnop);
    }
    
    
    /**
     * Verkrijgt of de game gepauzeerd is of niet
     */
    public boolean getPaused()
    {
        return isPaused;
    }

    /**
     * true = game op pauze zetten false = game door laten gaan
     */
    public void setPaused(boolean ToF)
    {
        isPaused = ToF;
        return;
    }

    /**
     * Golfsnelheid verkrijgen en setten.
     */
    public int getGolfSnelheid()
    {
        return _golfSnelheid;
    }

    public void setGolfSnelheid(int snelheid)
    {
        _golfSnelheid = snelheid;
    }
}