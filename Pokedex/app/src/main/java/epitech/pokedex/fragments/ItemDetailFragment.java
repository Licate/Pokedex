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

import java.util.concurrent.ExecutionException;

import epitech.pokedex.R;
import epitech.pokedex.apicomms.GetItem;
import epitech.pokedex.entities.Item;

public class ItemDetailFragment extends Fragment {
    private Item item;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Integer id = this.getArguments().getInt("id");

        GetItem itemapi = new GetItem();
        try {
            this.item = itemapi.new Detail().execute(id.toString()).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_item_detail, container, false);

        TextView name = (TextView) view.findViewById(R.id.name);
        String cap = item.getName().substring(0, 1).toUpperCase() + item.getName().substring(1);
        name.setText(cap);
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
