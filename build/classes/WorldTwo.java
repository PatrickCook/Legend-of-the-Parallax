
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class WorldTwo extends DefaultWorld {

    public int x = 0;
    boolean dead = false;
    public int disolve = 0;
    ArrayList<bossBullet> bossBullets;
    boolean change = true;
    boolean open = false;
    boolean bossDead = false;
    BufferedImage key;
    BufferedImage note;
    BufferedImage note2;
    boolean start = true;

    public WorldTwo(Link l) throws IOException {
        super(l);
        paused = false;
        menu = false;
        alive = true;
        l.setHealth(8);
        ImageIcon bg = new ImageIcon(this.getClass().getResource("/sprites/World2.png"));
        background = bg.getImage();
        for (int x = 0; x < 1; x++) {
            enemies.add(new Carlore());
        }
        enemies.add(new BossOne(400, 600));
        enemies.add(new BossOne());
        bossBullets = new ArrayList<bossBullet>();

    }

    @Override
    public void update(Graphics g) {


        start = false;
        g2d = (Graphics2D) g;
        g2d.setColor(Color.WHITE);
        g2d.setFont(new Font("Times New Roman", Font.PLAIN, 28));
        g2d.rotate(0);


        if (menu) {
            try {
                URL anim = this.getClass().getResource("/sprites/menu.png");
                File animIMG = new File(anim.toURI());
                help = ImageIO.read(animIMG);
                g2d.drawImage(help, 0, 0, this);
            } catch (URISyntaxException ex) {
            } catch (IOException ex) {
            }


        } else if (paused) {
            g2d.setFont(new Font("Times New Roman", Font.BOLD, 45));
            g2d.setColor(new Color(0, 0, 0, 128));
            g2d.fillRect(0, 0, 800, 800);
            g2d.setColor(new Color(0, 180, 20));
            g2d.drawString("Paused", 320, gameOverY);


        } else if (alive) {
            linkCollision();
            bulletCollision();
            arrowCollision(g2d);
            paintHealth();
            try {
                g2d.drawImage(link.getImage(), link.getX(), link.getY(), this);

            } catch (InterruptedException ex) {
            }
            if (link.getShoot() == true) {
                quiver.add(new Arrow(link, link.getDirection()));
                link.setShoot(false);
                if (quiver.size() >= 10) {
                    quiver.subList(0, 5).clear();
                }
            }
            if (!quiver.isEmpty()) {
                for (Arrow c : quiver) {
                    try {
                        g2d.drawImage(c.getImage(), c.getX(), c.getY(), this);
                    } catch (InterruptedException ex) {
                    }
                }
            }
            for (Enemy x : enemies) {
                if (bossBullets.size() >= 20) {
                    bossBullets.subList(0, 5).clear();
                }
                if (x instanceof BossOne) {
                    if (x.health == 0) {
                        bossDead = true;
                    }
                    if (step > 100) {
                        step = 0;
                        bossBullets.add(new bossBullet((BossOne) x, link.getX(), link.getY()));
                    } else {
                        step++;
                    }


                    /* changes boss colors
                     BossOne b = (BossOne) x;
                     if (b.frame < 9 && change) {
                     b.frame++;
                     } else if (b.frame <= 9) {
                     change = false;
                     b.frame--;
                     if (b.frame == 0) {
                     change = true;
                     }
                     }
                     */
                }

                g2d.drawImage(x.getImage(), x.getX(), x.getY(), this);
            }
            for (bossBullet b : bossBullets) {
                b.Draw(g2d);
            }
            int k = 0;
            for (int i = 0; i < link.getHealth(); i++) {
                if (i < 8) {
                    g2d.drawImage(heart, (20 * i) + 5, 5, this);
                } else {
                    g2d.drawImage(heart, (20 * k) + 5, 25, this);
                    k++;
                }
            }


            g2d.setColor(new Color(0, 150, 200));
            g2d.fillRoundRect(5, 25, link.getShield(), 10, 12, 12);
            if (link.getHealth() == 0) {
                dead = true;
            }
            if (enemies.isEmpty()) {
                alive = false;
                win = true;
            }
        } else if (win) {
            s.win();
            if (disolve < 254) {
                disolve++;
            }
            if (disolve == 254) {
                if (game.world != 2) {
                    try {
                        game.changeWorld();
                        map.setWorldCollision();
                        link.setHealth(game.world + 4);
                        disolve = 0;
                        win = false;
                        System.gc();

                    } catch (IOException ex) {
                    } catch (Exception ex) {
                        Logger.getLogger(WorldTwo.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
            g2d.setColor(new Color(255, 255, 255, 0 + disolve));
            g2d.fillRect(0, 0, 800, 800);
            g2d.setColor(new Color(0, 15, 145));
            g2d.setFont(new Font("Times New Roman", Font.BOLD, 45));
            g2d.drawString("You beat The Legend of the Parallax!", 25, gameOverY);
            g2d.setFont(new Font("Times New Roman", Font.BOLD, 25));
            g2d.drawString("Written and coded by Patrick Cook and Jacob Goldstein", 100, gameOverY + 100);

        } else if (dead) {
            s.die();
            g2d.setFont(new Font("Times New Roman", Font.BOLD, 45));
            g2d.setColor(new Color(0, 0, 0, 128));
            g2d.fillRect(0, 0, 800, 800);
            g2d.setColor(new Color(0, 0, 0));
            g2d.drawString("Game Over", 280, gameOverY);
            g2d.setColor(new Color(255, 0, 0));
            g2d.drawString("Game Over", 280, gameOverY);
        }
        if (link.lockOpen) {
            ImageIcon bg = new ImageIcon(this.getClass().getResource("/sprites/World2Open.png"));
            background = bg.getImage();
        }
        if (link.attempt) {
            try {
                URL anim = this.getClass().getResource("/sprites/note2.png");
                File animIMG = new File(anim.toURI());
                note2 = ImageIO.read(animIMG);
            } catch (URISyntaxException ex) {
            } catch (IOException ex) {
            }
            g2d.drawImage(note2, 200, 300, this);
            alive = false;
            Timer t = new Timer();
            t.schedule(new TimerTask() {
                public void run() {
                    alive = true;
                    link.attempt = false;
                }
            }, 3000);
        }
        if (chest.opened) {
            link.hasKey = true;
            try {
                URL anim = this.getClass().getResource("/sprites/key.png");
                File animIMG = new File(anim.toURI());
                key = ImageIO.read(animIMG);
                anim = this.getClass().getResource("/sprites/openChest.png");
                animIMG = new File(anim.toURI());
                chest.chest = ImageIO.read(animIMG);
                anim = this.getClass().getResource("/sprites/note.png");
                animIMG = new File(anim.toURI());
                note = ImageIO.read(animIMG);

            } catch (URISyntaxException ex) {
            } catch (IOException ex) {
            }
            g2d.drawImage(note, 200, 300, this);
            alive = false;
            Timer t = new Timer();
            t.schedule(new TimerTask() {
                public void run() {
                    alive = true;
                    chest.opened = false;
                }
            }, 3000);
        }
        if (drawChest && !win) {
            chest.poofAnim.Draw(g2d, 0, 0);
            if (key != null) {
                g2d.drawImage(key, 10, 40, this);
            }
            g2d.drawImage(chest.chest, 750, 50, this);
        }

    }

    @Override
    public void bulletCollision() {
        HashSet bulletRemove = new HashSet();
        Rectangle e = new Rectangle(link.getX(), link.getY(), link.getWidth(), link.getHeight());
        for (bossBullet c : bossBullets) {
            if (c != null) {
                Rectangle p = new Rectangle(c.x, c.y, c.frameWidth, c.frameHeight);
                if (p.intersects(e)) {
                    link.setHealth(link.getHealth() - 1);
                    bossBullets.remove(c);
                    break;
                }
            }
        }
    }
}
