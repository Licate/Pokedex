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
import epitech.pokedex.entities.Item;

public class ItemListFragment extends Fragment {
    ListView mListView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // TEST avant connexion à l'api
        Item pokeball = new Item();
        pokeball.setId(1);
        pokeball.setName("Pokeball");
        pokeball.setDefault_sprite("http://pokeapi.co/media/sprites/items/master-ball.png");
        //A supprimer une fois la connexion faite

        View view = inflater.inflate(R.layout.fragment_item_list, container, false);
        mListView = (ListView) view.findViewById(R.id.listView);

        List<Item> data = new ArrayList<Item>();
        data.add(pokeball);
        ArrayAdapter<Item> adapter = new ArrayAdapter<Item>(getActivity(),
                android.R.layout.simple_list_item_1, data);
        mListView.setAdapter(adapter);
        return view;
    }
}
