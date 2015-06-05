
import java.awt.Image;
import java.awt.Point;
import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;

public class Enemy {

    public double x;
    public int origHealth;
    public double y;
    public int dx;
    public int dy;
    public int oX;
    public int oY;
    public int Width;
    public int Height;
    public int frame;
    public int health = 1;
    public Image enemy;
    public int times = 20;
    MapCollision map = new MapCollision();
    BufferedImage death;
    Animation poof;
    Point spawn;

    public Enemy() {
        ImageIcon a = new ImageIcon(this.getClass().getResource("/sprites/Carlore.gif"));
        enemy = a.getImage();
        Width = 50;
        Height = 50;
        dx = 1;
        dy = 1;
        spawn = map.pointSpawn();
        x = (int)spawn.getX();
        y = (int)spawn.getY();
             
    }

    public void move() {
    }

    public void setHealth(int x) {
        health = x;
    }

    public int getX() {

        if (x <= 0) {
            x = 0;
            return (int)x;
        }
        if (x >= 760) {
            x = 760;
            return (int)x;

        } else {
            return (int)x;
        }

    }

    public int getY() {

        if (y <= 50) {
            y = 50;
            return (int)y;
        }
        if (y >= 590) {
            y = 590;
            return (int)y;
        } else {
            return (int)y;
        }

    }

    public int getWidth() {
        return Width;
    }

    public int getHeight() {
        return Height;
    }

    public int getHealth() {
        return health;
    }

    public void setImage(Image i) {
        enemy = i;
    }

    public Image getImage() {
        return enemy;
    }

    public BufferedImage getDeathAnim() {
        return death;
    }
    public int getFrame() {
        return frame;
    }
    public void setFrame(int x) {
        frame = x;
    }
}