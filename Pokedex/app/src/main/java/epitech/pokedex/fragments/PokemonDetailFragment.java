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
import java.util.concurrent.ExecutionException;

import epitech.pokedex.R;
import epitech.pokedex.adapters.PokemonAdapter;
import epitech.pokedex.apicomms.GetPokemon;
import epitech.pokedex.entities.Pokemon;


public class PokemonDetailFragment extends Fragment {

    private Pokemon pokemon;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Integer id = this.getArguments().getInt("id");

        GetPokemon pokeapi = new GetPokemon();
        try {
            this.pokemon = pokeapi.new Detail().execute(id.toString()).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_pokemon_detail, container, false);

        TextView name = (TextView) view.findViewById(R.id.name);
        name.setText(pokemon.getName());
        ImageView img = (ImageView) view.findViewById(R.id.sprite);
        Picasso instance = new Picasso.Builder(getActivity())
                .downloader(new OkHttpDownloader(getActivity()))
                .build();
        instance.load(pokemon.getDefault_sprite()).fit().into(img);
        TextView height = (TextView) view.findViewById(R.id.height);
        height.setText("Height : " + pokemon.getHeight());
        TextView weight = (TextView) view.findViewById(R.id.weight);
        weight.setText("Weight : " + pokemon.getWeight());
        //TODO Abilities
            /*        ListView abs = (ListView) view.findViewById(R.id.abilities);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1, test);*/


        return view;
    }

}
