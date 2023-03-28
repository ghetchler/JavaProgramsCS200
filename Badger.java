import java.lang.annotation.Inherited;

public class Badger extends MovingThing implements Clickable {

    private DanceStep[] danceSteps; // array storing Badger's dance show steps
    private boolean isDancing; // indicates whether this badger is dancing
    private boolean isDragging; // indicates whether this badger is being dragged
    private float[] nextDancePosition; // stores the next dance position of this badger
    private static int oldMouseX; // old x-position of the mouse
    private static int oldMouseY; // old y-position of the mouse
    private int stepIndex; // index position of the current dance step of this badger

    public Badger(float x, float y, DanceStep[] danceSteps) {
        super(x, y, 2, "badger.png");
        this.x = x;
        this.y = y;
        isDragging = false;
        isDancing = false;
        this.danceSteps = danceSteps;
        stepIndex = 1;

    }

    /**
     * Draws this badger to the display window
     */
    @Override
    public void draw() {
        super.draw();
        if (this.isDragging) {
            drag();
        }
        if (this.isDancing) {
            dance();
        }

    }

    /**
     * Checks whether this badger is being dragged
     *
     * @return true if the badger is being dragged, false otherwise
     */
    public boolean isDragging() {
        return isDragging;
    }

    /**
     * Helper method to drag this Badger object to follow the mouse moves
     */
    private void drag() {
        int dx = processing.mouseX - oldMouseX;
        int dy = processing.mouseY - oldMouseY;
        x += dx;
        y += dy;

        if (x > 0) x = Math.min(x, processing.width);
        else x = 0;
        if (y > 0) y = Math.min(y, processing.height);
        else y = 0;
        oldMouseX = processing.mouseX;
        oldMouseY = processing.mouseY;
    }

    /**
     * starts dragging this badger
     */
    public void startDragging() {
        oldMouseX = processing.mouseX;
        oldMouseY = processing.mouseY;
        this.isDragging = true;
        drag();
        //TODO fix drag issue
    }

    /**
     * stops dragging this badger
     */
    public void stopDragging() {
        this.isDragging = false;
    }

    /**
     * If the mouse is over this badger and this badger is NOT dancing, this method starts dragging
     * this badger.
     */
    public void mousePressed() {
        if (!isDancing && isMouseOver()) {
            startDragging();
        }

    }

    /**
     * If the mouse is released, this badger stops dragging.
     */
    public void mouseReleased() {
        stopDragging();
    }

    private boolean makeMoveDance() {
        float dx = nextDancePosition[0] - this.x; // x-move towards destination
        float dy = nextDancePosition[1] - this.y; // y-move towards destination
        int d = (int) Math.sqrt(dx * dx + dy * dy);
        this.y += speed * (dy / d);
        this.x += speed * (dx / d);

        isFacingRight = (dx > 0);

        return (d < (2.0f * speed));
    }

    private void dance() {
        if (makeMoveDance() == true) {
            this.nextDancePosition = danceSteps[stepIndex].getPositionAfter(x, y);

            stepIndex = (stepIndex + 1) % danceSteps.length;
        }
    }

    public void startDancing() {
        isDancing = true;
        stopDragging();
        stepIndex = 0;
        this.nextDancePosition = danceSteps[0].getPositionAfter(x, y);
    }

    public void stopDancing() {
        isDancing = false;
    }


}
