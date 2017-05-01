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
import epitech.pokedex.entities.GlobalItem;

public class ItemAdapter extends ArrayAdapter<GlobalItem>{

    public ItemAdapter(Context context, List<GlobalItem> items) {
        super(context, 0, items);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.pokemon_detail_row, parent, false);
        }

        ItemViewHolder viewHolder = (ItemViewHolder) convertView.getTag();
        if(viewHolder == null){
            viewHolder = new ItemViewHolder();
            viewHolder.name = (TextView) convertView.findViewById(R.id.name);
            viewHolder.sprite = (ImageView) convertView.findViewById(R.id.sprite);
            convertView.setTag(viewHolder);
        }

        GlobalItem item = getItem(position);
        viewHolder.name.setText(item.getName());
        // TODO : Transform String url into Drawable
        viewHolder.sprite.setImageDrawable(new ColorDrawable());

        return convertView;
    }

    private class ItemViewHolder{
        public TextView name;
        public ImageView sprite;

    }
}
