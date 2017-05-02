package epitech.pokedex;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.json.JSONArray;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


import epitech.pokedex.entities.GlobalBerry;
import epitech.pokedex.entities.GlobalItem;
import epitech.pokedex.entities.GlobalPokemon;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Headers;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import static android.widget.Toast.LENGTH_LONG;

/**
 * Created by jean-yves on 4/27/17.
 */

public class CommAPI {
    private static String url = "http://pokeapi.co/api/v2/";
    private static OkHttpClient client = new OkHttpClient();
    private ObjectMapper mapper = new ObjectMapper();


    /*public List<GlobalPokemon> GetPokemons() {
        List<GlobalPokemon> pokemons = null;

        try {
            JsonNode res;
            JsonNode obj = mapper.readTree(new RequestTask().execute(url + "pokemon").get());
            for (String next = obj.path("next").textValue(); next != null; next = obj.path("next").textValue()) {
                res = obj.path("results");
                if (res.isArray()) {
                    for (final JsonNode objNode : res) {
                        JsonNode pokenode = mapper.readTree(new RequestTask().execute(objNode.path("url").asText()).get());
                        GlobalPokemon tmp = new GlobalPokemon();
                        JsonNode spritenode = pokenode.path("sprites");
                        tmp.setDefault_sprite(spritenode.path("front_default").asText());
                        tmp.setId(pokenode.path("id").asInt());
                        tmp.setName(pokenode.path("name").asText());
                        pokemons.add(tmp);
                    }
                }
               obj = mapper.readTree(new RequestTask().execute(next).get());
            }
            return pokemons;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private class RequestTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... params) {
            Request request = new Request.Builder()
                    .url(params[0])
                    .build();
            Response response = null;
            try {
                response = client.newCall(request).execute();
                return response.body().string();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
    }*/

    private String getRequest(String param) {
        Request request = new Request.Builder()
                .url(param)
                .build();
        Response response;
        try {
            response = client.newCall(request).execute();
            return response.body().string();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public class GetPokemons extends AsyncTask<Void, Void, List<GlobalPokemon>> {
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
    public class GetItems extends AsyncTask<Void, Void, List<GlobalItem>> {
        @Override
        protected List<GlobalItem> doInBackground(Void... params) {
            List<GlobalItem> items = new ArrayList<GlobalItem>();

            try {
                JsonNode res;
                JsonNode obj = mapper.readTree(getRequest(url + "item"));
                for (String next = obj.path("next").textValue(); next != null; next = obj.path("next").textValue()) {
                    res = obj.path("results");
                    if (res.isArray()) {
                        for (final JsonNode objNode : res) {
                            String[] purl = objNode.path("url").asText().split("/");
                            String id = purl[purl.length-1];
                            GlobalItem tmp = new GlobalItem();
                            tmp.setName(objNode.path("name").asText());
                            tmp.setId(Integer.valueOf(id));
                            tmp.setDefault_sprite("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/items/"+tmp.getName()+".png");
                            items.add(tmp);
                        }
                    }
                    obj = mapper.readTree(getRequest(next));
                }
                return items;
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }
    }
    public class GetBerries extends AsyncTask<Void, Void, List<GlobalBerry>> {
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
}
