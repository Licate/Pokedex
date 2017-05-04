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
import epitech.pokedex.entities.GlobalPokemon;

public class PokemonAdapter extends ArrayAdapter<GlobalPokemon>{

    public PokemonAdapter(Context context, List<GlobalPokemon> pokemons) {
        super(context, 0, pokemons);
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.pokemon_detail_row, parent, false);
        }

        PokemonViewHolder viewHolder = (PokemonViewHolder) convertView.getTag();
        if (viewHolder == null) {
            viewHolder = new PokemonViewHolder();
            viewHolder.name = (TextView) convertView.findViewById(R.id.name);
            viewHolder.sprite = (ImageView) convertView.findViewById(R.id.sprite);
            convertView.setTag(viewHolder);
        }


        GlobalPokemon pokemon = getItem(position);
        String cap = pokemon.getName().substring(0, 1).toUpperCase() + pokemon.getName().substring(1);
        viewHolder.name.setText(cap);
        Picasso instance = new Picasso.Builder(parent.getContext())
                .downloader(new OkHttpDownloader(parent.getContext()))
                .build();
        instance.load(pokemon.getDefault_sprite())
                .fit()
                .into(viewHolder.sprite);
        return convertView;
    }

    private class PokemonViewHolder{
        public TextView name;
        public ImageView sprite;

    }
}
