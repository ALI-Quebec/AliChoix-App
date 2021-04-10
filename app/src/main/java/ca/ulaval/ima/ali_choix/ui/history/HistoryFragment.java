package ca.ulaval.ima.ali_choix.ui.history;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.ListFragment;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import ca.ulaval.ima.ali_choix.R;
import ca.ulaval.ima.ali_choix.domain.HistoryElement;
import ca.ulaval.ima.ali_choix.services.HistoryService;
import ca.ulaval.ima.ali_choix.services.ServiceLocator;

public class HistoryFragment extends ListFragment {
    ArrayList<HistoryElement> listHistoryItem =new ArrayList<>();
    HistoryItemListAdapter adapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_history, container, false);

        HistoryService historyService = (HistoryService) ServiceLocator.getInstance().get(HistoryService.class);

        historyService.addHistoricElement("1","https://static.openfoodfacts.org/images/products/067/721/009/0246/front_en.7.400.jpg","Nom du produit vraiment long mais pas 1");
        historyService.addHistoricElement("2","https://static.openfoodfacts.org/images/products/067/721/009/0246/front_en.7.400.jpg","Nom du produit vraiment long mais pas 2");
        historyService.addHistoricElement("3","https://static.openfoodfacts.org/images/products/067/721/009/0246/front_en.7.400.jpg","Nom du produit vraiment long mais pas 3");
        historyService.addHistoricElement("4","https://static.openfoodfacts.org/images/products/067/721/009/0246/front_en.7.400.jpg","Nom du produit vraiment long mais pas 4");
        historyService.addHistoricElement("5","https://static.openfoodfacts.org/images/products/067/721/009/0246/front_en.7.400.jpg","Nom du produit vraiment long mais pas 5");
        historyService.addHistoricElement("6","https://static.openfoodfacts.org/images/products/067/721/009/0246/front_en.7.400.jpg","Nom du produit vraiment long mais pas 6");
        historyService.addHistoricElement("7","https://static.openfoodfacts.org/images/products/067/721/009/0246/front_en.7.400.jpg","Nom du produit vraiment long mais pas 7");
        historyService.addHistoricElement("8","https://static.openfoodfacts.org/images/products/067/721/009/0246/front_en.7.400.jpg","Nom du produit vraiment long mais pas 8");

        fillItemListFromHistory();

        adapter=new HistoryItemListAdapter(getActivity(),
                android.R.layout.activity_list_item,
                listHistoryItem);
        setListAdapter(adapter);

        return root;
    }

    @Override
    public void onListItemClick(ListView listView, View view, int positionIndex, long id) {
        super.onListItemClick(listView, view, positionIndex, id);

        HistoryElement clickedItem = listHistoryItem.get(positionIndex);
        Toast.makeText(getActivity(), clickedItem.getProductId().toString(), Toast.LENGTH_SHORT).show();
    }

    private void fillItemListFromHistory(){
        HistoryService historyService = (HistoryService) ServiceLocator.getInstance().get(HistoryService.class);
        List history = historyService.getHistory();

        ListIterator<HistoryElement> historyIterator = history.listIterator(history.size());

        while(historyIterator.hasPrevious()) {
            listHistoryItem.add(historyIterator.previous());
        }
    }
}

