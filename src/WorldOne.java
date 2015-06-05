
import java.io.IOException;
import javax.swing.ImageIcon;

/**
 *
 * @author Patrick
 */
public class WorldOne extends DefaultWorld{
    /**
     *
     * @param l
     * @throws IOException
     */
    public WorldOne(Link l) throws IOException {
        super(l); 
        ImageIcon bg = new ImageIcon(this.getClass().getResource("/sprites/World1.png"));
        background = bg.getImage();
        for (int x = 0; x < 4; x++) {
            enemies.add(new Carlore());
            enemies.add(new Ghost());
            enemies.add(new Scrub(l));
        }
    }   
}
