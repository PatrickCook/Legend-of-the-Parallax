

import java.applet.*;
import java.net.*;
import javax.media.*;
public class MusicThread  implements Runnable {
    Player p;
    boolean play= true;
    URL url = this.getClass().getResource("/music/Theme.wav");
    AppletContext ac;
    public MusicThread () throws Exception{      
        p = Manager.createRealizedPlayer(url);
        
    }
    public void run(int world) throws Exception{
        if (play){
        switch(world){
            case 1: url = this.getClass().getResource("/music/Theme.wav");
                break;
            case 2: url = this.getClass().getResource("/music/Theme2.wav");
                break;
        }
        p = Manager.createRealizedPlayer(url);
        p.start();
        }
    }
    public void run() {   
    }
    public void stop() {
        p.stop();
        play = false;
    }    
}
