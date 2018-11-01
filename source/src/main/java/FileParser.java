import com.mpatric.mp3agic.*;
import javafx.application.Application;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.scene.image.WritableImage;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

class FileParser{
    private Mp3File mp3file;

    private final double minValidFileSize = 256;

    private String artist;
    private String title;
    private String duration;
    private String bitrate;
    Image albumImageFromAudiofile;

    MediaPlayer audioFileToPlay;


    FileParser(File file) throws IOException, UnsupportedTagException, InvalidDataException {
        if(file.length() <= minValidFileSize)
            throw new IOException("noaudio");
        mp3file = new Mp3File(file);

        bitrate = checkBitrate();
        duration = checkDuration();
        if(mp3file.hasId3v2Tag()){
            initInfOutFileID3v2();
        }
        else if(mp3file.hasId3v1Tag()){
            initInfOutFileID3v1();
        }
        String path = file.getAbsolutePath();
        path = path.replace("\\", "/");
        Media media = new Media(new File(path).toURI().toString());
        audioFileToPlay = new MediaPlayer(media);
    }


    public String checkBitrate(){
        return String.valueOf(mp3file.getBitrate());
    }

    public String checkDuration(){
        return String.valueOf(mp3file.getLengthInSeconds());
    }



    public void initInfOutFileID3v2(){
        if (mp3file.hasId3v2Tag()) {
            ID3v2 id3v2Tag = mp3file.getId3v2Tag();
            artist =id3v2Tag.getArtist();
            title = id3v2Tag.getTitle();
            byte[] buffer = id3v2Tag.getAlbumImage();
            if(buffer   != null){
                albumImageFromAudiofile = convertToJavaFXImage(buffer, 500, 500);
            }
            else{
                try{
                    BufferedImage bImage = ImageIO.read(getClass().getResource("/images/DefaultImage.jpg"));
                    Image im = SwingFXUtils.toFXImage(bImage, null);
                    albumImageFromAudiofile = im;

                }
                catch (IOException ex){}
            }

        }
    }
    public void initInfOutFileID3v1() {
        if (mp3file.hasId3v1Tag()) {
            ID3v1 id3v1Tag = mp3file.getId3v1Tag();
            artist =id3v1Tag.getArtist();
            title = id3v1Tag.getTitle();
            try{
                BufferedImage bImage = ImageIO.read(getClass().getResource("/images/DefaultImage.jpg"));
                albumImageFromAudiofile = SwingFXUtils.toFXImage(bImage, null);

            }
            catch (IOException ex){}
        }
    }

    public String getArtist() {
        if(artist == null)
            return (new String("Unknown artist"));
        return artist;
    }

    public String getTitle() {
        if(artist == null)
            return (new String("Unknown track"));
        return title;
    }

    public String getDuration() {
        return duration;
    }

    public String getBitrate() {
        return bitrate;
    }

    private Image convertToJavaFXImage(byte[] raw, final int width, final int height) {
        WritableImage image = new WritableImage(width, height);
        try {
            ByteArrayInputStream bis = new ByteArrayInputStream(raw);
            BufferedImage read = ImageIO.read(bis);
            if (read == null){
                BufferedImage bImage = ImageIO.read(getClass().getResource("/images/DefaultImage.jpg"));
                albumImageFromAudiofile = SwingFXUtils.toFXImage(bImage, null);
            }
            else{
                image = SwingFXUtils.toFXImage(read, null);
            }
        } catch (IOException ex) {
            Logger.getLogger(Application.class.getName()).log(Level.SEVERE, null, ex);
        }
        return image;
    }

    public MediaPlayer getAudioFileToPlay() {
        return audioFileToPlay;
    }

    public Image getImage(){
        return albumImageFromAudiofile;
    }

}