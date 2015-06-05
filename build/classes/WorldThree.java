
import java.io.IOException;
import javax.swing.ImageIcon;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Patrick
 */
public class WorldThree extends DefaultWorld{
    public WorldThree(Link l) throws IOException {
        super(l);
        l.setHealth(7);
        ImageIcon bg = new ImageIcon(this.getClass().getResource("World1.png"));
        background = bg.getImage();
        for (int x = 0; x < 6; x++) {
            enemies.add(new Carlore());
            enemies.add(new Ghost());
            enemies.add(new Scrub(l));
        }
    }
}
