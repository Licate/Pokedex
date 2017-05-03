package epitech.pokedex.fragments;


import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.squareup.picasso.OkHttpDownloader;
import com.squareup.picasso.Picasso;

import java.lang.reflect.Array;
import java.util.ArrayList;

import epitech.pokedex.R;
import epitech.pokedex.entities.Pokemon;

public class PokemonDetailFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_pokemon_detail, container, false);

        // TEST SUPPRIMER
        ArrayList<String> abilities = new ArrayList<String>();
        abilities.add("ability 1");
        abilities.add("ability 2");

        String [] test = (String[]) abilities.toArray();

        Pokemon poke = new Pokemon();
        poke.setId(1);
        poke.setName("Pika");
        poke.setHeight(4);
        poke.setDefault_sprite("http://pokeapi.co/media/sprites/pokemon/12.png");
        poke.setWeight(6);
        poke.setTypes_name(abilities);

        TextView name = (TextView) view.findViewById(R.id.name);
        name.setText(poke.getName());
        ImageView img = (ImageView) view.findViewById(R.id.sprite);
        Picasso instance = new Picasso.Builder(getActivity())
                .downloader(new OkHttpDownloader(getActivity()))
                .build();
        instance.load(poke.getDefault_sprite()).fit().into(img);
        TextView height = (TextView) view.findViewById(R.id.height);
        height.setText("Height : " + poke.getHeight());
        TextView weight = (TextView) view.findViewById(R.id.weight);
        weight.setText("Weight : " + poke.getWeight());
        //TODO Abilities
/*        ListView abs = (ListView) view.findViewById(R.id.abilities);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1, test);*/

        return view;
    }

}
