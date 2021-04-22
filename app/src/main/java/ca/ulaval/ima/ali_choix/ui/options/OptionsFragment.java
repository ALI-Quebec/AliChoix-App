package ca.ulaval.ima.ali_choix.ui.options;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import ca.ulaval.ima.ali_choix.R;

public class OptionsFragment extends Fragment {

    public OptionsFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_options, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.option_recycler_view);
        Context context = view.getContext();
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(new OptionsRecyclerViewAdapter(OptionsContent.OPTIONS_ITEMS, getParentFragmentManager()));

        return view;
    }
}