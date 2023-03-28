import processing.core.PApplet;

import java.io.File;
import java.util.ArrayList;
import java.util.Random;

public class DancingBadgers extends PApplet {

    //array storing badgers dance show steps
    private static DanceStep[] badgersDanceSteps = new DanceStep[]{DanceStep.LEFT,
            DanceStep.RIGHT, DanceStep.RIGHT, DanceStep.LEFT, DanceStep.DOWN, DanceStep.LEFT,
            DanceStep.RIGHT, DanceStep.RIGHT, DanceStep.LEFT, DanceStep.UP};

    // array storing the positions of the dancing badgers at the start of the dance show
    private static float[][] startDancePositions = new float[][]{{300, 250}, {364, 250}, {428,
            250}, {492, 250}, {556, 250}};

    //background image
    private static processing.core.PImage backgroundImage;
    // max number of badger objects allowed in the basketball court
    private static int badgersCountMax;
    //tells whether the dance show is on. Default false
    private boolean danceShowOn;
    //generator of random numbers
    private static Random randGen;
    //arraylist storing Thing objects
    private static ArrayList<Thing> things;


    /**
     * Sets the size of the display window of this graphic application
     * <p>
     * Overrides settings in class processing.core.PApplet
     */
    @Override
    public void settings() {
        this.size(800, 600);
    }

    /**
     * Sets the title and defines the initial environment properties of this graphic application.
     * This method initializes all the data fields defined in this class.
     * <p>
     * Overrides setup in class processing.core.PApplet
     */
    @Override
    public void setup() {
        Thing.setProcessing(this);
        this.getSurface().setTitle("P5 Dancing Badgers");// displays the title of the screen
        this.textAlign(3, 3); // sets the alignment of the text
        this.imageMode(3); // interprets the x and y position of an image to its center
        this.focused = true; // confirms that this screen is "focused", meaning
        //it is active and will accept mouse and keyboard input
        this.randGen = new Random();
        this.badgersCountMax = 5;
        this.backgroundImage = loadImage("images" + File.separator + "background.png");

        things = new ArrayList<Thing>();
        things.add(new Thing(50, 50, "target.png"));
        things.add(new Thing(750, 550, "target.png"));
        things.add(new Thing(750, 50, "shoppingCounter.png"));
        things.add(new Thing(50, 550, "shoppingCounter.png"));

        things.add(new StarshipRobot(things.get(2), things.get(0), 3));
        things.add(new StarshipRobot(things.get(3), things.get(1), 5));

        Thing.setProcessing(Badger.processing);

        things.add(new Basketball(50, 300));
        things.add(new Basketball(750, 300));

    }

    /**
     * Callback method that draws and updates the application display window. This method runs in an
     * infinite loop until the program exits.
     * <p>
     * Overrides draw in class processing.core.PApplet
     */
    @Override
    public void draw() {
        background(color(255, 218, 185));
        image(backgroundImage, width / 2, height / 2);


        for (int i = 0; i < things.size(); i++)
            things.get(i).draw();
    }

    /**
     * Callback method called each time the user presses the mouse. This method iterates through the
     * list of things. If the mouse is over a Clickable object, it calls its mousePressed method,
     * then returns.
     * <p>
     * Overrides mousePressed in class processing.core.PApplet
     */
    @Override
    public void mousePressed() {
        for (int i = 0; i < things.size(); i++)
            if (things.get(i) instanceof Clickable && things.get(i).isMouseOver()) {
                ((Clickable) things.get(i)).mousePressed();
                break;
            }
    }

    /**
     * Callback method called each time the mouse is released. This method calls the mouseReleased()
     * method on every Clickable object stored in the things list.
     * <p>
     * Overrides mouseReleased in class processing.core.PApplet
     */
    @Override
    public void mouseReleased() {
        for (int i = 0; i < things.size(); i++) {
            if (things.get(i) instanceof Clickable) {
                ((Clickable) things.get(i)).mouseReleased();
            }
        }
    }

    /**
     * Gets the number of Badger objects present in the basketball arena
     *
     * @return the number of Badger objects present in the basketball arena
     */
    public int badgersCount() {
        int badgerCount = 0;
        for (int i = 0; i < things.size(); i++) {
            if (things.get(i) instanceof Badger) {
                ++badgerCount;
            }
        }
        return badgerCount;
    }

    private void setStartDancePositions() {
        int badgerPosition = 0;
        for (int i = 0; i < things.size(); i++) {
            if (things.get(i) instanceof Badger) {
                things.get(i).x = startDancePositions[badgerPosition][0];
                things.get(i).y = startDancePositions[badgerPosition][1];
                ++badgerPosition;
            }
        }
    }
@Override
    public void keyPressed() {
        switch (Character.toUpperCase(key)) {
            case 'B': // add new badgers as long as the maximum numbers of badgers allowed to be
                // present in the field is not reached
                if (!danceShowOn && badgersCount() < badgersCountMax) {
                    //System.out.println("making badger");
                    things.add(new Badger(randGen.nextInt(width), randGen.nextInt(height),
                            badgersDanceSteps));
                }
                break;
            case 'R': // delete the badger being pressed
                if (!danceShowOn) {
                    for (int i = 0; i < things.size(); i++) {
                        if (things.get(i).isMouseOver() && things.get(i) instanceof Badger) {
                            things.remove(i);
                            break;
                        }
                    }
                }
                break;
            case 'C': // stop badgers from dancing and remove all moving things
                danceShowOn = false;
                for (int i = things.size() - 1; i > 0; i--) {
                    if (things.get(i) instanceof MovingThing || things.get(i) instanceof Badger) {
                        // TODO have this remove Badgers as well
                        things.remove(i);
                    }
                }
                break;
            case 'D': // start the dance show
                if (!danceShowOn) {
                    danceShowOn = true;
                    setStartDancePositions();
                    for (int i = 0; i < things.size(); i++) {
                        if (things.get(i) instanceof Badger) {
                            ((Badger) things.get(i)).startDancing();
                        }
                        // TODO nothing happens when pressed
                    }
                }
                break;
            case 'S': // stop the dance show
                for (int i = 0; i < things.size(); i++) {
                    if (things.get(i) instanceof Badger) {
                        ((Badger) things.get(i)).stopDancing();
                    }

                }
                break;
        }
    }

    /**
     * Driver method to run this graphic application
     *
     * @param args list of input arguments if any
     */
    public static void main(String[] args) {
        PApplet.main("DancingBadgers");
    }

}