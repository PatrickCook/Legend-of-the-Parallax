
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class Ghost extends Enemy {   
    private boolean movement = true;

    public Ghost() {
        super();
        ImageIcon a = new ImageIcon(this.getClass().getResource("/sprites/ghost.png"));
        enemy = a.getImage();
        Width = 32;
        Height = 32;
        origHealth = health;
        try {
            URL anim = this.getClass().getResource("/sprites/ghostdeath.png");
            File animIMG;
            animIMG = new File(anim.toURI());
            death = ImageIO.read(animIMG);
        } catch (URISyntaxException ex) {
        } catch (IOException ex) {
        }
        health = 1;

    }

    @Override
    public void move() {

        if (!map.blockedMove((int)x+dx, (int)y+dy, Width, Height)) {
            if (movement) {
                times++;
                dx = 2;
            } else {
                dx = -2;
                times++;
            }
            if (times >= 100) {
                times = 0;
                movement ^= true;
            }
            x+=dx;
           
        } 
        if (map.blockedMove((int)x+dx,(int) y+dy, Width, Height))  {
            movement^=true;
            if (movement) {
                times++;
                dx = 2;
            } else {
                dx = -2;
                times++;
            }
            if (times >= 100) {
                times = 0;
                movement ^= true;
            }
            x+=dx;
            
                
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
