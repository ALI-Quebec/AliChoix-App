package ca.ulaval.ima.ali_choix.ui.history;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.ListFragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import ca.ulaval.ima.ali_choix.R;
import ca.ulaval.ima.ali_choix.domain.history.HistoryElement;
import ca.ulaval.ima.ali_choix.services.HistoryService;
import ca.ulaval.ima.ali_choix.services.ServiceLocator;
import ca.ulaval.ima.ali_choix.ui.dialog.DialogInformationFragment;

import static ca.ulaval.ima.ali_choix.ui.UIConstant.DIALOG_MESSAGE_KEY;
import static ca.ulaval.ima.ali_choix.ui.UIConstant.HISTORY_LOAD_ERROR_MESSAGE;
import static ca.ulaval.ima.ali_choix.ui.UIConstant.HISTORY_SAVE_ERROR_MESSAGE;
import static ca.ulaval.ima.ali_choix.ui.UIConstant.PRODUCT_ID_KEY;

public class HistoryFragment extends ListFragment {
    private ArrayList<HistoryElement> historyItems;
    private HistoryItemRecyclerViewAdapter adapter;
    private HistoryService historyService;
    private ImageButton deleteButton;
    private ImageButton completeDeletionButton;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_history, container, false);

        historyService = (HistoryService) ServiceLocator.getInstance().get(HistoryService.class);

        assignVisualElements(root);

        fillItemListFromHistory();

        adapter = new HistoryItemRecyclerViewAdapter(getActivity(),
                android.R.layout.activity_list_item,
                historyItems);
        setListAdapter(adapter);
        adapter.notifyDataSetChanged();

        verifyHistoryProblemStates();

        return root;
    }

    @Override
    public void onListItemClick(ListView listView, View view, int positionIndex, long id) {
        super.onListItemClick(listView, view, positionIndex, id);

        HistoryElement clickedItem = historyItems.get(positionIndex);

        NavController navController = Navigation.findNavController(getActivity(), R.id.nav_host_fragment);
        Bundle bundle = new Bundle();
        bundle.putString(PRODUCT_ID_KEY, clickedItem.getProductId().toString());
        navController.navigate(R.id.action_navigation_history_to_navigation_scanned_product, bundle);
    }

    private void fillItemListFromHistory() {
        List history = historyService.getHistory();
        ListIterator<HistoryElement> historyIterator = history.listIterator(history.size());

        historyItems = new ArrayList<>();
        while (historyIterator.hasPrevious()) {
            historyItems.add(historyIterator.previous());
        }
    }

    private void verifyHistoryProblemStates() {
        if (historyService.historyLoadProblemState()) {
            DialogFragment dialog = new DialogInformationFragment();
            Bundle bundle = new Bundle();
            bundle.putString(DIALOG_MESSAGE_KEY, HISTORY_LOAD_ERROR_MESSAGE);
            dialog.setArguments(bundle);
            dialog.show(getFragmentManager(), "DialogInformationFragment");

            historyService.resetHistoryLoadingProblemState();
        }

        if (historyService.historySavingProblemState()) {
            DialogFragment dialog = new DialogInformationFragment();
            Bundle bundle = new Bundle();
            bundle.putString(DIALOG_MESSAGE_KEY, HISTORY_SAVE_ERROR_MESSAGE);
            dialog.setArguments(bundle);
            dialog.show(getFragmentManager(), "DialogInformationFragment");

            historyService.resetHistorySavingProblemState();
        }
    }

    private void assignVisualElements(View root) {
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
    }

    private void startDeletionProcess() {
        deleteButton.setVisibility(View.GONE);
        completeDeletionButton.setVisibility(View.VISIBLE);

        adapter.setDeleteMode(true);
        adapter.notifyDataSetChanged();
    }

    private void completeDeletionProcess() {
        ArrayList<Boolean> checkedItems = (ArrayList) adapter.getCheckboxStates();

        for (int i = 0; i < checkedItems.size(); i++) {
            if (checkedItems.get(i)) {
                historyService.removeHistoryElement(historyItems.get(i).getProductId().toString());
            }
        }

        fillItemListFromHistory();

        completeDeletionButton.setVisibility(View.GONE);
        deleteButton.setVisibility(View.VISIBLE);

        adapter = new HistoryItemRecyclerViewAdapter(getActivity(),
                android.R.layout.activity_list_item,
                historyItems);

        setListAdapter(adapter);
    }
}
