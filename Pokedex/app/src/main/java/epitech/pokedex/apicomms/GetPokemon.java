package epitech.pokedex.apicomms;

import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;

import com.fasterxml.jackson.databind.JsonNode;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import epitech.pokedex.adapters.PokemonAdapter;
import epitech.pokedex.entities.GlobalPokemon;
import epitech.pokedex.entities.Pokemon;
import epitech.pokedex.fragments.PokemonListFragment;

/**
 * Created by jean-yves on 5/3/17.
 */

public class GetPokemon extends CommAPI {

    public class Global extends AsyncTask<Void, Void, List<GlobalPokemon>> {
        public PokemonAdapter mAdapter;

        public Global(PokemonAdapter adapter) {
            mAdapter = adapter;
        }

        @Override
        protected void onPreExecute() {
            // start loading animation maybe?
        }

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

        @Override
        protected void onPostExecute(List<GlobalPokemon> items) {
            // stop the loading animation or something
            // adapter.addAll(items);
            mAdapter.addAll(items);
        }
    }

    public class Detail extends AsyncTask<String, Void, Pokemon> {
        @Override
        protected Pokemon doInBackground(String... params) {
            Pokemon pokemon = new Pokemon();
            try {
                JsonNode obj = mapper.readTree(getRequest(url + "pokemon/" + params[0]));
                pokemon.setId(obj.path("id").asInt());
                pokemon.setName(obj.path("name").asText());
                JsonNode typenodes = obj.path("types");
                ArrayList<String> types = new ArrayList<String>();
                for (JsonNode type : typenodes) {
                    JsonNode typenode = type.path("type");
                    if (!typenode.isMissingNode()) {
                        types.add(typenode.path("name").asText());
                    }
                }
                pokemon.setTypes_name(types);
                JsonNode sprites = obj.path("sprites");
                pokemon.setDefault_sprite(sprites.path("front_default").asText());
                pokemon.setHeight(obj.path("height").asInt());
                pokemon.setWeight(obj.path("weight").asInt());
            } catch (IOException e) {
                e.printStackTrace();
            }

            return pokemon;
        }
    }
}
