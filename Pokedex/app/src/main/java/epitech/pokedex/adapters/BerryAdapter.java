package epitech.pokedex.adapters;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import epitech.pokedex.R;
import epitech.pokedex.entities.GlobalBerry;

public class BerryAdapter extends ArrayAdapter<GlobalBerry>{

    public BerryAdapter(Context context, List<GlobalBerry> berries) {
        super(context, 0, berries);
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.berry_detail_row, parent, false);
        }

        BerryViewHolder viewHolder = (BerryViewHolder) convertView.getTag();
        if(viewHolder == null){
            viewHolder = new BerryViewHolder();
            viewHolder.name = (TextView) convertView.findViewById(R.id.name);
            convertView.setTag(viewHolder);
        }

        GlobalBerry berry = getItem(position);
        String cap = berry.getName().substring(0, 1).toUpperCase() + berry.getName().substring(1);
        viewHolder.name.setText(cap);

        return convertView;
    }

    private class BerryViewHolder{
        public TextView name;
    }
}
