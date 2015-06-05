
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class Carlore extends Enemy {

    private Image enemy;
    private double theta;
    private boolean changeDir = false;
    int times = 0; 
    private boolean movement = true;


    public Carlore() {
        super();
        ImageIcon a = new ImageIcon(this.getClass().getResource("/sprites/Carlore.gif"));
        enemy = a.getImage();
        Width = 32;
        Height = 32;   
        
        try {
            URL anim = this.getClass().getResource("/sprites/carloredeath.png");
            File animIMG;
            animIMG = new File(anim.toURI());
            death = ImageIO.read(animIMG);
        } catch (URISyntaxException ex) {
        } catch (IOException ex) {
        }
        health = 4;
        origHealth = health;

    }

    @Override
    public void move() {
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
