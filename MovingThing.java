import java.lang.annotation.Inherited;

public class MovingThing extends Thing implements Comparable<MovingThing> {
    protected boolean isFacingRight; // indicates whether this MovingThing is facing right or not
    protected int speed; // movement speed of this MovingThing

    /**
     * Creates a new MovingThing and sets its speed, image file, and initial x and y position. A
     * MovingThing object is initially facing right
     *
     * @param x             starting x-position of this MovingThing
     * @param y             starting y-position of this MovingThing
     * @param speed         movement speed of this MovingThing
     * @param imageFileName filename of the image of this MovingThing, for instance "name.png"
     */
    public MovingThing(float x, float y, int speed, String imageFileName) {
        super(x, y, imageFileName); // TODO this might be wrong to ask for help in office hours
        this.speed = speed;
        isFacingRight = true;

    }

    /**
     * Draws this MovingThing at its current position.
     */
    @Override
    public void draw() {
        processing.pushMatrix();
        processing.rotate(0.0f);
        processing.translate(x, y);
        if (!isFacingRight) {
            processing.scale(-1.0f, 1.0f);
        }
        processing.image(image(), 0.0f, 0.0f);
        processing.popMatrix();

    }

    /**
     * Compares this object with the specified MovingThing for order, in the increasing order of
     * their speeds.
     *
     * @param other the object to be compared.
     * @return zero if this object and other have the same speed, a negative integer if the speed of
     * this moving object is less than the speed of other, and a positive integer otherwise.
     */
    public int compareTo(MovingThing other) {
        if (this.speed > other.speed) {
            return 1;
        }
        else if (this.speed < other.speed) {
            return -1;
        } else {
            return 0;
        }
    }

}

