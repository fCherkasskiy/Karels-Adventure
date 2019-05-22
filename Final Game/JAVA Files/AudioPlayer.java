import java.io.*;
import java.util.*;
import javax.sound.sampled.*; 



/*****************************************************************
* An AudioPlayer is a class that will play sound.
* An AudioPlayer allows for music to be played in the game's background.
* @author Rushil Sidhu
* @version 1.0
****************************************************************/
public class AudioPlayer  
{ 
   public Clip clip;
   AudioInputStream audioStream; 
   static String filePath;
   // constructor to initialize streams and clip 
   
   /************************************************************* 
   * Runs the Menu constructor.
   * @param volume    required to determine volume of the music
   * @throws UnsupportedAudioFileException, IOException, LineUnavailableException   throws an exception if the file does not exist, if the file is not playable, or if there was an error reading the file.
   **************************************************************/
   public AudioPlayer(float volume) throws UnsupportedAudioFileException, IOException, LineUnavailableException  
   {
        // create AudioInputStream object 
      audioStream = AudioSystem.getAudioInputStream(new File(filePath).getAbsoluteFile()); 
          
        // create clip reference 
      clip = AudioSystem.getClip();
        // open audioStream to the clip 
      clip.open(audioStream);
      FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
      
      gainControl.setValue(volume); // Reduce volume by 10 decibels.
      clip.loop(Clip.LOOP_CONTINUOUSLY); 
   }
  
} 
