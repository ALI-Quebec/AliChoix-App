package ca.ulaval.ima.ali_choix.ui.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class HomeViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public HomeViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Alimentation saine, durable, en toute simplicité!");
    }

    public LiveData<String> getText() {
        return mText;
    }
}