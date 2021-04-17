package ca.ulaval.ima.ali_choix.Infrastructure;

import android.app.Activity;
import android.content.Context;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import ca.ulaval.ima.ali_choix.domain.history.HistoryRepositoryCollector;
import ca.ulaval.ima.ali_choix.domain.history.HistoryElement;
import ca.ulaval.ima.ali_choix.domain.history.HistoryRepository;

public class LocalHistoryFile implements HistoryRepositoryCollector {

    private static final String HistoryFileName = "LocalHistory";

    public void saveHistory(HistoryRepository historyRepository, Context context){
        List<HistoryElement> historyElements = historyRepository.getHistory();

        FileOutputStream fileOutputStream;
        ObjectOutputStream objectOutputStream=null;
        try {
            fileOutputStream= context.openFileOutput(HistoryFileName, Activity.MODE_PRIVATE);
            objectOutputStream=new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(historyElements);
        }

        catch(IOException ex)
        {
            throw new HistorySavingException();
        }
        finally
        {
            try {
                objectOutputStream.close();
            } catch (IOException e) {
                throw new HistorySavingException();
            }
        }
    }

    public HistoryRepository loadHistory(Context context){
        List<HistoryElement> historyElements = null;

        FileInputStream fileInputStream;
        ObjectInputStream objectInputStream=null;
        try {
            fileInputStream= context.openFileInput(HistoryFileName);
            objectInputStream=new ObjectInputStream(fileInputStream);
            historyElements =(ArrayList<HistoryElement>)objectInputStream.readObject();
        }
        catch(FileNotFoundException ex)
        {
            historyElements=new ArrayList<>();
        }
        catch(IOException ex)
        {
            throw new HistoryLoadingException();
        } catch (ClassNotFoundException e) {
            throw new HistoryLoadingException();
        } finally
        {
            try {
                if(objectInputStream != null) {
                    objectInputStream.close();
                }
            } catch (IOException e) {
                throw new HistoryLoadingException();
            }
        }

        if(historyElements==null){
            historyElements=new ArrayList<>();
        }

        return new HistoryRepositoryLocal(historyElements);
    }
}
