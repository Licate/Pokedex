package epitech.pokedex.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.OkHttpDownloader;
import com.squareup.picasso.Picasso;

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
        String cap = item.getName().substring(0, 1).toUpperCase() + item.getName().substring(1);
        viewHolder.name.setText(cap);
        Picasso instance = new Picasso.Builder(parent.getContext())
                .downloader(new OkHttpDownloader(parent.getContext()))
                .build();
        instance.load(item.getDefault_sprite())
                .fit()
                .into(viewHolder.sprite);

        return convertView;
    }

    private class ItemViewHolder{
        public TextView name;
        public ImageView sprite;

    }
}
