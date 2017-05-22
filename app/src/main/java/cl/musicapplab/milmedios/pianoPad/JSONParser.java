package cl.musicapplab.milmedios.pianoPad;


import java.io.IOException;
import java.io.InputStream;

import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.util.Log;



/**
 * Created by lcarreraz on 04-09-16.
 */
public class JSONParser {


    private static JSONParser instance = null;

    static JSONObject jObj = null;
    InputStream is = null;
    String json = null;


    Context context;

    /**
     * @param context Context
     * @return SoundManager
     */
    public static JSONParser getInstance(Context context) {
        if (instance == null) {
            instance = new JSONParser(context);
        }

        return instance;
    }

    public JSONParser(Context context) {
        this.context = context;
        //loadJSONFromAsset();
    }

    public String loadJSONFromAsset() {
        String json = null;

        try {

            Log.d("Cargar", "Cargar listas ");
            is = context.getAssets().open("scales.json");

            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();

            json = new String(buffer, "UTF-8");
            JSONObject jsonObj = new JSONObject(json);

        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return json;

    }

}



