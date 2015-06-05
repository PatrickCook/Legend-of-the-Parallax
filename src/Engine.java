
import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class Engine extends JFrame {

    public static Link link = new Link();
    public static MusicThread music;
    
    public Engine(final int world) throws Exception {
        music = new MusicThread();
        link = new Link();
        ImageIcon aa = new ImageIcon(this.getClass().getResource("/sprites/Icon.png"));
        this.setIconImage(aa.getImage());
         
        switch (world) {
            case 1:        
                music.run(world);
                add(new WorldOne(link));
                break;
            case 2:
                music.stop(); 
                music.run(world);
                add(new WorldTwo(link));
                break;
            case 3:
                add(new WorldThree(link));
                break;
            case 4:
                add(new WorldFour(link));
                break;
            case 5:
                add(new WorldFive(link));
                break;
            case 6:
                add(new WorldSix(link));
                break;
            case 7:
                add(new DefaultWorld(link));
                break;
        }
        setSize(800, 675);
        setTitle("The Legend of the Parallax");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocation(320, 100);
        setResizable(false);
        setVisible(true);
        setBackground(Color.BLACK);

        
    }
}
