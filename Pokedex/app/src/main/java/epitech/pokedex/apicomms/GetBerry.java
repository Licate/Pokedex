package epitech.pokedex.apicomms;

import android.os.AsyncTask;

import com.fasterxml.jackson.databind.JsonNode;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import epitech.pokedex.entities.Berry;
import epitech.pokedex.entities.GlobalBerry;

/**
 * Created by jean-yves on 5/3/17.
 */

public class GetBerry extends CommAPI {
    public class Global extends AsyncTask<Void, Void, List<GlobalBerry>> {

        @Override
        protected List<GlobalBerry> doInBackground(Void... params) {
            List<GlobalBerry> pokemons = new ArrayList<GlobalBerry>();

            try {
                JsonNode res;
                JsonNode obj = mapper.readTree(getRequest(url + "berry"));
                for (String next = obj.path("next").textValue(); next != null; next = obj.path("next").textValue()) {
                    res = obj.path("results");
                    if (res.isArray()) {
                        for (final JsonNode objNode : res) {
                            String[] purl = objNode.path("url").asText().split("/");
                            String id = purl[purl.length-1];
                            GlobalBerry tmp = new GlobalBerry();
                            tmp.setName(objNode.path("name").asText());
                            tmp.setId(Integer.valueOf(id));
                            pokemons.add(tmp);
                        }
                    }
                    obj = mapper.readTree(getRequest(next));
                }
                return pokemons;
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }
    }

    public class Detail extends AsyncTask<String, Void, Berry> {
        @Override
        protected Berry doInBackground(String... params) {
            Berry berry = new Berry();
            try {
                JsonNode obj = mapper.readTree(getRequest(url + "berry/" + params[0]));
                berry.setId(obj.path("id").asInt());
                berry.setName(obj.path("name").asText());
                berry.setGrowth_time(obj.path("growth_time").asInt());
                berry.setSize(obj.path("size").asInt());
                berry.setSmoothness(obj.path("smoothness").asInt());
            } catch (IOException e) {
                e.printStackTrace();
            }
            return berry;
        }
    }
}
