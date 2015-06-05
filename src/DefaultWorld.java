
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Timer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JPanel;


public class DefaultWorld extends JPanel implements ActionListener {

    MapCollision map = new MapCollision();
    public boolean drawChest = false;
    public boolean winSound = true;
    public int spin = 0;
    public int step = 0;
    public Timer timer;
    public javax.swing.Timer swingTimer;
    public long delayStart;
    public long delayStop;
    public int delay;
    public int disolve = 0;
    public int gameOverY = 300;
    public boolean win = false;
    public Link link;
    public Enemy enemy;
    public ArrayList<Arrow> quiver = new ArrayList<Arrow>();
    public ArrayList<Enemy> enemies = new ArrayList<Enemy>();
    public ArrayList<Bullet> bullets = new ArrayList<Bullet>();
    public Image heart;
    public Image rupee;
    public Image background;
    public int rupeeNum = 0;
    public boolean block = false;
    public boolean alive = true;
    public boolean paused = false;
    public boolean menu = false;
    public Chest chest = new Chest();
    public GameStart game = new GameStart();
    BufferedImage help;

    //Animation
    Sound s = new Sound();
    Animation expAnim;
    Graphics2D g2d;

    public DefaultWorld(Link l) {
        addKeyListener(new TAdapter());
        setBackground(Color.BLACK);
        setDoubleBuffered(true);
        setFocusable(true);
        requestFocusInWindow();
        delayStart = System.currentTimeMillis();
        delayStop = System.currentTimeMillis();
        delay = 2;
        link = l;
        swingTimer = new javax.swing.Timer(5,this);
        swingTimer.start();
        link.setHealth(8);
        game = new GameStart();
        ImageIcon aa = new ImageIcon(this.getClass().getResource("/sprites/rupee.gif"));
        rupee = aa.getImage();
        ImageIcon a = new ImageIcon(this.getClass().getResource("/sprites/heart.gif"));
        heart = a.getImage();
        ImageIcon bg = new ImageIcon(this.getClass().getResource("/sprites/World1.png"));
        background = bg.getImage();
    }

    @Override
    public void paint(Graphics g) {
        g2d = (Graphics2D) g;
        RenderingHints rh = new RenderingHints(RenderingHints.KEY_RENDERING,
                RenderingHints.VALUE_RENDER_QUALITY);
        rh.put(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHints(rh);
        super.paint(g);
        g2d.drawImage(background, 0, 0, this);

        if (link.getHealth() == 0) {
            alive = false;
        }
        Toolkit.getDefaultToolkit().sync();

        update(g);
        g.dispose();

    }

    public void paintHealth() {
         
        for (Enemy x : enemies) {
            g2d.setColor(new Color(0, 180, 14));
            double ratio = 50 * ((double) x.health / (double) x.origHealth);

            if (x instanceof Carlore) {
                g2d.fillRoundRect(x.getX() - 1, x.getY() - 20, (int) ratio, 5, 5, 5);
                g2d.setColor(new Color(0, 50, 14));
                g2d.drawRoundRect(x.getX() - 1, x.getY() - 20, 50, 5, 5, 5);
            } else if (x instanceof Ghost) {
                g2d.setColor(new Color(0, 180, 14));
                g2d.fillRoundRect(x.getX() - 10, x.getY() - 20, (int) ratio, 5, 5, 5);
                g2d.setColor(new Color(0, 150, 14));
                g2d.drawRoundRect(x.getX() - 10, x.getY() - 20, 50, 5, 5, 5);
            } else if (x instanceof Scrub) {
                g2d.setColor(new Color(0, 180, 14));
                g2d.fillRoundRect(x.getX() - 10, x.getY() - 20, (int) ratio, 5, 5, 5);
                g2d.setColor(new Color(0, 50, 14));
                g2d.drawRoundRect(x.getX() - 10, x.getY() - 20, 50, 5, 5, 5);
            } else if (x instanceof BossOne) {
                g2d.setColor(new Color(180, 10, 14));
                g2d.fillRoundRect(x.getX(), x.getY() - 20, (int) (2 * ratio), 5, 5, 5);
                g2d.setColor(new Color(50, 0, 14));
                g2d.drawRoundRect(x.getX(), x.getY() - 20, 100, 5, 5, 5);
            }
        }

    }

    @Override
    public void update(Graphics g) {
        if (!paused) {
            g2d = (Graphics2D) g;
            g2d.setColor(Color.WHITE);
            g2d.setFont(new Font("Times New Roman", Font.PLAIN, 28));
            linkCollision();
            bulletCollision();
            arrowCollision(g2d);
            paintHealth();
            //Paint Link sprite

            if (alive) {
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
                for (Enemy enemy : enemies) {
                    if (bullets.size() >= 15) {
                        bullets.subList(0, 5).clear();
                    }

                        //timer.schedule(bullets.add(new Bullet(link, enemy)), 0, 5000);
                    for (Bullet c : bullets) {
                        try {
                            g2d.drawImage(c.getImage(), c.getX(), c.getY(), this);
                        } catch (InterruptedException ex) {
                        }
                    }
                    g2d.drawImage(enemy.getImage(), enemy.getX(), enemy.getY(), this);
                }
                if (enemies.isEmpty()) {
                    win = true;
                    alive = false;
                }
                int k = 0;
                for (int i = 0; i < link.getHealth(); i++) {
                    if (i < 8) {
                        g2d.drawImage(heart, (20 * i) + 5, 5, this);
                    } else {
                        g2d.drawImage(heart, (20 * k) + 5, 15, this);
                        k++;
                    }

                }

                g2d.setColor(new Color(0, 150, 200));
                g2d.fillRoundRect(5, 25, link.getShield(), 10, 12, 12);
                g2d.setColor(new Color(0, 150, 250));

            } else if (win) {
                if (disolve < 254) {
                    disolve++;
                }
                if (disolve == 254) {
                    try {
                        game.changeWorld();
                        map.setWorldCollision();
                        link.setHealth(game.world + 4);
                        disolve = 0;
                        win = false;
                        System.gc();

                    } catch (IOException ex) {
                    } catch (Exception ex) {
                        Logger.getLogger(DefaultWorld.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                g2d.setFont(new Font("Times New Roman", Font.BOLD, 45));
                g2d.setColor(new Color(255, 255, 255, 0 + disolve));
                g2d.fillRect(0, 0, 800, 800);
                g2d.setColor(new Color(0, 0, 0));
                g2d.drawString("You beat World " + game.world + "!", 225, gameOverY);

            } else {
                g2d.setColor(new Color(0, 0, 0, 128));
                g2d.fillRect(0, 0, 800, 800);
                g2d.setColor(new Color(0, 0, 0));
                g2d.drawString("Game Over", 280, gameOverY);
            }
        } else if (menu) {
            try {
                URL anim = this.getClass().getResource("/sprites/menu.png");
                File animIMG = new File(anim.toURI());
                help = ImageIO.read(animIMG);
                g2d.drawImage(help, 0, 0, this);
            } catch (URISyntaxException ex) {
            } catch (IOException ex) {
            }

        } else if (paused) {
            g2d.setColor(new Color(0, 0, 0, 128));
            g2d.fillRect(0, 0, 800, 800);
            g2d.setColor(new Color(0, 180, 20));
            g2d.drawString("Paused", 280, gameOverY);
        }
    }

    public void linkCollision() {
        delayStop = System.currentTimeMillis();
        delay = (int) (delayStop - delayStart) / 1000;
        if (delay > 1) {
            block = true;
        }
        for (Enemy x : enemies) {
            Rectangle e = new Rectangle(x.getX(), x.getY(), x.getWidth(), x.getHeight());
            Rectangle p = new Rectangle(link.getX(), link.getY(), link.getWidth(), link.getHeight());
            if (e.intersects(p) && block) {
                link.setHealth(link.getHealth() - 1);
                delayStart = System.currentTimeMillis();
                block = false;
                delay = 0;
            }
        }
    }

    public void bulletCollision() {
        HashSet bulletRemove = new HashSet();
        Rectangle e = new Rectangle(link.getX(), link.getY(), link.getWidth(), link.getHeight());
        for (Bullet c : bullets) {
            if (c != null) {
                Rectangle p = new Rectangle(c.getX(), c.getY(), c.getWidth(), c.getHeight());
                if (p.intersects(e)) {
                    link.setHealth(link.getHealth() - 1);
                    bulletRemove.add(c);
                }
            }
        }

        if (expAnim != null) {
            expAnim.Draw(g2d, 0, 0);
        }
        bullets.removeAll(bulletRemove);
    }

    public void arrowCollision(Graphics2D g2d) {

        //create list of enemies and arrows to remove
        HashSet arrowRemove = new HashSet();
        HashSet enemyRemove = new HashSet();
        for (Arrow c : quiver) {
            for (Enemy x : enemies) {
                if (c != null) {
                    //does the arrow interect any enemies?
                    Rectangle e = new Rectangle(x.getX(), x.getY(), x.getWidth(), x.getHeight());
                    Rectangle p = new Rectangle(c.getX(), c.getY(), c.getWidth(), c.getHeight());

                    if (p.intersects(e) && !(x instanceof BossOne)) {
                        s.hit();
                        x.setHealth(x.getHealth() - 1);
                        expAnim = new Animation(x.getDeathAnim(),
                                134, 135, 4, 45,
                                false, x.getX() - 50, x.getY() - 50, 0);
                        expAnim.setTimer(30);
                        arrowRemove.add(c);
                        if (x.getHealth() <= 0) {
                            enemyRemove.add(x);
                        }
                    }
                    if (p.intersects(e) && x instanceof BossOne) {
                        s.hit();
                        x.setHealth(x.getHealth() - 1);
                        arrowRemove.add(c);
                        if (x.getHealth() <= 0) {
                            new Chest().poofAnim.Draw(g2d, 0, 0);
                            drawChest = true;
                            enemyRemove.add(x);
                        }
                    }
                }
            }
        }
        if (expAnim != null) {
            expAnim.Draw(g2d, 0, 0);
        }
        quiver.removeAll(arrowRemove);
        enemies.removeAll(enemyRemove);

    }

    public void actionPerformed(ActionEvent e) {
        link.move();
        for (Arrow c : quiver) {
            if (c != null) {
                c.move();
            }
        }
        for (Enemy x : enemies) {
            if (x != null) {
                x.move();

            }
        }
        for (Bullet b : bullets) {
            if (b != null) {
                b.move();

            }
        }
        repaint();

    }

    private class TAdapter extends KeyAdapter {

        public void keyReleased(KeyEvent e) {

            link.keyReleased(e);
        }

        public void keyPressed(KeyEvent e) {
            int key = e.getKeyCode();
            if (key == KeyEvent.VK_P) {
                alive ^= true;
                paused ^= true;
            }
            if (key == KeyEvent.VK_ESCAPE) {
                alive ^= true;
                paused ^= true;
                menu ^= true;
            }
            chest.keyPressed(e, link);
            link.keyPressed(e);
        }
    }
}
