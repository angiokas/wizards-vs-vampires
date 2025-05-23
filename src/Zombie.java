
import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Double;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Zombie extends Actor {
    private static final int HEALTH = 50;
    private static final int COOLDOWN = 50;
    private static final int SPEED = -3;
    private static final int ATTACKDAMAGE = 10;

    private static final BufferedImage image;


    static {
        BufferedImage tempImage = null;
        try {
            tempImage = ImageIO.read(new File("assets/zombie.png"));

        } catch (IOException e) {
            System.out.println("A file was not found");
            System.exit(0);
        }
        image = tempImage;
    }

    private boolean isColliding;

    public Zombie(Double startingPosition, Double initHitbox, BufferedImage img, int health, int coolDown, double speed,
                  int attackDamage) {
        super(startingPosition, initHitbox, img, health, coolDown, speed, attackDamage);
        isColliding = false;
    }

    public Zombie(Double startingPosition) {
        super(startingPosition, new Point2D.Double(image.getWidth(), image.getHeight()), image, HEALTH, COOLDOWN, SPEED, ATTACKDAMAGE);
        isColliding = false;
    }

    /**
     * Move if not colliding. Only moves in x.
     */
    @Override
    public void move() {
        if (!isColliding) {
            shiftPosition(new Point2D.Double(getSpeed(), 0));
        }


    }

    /**
     * Set the collision status
     * @param collisionStatus
     */
    public void setColliding(boolean collisionStatus) {
        isColliding = collisionStatus;
    }

    /**
     * Get the collision status.
     * @return
     */
    public boolean getColliding() {
        return isColliding;
    }

    /**
     * Set collision status on this if it overlaps with other.
     * @param other
     */
    public void setCollisionStatus(Actor other) {
        if (other instanceof Plant && this.isCollidingOther(other))
            setColliding(true);
    }

    /**
     * Update the internal state of the Actor. This means reset the
     * collision status to false and decrement the cool down counter.
     */
    public void update() {
        isColliding = false;
        decrementCooldown();
    }

    /**
     * An attack means the two hotboxes are overlapping and the
     * Actor is ready to attack again (based on its cooldown).
     *
     * @param other
     */
    @Override
    public void attack(Actor other) {
        if (other instanceof Plant) {
            super.attack(other);
        }
    }


}
