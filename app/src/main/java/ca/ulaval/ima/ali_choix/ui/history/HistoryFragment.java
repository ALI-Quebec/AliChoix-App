package ca.ulaval.ima.ali_choix.ui.history;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.ListFragment;

import java.util.ArrayList;

import ca.ulaval.ima.ali_choix.R;
import ca.ulaval.ima.ali_choix.domain.HistoricElement;
import ca.ulaval.ima.ali_choix.domain.HistoricElementFactory;

public class HistoryFragment extends ListFragment {
    private HistoricElementFactory historicElementFactory;

    ArrayList<HistoricElement> listItems=new ArrayList<>();
    ArrayAdapter<HistoricElement> adapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_history, container, false);

        historicElementFactory = new HistoricElementFactory();
        HistoricElement h1 = historicElementFactory.create("1","https://static.openfoodfacts.org/images/products/067/721/009/0246/front_en.7.400.jpg","Nom du produit vraiment long mais pas trop");
        HistoricElement h2 = historicElementFactory.create("2","https://static.openfoodfacts.org/images/products/067/721/009/0246/front_en.7.400.jpg","Nom du produit vraiment long mais pas trop");
        HistoricElement h3 = historicElementFactory.create("3","https://static.openfoodfacts.org/images/products/067/721/009/0246/front_en.7.400.jpg","Nom du produit vraiment long mais pas trop");
        HistoricElement h4 = historicElementFactory.create("4","https://static.openfoodfacts.org/images/products/067/721/009/0246/front_en.7.400.jpg","Nom du produit vraiment long mais pas trop");

        listItems.add(h1);
        listItems.add(h2);
        listItems.add(h3);
        listItems.add(h4);

        adapter=new ArrayAdapter<HistoricElement>(getActivity(),
                android.R.layout.activity_list_item,
                listItems);
        setListAdapter(adapter);

        return root;
    }
}
