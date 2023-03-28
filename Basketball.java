import processing.core.PApplet;

public class Basketball extends Thing implements Clickable {
    private int rotations; // total number of rotations this basketball object has made
    public float rotation; // defines the rotation angle in radians that this basketball object
    // make when clicked

    /**
     * Creates a new Basketball object located at (x,y) position with 0 initial rotations
     *
     * @param x x-position of this Basketball object in the display window
     * @param y y-position of this Basketball object in the display window
     */
    public Basketball(float x, float y) {
        super(x, y, "basketball.png");
        this.rotation = PApplet.PI / 2;
        this.rotations = 0;

    }

    /**
     * Draws this rotating Basketball object to the display window.
     */
    @Override
    public void draw() {
        processing.pushMatrix();
        processing.translate(x, y);
        processing.rotate(this.rotation * rotations);
        processing.image(image(), 0.0f, 0.0f);
        processing.popMatrix();
    }

    /**
     * Defines the behavior of this basketball when the mouse is pressed.
     */
    @Override
    public void mousePressed() {
        if (isMouseOver()) {
            rotate();
        }
    }

    /**
     * Called when the mouse is released
     */
    @Override
    public void mouseReleased() {
    }

    /**
     * This method rotates this basketball object by incrementing the number of its rotations by
     * one.
     */
    public void rotate() {
        ++rotations;
    }

}
