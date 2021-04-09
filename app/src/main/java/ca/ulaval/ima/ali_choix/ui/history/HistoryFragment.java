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
import ca.ulaval.ima.ali_choix.domain.HistoricElement;
import ca.ulaval.ima.ali_choix.domain.HistoricElementFactory;
import ca.ulaval.ima.ali_choix.services.HistoricService;
import ca.ulaval.ima.ali_choix.services.ServiceLocator;

public class HistoryFragment extends ListFragment {
    ArrayList<HistoricElement> listHistoryItem =new ArrayList<>();
    HistoryItemListAdapter adapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_history, container, false);

        HistoricService historicService = (HistoricService) ServiceLocator.getInstance().get(HistoricService.class);

        historicService.addHistoricElement("1","https://static.openfoodfacts.org/images/products/067/721/009/0246/front_en.7.400.jpg","Nom du produit vraiment long mais pas 1");
        historicService.addHistoricElement("2","https://static.openfoodfacts.org/images/products/067/721/009/0246/front_en.7.400.jpg","Nom du produit vraiment long mais pas 2");
        historicService.addHistoricElement("3","https://static.openfoodfacts.org/images/products/067/721/009/0246/front_en.7.400.jpg","Nom du produit vraiment long mais pas 3");
        historicService.addHistoricElement("4","https://static.openfoodfacts.org/images/products/067/721/009/0246/front_en.7.400.jpg","Nom du produit vraiment long mais pas 4");
        historicService.addHistoricElement("5","https://static.openfoodfacts.org/images/products/067/721/009/0246/front_en.7.400.jpg","Nom du produit vraiment long mais pas 5");
        historicService.addHistoricElement("6","https://static.openfoodfacts.org/images/products/067/721/009/0246/front_en.7.400.jpg","Nom du produit vraiment long mais pas 6");
        historicService.addHistoricElement("7","https://static.openfoodfacts.org/images/products/067/721/009/0246/front_en.7.400.jpg","Nom du produit vraiment long mais pas 7");
        historicService.addHistoricElement("8","https://static.openfoodfacts.org/images/products/067/721/009/0246/front_en.7.400.jpg","Nom du produit vraiment long mais pas 8");

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

        HistoricElement clickedItem = listHistoryItem.get(positionIndex);
        Toast.makeText(getActivity(), clickedItem.getProductId().toString(), Toast.LENGTH_SHORT).show();
    }

    private void fillItemListFromHistory(){
        HistoricService historicService = (HistoricService) ServiceLocator.getInstance().get(HistoricService.class);
        List history = historicService.getHistoric();

        ListIterator<HistoricElement> historyIterator = history.listIterator(history.size());

        while(historyIterator.hasPrevious()) {
            listHistoryItem.add(historyIterator.previous());
        }
    }
}

