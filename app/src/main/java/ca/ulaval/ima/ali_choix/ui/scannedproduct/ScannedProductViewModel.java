package ca.ulaval.ima.ali_choix.ui.scannedproduct;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ScannedProductViewModel extends ViewModel {

    private MutableLiveData<String> scannedProductDescription;

    public ScannedProductViewModel() {
        scannedProductDescription = new MutableLiveData<>();
        scannedProductDescription.setValue("This is the scanned product description");
    }

    public LiveData<String> getText() {
        return scannedProductDescription;
    }
}