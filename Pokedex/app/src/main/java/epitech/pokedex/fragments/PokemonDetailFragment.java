package epitech.pokedex.fragments;


import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.OkHttpDownloader;
import com.squareup.picasso.Picasso;

import java.util.concurrent.ExecutionException;

import epitech.pokedex.R;
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
        String cap = pokemon.getName().substring(0, 1).toUpperCase() + pokemon.getName().substring(1);
        name.setText(cap);
        ImageView img = (ImageView) view.findViewById(R.id.sprite);
        Picasso instance = new Picasso.Builder(getActivity())
                .downloader(new OkHttpDownloader(getActivity()))
                .build();
        instance.load(pokemon.getDefault_sprite()).fit().into(img);
        TextView height = (TextView) view.findViewById(R.id.height);
        height.setText("Height : " + pokemon.getHeight());
        TextView weight = (TextView) view.findViewById(R.id.weight);
        weight.setText("Weight : " + pokemon.getWeight());
        if (pokemon.getTypes_name().size() > 0) {
            TextView ab = (TextView) view.findViewById(R.id.ab);
            ab.setText("Abilities : ");
            TextView ab1 = (TextView) view.findViewById(R.id.ab1);
            ab1.setText(" - " + pokemon.getTypes_name().get(0));
        }
        if (pokemon.getTypes_name().size() > 1) {
            TextView ab2 = (TextView) view.findViewById(R.id.ab2);
            ab2.setText(" - " + pokemon.getTypes_name().get(1).toString());
        }
        if (pokemon.getTypes_name().size() > 2) {
            TextView ab3 = (TextView) view.findViewById(R.id.ab3);
            ab3.setText(" - " + pokemon.getTypes_name().get(2).toString());
        }
        return view;
    }

}
