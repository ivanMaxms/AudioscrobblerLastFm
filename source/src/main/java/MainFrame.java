import de.umass.lastfm.Authenticator;
import javafx.application.Platform;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.embed.swing.SwingFXUtils;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.*;
import javafx.scene.layout.*;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.media.MediaPlayer;
import javafx.scene.shape.Circle;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class MainFrame extends Application {


    Button fileChooseBtn;
    AudioFiles audioFiles;
    ImageView albumImageView;
    TableView<AudioTable> audioTable;
    HBox mediaBar;
    BorderPane borderPane;
    MediaPlayer currentAudio;
    Slider time;
    ImageView playImage;
    ImageView pauseImage ;
    ImageView jumpBackImage;
    ImageView jumpForwardImage;
    ImageView replayImage;
    ImageView seekForwardImage;
    ImageView seekBackwardImage;
    ImageView shuffleImage;
    ImageView stopImage;

    Scene scene;

    int numberOfTracks;

    Button playPauseButton;
    Button jumpBackButton;
    Button jumpForwardButton;
    Button replayButton;
    Button seekBackwardButton;
    Button seekForwardButton;
    Button shuffleButton;
    Button stopButton;

    MenuBar menuBar;
    Menu mainMenu;
    MenuItem clearCmd;
    MenuItem exitCmd;
    MenuItem loginCmd;

    Scrobbling scrobbling;




    final KeyCombination kb = new KeyCodeCombination(KeyCode.N, KeyCombination.CONTROL_DOWN);

    @Override
    public void start(Stage stage) {

        menuBar = new MenuBar();
        mainMenu =  new Menu("File");
        clearCmd = new MenuItem("Clear list");
        exitCmd = new MenuItem("Exit");
        loginCmd = new MenuItem("Login to Last.fm");

        mainMenu.getItems().addAll(clearCmd, exitCmd ,loginCmd);
        menuBar.getMenus().addAll(mainMenu);

        stage.setTitle("MXMS-Player");


        TableColumn<AudioTable, String> titleColumn = new TableColumn<>();
        titleColumn .setText("Title");
        titleColumn .setMinWidth(290);
        titleColumn .setCellValueFactory(new PropertyValueFactory<>("title"));
        titleColumn.setStyle("-fx-alignment: CENTER;");
        //titleColumn.setSortable(false);

        TableColumn<AudioTable, String> artistColumn = new TableColumn<>();
        artistColumn.setText("Artist");
        artistColumn.setMinWidth(290);
        artistColumn.setCellValueFactory(new PropertyValueFactory<>("artist"));
        artistColumn.setStyle("-fx-alignment: CENTER;");
        //artistColumn.setSortable(false);

        TableColumn<AudioTable, String> bitrateColumn = new TableColumn<>();
        bitrateColumn.setText("Bitrate");
        bitrateColumn.setMinWidth(80);
        bitrateColumn.setMaxWidth(80);
        bitrateColumn.setCellValueFactory(new PropertyValueFactory<>("bitrate"));
        bitrateColumn.setStyle("-fx-alignment: CENTER;");
        //bitrateColumn.setSortable(false);

        TableColumn<AudioTable, String> durationColumn = new TableColumn<>();
        durationColumn .setText("Duration");
        durationColumn .setMinWidth(80);
        durationColumn .setMaxWidth(80);
        durationColumn .setCellValueFactory(new PropertyValueFactory<>("duration"));
        durationColumn.setStyle("-fx-alignment: CENTER;");
        //durationColumn.setSortable(false);

        audioTable = new TableView<>();
        //audioTable.setItems();
        audioTable.getColumns().addAll(artistColumn, titleColumn, bitrateColumn, durationColumn);
        audioTable.setPrefSize(600, 600);
        audioTable.setStyle("-fx-focus-color: transparent;");


        Insets insets = new Insets(10);


        mediaBar = new HBox();

        playPauseButton = new Button("");
        jumpBackButton = new Button("");
        jumpForwardButton = new Button("");
        replayButton = new Button("");
        seekBackwardButton = new Button("");
        seekForwardButton = new Button("");
        shuffleButton = new Button("");
        stopButton = new Button("");

        playPauseButton.setStyle("-fx-focus-color: transparent;");
        jumpBackButton.setStyle("-fx-focus-color: transparent;");
        jumpForwardButton.setStyle("-fx-focus-color: transparent;");
        replayButton.setStyle("-fx-focus-color: transparent;");
        seekBackwardButton.setStyle("-fx-focus-color: transparent;");
        seekForwardButton.setStyle("-fx-focus-color: transparent;");
        shuffleButton.setStyle("-fx-focus-color: transparent;");
        stopButton.setStyle("-fx-focus-color: transparent;");


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
            playImage = new ImageView(SwingFXUtils.toFXImage(bufferedImage, null));
            playImage.setFitWidth(2*r);
            playImage.setFitHeight(2*r);
            playImage.setSmooth(true);
            playImage.setCache(true);

            BufferedImage bufferedImage1 = ImageIO.read(getClass().getResource("/images/Pause.png"));
            pauseImage = new ImageView(SwingFXUtils.toFXImage(bufferedImage1, null));
            pauseImage.setFitWidth(2*r);
            pauseImage.setFitHeight(2*r);
            pauseImage.setSmooth(true);
            pauseImage.setCache(true);

            BufferedImage bufferedImage2 = ImageIO.read(getClass().getResource("/images/JumpBack.png"));
            jumpBackImage = new ImageView(SwingFXUtils.toFXImage(bufferedImage2, null));
            jumpBackImage.setFitWidth(2*r);
            jumpBackImage.setFitHeight(2*r);
            jumpBackImage.setSmooth(true);
            jumpBackImage.setCache(true);

            BufferedImage bufferedImage3 = ImageIO.read(getClass().getResource("/images/JumpForward.png"));
            jumpForwardImage = new ImageView(SwingFXUtils.toFXImage(bufferedImage3, null));
            jumpForwardImage.setFitWidth(2*r);
            jumpForwardImage.setFitHeight(2*r);
            jumpForwardImage.setSmooth(true);
            jumpForwardImage.setCache(true);

            BufferedImage bufferedImage4 = ImageIO.read(getClass().getResource("/images/Replay.png"));
            replayImage = new ImageView(SwingFXUtils.toFXImage(bufferedImage4, null));
            replayImage.setFitWidth(2*r);
            replayImage.setFitHeight(2*r);
            replayImage.setSmooth(true);
            replayImage.setCache(true);

            BufferedImage bufferedImage5 = ImageIO.read(getClass().getResource("/images/SeekBackward.png"));
            seekBackwardImage = new ImageView(SwingFXUtils.toFXImage(bufferedImage5, null));
            seekBackwardImage.setFitWidth(2*r);
            seekBackwardImage.setFitHeight(2*r);
            seekBackwardImage.setSmooth(true);
            seekBackwardImage.setCache(true);

            BufferedImage bufferedImage6 = ImageIO.read(getClass().getResource("/images/SeekForward.png"));
            seekForwardImage = new ImageView(SwingFXUtils.toFXImage(bufferedImage6, null));
            seekForwardImage.setFitWidth(2*r);
            seekForwardImage.setFitHeight(2*r);
            seekForwardImage.setSmooth(true);
            seekForwardImage.setCache(true);

            BufferedImage bufferedImage7 = ImageIO.read(getClass().getResource("/images/Shuffle.png"));
            shuffleImage = new ImageView(SwingFXUtils.toFXImage(bufferedImage7, null));
            shuffleImage.setFitWidth(2*r);
            shuffleImage.setFitHeight(2*r);
            shuffleImage.setSmooth(true);
            shuffleImage.setCache(true);

            BufferedImage bufferedImage8 = ImageIO.read(getClass().getResource("/images/Stop.png"));
            stopImage = new ImageView(SwingFXUtils.toFXImage(bufferedImage8, null));
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
        /*time.setStyle("-fx-background-color:null;" +
        "-fx-background-insets: 1 0 -1 0, 0, 1;" +
        "-fx-background-radius: 2.5, 2.5, 1.5;" +
        "-fx-padding: 0.208333em;");*/
        time.setStyle("-fx-control-inner-background: palegreen;");

        Slider vol = new Slider();
        Label volume = new Label("Volume: ");

        vol.setStyle("-fx-control-inner-background: blue;");
        volume.setStyle("-fx-font-family: \"arial\";" +
                "-fx-font-size: 16px;" +
                "-fx-font-style: italic;"
        );


        mediaBar.setAlignment(Pos.BOTTOM_CENTER);
        mediaBar.setPadding(new Insets(5, 10, 5, 10));
        vol.setPrefWidth(70);
        vol.setMinWidth(30);
        vol.setValue(100);
        mediaBar.setHgrow(time,  Priority.ALWAYS);
        //playPauseButton.setPrefWidth(30);
        mediaBar.getChildren().addAll(jumpBackButton, seekBackwardButton,playPauseButton, stopButton, seekForwardButton,
                jumpForwardButton, replayButton, shuffleButton, time, volume, vol);

        VBox menuAndMedia = new VBox();
        menuAndMedia.getChildren().addAll(menuBar, mediaBar);

        VBox fileChooseMenu = new VBox(70);

        try{
            BufferedImage bImage = ImageIO.read(getClass().getResource("/images/StartImage.jpg"));
            Image albumImage = SwingFXUtils.toFXImage(bImage, null);
            albumImageView = new ImageView(albumImage);
            albumImageView.setFitHeight(300);
            albumImageView.setFitWidth(300);
        }
        catch (IOException ex){
            ex.printStackTrace();
        }

        fileChooseBtn = new Button("Choose File");
        fileChooseBtn.setStyle("-fx-focus-color: transparent;");
        fileChooseMenu.setAlignment(Pos.CENTER);
        fileChooseMenu.getChildren().addAll(albumImageView, fileChooseBtn);


        audioFiles = new AudioFiles();

        VBox tableContainer = new VBox(20);
        tableContainer.getChildren().addAll(audioTable);

        borderPane = new BorderPane();
        borderPane.setMargin(fileChooseMenu, insets);
        borderPane.setMargin(mediaBar, insets);
        borderPane.setMargin(tableContainer, insets);
        borderPane.setTop(menuAndMedia);
        borderPane.setLeft(fileChooseMenu);
        borderPane.setCenter(tableContainer);

        borderPane.setStyle("-fx-focus-color: transparent;");
        borderPane.setStyle("-fx-faint-focus-color: transparent;");
        scene = new Scene(borderPane, 1080, 720);

        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();


        fileChooseBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {

                FileChooser fileChooser = new FileChooser();
                fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Audio Files", "*.mp3"));
                List<File> list = fileChooser.showOpenMultipleDialog(stage);

                HBox secondaryLayout = new HBox();
                ProgressIndicator progressIndicator = new ProgressIndicator();progressIndicator.setMaxSize(50, 50);

                secondaryLayout.getChildren().addAll(progressIndicator);
                secondaryLayout.setAlignment(Pos.CENTER);
                Scene secondScene = new Scene(secondaryLayout, 230, 100);

                // New window (Stage)
                Stage newWindow = new Stage();
                newWindow.setTitle("Parsing files...");
                newWindow.setScene(secondScene);

                newWindow.initStyle(StageStyle.UTILITY);
                newWindow.show();

                audioFiles.parseFiles(list);
                //removeDublicates();
                newWindow.close();
                setTable();

                audioFiles.isDisplayed();


            }
        });

        audioTable.setOnMouseClicked(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent click) {

                if (click.getClickCount() == 2) {

                    if (currentAudio != null){
                        currentAudio.stop();
                        if (scrobbling != null) {
                            scrobbling.endPlaying();
                        }
                    }

                    audioTable.getItems().get(audioTable.getSelectionModel().getSelectedIndex()).getAudioFileToPlay().play();
                    playPauseButton.setGraphic(pauseImage);
                    currentAudio = audioTable.getItems().get(audioTable.getSelectionModel().getSelectedIndex()).getAudioFileToPlay();
                    if (scrobbling != null)
                        scrobbling.setNowPlayingAudio(audioTable.getItems().get(audioTable.getSelectionModel().getSelectedIndex()).getArtist(),
                            audioTable.getItems().get(audioTable.getSelectionModel().getSelectedIndex()).getTitle());
                    currentAudio.currentTimeProperty().addListener(new InvalidationListener() {
                        public void invalidated(Observable ov) {
                            updateValues();
                        }
                    });
                    audioFiles.setNowPlayingInd(audioTable.getSelectionModel().getSelectedIndex());
                    albumImageView.setImage(audioTable.getItems().get(audioTable.getSelectionModel().getSelectedIndex()).getAlbumImage());
                }
            }
        });

        playPauseButton.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                if (currentAudio == null){
                    return;
                }

                MediaPlayer.Status status = currentAudio.getStatus();


                if (status == MediaPlayer.Status.PLAYING) {
                    if (currentAudio.getCurrentTime().greaterThanOrEqualTo(currentAudio.getTotalDuration())) {
                        currentAudio.seek(currentAudio.getStartTime());
                        currentAudio.play();
                    } else {
                        currentAudio.pause();
                        playPauseButton.setGraphic(playImage);
                    }
                }
                if (status == MediaPlayer.Status.PAUSED || status == MediaPlayer.Status.HALTED || status == MediaPlayer.Status.STOPPED) {
                    currentAudio.play();
                    playPauseButton.setGraphic(pauseImage);
                }
            }
        });

        stopButton.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                if (currentAudio == null){
                    return;
                }
                currentAudio.stop();
                playPauseButton.setGraphic(playImage);
                if (scrobbling != null)
                    scrobbling.endPlaying();
            }
        });


        seekBackwardButton.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                if (currentAudio == null){
                    return;
                }
                currentAudio.seek(currentAudio.getCurrentTime().divide(1.2));
            }
        });

        seekForwardButton.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                if (currentAudio == null){
                    return;
                }
                currentAudio.seek(currentAudio.getCurrentTime().multiply(1.2));
            }
        });



        time.valueProperty().addListener(new InvalidationListener() {

            @Override
            public void invalidated(Observable ov) {
                // TODO Auto-generated method stub
                if (time.getValue() > time.getMax()*0.99) {

                    /*if(audioFiles.audioToPlay.get(audioFiles.audioToPlay.size()).equals(currentAudio)){
                        audioFiles.audioToPlay.get(audioFiles.getNowPlayingInd()).stop();
                    }*/

                    jumpForwardButton.fire();
                    currentAudio.currentTimeProperty().addListener(new InvalidationListener() {
                        public void invalidated(Observable ov) {
                            updateValues();
                        }
                    });
                    /*currentAudio.stop();
                    if(scrobbling != null)
                        scrobbling.endPlaying();
                    audioTable.getItems().get(audioFiles.getNowPlayingInd() + 1).getAudioFileToPlay().play();
                    playPauseButton.setGraphic(pauseImage);
                    currentAudio = audioTable.getItems().get(audioFiles.getNowPlayingInd() + 1).getAudioFileToPlay();
                    if(scrobbling != null){
                        scrobbling.setNowPlayingAudio(audioTable.getItems().get(audioFiles.getNowPlayingInd()+1).getArtist(),
                                audioTable.getItems().get(audioFiles.getNowPlayingInd()+1).getTitle());
                    }

                    currentAudio.currentTimeProperty().addListener(new InvalidationListener() {
                        public void invalidated(Observable ov) {
                            updateValues();
                        }
                    });
                    audioFiles.setNowPlayingInd(audioFiles.getNowPlayingInd() + 1);
                    albumImageView.setImage(audioFiles.audioList.get(audioTable.getSelectionModel().getSelectedIndex()).getImage());*/
                }
                if (time.isPressed()) {
                    currentAudio.seek(currentAudio.getMedia().getDuration().multiply(time.getValue() / 100));
                }
            }

        });

        jumpBackButton.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                if ((currentAudio == null) || (audioTable.getItems().get(0).getAudioFileToPlay().equals(currentAudio))){
                    return;
                }

                currentAudio.stop();
                if (scrobbling != null)
                    scrobbling.endPlaying();

                audioTable.getItems().get(audioFiles.getNowPlayingInd() - 1).getAudioFileToPlay().play();
                audioTable.getSelectionModel().selectPrevious();
                playPauseButton.setGraphic(pauseImage);
                currentAudio = audioTable.getItems().get(audioFiles.getNowPlayingInd() - 1).getAudioFileToPlay();
                if (scrobbling != null) {
                    scrobbling.setNowPlayingAudio(audioTable.getItems().get(audioFiles.getNowPlayingInd() - 1).getArtist(),
                            audioTable.getItems().get(audioFiles.getNowPlayingInd() - 1).getTitle());
                }
                currentAudio.currentTimeProperty().addListener(new InvalidationListener() {
                    public void invalidated(Observable ov) {
                        updateValues();
                    }
                });
                audioFiles.setNowPlayingInd(audioFiles.getNowPlayingInd() - 1);
                albumImageView.setImage(audioTable.getItems().get(audioTable.getSelectionModel().getSelectedIndex()).getAlbumImage());
            }
        });

        jumpForwardButton.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                if ((currentAudio == null) || (audioTable.getItems().get(audioFiles.audioList.size()-1).getAudioFileToPlay().equals(currentAudio))){
                    return;
                }
                currentAudio.stop();
                if (scrobbling != null)
                    scrobbling.endPlaying();
                audioTable.getItems().get(audioFiles.getNowPlayingInd() + 1).getAudioFileToPlay().play();
                audioTable.getSelectionModel().selectNext();
                playPauseButton.setGraphic(pauseImage);
                currentAudio = audioTable.getItems().get(audioFiles.getNowPlayingInd() + 1).getAudioFileToPlay();
                if (scrobbling != null){
                    scrobbling.setNowPlayingAudio(audioTable.getItems().get(audioFiles.getNowPlayingInd()+1).getArtist(),
                            audioTable.getItems().get(audioFiles.getNowPlayingInd()+1).getTitle());
                }

                currentAudio.currentTimeProperty().addListener(new InvalidationListener() {
                    public void invalidated(Observable ov) {
                        updateValues();
                    }
                });
                audioFiles.setNowPlayingInd(audioFiles.getNowPlayingInd() + 1);
                albumImageView.setImage(audioTable.getItems().get(audioTable.getSelectionModel().getSelectedIndex()).getAlbumImage());
            }
        });

        //audioTable.setOnMouseClicked();

        replayButton.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                currentAudio.stop();
                if (scrobbling != null)
                    scrobbling.endPlaying();
                currentAudio.play();
                if (scrobbling != null){
                    scrobbling.setNowPlayingAudio(audioTable.getItems().get(audioFiles.getNowPlayingInd()).getArtist(),
                            audioTable.getItems().get(audioFiles.getNowPlayingInd()).getTitle());
                }

            }
        });

        shuffleButton.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                Collections.shuffle(audioTable.getItems());
                int nowPlayingInd = 0;
                numberOfTracks = audioFiles.audioList.size();
                for (int i = 0; i < numberOfTracks; i++){
                    if (audioTable.getItems().get(i).getAudioFileToPlay().equals(currentAudio))
                        nowPlayingInd = i;

                }
                audioFiles.setNowPlayingInd(nowPlayingInd);
                audioTable.getSelectionModel().select(nowPlayingInd);

            }
        });


        clearCmd.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                audioTable.getItems().clear();
            }
        });

        loginCmd.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                VBox secondaryLayout = new VBox();
                secondaryLayout.setPadding(new Insets(20, 20, 20, 20));
                Label infoLabel = new Label("We want you to Last.fm login To allow access of our app to your Last.fm account. " +
                        "If you already login, please push button 'Ok'");
                infoLabel.setWrapText(true);
                Button logButton = new Button("Redirect");
                Button okButton = new Button("Ok");

                infoLabel.setStyle("-fx-effect: dropshadow( gaussian , rgba(255,255,255,0.5) , 0,0,0,1 );");

                secondaryLayout.getChildren().addAll(infoLabel, logButton, okButton);
                secondaryLayout.setAlignment(Pos.CENTER);
                Scene secondScene = new Scene(secondaryLayout, 480, 160);

                // New window (Stage)
                Stage logWindow = new Stage();
                logWindow.setTitle("Login to Last.fm");
                logWindow.setScene(secondScene);
                //logWindow.initStyle(StageStyle.UNDECORATED);

                logWindow.show();

                logButton.setOnAction(new EventHandler<ActionEvent>() {
                    @Override public void handle(ActionEvent e) {
                        scrobbling = new Scrobbling();
                    }
                });

                okButton.setOnAction(new EventHandler<ActionEvent>() {
                    @Override public void handle(ActionEvent e) {
                        if (scrobbling.token != null){
                            scrobbling.session = Authenticator.getSession(scrobbling.token, scrobbling.apiKey, scrobbling.apiSecret);
                            if (currentAudio != null) {
                                scrobbling.setNowPlayingAudio(audioTable.getItems().get(audioFiles.getNowPlayingInd()).getArtist(),
                                        audioTable.getItems().get(audioFiles.getNowPlayingInd()).getTitle());
                            }
                        }
                        logWindow.close();
                    }
                });


                //Scrobbling scrobbling = new Scrobbling();

            }
        });


        final KeyCombination keyComb1 = new KeyCodeCombination(KeyCode.E,
                KeyCombination.CONTROL_DOWN);
        scene.addEventHandler(KeyEvent.KEY_RELEASED, new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (keyComb1.match(event)) {
                    stage.close();
                }
            }
        });

        exitCmd.setAccelerator(new KeyCodeCombination(KeyCode.E, KeyCombination.CONTROL_DOWN));
        /*scene.addEventHandler(KeyEvent.KEY_RELEASED, new EventHandler<ActionEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (keyComb1.match(event)) {
                    System.out.println("Ctrl+R pressed");
                }
            }
        });*/


        /*audioTable.setOnSort(sortEvent->{

        });*/


        vol.valueProperty().addListener(new InvalidationListener() {

            @Override
            public void invalidated(Observable ov) {
                if (vol.isPressed()) {
                    currentAudio.setVolume(vol.getValue() / 100);
                }
            }
        });
    }

    protected void updateValues() {
        Platform.runLater(new Runnable() {
            public void run() {
                time.setValue(currentAudio.getCurrentTime().toMillis() / currentAudio.getTotalDuration().toMillis() * 100);
            }
        });
    }

    public void setTable(){
        List<AudioTable> newAudios = new ArrayList<>();



        for (int i = audioFiles.audioList.size() - audioFiles.successfulImported; i < audioFiles.audioList.size(); i++){
            newAudios.add(new AudioTable(audioFiles.audioList.get(i).getArtist(), audioFiles.audioList.get(i).getTitle(),
                    String.valueOf(Integer.valueOf(audioFiles.audioList.get(i).getDuration())/60) + ":"
                            + String.valueOf(Integer.valueOf(audioFiles.audioList.get(i).getDuration())%60),
                    audioFiles.audioList.get(i).getBitrate() + " kbps", audioFiles.audioList.get(i).getAudioFileToPlay(),
                    audioFiles.audioList.get(i).getImage()));
        }

        //audioTable.getItems().clear();
        audioTable.getItems().addAll(newAudios);

    }

    public static void main(String[] args) {
        launch(args);
    }
}
