package ca.ulaval.ima.ali_choix.ui.history;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.fragment.app.ListFragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import ca.ulaval.ima.ali_choix.R;
import ca.ulaval.ima.ali_choix.domain.HistoryElement;
import ca.ulaval.ima.ali_choix.services.HistoryService;
import ca.ulaval.ima.ali_choix.services.ServiceLocator;

import static ca.ulaval.ima.ali_choix.domain.GlobalConstant.PRODUCT_ID_KEY;

public class HistoryFragment extends ListFragment {
    private ArrayList<HistoryElement> listHistoryItem;
    private HistoryItemListAdapter adapter;
    private HistoryService historyService;
    
    private ImageButton deleteButton;
    private ImageButton completeDeletionButton;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_history, container, false);

        historyService = (HistoryService) ServiceLocator.getInstance().get(HistoryService.class);

        deleteButton = root.findViewById(R.id.history_start_deletion_button);
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startDeletionProcess();
            }
        });

        completeDeletionButton = root.findViewById(R.id.history_complete_deletion_button);
        completeDeletionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                completeDeletionProcess();
            }
        });

        completeDeletionButton.setVisibility(View.GONE);


        //TODO remove when testing is done
        historyService.addHistoryElement("737628064502","https://static.openfoodfacts.org/images/products/073/762/806/4502/front_en.6.full.jpg","Thai peanut noodle kit includes stir-fry rice noodles & thai peanut seasoning - Thai Kitchen - 155 g");
        historyService.addHistoryElement("3274080005003","https://static.openfoodfacts.org/images/products/327/408/000/5003/front_en.640.full.jpg","Spring water - Cristaline - 1,5 l");
        historyService.addHistoryElement("3017620422003","https://static.openfoodfacts.org/images/products/301/762/042/2003/front_fr.260.full.jpg","Nutella - Ferrero - 400 g");
        historyService.addHistoryElement("3229820100234","https://static.openfoodfacts.org/images/products/322/982/010/0234/front_fr.115.full.jpg","Fourrés Chocolat noir - bjorg - 225 g");
        historyService.addHistoryElement("3392460480827","https://static.openfoodfacts.org/images/products/339/246/048/0827/front_en.72.full.jpg","Biscottes heudebert - 300 g");
        historyService.addHistoryElement("8002270014901","https://static.openfoodfacts.org/images/products/800/227/001/4901/front_fr.164.full.jpg","san Pellegrino - 1 L");
        historyService.addHistoryElement("7503018092775","https://static.openfoodfacts.org/images/products/067/721/009/0246/front_en.7.400.jpg","Hongo shiitake Morimoto - 50 g");
        historyService.addHistoryElement("0011110844149","https://static.openfoodfacts.org/images/products/067/721/009/0246/front_en.7.400.jpg","Private selection, mukimame");

        fillItemListFromHistory();

        adapter=new HistoryItemListAdapter(getActivity(),
                android.R.layout.activity_list_item,
                listHistoryItem);
        setListAdapter(adapter);
        adapter.notifyDataSetChanged();

        return root;
    }

    private void startDeletionProcess() {
        deleteButton.setVisibility(View.GONE);
        completeDeletionButton.setVisibility(View.VISIBLE);

        adapter.setDeleteMode(true);
        adapter.notifyDataSetChanged();
    }

    private void completeDeletionProcess(){

        ArrayList<Boolean> checkedItem = (ArrayList) adapter.getCheckboxState();
        for(int i = 0; i < checkedItem.size(); i++){
            if(checkedItem.get(i)){
                historyService.removeHistoryElement(listHistoryItem.get(i).getProductId().toString());
            }
        }
        fillItemListFromHistory();

        completeDeletionButton.setVisibility(View.GONE);
        deleteButton.setVisibility(View.VISIBLE);

        adapter=new HistoryItemListAdapter(getActivity(),
                android.R.layout.activity_list_item,
                listHistoryItem);
        setListAdapter(adapter);
    }

    @Override
    public void onListItemClick(ListView listView, View view, int positionIndex, long id) {
        super.onListItemClick(listView, view, positionIndex, id);

        HistoryElement clickedItem = listHistoryItem.get(positionIndex);

        NavController navController = Navigation.findNavController(getActivity(), R.id.nav_host_fragment);
        Bundle bundle = new Bundle();
        bundle.putString(PRODUCT_ID_KEY, clickedItem.getProductId().toString());
        navController.navigate(R.id.action_navigation_history_to_navigation_scanned_product,bundle);
    }

    private void fillItemListFromHistory(){
        List history = historyService.getHistory();
        ListIterator<HistoryElement> historyIterator = history.listIterator(history.size());

        listHistoryItem = new ArrayList<>();
        while(historyIterator.hasPrevious()) {
            listHistoryItem.add(historyIterator.previous());
        }
    }
}

