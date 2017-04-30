package epitech.pokedex.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import epitech.pokedex.R;
import epitech.pokedex.entities.Berry;

public class BerryListFragment extends Fragment {
    ListView mListView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // TEST avant connexion à l'api
        Berry b = new Berry();
        b.setName("Strawberry");
        b.setId(1);
        //A supprimer une fois la connexion faite

        View view = inflater.inflate(R.layout.fragment_berry_list, container, false);
        mListView = (ListView) view.findViewById(R.id.listView);

        List<Berry> data = new ArrayList<Berry>();
        data.add(b);
        ArrayAdapter<Berry> adapter = new ArrayAdapter<Berry>(getActivity(),
                android.R.layout.simple_list_item_1, data);
        mListView.setAdapter(adapter);
        return view;
    }
}
