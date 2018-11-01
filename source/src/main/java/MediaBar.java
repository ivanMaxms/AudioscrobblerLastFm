import javafx.application.Platform;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaPlayer.Status;
import javafx.scene.shape.Circle;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;


public class MediaBar extends HBox{

    Button playPauseButton = new Button("");
    Button jumpBackButton = new Button("");
    Button jumpForwardButton = new Button("");
    Button replayButton = new Button("");
    Button seekBackwardButton = new Button("");
    Button seekForwardButton = new Button("");
    Button shuffleButton = new Button("");
    Button stopButton = new Button("");

    Slider time;

    MediaPlayer player;

    public MediaBar(MediaPlayer play) {
        player = play;

        double r = 20;

        playPauseButton.setShape(new Circle(r));
        playPauseButton.setMinSize(2*r, 2*r);
        playPauseButton.setMaxSize(2*r, 2*r);
        jumpForwardButton.setShape(new Circle(r));
        jumpForwardButton.setMinSize(2*r, 2*r);
        jumpForwardButton.setMaxSize(2*r, 2*r);
        jumpBackButton.setShape(new Circle(r));
        jumpBackButton.setMinSize(2*r, 2*r);
        jumpBackButton.setMaxSize(2*r, 2*r);
        replayButton.setShape(new Circle(r));
        replayButton.setMinSize(2*r, 2*r);
        replayButton.setMaxSize(2*r, 2*r);
        seekBackwardButton.setShape(new Circle(r));
        seekBackwardButton.setMinSize(2*r, 2*r);
        seekBackwardButton.setMaxSize(2*r, 2*r);
        seekForwardButton.setShape(new Circle(r));
        seekForwardButton.setMinSize(2*r, 2*r);
        seekForwardButton.setMaxSize(2*r, 2*r);
        shuffleButton.setShape(new Circle(r));
        shuffleButton.setMinSize(2*r, 2*r);
        shuffleButton.setMaxSize(2*r, 2*r);
        stopButton.setShape(new Circle(r));
        stopButton.setMinSize(2*r, 2*r);
        stopButton.setMaxSize(2*r, 2*r);


        try{
            BufferedImage bufferedImage = ImageIO.read(getClass().getResource("/images/Play.png"));
            ImageView playImage = new ImageView(SwingFXUtils.toFXImage(bufferedImage, null));
            playImage.setFitWidth(2*r);
            playImage.setFitHeight(2*r);
            playImage.setSmooth(true);
            playImage.setCache(true);

            BufferedImage bufferedImage1 = ImageIO.read(getClass().getResource("/images/Pause.png"));
            ImageView pauseImage = new ImageView(SwingFXUtils.toFXImage(bufferedImage1, null));
            pauseImage.setFitWidth(2*r);
            pauseImage.setFitHeight(2*r);
            pauseImage.setSmooth(true);
            pauseImage.setCache(true);

            BufferedImage bufferedImage2 = ImageIO.read(getClass().getResource("/images/JumpBack.png"));
            ImageView jumpBackImage = new ImageView(SwingFXUtils.toFXImage(bufferedImage2, null));
            jumpBackImage.setFitWidth(2*r);
            jumpBackImage.setFitHeight(2*r);
            jumpBackImage.setSmooth(true);
            jumpBackImage.setCache(true);

            BufferedImage bufferedImage3 = ImageIO.read(getClass().getResource("/images/JumpForward.png"));
            ImageView jumpForwardImage = new ImageView(SwingFXUtils.toFXImage(bufferedImage3, null));
            jumpForwardImage.setFitWidth(2*r);
            jumpForwardImage.setFitHeight(2*r);
            jumpForwardImage.setSmooth(true);
            jumpForwardImage.setCache(true);

            BufferedImage bufferedImage4 = ImageIO.read(getClass().getResource("/images/Replay.png"));
            ImageView replayImage = new ImageView(SwingFXUtils.toFXImage(bufferedImage4, null));
            replayImage.setFitWidth(2*r);
            replayImage.setFitHeight(2*r);
            replayImage.setSmooth(true);
            replayImage.setCache(true);

            BufferedImage bufferedImage5 = ImageIO.read(getClass().getResource("/images/SeekBackward.png"));
            ImageView seekBackwardImage = new ImageView(SwingFXUtils.toFXImage(bufferedImage5, null));
            seekBackwardImage.setFitWidth(2*r);
            seekBackwardImage.setFitHeight(2*r);
            seekBackwardImage.setSmooth(true);
            seekBackwardImage.setCache(true);

            BufferedImage bufferedImage6 = ImageIO.read(getClass().getResource("/images/SeekForward.png"));
            ImageView seekForwardImage = new ImageView(SwingFXUtils.toFXImage(bufferedImage6, null));
            seekForwardImage.setFitWidth(2*r);
            seekForwardImage.setFitHeight(2*r);
            seekForwardImage.setSmooth(true);
            seekForwardImage.setCache(true);

            BufferedImage bufferedImage7 = ImageIO.read(getClass().getResource("/images/Shuffle.png"));
            ImageView shuffleImage = new ImageView(SwingFXUtils.toFXImage(bufferedImage7, null));
            shuffleImage.setFitWidth(2*r);
            shuffleImage.setFitHeight(2*r);
            shuffleImage.setSmooth(true);
            shuffleImage.setCache(true);

            BufferedImage bufferedImage8 = ImageIO.read(getClass().getResource("/images/Stop.png"));
            ImageView stopImage = new ImageView(SwingFXUtils.toFXImage(bufferedImage8, null));
            stopImage.setFitWidth(2*r);
            stopImage.setFitHeight(2*r);
            stopImage.setSmooth(true);
            stopImage.setCache(true);



            playPauseButton.setGraphic(playImage);
            jumpBackButton.setGraphic(jumpBackImage);
            jumpForwardButton.setGraphic(jumpForwardImage);
            replayButton.setGraphic(replayImage);
            seekBackwardButton.setGraphic(seekBackwardImage);
            seekForwardButton.setGraphic(seekForwardImage);
            shuffleButton.setGraphic(shuffleImage);
            stopButton.setGraphic(stopImage);
        }
        catch (IOException ex){
            ex.printStackTrace();
        }


        time = new Slider();
        time.setPadding(new Insets(5, 20, 0, 10));
        Slider vol = new Slider();
        Label volume = new Label("Volume: ");

        setAlignment(Pos.CENTER);
        setPadding(new Insets(5, 10, 5, 10));
        vol.setPrefWidth(70);
        vol.setMinWidth(30);
        vol.setValue(100);
        setHgrow(time,  Priority.ALWAYS);
        //playPauseButton.setPrefWidth(30);
        getChildren().addAll(jumpBackButton, seekBackwardButton,playPauseButton, stopButton, seekForwardButton,
                jumpForwardButton, replayButton, shuffleButton, time, volume, vol);

        playPauseButton.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                Status status = player.getStatus();

                if (status == Status.PLAYING) {
                    if (player.getCurrentTime().greaterThanOrEqualTo(player.getTotalDuration())) {
                        player.seek(player.getStartTime());
                        player.play();
                    } else {
                        player.pause();
                        playPauseButton.setText(">");
                    }
                }
                if (status == Status.PAUSED || status == Status.HALTED || status == Status.STOPPED) {
                    player.play();
                    playPauseButton.setText("||");
                }
            }
        });

        player.currentTimeProperty().addListener(new InvalidationListener() {
            public void invalidated(Observable ov) {
                updateValues();
            }
        });

        time.valueProperty().addListener(new InvalidationListener() {

            @Override
            public void invalidated(Observable ov) {
                // TODO Auto-generated method stub
                if (time.isPressed()) {
                    player.seek(player.getMedia().getDuration().multiply(time.getValue() / 100));
                }
            }

        });

        vol.valueProperty().addListener(new InvalidationListener() {

            @Override
            public void invalidated(Observable ov) {
                if (vol.isPressed()) {
                    player.setVolume(vol.getValue() / 100);
                }
            }
        });
    }

    protected void updateValues() {
        Platform.runLater(new Runnable() {
            public void run() {
                time.setValue(player.getCurrentTime().toMillis() / player.getTotalDuration().toMillis() * 100);
            }
        });
    }
}