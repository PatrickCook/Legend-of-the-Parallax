
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import javax.imageio.ImageIO;

public class Chest {

    BufferedImage chest;
    BufferedImage open;
    BufferedImage poof;
    Animation poofAnim;
    Animation openAnim;
    Link link;
    double distance = 0;
    public boolean opened = false;
  
    public Chest() {
        try {

            URL anim = this.getClass().getResource("/sprites/explosion.png");
            File animIMG;
            animIMG = new File(anim.toURI());
            poof = ImageIO.read(animIMG);
            anim = this.getClass().getResource("/sprites/ChestOpen.png");
            animIMG = new File(anim.toURI());
            open = ImageIO.read(animIMG);
            anim = this.getClass().getResource("/sprites/Chest.png");
            animIMG = new File(anim.toURI());
            chest = ImageIO.read(animIMG);
        } catch (URISyntaxException ex) {
        } catch (IOException ex) {
        }
        poofAnim = new Animation(poof,
                134, 135, 4, 70,
                false, 700, 20, 0);
        poofAnim.setTimer(20);
        openAnim = new Animation(open,
                50, 50, 4, 70,
                false, 752, 42, 0);
        openAnim.setTimer(20);

    }
    
    public BufferedImage getImage() {
        return chest;
    }

    public void keyPressed(KeyEvent e, Link link) {
        distance = Math.sqrt((link.getX() - 750) * (link.getX() - 750) + (link.getY() - 50) * (link.getY() - 50)); 
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_E) {
            if (distance <= 40) {
                Sound s = new Sound();
                s.item();
                opened = true;   
            }
        }
    }
}
