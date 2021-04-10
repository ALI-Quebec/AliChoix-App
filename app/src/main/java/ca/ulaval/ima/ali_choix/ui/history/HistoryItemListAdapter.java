package ca.ulaval.ima.ali_choix.ui.history;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import ca.ulaval.ima.ali_choix.R;
import ca.ulaval.ima.ali_choix.domain.HistoryElement;

public class HistoryItemListAdapter extends ArrayAdapter<HistoryElement> {

    Context context;

    public HistoryItemListAdapter(Context context, int resourceId, List<HistoryElement> items) {
        super(context, resourceId, items);
        this.context = context;
    }

    private class ViewHolder {
        ImageView imageView;
        TextView txtTitle;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        HistoryElement historyElement = getItem(position);

        LayoutInflater mInflater = (LayoutInflater) context
                .getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.history_item, null);
            holder = new ViewHolder();
            holder.txtTitle = (TextView) convertView.findViewById(R.id.history_product_name);
            holder.imageView = (ImageView) convertView.findViewById(R.id.history_product_image);
            convertView.setTag(holder);
        } else
            holder = (ViewHolder) convertView.getTag();

        holder.txtTitle.setText(historyElement.getProductName());
        Picasso.get().load(historyElement.getImage_front_url()).into(holder.imageView);

        return convertView;
    }
}
