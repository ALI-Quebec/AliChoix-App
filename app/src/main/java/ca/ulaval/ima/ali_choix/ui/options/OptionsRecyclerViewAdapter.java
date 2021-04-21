package ca.ulaval.ima.ali_choix.ui.options;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import ca.ulaval.ima.ali_choix.R;
import ca.ulaval.ima.ali_choix.ui.options.OptionsContent.OptionsItem;

import java.util.List;

public class OptionsRecyclerViewAdapter extends RecyclerView.Adapter<OptionsRecyclerViewAdapter.ViewHolder> {
    private final List<OptionsItem> optionsItems;
    private final FragmentManager fragmentManager;

    public OptionsRecyclerViewAdapter(List<OptionsItem> optionsItems, FragmentManager fragmentManager) {
        this.optionsItems = optionsItems;
        this.fragmentManager = fragmentManager;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_item, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.item = optionsItems.get(position);
        holder.iconView.setImageResource(optionsItems.get(position).icon);
        holder.textView.setText(optionsItems.get(position).content);
        holder.textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeFragment(optionsItems.get(position).target);
            }
        });
        holder.iconView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeFragment(optionsItems.get(position).target);
            }
        });
    }

    @Override
    public int getItemCount() {
        return optionsItems.size();
    }

    private void changeFragment(Fragment target) {
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.nav_host_fragment, target);
        fragmentTransaction.commit();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View view;
        public final ImageView iconView;
        public final TextView textView;
        public OptionsItem item;

        public ViewHolder(View view) {
            super(view);
            this.view = view;
            iconView = view.findViewById(R.id.item_icon);
            textView = view.findViewById(R.id.content);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + textView.getText() + "'";
        }
    }
}