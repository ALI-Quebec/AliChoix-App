package ca.ulaval.ima.ali_choix.ui.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class HomeViewModel extends ViewModel {

    private MutableLiveData<String> middleText;
    private MutableLiveData<String> bottomText;

    public HomeViewModel() {
        middleText = new MutableLiveData<>();
        middleText.setValue("Alimentation saine, durable, en toute simplicité!");
        bottomText = new MutableLiveData<>();
        bottomText.setValue("Recherchez vos produits dès maintenant!");
    }

    public LiveData<String> getMiddleText() {
        return middleText;
    }

    public LiveData<String> getBottomText() {
        return bottomText;
    }
}