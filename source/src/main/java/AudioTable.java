import javafx.scene.image.Image;
import javafx.scene.media.MediaPlayer;


public class AudioTable {

    private String artist;
    private String title;
    private String duration;
    private String bitrate;

    private MediaPlayer audioFileToPlay;
    private Image albumImage;

    public AudioTable(){
        this.artist = "";
        this.title = "";
        this.duration = "";
        this.bitrate = "";
        this.audioFileToPlay = null;
        this.albumImage = null;
    }

    public AudioTable(String artist, String title, String duration, String bitrate, MediaPlayer audioFileToPlay, Image albumImage){
        this.artist = artist;
        this.title = title;
        this.duration = duration;
        this.bitrate = bitrate;
        this.audioFileToPlay = audioFileToPlay;
        this.albumImage = albumImage;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getBitrate() {
        return bitrate;
    }

    public void setBitrate(String bitrate) {
        this.bitrate = bitrate;
    }

    public MediaPlayer getAudioFileToPlay(){
        return audioFileToPlay;
    }

    public void setAlbumImage(Image albumImage) {
        this.albumImage = albumImage;
    }

    public Image getAlbumImage() {
        return albumImage;
    }
}
