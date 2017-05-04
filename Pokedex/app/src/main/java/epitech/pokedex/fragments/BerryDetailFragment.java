package epitech.pokedex.fragments;

import android.app.Fragment;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.OkHttpDownloader;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.ExecutionException;

import epitech.pokedex.R;
import epitech.pokedex.apicomms.GetBerry;
import epitech.pokedex.entities.Berry;

public class BerryDetailFragment extends Fragment{
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_berry_detail, container, false);

        String id = "10";
        GetBerry berryapi = new GetBerry();
        try {
            Berry berry = berryapi.new Detail().execute(id).get();
            TextView name = (TextView) view.findViewById(R.id.name);
            name.setText(berry.getName());
            ImageView img = (ImageView) view.findViewById(R.id.sprite);
            Picasso instance = new Picasso.Builder(getActivity())
                    .downloader(new OkHttpDownloader(getActivity()))
                    .build();
            instance.load("http://blog.xebia.fr/wp-content/uploads/2015/03/rass.png").fit().into(img);
            TextView size = (TextView) view.findViewById(R.id.size);
            size.setText("Size : "+berry.getSize()+ " milimeters");
            TextView growth = (TextView) view.findViewById(R.id.growth);
            growth.setText("Growth time : "+berry.getGrowth_time() + " hours");
            TextView sm = (TextView) view.findViewById(R.id.sm);
            sm.setText("Smoothness : "+ berry.getSmoothness());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        return view;
    }

}
