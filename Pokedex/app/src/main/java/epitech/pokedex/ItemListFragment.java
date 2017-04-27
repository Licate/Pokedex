package epitech.pokedex;

import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import epitech.pokedex.entities.Item;

public class ItemListFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        List<Item> data = new ArrayList<Item>();
        return inflater.inflate(R.layout.fragment_item_list, container, false);
    }
}
