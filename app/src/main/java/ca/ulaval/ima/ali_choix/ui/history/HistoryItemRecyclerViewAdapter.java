package ca.ulaval.ima.ali_choix.ui.history;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import ca.ulaval.ima.ali_choix.R;
import ca.ulaval.ima.ali_choix.domain.history.HistoryElement;

public class HistoryItemRecyclerViewAdapter extends ArrayAdapter<HistoryElement> {
    private Context context;
    private List<Boolean> checkboxStates;
    private boolean deleteMode;

    public HistoryItemRecyclerViewAdapter(Context context, int resourceId, List<HistoryElement> items) {
        super(context, resourceId, items);
        this.context = context;
        this.checkboxStates = new ArrayList<>(Collections.nCopies(items.size(), false));
    }

    public void setDeleteMode(boolean allowDelete) {
        deleteMode = allowDelete;
    }

    public List<Boolean> getCheckboxStates() {
        return checkboxStates;
    }

    private class ViewHolder {
        ImageView historyProductImage;
        TextView historyProductName;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        HistoryElement historyElement = getItem(position);
        LayoutInflater layoutInflater = (LayoutInflater) context
                .getSystemService(Activity.LAYOUT_INFLATER_SERVICE);

        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.history_item, null);
            holder = new ViewHolder();
            holder.historyProductName = (TextView) convertView.findViewById(R.id.history_product_name);
            holder.historyProductImage = (ImageView) convertView.findViewById(R.id.history_product_image);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.historyProductName.setText(historyElement.getProductName());
        Picasso.get().load(historyElement.getImage_front_url()).into(holder.historyProductImage);
        CheckBox checkBox = (CheckBox) convertView.findViewById(R.id.history_delete_item_checkbox);

        if (deleteMode) {
            checkBox.setVisibility(View.VISIBLE);
            checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    setChecked(isChecked, position);
                }
            });
        } else {
            checkBox.setVisibility(View.GONE);
        }

        return convertView;
    }

    private void setChecked(boolean state, int position) {
        checkboxStates.set(position, state);
    }
}
