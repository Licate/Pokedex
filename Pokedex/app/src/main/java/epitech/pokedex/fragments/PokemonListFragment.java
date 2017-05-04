package epitech.pokedex.fragments;

import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.concurrent.ExecutionException;

import epitech.pokedex.apicomms.GetPokemon;
import epitech.pokedex.R;
import epitech.pokedex.adapters.PokemonAdapter;

public class PokemonListFragment extends Fragment {
    ListView mListView;
    PokemonAdapter adapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_pokemon_list, container, false);
        mListView = (ListView) view.findViewById(R.id.listView);

        GetPokemon pokeapi = new GetPokemon();

        try {
            adapter = new PokemonAdapter(getActivity(), pokeapi.new Global().execute().get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        mListView.setAdapter(adapter);

        return view;
    }
}
