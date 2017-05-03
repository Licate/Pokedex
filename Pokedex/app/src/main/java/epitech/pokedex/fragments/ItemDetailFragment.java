package epitech.pokedex.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.OkHttpDownloader;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import epitech.pokedex.R;
import epitech.pokedex.entities.Item;

public class ItemDetailFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_item_detail, container, false);

        // TEST SUPPRIMER
        Item item = new Item();
        item.setId(1);
        item.setName("Pokeball");
        item.setCost(10);
        item.setDefault_sprite("http://pokeapi.co/media/sprites/items/master-ball.png");


        TextView name = (TextView) view.findViewById(R.id.name);
        name.setText(item.getName());
        ImageView img = (ImageView) view.findViewById(R.id.sprite);
        Picasso instance = new Picasso.Builder(getActivity())
                .downloader(new OkHttpDownloader(getActivity()))
                .build();
        instance.load(item.getDefault_sprite()).fit().into(img);
        TextView cost = (TextView) view.findViewById(R.id.cost);
        cost.setText("Cost : " + item.getCost());
        return view;
    }
}
