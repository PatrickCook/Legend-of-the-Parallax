
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class Scrub extends Enemy {

    double bulletSlope;
    Link link;
    double dx;
    double dy;

    public Scrub(Link l) {
        super();
        link = l;

        ImageIcon a = new ImageIcon(this.getClass().getResource("/sprites/scrub.png"));
        enemy = a.getImage();
        Width = 32;
        Height = 32;

        try {
            URL anim = this.getClass().getResource("/sprites/scrubdeath.png");
            File animIMG;
            animIMG = new File(anim.toURI());
            death = ImageIO.read(animIMG);
        } catch (URISyntaxException ex) {
        } catch (IOException ex) {
        }
        health = 2;
        origHealth = health;
    }

    @Override
    public void move() {
        if (link.getAllowed()) {
            bulletSlope = Math.atan2(link.getX() - x, link.getY() - y);
            dx = Math.sin(bulletSlope);
            dy = Math.cos(bulletSlope);
            if (!map.blockedMove((int) x, (int) y, Width, Height)) {
                x += dx;
                y += dy;
            } else {
                enemy = null;
            }
        }
    }

    @Override
    public void setImage(Image i) {
        enemy = i;
    }

    @Override
    public Image getImage() {
        return enemy;
    }
}
