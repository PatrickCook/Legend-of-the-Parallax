
import java.awt.Image;
import javax.swing.ImageIcon;

public class Bullet {

    private double x;
    private double y;
    private double dx;
    private double dy;
    private int Width = 10;
    private int Height = 10;
    private double bulletSlope;
    private Image bullet;
    private Link link;
    private Carlore car;
    MapCollision map;

    public Bullet(Link l, Enemy c) {
        ImageIcon a = new ImageIcon(this.getClass().getResource("/sprites/bullet.png"));
        bullet = a.getImage();
        link = l;
        map = new MapCollision();
        car = (Carlore) c;
        x = c.getX();
        y = c.getY();
        bulletSlope = Math.atan2(link.getX() - c.getX(), link.getY() - c.getY());
        dx = Math.sin(bulletSlope);
        dy = Math.cos(bulletSlope);
    }

    public int getWidth() {
        return Width;
    }

    public int getHeight() {
        return Height;
    }

    public void move() {

        if (!map.blockedMove((int) x, (int) y, Width, Height)) {
            x += 5*dx;
            y += 5*dy;
        } else {
            bullet = null;
        }
    }

    public int getX() {
        return (int) x;
    }

    public int getY() {
        return (int) y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Image getImage() throws InterruptedException {
        return bullet;

    }
}