package epitech.pokedex.apicomms;

import android.os.AsyncTask;

import com.fasterxml.jackson.databind.JsonNode;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import epitech.pokedex.entities.GlobalItem;
import epitech.pokedex.entities.Item;

/**
 * Created by jean-yves on 5/3/17.
 */

public class GetItem extends CommAPI {
    public class Global extends AsyncTask<Void, Void, List<GlobalItem>> {

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

    public class Detail extends AsyncTask<String, Void, Item> {
        @Override
        protected Item doInBackground(String... params) {
            Item item = new Item();
            try {
                JsonNode obj = mapper.readTree(getRequest(url + "item/"+params[0]));
                item.setId(obj.path("id").asInt());
                item.setName(obj.path("name").asText());
                item.setCost(obj.path("cost").asInt());
                JsonNode sprite = obj.path("sprites");
                if (sprite != null) {
                    item.setDefault_sprite(sprite.path("default").asText());
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return item;
        }
    }
}
