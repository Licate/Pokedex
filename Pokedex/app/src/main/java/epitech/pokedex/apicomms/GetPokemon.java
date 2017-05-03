package epitech.pokedex.apicomms;

import android.os.AsyncTask;

import com.fasterxml.jackson.databind.JsonNode;

import java.util.ArrayList;
import java.util.List;

import epitech.pokedex.entities.GlobalPokemon;

/**
 * Created by jean-yves on 5/3/17.
 */

public class GetPokemon extends CommAPI {
    public class Global extends AsyncTask<Void, Void, List<GlobalPokemon>> {

        @Override
        protected List<GlobalPokemon> doInBackground(Void... params) {
            List<GlobalPokemon> pokemons = new ArrayList<GlobalPokemon>();

            try {
                JsonNode res;
                JsonNode obj = mapper.readTree(getRequest(url + "pokemon"));
                for (String next = obj.path("next").textValue(); next != null; next = obj.path("next").textValue()) {
                    res = obj.path("results");
                    if (res.isArray()) {
                        for (final JsonNode objNode : res) {
                            String[] purl = objNode.path("url").asText().split("/");
                            String id = purl[purl.length-1];
                            GlobalPokemon tmp = new GlobalPokemon();
                            tmp.setName(objNode.path("name").asText());
                            tmp.setId(Integer.valueOf(id));
                            tmp.setDefault_sprite("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/"+id+".png");
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
}
