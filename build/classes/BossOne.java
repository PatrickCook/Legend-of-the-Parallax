
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Patrick
 */
public class BossOne extends Enemy {

    
    BufferedImage bullet;
    BufferedImage bossFrame;
    BufferedImage enemy;
    public int degree = 0;
    public boolean dead = false;

    public BossOne() {
        super();
        try {
            enemy = ImageIO.read(this.getClass().getResource("/sprites/BossSprites.png"));
            bossFrame = enemy.getSubimage(100, 0, 100, 100);
            URL anim = getClass().getResource("/sprites/GoopAnim.png");
            File animIMG;
            animIMG = new File(anim.toURI());
            bullet = ImageIO.read(animIMG);
            Width = 100;
            Height = 100;
            health = 20;
            origHealth = health;
            x = 700;
            y = 50;
        } catch (IOException ex) {
            
        } catch (URISyntaxException ex) {
            Logger.getLogger(BossOne.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public BossOne(int x, int y) {
        super();
        try {
            enemy = ImageIO.read(this.getClass().getResource("/sprites/BossSprites.png"));
            bossFrame = enemy.getSubimage(100, 0, 100, 100);
            URL anim = getClass().getResource("/sprites/GoopAnim.png");
            File animIMG;
            animIMG = new File(anim.toURI());
            bullet = ImageIO.read(animIMG);
            Width = 100;
            Height = 100;
            health = 20;
            origHealth = health;
            this.x = x;
            this.y = y;
        } catch (IOException ex) {
            
        } catch (URISyntaxException ex) {
            Logger.getLogger(BossOne.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void move() {
        x -= Math.sin(Math.toRadians(degree));
        y -= Math.sin(Math.toRadians(degree));
        degree++;
    }

    public void setImage(BufferedImage i) {
        enemy = i;
    }

    @Override
    public BufferedImage getImage() {
        bossFrame = enemy.getSubimage(frame * 100, 0, 100, 100);
        return bossFrame;
    }

    public int getFrame() {
        return frame;
    }

    public void setFrame(int x) {
        frame += x;
    }
    public void setHealth(int x){
        health = x;
    }
}
