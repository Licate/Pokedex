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
import java.util.concurrent.ExecutionException;

import epitech.pokedex.R;
import epitech.pokedex.apicomms.GetItem;
import epitech.pokedex.entities.Item;

public class ItemDetailFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_item_detail, container, false);

        String id = "10";
        GetItem itemapi = new GetItem();
        try {
            Item item = itemapi.new Detail().execute(id).get();
            TextView name = (TextView) view.findViewById(R.id.name);
            name.setText(item.getName());
            ImageView img = (ImageView) view.findViewById(R.id.sprite);
            Picasso instance = new Picasso.Builder(getActivity())
                    .downloader(new OkHttpDownloader(getActivity()))
                    .build();
            instance.load(item.getDefault_sprite()).fit().into(img);
            TextView cost = (TextView) view.findViewById(R.id.cost);
            cost.setText("Cost : " + item.getCost());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return view;
    }
}
