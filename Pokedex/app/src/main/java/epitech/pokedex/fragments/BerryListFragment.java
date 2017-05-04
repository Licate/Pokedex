package epitech.pokedex.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.concurrent.ExecutionException;

import epitech.pokedex.PokemonActivity;
import epitech.pokedex.R;
import epitech.pokedex.adapters.BerryAdapter;
import epitech.pokedex.apicomms.GetBerry;
import epitech.pokedex.entities.GlobalBerry;

public class BerryListFragment extends Fragment {
    ListView mListView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_berry_list, container, false);
        mListView = (ListView) view.findViewById(R.id.listView);

        GetBerry berryapi = new GetBerry();
        BerryAdapter adapter = null;
        final PokemonActivity activity = (PokemonActivity) getActivity();
        try {
            adapter = new BerryAdapter(activity, berryapi.new Global().execute().get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        mListView.setAdapter(adapter);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                GlobalBerry berry = (GlobalBerry) mListView.getItemAtPosition(position);
                Fragment fragment = new BerryDetailFragment();
                Bundle args = new Bundle();
                args.putInt("id", berry.getId());
                fragment.setArguments(args);
                activity.changeView(fragment, berry.getName());
            }
        });

        return view;
    }
}
