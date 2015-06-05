
import java.awt.Image;
import javax.swing.ImageIcon;

public class Arrow {

    private int x;
    private int y;
    private int Width = 20;
    private int Height = 20;
    private int damage = 2;
    private Image arrow_L;
    private Image arrow_R;
    private Image arrow_U;
    private Image arrow_D;
    private Link link;
    String direction = "D";
    MapCollision map = new MapCollision();

    public Arrow(Link l, String dir) {
        link = l;
        direction = dir;
        ImageIcon a = new ImageIcon(this.getClass().getResource("/sprites/arrow_U.gif"));
        arrow_U = a.getImage();
        ImageIcon b = new ImageIcon(this.getClass().getResource("/sprites/arrow_D.gif"));
        arrow_D = b.getImage();
        ImageIcon c = new ImageIcon(this.getClass().getResource("/sprites/arrow_L.gif"));
        arrow_L = c.getImage();
        ImageIcon d = new ImageIcon(this.getClass().getResource("/sprites/arrow_R.gif"));
        arrow_R = d.getImage();

        x = link.getX();
        y = link.getY();
    }

    public int getWidth() {
        return Width;
    }

    public int getHeight() {
        return Height;
    }

    public int getDamage() {
        return damage;
    }

    public void move() {
        
        if (!map.blockedMove(x, y, Width, Height)) {
            if (direction.equals("L")) {
                x = x - 10;
            } else if (direction.equals("R")) {
                x = x + 10;
            }
            if (direction.equals("U")) {
                y = y - 10;
            } else if (direction.equals("D")) {
                y = y + 10;
            }
        } else 
            setImage("null");
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Image getImage() throws InterruptedException {


        if (direction.equals("L")) {

            return arrow_L;

        } else if (direction.equals("R")) {

            return arrow_R;

        } else if (direction.equals("U")) {

            return arrow_U;


        } else if (direction.equals("D")) {

            return arrow_D;

        } else if (direction.equals("null")) {
            return null;
        }
        return null;
    }

    public void setVisable(boolean v) {
        if (v = false) {
            setDirection("invisible");
        }


    }

    public void setImage(String i) {
        direction = i;
    }

    public void setDirection(String d) {
        direction = d;
    }
}