package epitech.pokedex.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import epitech.pokedex.R;
import epitech.pokedex.entities.Berry;
import epitech.pokedex.entities.Pokemon;

/**
 * Created by manon on 30/04/2017.
 */

public class BerryListFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        List<Berry> data = new ArrayList<Berry>();
        return inflater.inflate(R.layout.fragment_berry_list, container, false);
    }
}
