import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.UnsupportedTagException;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AudioFiles extends Thread {
    int successfulImported;
    int invalidImported;
    int nowPlayingInd;
    List<File> list;
    ArrayList<FileParser> audioList;
    private boolean wasDisplayed;


    public AudioFiles(){
        audioList = new ArrayList<>();
    }


    public void parseFiles(List<File> listFromFileChooser){
        ParseFilesThread parseFilesThread = new ParseFilesThread(listFromFileChooser);

        parseFilesThread.start();
        try{
            parseFilesThread.join();
        }
        catch (InterruptedException ex){ex.printStackTrace();}
        successfulImported = parseFilesThread.successfulImported;
        invalidImported = parseFilesThread.invalidImported;
        audioList.addAll(parseFilesThread.audioListParsed);
      }

    public void setNowPlayingInd(int x){
        nowPlayingInd = x;
    }

    public int getNowPlayingInd(){
        return nowPlayingInd;
    }

    public boolean isWasDisplayed(){
        return  wasDisplayed;
    }

    public void isDisplayed(){
        wasDisplayed = true;
    }
}

class ParseFilesThread extends Thread {
    List<File> list;
    ArrayList<FileParser> audioListParsed;
    int successfulImported;
    int invalidImported;


    public ParseFilesThread(List<File> listFromFileChooser){
        audioListParsed = new ArrayList<>();
        list = listFromFileChooser;
        invalidImported = 0;
    }

    @Override
    public void run(){
        if (list != null) {
            for (int i = 0; i < list.size(); i++) {
                try{
                    FileParser parsedFile = new FileParser(list.get(i));
                    audioListParsed.add(parsedFile);
                }
                catch (IOException ex){invalidImported++;}
                catch (UnsupportedTagException ex){invalidImported++;}
                catch (InvalidDataException ex){invalidImported++;}
            }
        }
        successfulImported = list.size() - invalidImported;
    }

}



