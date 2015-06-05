

import java.applet.*;
import java.net.*;
import javax.media.*;
public class Music  implements Runnable {
    Player p;
    boolean play= true;
    URL url = this.getClass().getResource("/music/Theme.wav");
    AppletContext ac;
    public Music () throws Exception{      
        p = Manager.createRealizedPlayer(url);
        
    }
    public void start(int world) throws Exception{
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
    }    
}
