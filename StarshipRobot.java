import java.io.File;

public class StarshipRobot extends MovingThing {

    private Thing destination; // destination of starshipRobot
    private Thing source; // source point of starshipRobot

    public StarshipRobot(Thing source, Thing destination, int speed) {
        super(source.x, source.y, speed, "starshipRobot.png");

        this.source = source;
        this.destination = destination;
        this.speed = speed;

        if (source.x < destination.x) {
            isFacingRight = true;
        } else {
            isFacingRight = false;
        }


    }

    /**
     *
     */
    @Override
    public void draw() {
        super.draw();
        this.go();
        }

    /**
     * Checks whether this StarshipRobot is over a specific Thing
     *
     * @param thing a given Thing object
     * @return true if this StarshipRobot is over the Thing object passed as input, otherwise,
     * returns false
     */
    public boolean isOver(Thing thing) {
        // (x1 < x4) && (x3 < x2) && (y1 < y4) && (y3 < y2)
        float x1 = this.x - this.image().width / 2;
        float x2 = this.x + this.image().width / 2;
        float y1 = this.y - this.image().height / 2;
        float y2 = this.y + this.image().height / 2;

        float x3 = thing.x - thing.image().width / 2;
        float x4 = thing.x + thing.image().width / 2;
        float y3 = thing.y - thing.image().height / 2;
        float y4 = thing.y + thing.image().height / 2;

        return (x1 < x4) && (x3 < x2) && (y1 < y4) && (y3 < y2);
    }

    /**
     * Helper method to move this StarshipRobot towards its destination
     */
    private void moveTowardsDestination() {
        float dx = destination.x - this.x; // x-move towards destination
        float dy = destination.y - this.y; // y-move towards destination
        int d = (int) Math.sqrt(dx * dx + dy * dy); // distance to destination
        if (d != 0) { // move!
            this.x += speed * dx / d;
            this.y += speed * dy / d;
        }
    }

    /**
     * Implements the action of this StarshipRobot. By default, an StarshipRobot object moves
     * back-and-forth between its source and destination
     */
    public void go() {
        moveTowardsDestination();
        // switch source and destination if this StarshipRobot reached its destination
        if (this.isOver(this.destination)) {
            Thing d = destination;
            destination = source;
            source = d;

            if (isFacingRight) {
                isFacingRight = false;
            }
            else {
                isFacingRight = true;
            }
        }


    }


}
