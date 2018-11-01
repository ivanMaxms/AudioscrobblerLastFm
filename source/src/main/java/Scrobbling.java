import de.umass.lastfm.Authenticator;
import de.umass.lastfm.Session;
import de.umass.lastfm.Track;
import de.umass.lastfm.scrobble.ScrobbleData;

import java.io.IOException;
import java.net.*;
import java.time.Instant;

public class Scrobbling {
    final static String apiKey = "fa6d295e7c8de9c0f49b44dcdf0ad48d";
    final static String apiSecret = "45e861fcf37a3527483b19c0515dc7c9";
    Session session;
    String token;
    ScrobbleData currentAudioScrobbleData;
    int startTime;
    int endTime;

    public Scrobbling(){

        token = Authenticator.getToken(apiKey);

        String httpRequestAuth = "http://www.last.fm/api/auth/?api_key="
                + apiKey + "&token=" +  token;

        //logged = false;


        try {
            URI uri = new URI(httpRequestAuth);
            java.awt.Desktop.getDesktop().browse(uri);

        } catch (IOException ex) {
            ex.printStackTrace();
        }  catch (URISyntaxException ex) {
            ex.printStackTrace();
        }
        /*if(!logged) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException ex) {
            }
        }*/


    }

    public void setCurrentAudioScrobbleData(String artist, String title){
        this.currentAudioScrobbleData = new ScrobbleData(artist,
                title, getCurrentUnixTime());
    }

    public ScrobbleData getCurrentAudioScrobbleData() {
        return currentAudioScrobbleData;
    }
    public void setNowPlayingAudio(String artist, String title){
        startTime = (int) Instant.now().getEpochSecond();
        setCurrentAudioScrobbleData(artist, title);
        Track.updateNowPlaying(currentAudioScrobbleData, session);
    }

    public void endPlaying(){
        endTime = (int) Instant.now().getEpochSecond();
        if((endTime - startTime) > 30){
            Track.scrobble(currentAudioScrobbleData, session);
        }
    }

    public int getCurrentUnixTime(){
        return  (int) Instant.now().getEpochSecond();
    }

}
