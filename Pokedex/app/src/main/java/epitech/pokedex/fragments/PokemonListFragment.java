package epitech.pokedex.fragments;

import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import epitech.pokedex.R;
import epitech.pokedex.adapters.PokemonAdapter;
import epitech.pokedex.entities.GlobalPokemon;
import epitech.pokedex.entities.Pokemon;

public class PokemonListFragment extends Fragment {
    ListView mListView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // TEST avant connexion à l'api
        GlobalPokemon pikachu = new GlobalPokemon();
        pikachu.setId(1);
        pikachu.setName("Pikachu");
        pikachu.setDefault_sprite("http://pokeapi.co/media/sprites/pokemon/12.png");

        GlobalPokemon dracofeu = new GlobalPokemon();
        dracofeu.setId(1);
        dracofeu.setName("Dracofeu");
        dracofeu.setDefault_sprite("http://pokeapi.co/media/sprites/pokemon/11.png");
        //A supprimer une fois la connexion faite

        View view = inflater.inflate(R.layout.fragment_pokemon_list, container, false);
        mListView = (ListView) view.findViewById(R.id.listView);

        List<GlobalPokemon> data = new ArrayList<GlobalPokemon>();
        data.add(pikachu);
        data.add(dracofeu);

        PokemonAdapter adapter = new PokemonAdapter(getActivity(), data);
        mListView.setAdapter(adapter);
        return view;
    }
}
