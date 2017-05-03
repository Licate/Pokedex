package epitech.pokedex.apicomms;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import static android.widget.Toast.LENGTH_LONG;

/**
 * Created by jean-yves on 4/27/17.
 */

public class CommAPI {
    static String url = "http://pokeapi.co/api/v2/";
    private static OkHttpClient client = new OkHttpClient();
    ObjectMapper mapper = new ObjectMapper();

    String getRequest(String param) {
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
}
