package ca.ulaval.ima.ali_choix.services;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import androidx.annotation.Nullable;

import java.util.List;

import ca.ulaval.ima.ali_choix.Infrastructure.HistoricRepositoryLocal;
import ca.ulaval.ima.ali_choix.domain.HistoricElement;
import ca.ulaval.ima.ali_choix.domain.HistoricElementFactory;
import ca.ulaval.ima.ali_choix.domain.HistoricRepository;
import ca.ulaval.ima.ali_choix.domain.ProductId;

public class HistoricService{
    private HistoricRepository historicRepository;
    private HistoricElementFactory historicElementFactory;

    public HistoricService(){
        historicRepository = new HistoricRepositoryLocal();
        getHistoricFromDevice();
        historicElementFactory = new HistoricElementFactory();
    }

    public void addHistoricElement(String productId, String image_front_url, String productName){
        HistoricElement addedHistoricElement = historicElementFactory.create(productId,image_front_url,productName);
        historicRepository.addElement(addedHistoricElement);
        saveHistoricOnDevice();
    }

    public void removeHistoricElement(String productId){
        ProductId removedProductId = new ProductId(productId);
        historicRepository.removeElement(removedProductId);
        saveHistoricOnDevice();
    }

    public void removeAllHistoric(){
        historicRepository.removeAllElement();
        saveHistoricOnDevice();
    }

    public List<HistoricElement> getHistoric(){
        return historicRepository.getHistoric();
    }

    public String getLastSearchedProductId(){
        return historicRepository.getLastSearchedProductId().toString();
    }

    private void saveHistoricOnDevice(){
      //TODO
    }

    private void getHistoricFromDevice(){
        //TODO
    }
}
