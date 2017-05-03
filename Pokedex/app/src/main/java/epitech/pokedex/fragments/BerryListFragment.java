package epitech.pokedex.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import epitech.pokedex.apicomms.CommAPI;
import epitech.pokedex.R;
import epitech.pokedex.adapters.BerryAdapter;
import epitech.pokedex.apicomms.GetBerry;
import epitech.pokedex.entities.GlobalBerry;

public class BerryListFragment extends Fragment {
    ListView mListView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // TEST avant connexion à l'api
        GlobalBerry b = new GlobalBerry();
        b.setName("Strawberry");
        //A supprimer une fois la connexion faite

        View view = inflater.inflate(R.layout.fragment_berry_list, container, false);
        mListView = (ListView) view.findViewById(R.id.listView);

        List<GlobalBerry> data = new ArrayList<GlobalBerry>();
        data.add(b);

        GetBerry berryapi = new GetBerry();
        BerryAdapter adapter = null;
        try {
            adapter = new BerryAdapter(getActivity(), berryapi.new Global().execute().get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        mListView.setAdapter(adapter);
        return view;
    }
}
