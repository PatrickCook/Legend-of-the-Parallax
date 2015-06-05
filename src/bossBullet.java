
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;

/**
 *
 * @author Patrick
 */
public class bossBullet extends Animation {

    double dx;
    double dy;
    double bulletSlope;
    boolean rotated = false;
    AffineTransform rot = new AffineTransform();

    /**
     *
     * @param boss
     * @param dirX
     * @param dirY
     */
    public bossBullet(BossOne boss,int dirX, int dirY) {
        super();

        animImage = new BossOne().bullet;
        frameWidth = 45;
        this.frameHeight = 30;
        this.numberOfFrames = 5;
        this.frameTime = 45;
        this.loop = true;
        this.x = (int)boss.x;
        this.y = (int)boss.y;
        timeOfAnimationCration = System.currentTimeMillis();
        startingXOfFrameInImage = 0;
        endingXOfFrameInImage = frameWidth;
        startingFrameTime = System.currentTimeMillis();
        timeForNextFrame = startingFrameTime + this.frameTime;
        currentFrameNumber = 0;
        active = true;
        bulletSlope = Math.atan2(dirX - x, dirY - y);
        dx = Math.sin(bulletSlope);
        dy = Math.cos(bulletSlope);

    }

    /**
     *
     * @param g2d
     */
    public void Draw(Graphics2D g2d) {
        super.Update();
        x += 3 * dx;
        y += 3 * dy;
        if (this.timeOfAnimationCration + this.showDelay <= System.currentTimeMillis()) {
            g2d.drawImage(animImage, x, y, x + frameWidth, y + frameHeight, startingXOfFrameInImage, 0, endingXOfFrameInImage, frameHeight, null);
        }
    }
}
