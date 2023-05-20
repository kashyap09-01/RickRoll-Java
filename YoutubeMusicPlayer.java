import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import uk.co.caprica.vlcj.player.MediaPlayerFactory;
import uk.co.caprica.vlcj.player.MediaPlayer;

public class YouTubeMusicPlayer {
    public static void main(String[] args) {
        String youtubeLink = "https://www.youtube.com/watch?v=dQw4w9WgXcQ"; // Update with your YouTube link
        String command = "youtube-dl -x --audio-format mp3 --audio-quality 0 -o - " + youtubeLink;

        try {
            // Execute the youtube-dl command and get the process
            Process process = Runtime.getRuntime().exec(command);

            // Create a BufferedReader to read the output of the process
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));

            // Create a media player using VLCJ
            uk.co.caprica.vlcj.player.MediaPlayerFactory mediaPlayerFactory = new uk.co.caprica.vlcj.player.MediaPlayerFactory();
            uk.co.caprica.vlcj.player.MediaPlayer mediaPlayer = mediaPlayerFactory.newMediaPlayer();

            // Get the audio stream URL from the youtube-dl output
            String audioStreamUrl = reader.readLine();

            // Start playing the audio stream
            mediaPlayer.playMedia(audioStreamUrl);

            // Wait for the user to press Enter to stop the playback
            System.out.println("Press Enter to stop playback...");
            System.in.read();

            // Stop the media player and release resources
            mediaPlayer.stop();
            mediaPlayer.release();
            mediaPlayerFactory.release();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
