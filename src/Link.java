
import java.awt.Image;
import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;

public class Link {

    private int dx;
    private int dy;
    private double ds = 0;
    private int x;
    private int y;
    public int gridX = 0;
    public int gridY = 0;
    private int Width;
    private int Height;
    private int health = 3;
    public boolean win = false;
    public boolean allowed = false;
    public boolean lockOpen = false;
    private double shield = 100;
    public boolean hasKey;
    public boolean attempt = false;
    private boolean shoot;
    private boolean block;
    private double distance;
    private Image link_L;
    private Image link_R;
    private Image link_U;
    private Image link_D;
    private Image link_LB;
    private Image link_RB;
    private Image link_UB;
    private Image link_DB;
    String direction = "D";
    MapCollision map = new MapCollision();

    public Link() {
        ImageIcon a = new ImageIcon(this.getClass().getResource("/sprites/link_U.gif"));
        link_U = a.getImage();
        ImageIcon b = new ImageIcon(this.getClass().getResource("/sprites/link_D.gif"));
        link_D = b.getImage();
        ImageIcon c = new ImageIcon(this.getClass().getResource("/sprites/link_L.gif"));
        link_L = c.getImage();
        ImageIcon d = new ImageIcon(this.getClass().getResource("/sprites/link_R.gif"));
        link_R = d.getImage();
        ImageIcon aa = new ImageIcon(this.getClass().getResource("/sprites/link_UB.gif"));
        link_UB = aa.getImage();
        ImageIcon bb = new ImageIcon(this.getClass().getResource("/sprites/link_DB.gif"));
        link_DB = bb.getImage();
        ImageIcon cc = new ImageIcon(this.getClass().getResource("/sprites/link_LB.gif"));
        link_LB = cc.getImage();
        ImageIcon dd = new ImageIcon(this.getClass().getResource("/sprites/link_RB.gif"));
        link_RB = dd.getImage();
        shoot = false;
        block = false;
        x = 100;
        y = 100;
        Width = 30;
        Height = 30;
    }

    public void move() {
        if (!map.blockedMove(x + dx, y + dy, Width, Height)) {
            x += dx;
            y += dy;
        }
        if (!(shield+ds < 0))
         shield += ds;
        if (shield > 100) {
            ds = 0;          
        }
       
    }

    public boolean getShoot() {
        return shoot;
    }

    public int getX() {


        if (x <= 0) {
            x = 0;
            return x;
        }
        if (x >= 760) {
            x = 760;
            return x;

        } else {
            return x;
        }

    }

    public int getY() {

        if (y <= 0) {
            y = 0;
            return y;
        }
        if (y >= 610) {
            y = 610;
            return y;
        } else {
            return y;
        }

    }

    public int getHealth() {
        return health;
    }

    public Image getImage() throws InterruptedException {
        if (block) {
            if (direction.equals("L")) {

                return link_LB;

            } else if (direction.equals("R")) {

                return link_RB;

            } else if (direction.equals("U")) {

                return link_UB;


            } else if (direction.equals("D")) {

                return link_DB;

            }
        }
        if (direction.equals("L")) {

            return link_L;

        } else if (direction.equals("R")) {

            return link_R;

        } else if (direction.equals("U")) {

            return link_U;


        } else if (direction.equals("D")) {

            return link_D;

        }

        return null;
    }

    public String getDirection() {
        return direction;
    }

    public int getWidth() {
        return Width;
    }

    public int getHeight() {
        return Height;
    }

    public void setHealth(int h) {
        if (!block) {
            health = h;
        }
    }

    public void setShoot(boolean s) {
        shoot = s;
    }

    public void setShield(int x) {
        shield = x;
    }

    public int getShield() {
        return (int) shield;
    }
    public boolean getWin() {
        return win;
    }

    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_A || key == KeyEvent.VK_LEFT) {
            allowed = true;
            direction = "L";
            dx = -3;
        } else if (key == KeyEvent.VK_D || key == KeyEvent.VK_RIGHT) {
            allowed = true;
            direction = "R";
            dx = 3;
        } else if (key == KeyEvent.VK_W || key == KeyEvent.VK_UP) {
            allowed = true;
            direction = "U";
            dy = -3;
        } else if (key == KeyEvent.VK_S || key == KeyEvent.VK_DOWN) {
            allowed = true;
            direction = "D";
            dy = 3;
        }
        distance = Math.sqrt((x- 200) * (x - 200) + (y - 550) * (y - 550));  
        if (key == KeyEvent.VK_E) {
            System.out.println(distance);
            if (distance <= 40 && hasKey) {
                lockOpen = true;
                map.collisionMap[4][11]= 0;
                map.collisionMap = new int[][]{
                //0  1  2  3  4  5  6  7  8  9  10 11 12 13 14 15
                {1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0},
                {1, 1, 0, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 1},
                {1, 1, 0, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0},
                {1, 1, 0, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0},
                {1, 1, 0, 0, 0, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 1},
                {1, 1, 1, 1, 0, 1, 1, 1, 0, 0, 0, 0, 0, 0, 1, 1},
                {1, 1, 1, 0, 0, 0, 1, 0, 0, 1, 1, 1, 1, 1, 1, 1},
                {1, 0, 0, 0, 1, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1},
                {1, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
                {1, 0, 0, 1, 1, 1, 1, 0, 0, 0, 0, 1, 1, 1, 1, 1},
                {1, 1, 0, 0, 1, 1, 1, 0, 0, 0, 0, 1, 1, 1, 1, 1},
                {1, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1, 1, 1, 1, 1},
                {1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1},};
                map.setOpenAndBlocked();
                hasKey = false;
            } else if (distance <= 40)
                attempt = true;
        }
        if (key == KeyEvent.VK_J && shield >= 0) {
            ds = -.5;
            
            block = true;
        } else if (!block && key == KeyEvent.VK_SPACE) {
            shoot = true;
        }

    }

    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_A || key == KeyEvent.VK_LEFT) {
            allowed = false;
            dx = 0;
        }
        if (key == KeyEvent.VK_D || key == KeyEvent.VK_RIGHT) {
            allowed = false;
            dx = 0;
        }
        if (key == KeyEvent.VK_W || key == KeyEvent.VK_UP) {
            allowed = false;
            dy = 0;
        }
        if (key == KeyEvent.VK_S || key == KeyEvent.VK_DOWN) {
            allowed = false;
            dy = 0;
        }
        if (key == KeyEvent.VK_SPACE) {

            shoot = false;
        }
        if (key == KeyEvent.VK_J) {
            ds = .3;
            
            block = false;
        }
    }
    public boolean getAllowed() {
        return allowed;
    }
}
