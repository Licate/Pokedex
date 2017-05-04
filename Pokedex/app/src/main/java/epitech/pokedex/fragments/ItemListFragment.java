package epitech.pokedex.fragments;

import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.concurrent.ExecutionException;

import epitech.pokedex.PokemonActivity;
import epitech.pokedex.apicomms.CommAPI;
import epitech.pokedex.R;
import epitech.pokedex.adapters.ItemAdapter;
import epitech.pokedex.apicomms.GetItem;
import epitech.pokedex.entities.GlobalItem;

public class ItemListFragment extends Fragment {
    ListView mListView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_item_list, container, false);
        mListView = (ListView) view.findViewById(R.id.listView);

        GetItem itemapi = new GetItem();
        ItemAdapter adapter = null;
        final PokemonActivity activity = (PokemonActivity) getActivity();
        try {
            adapter = new ItemAdapter(activity, itemapi.new Global().execute().get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        mListView.setAdapter(adapter);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                GlobalItem item = (GlobalItem) mListView.getItemAtPosition(position);
                Fragment fragment = new ItemDetailFragment();
                Bundle args = new Bundle();
                args.putInt("id", item.getId());
                fragment.setArguments(args);
                activity.changeView(fragment, item.getName());
            }
        });
        return view;
    }
}
