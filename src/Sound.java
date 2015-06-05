
import java.io.*;
import java.net.URL;
import javax.sound.sampled.*;
import javax.swing.*;
import java.applet.*;

public class Sound extends JFrame {

    private AudioClip die; // Sound player
    URL url;
    AudioInputStream audioIn;
    int switch1 = 0;

    public Sound() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(0, 0);
        this.setVisible(false);
    }

    public void item() {
        try {
            audioIn = null;
            url = this.getClass().getResource("/music/item.wav");
            audioIn = AudioSystem.getAudioInputStream(url);
            Clip clip = AudioSystem.getClip();
            clip.drain();
            clip.open(audioIn);
            clip.start();
        } catch (UnsupportedAudioFileException e) {
        } catch (IOException e) {
        } catch (LineUnavailableException e) {
        }
    }
    public void die() {
        try {
            audioIn = null;
            url = this.getClass().getResource("/music/die.wav");
            audioIn = AudioSystem.getAudioInputStream(url);
            Clip clip = AudioSystem.getClip();
            clip.drain();
            clip.open(audioIn);
            clip.start();
        } catch (UnsupportedAudioFileException e) {
        } catch (IOException e) {
        } catch (LineUnavailableException e) {
        }
    }

    public void win() {
        try {
            audioIn = null;
            url = this.getClass().getResource("/music/win.wav");
            audioIn = AudioSystem.getAudioInputStream(url);
            Clip clip = AudioSystem.getClip();
            clip.drain();
            clip.open(audioIn);
            //clip.start();
        } catch (UnsupportedAudioFileException e) {
        } catch (IOException e) {
        } catch (LineUnavailableException e) {
        }
    }

    public void hit() {
        try {
            url = this.getClass().getResource("/music/hit.wav");
            audioIn = AudioSystem.getAudioInputStream(url);

            Clip clip = AudioSystem.getClip();

            clip.open(audioIn);
            clip.start();
        } catch (UnsupportedAudioFileException e) {
        } catch (IOException e) {
        } catch (LineUnavailableException e) {
        }
    }
}