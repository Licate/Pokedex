package epitech.pokedex.fragments;

import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

import epitech.pokedex.PokemonActivity;
import epitech.pokedex.apicomms.GetPokemon;
import epitech.pokedex.R;
import epitech.pokedex.adapters.PokemonAdapter;
import epitech.pokedex.entities.GlobalPokemon;

public class PokemonListFragment extends Fragment {
    ListView mListView;
    PokemonAdapter adapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_pokemon_list, container, false);
        mListView = (ListView) view.findViewById(R.id.listView);

        GetPokemon pokeapi = new GetPokemon();
        final PokemonActivity activity = (PokemonActivity) getActivity();

        adapter = new PokemonAdapter(activity, new ArrayList<GlobalPokemon>());
        pokeapi.new Global(adapter).execute();
        mListView.setAdapter(adapter);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                GlobalPokemon pokemon = (GlobalPokemon) mListView.getItemAtPosition(position);
                Fragment fragment = new PokemonDetailFragment();
                Bundle args = new Bundle();
                args.putInt("id", pokemon.getId());
                fragment.setArguments(args);
                String cap = pokemon.getName().substring(0, 1).toUpperCase() + pokemon.getName().substring(1);
                activity.changeView(fragment, cap);
            }
        });
        return view;
    }
}
