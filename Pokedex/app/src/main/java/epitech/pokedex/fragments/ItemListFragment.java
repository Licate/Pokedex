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
import java.util.concurrent.ExecutionException;

import epitech.pokedex.CommAPI;
import epitech.pokedex.R;
import epitech.pokedex.adapters.ItemAdapter;
import epitech.pokedex.entities.GlobalItem;
import epitech.pokedex.entities.Item;

public class ItemListFragment extends Fragment {
    ListView mListView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // TEST avant connexion à l'api
        GlobalItem pokeball = new GlobalItem();
        pokeball.setId(1);
        pokeball.setName("Pokeball");
        pokeball.setDefault_sprite("http://pokeapi.co/media/sprites/items/master-ball.png");
        //A supprimer une fois la connexion faite

        View view = inflater.inflate(R.layout.fragment_item_list, container, false);
        mListView = (ListView) view.findViewById(R.id.listView);

        List<GlobalItem> data = new ArrayList<GlobalItem>();
        data.add(pokeball);

        CommAPI api = new CommAPI();
        ItemAdapter adapter = null;
        try {
            adapter = new ItemAdapter(getActivity(), api.new GetItems().execute().get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        mListView.setAdapter(adapter);
        return view;
    }
}
