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

public class MyItemRecyclerViewAdapter extends RecyclerView.Adapter<MyItemRecyclerViewAdapter.ViewHolder> {

    private final List<OptionsItem> mValues;
    private final FragmentManager fragmentManager;

    public MyItemRecyclerViewAdapter(List<OptionsItem> items, FragmentManager fragmentManager) {
        mValues = items;
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
        holder.mItem = mValues.get(position);
        holder.mIconView.setImageResource(mValues.get(position).icon);
        holder.mContentView.setText(mValues.get(position).content);
        holder.mContentView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeFragment(mValues.get(position).target);
            }
        });
        holder.mIconView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeFragment(mValues.get(position).target);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    private void changeFragment(Fragment target) {
        //TODO it says the fragment already exists if you go there multiple times (might be fixed)
        FragmentTransaction ft = fragmentManager.beginTransaction();
        ft.replace(R.id.nav_host_fragment, target);
        ft.commit();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final ImageView mIconView;
        public final TextView mContentView;
        public OptionsItem mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mIconView = view.findViewById(R.id.item_icon);
            mContentView = view.findViewById(R.id.content);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mContentView.getText() + "'";
        }
    }
}