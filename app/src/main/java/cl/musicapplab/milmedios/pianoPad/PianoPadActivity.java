package cl.musicapplab.milmedios.pianoPad;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;


public class PianoPadActivity extends AppCompatActivity {
    // Remove the below line after defining your own ad unit ID.

    Button[] box;
    Button[] notes_list;
    int notes[];
    int large_scale;
    int init_octave;

    int tono;
    String[] musical_system;
    String[] musical_system_latin = new String[]{"do", "do♯", "re", "re♯", "mi", "fa", "fa♯", "sol", "sol♯", "la", "la♯", "si"};
    String[] musical_system_german = new String[]{"C", "C♯", "D", "D♯", "E", "F", "F♯", "G", "G♯", "A", "A♯", "H"};
    String[] musical_system_english = new String[]{"C", "C♯", "D", "D♯", "E", "F", "F♯", "G", "G♯", "A", "A♯", "B"};
    int[] some_array_int;

    Integer notePlay;
    int note = 0;


    private static final String TOAST_TEXT = "Test ads are being shown. "
            + "To show live ads, replace the ad unit ID in res/values/strings.xml with your own ad unit ID.";

    private static final int START_LEVEL = 1;
    private int mLevel;
    private Button mNextLevelButton;
    private Button musicalScalesList;
    private InterstitialAd mInterstitialAd;


    int[] scale;


    int number_of_keys = 35;

    ArrayList<String> musicalScalesListRange = new ArrayList<String>();
    ArrayList<String> musicalScalesListName = new ArrayList<String>();

    SoundManager soundManager;
    JSONParser jParser;
    String scaleName;

    TextView scale_actual;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        scale_actual=(TextView) findViewById(R.id.scales2);




        jParser = JSONParser.getInstance(getApplicationContext());

        soundManager = SoundManager.getInstance(getApplicationContext());

        Bundle extras = getIntent().getExtras();

        int position;

        Intent intent = getIntent();

        if (intent.hasExtra("musicalScalesListName")) {

            position = extras.getInt("position");
            musicalScalesListName = getIntent().getStringArrayListExtra("musicalScalesListName");
            musicalScalesListRange = getIntent().getStringArrayListExtra("musicalScalesListRange");

        } else {
            position = 1;
            try {

                String json = jParser.loadJSONFromAsset();

                JSONObject jsonObj = new JSONObject(json);

                JSONArray contacts = jsonObj.getJSONArray("scales");

                // looping through All Contacts
                for (int i = 0; i < contacts.length(); i++) {
                    JSONObject c = contacts.getJSONObject(i);

                    String name = c.getString("name");
                    String rango = c.getString("range");

                    name=name.substring(0, 1).toUpperCase() + name.substring(1) + " scale";

                    musicalScalesListRange.add(rango);
                    musicalScalesListName.add(name);

                }


            } catch (JSONException e) {
                e.printStackTrace();
            }
        }



        String musical_scale;

        musical_scale=musicalScalesListRange.get(position).toString();

        String note_text = "C";

        scaleName =musicalScalesListName.get(position).toString();

        scaleName = scaleName.substring(0, 1).toUpperCase() + scaleName.substring(1);

        setTitle("Musical Scales-" + note_text + " " + scaleName);

        scale_actual.setText(note_text + " " + scaleName);


        init_octave=2;
        mLevel=1;


        notes_list = new Button[12];

        notes_list[0] = (Button) findViewById(R.id.note_0);
        notes_list[1] = (Button) findViewById(R.id.note_1);
        notes_list[2] = (Button) findViewById(R.id.note_2);
        notes_list[3] = (Button) findViewById(R.id.note_3);
        notes_list[4] = (Button) findViewById(R.id.note_4);
        notes_list[5] = (Button) findViewById(R.id.note_5);
        notes_list[6] = (Button) findViewById(R.id.note_6);
        notes_list[7] = (Button) findViewById(R.id.note_7);
        notes_list[8] = (Button) findViewById(R.id.note_8);
        notes_list[9] = (Button) findViewById(R.id.note_9);
        notes_list[10] = (Button) findViewById(R.id.note_10);
        notes_list[11] = (Button) findViewById(R.id.note_11);




        box = new Button[43];

        box[0] = (Button) findViewById(R.id.box_0);
        box[1] = (Button) findViewById(R.id.box_1);
        box[2] = (Button) findViewById(R.id.box_2);
        box[3] = (Button) findViewById(R.id.box_3);
        box[4] = (Button) findViewById(R.id.box_4);
        box[5] = (Button) findViewById(R.id.box_5);
        box[6] = (Button) findViewById(R.id.box_6);
        box[7] = (Button) findViewById(R.id.box_7);
        box[8] = (Button) findViewById(R.id.box_8);
        box[9] = (Button) findViewById(R.id.box_9);
        box[10] = (Button) findViewById(R.id.box_10);
        box[11] = (Button) findViewById(R.id.box_11);
        box[12] = (Button) findViewById(R.id.box_12);
        box[13] = (Button) findViewById(R.id.box_13);
        box[14] = (Button) findViewById(R.id.box_14);
        box[15] = (Button) findViewById(R.id.box_15);
        box[16] = (Button) findViewById(R.id.box_16);
        box[17] = (Button) findViewById(R.id.box_17);
        box[18] = (Button) findViewById(R.id.box_18);
        box[19] = (Button) findViewById(R.id.box_19);
        box[20] = (Button) findViewById(R.id.box_20);
        box[21] = (Button) findViewById(R.id.box_21);
        box[22] = (Button) findViewById(R.id.box_22);
        box[23] = (Button) findViewById(R.id.box_23);
        box[24] = (Button) findViewById(R.id.box_24);
        box[25] = (Button) findViewById(R.id.box_25);
        box[26] = (Button) findViewById(R.id.box_26);
        box[27] = (Button) findViewById(R.id.box_27);
        box[28] = (Button) findViewById(R.id.box_28);
        box[29] = (Button) findViewById(R.id.box_29);
        box[30] = (Button) findViewById(R.id.box_30);
        box[31] = (Button) findViewById(R.id.box_31);
        box[32] = (Button) findViewById(R.id.box_32);
        box[33] = (Button) findViewById(R.id.box_33);
        box[34] = (Button) findViewById(R.id.box_34);


        HashMap<String, Integer> map2 = new HashMap<String, Integer>();

        map2.put("1",1);
        map2.put("2b",2);
        map2.put("2b",2);
        map2.put("2",3);
        map2.put("2#",4);
        map2.put("3b",4);
        map2.put("3",5);
        map2.put("4",6);
        map2.put("4#",7);
        map2.put("5b",7);
        map2.put("5",8);
        map2.put("5#",9);
        map2.put("6b",9);
        map2.put("6",10);
        map2.put("6#",11);
        map2.put("7b",11);
        map2.put("7",12);



        String[] separated = musical_scale.split(" ");

        some_array_int = new int[separated.length];

        for (int i = 0; i < separated.length; i++) {
            some_array_int[i]=map2.get(separated[i]);
        }


        String[] musical_system_b ={"C", "D♭", "D", "E♭", "E", "F", "G♭", "G", "A♭", "A", "B♭", "B"};


        String notation;

        notation="english";


        if(notation=="latin") {
            musical_system = musical_system_latin;
        }else if(notation=="english"){
            musical_system = musical_system_english;
        }
        else if (notation=="german") {
            musical_system = musical_system_german;
        }


        tono = 0;

        notes = new int[some_array_int.length];

        large_scale = some_array_int.length;



        for (int i = 0; i < some_array_int.length; i++) {
            if (musical_system[(some_array_int[i%large_scale]- 1 + tono)%12].length()>1) {
                notes[i]=0;
            } else {
                notes[i]=1;
            }
        }


        scale = new int[number_of_keys];
        for (int i = 0; i < number_of_keys; i++) {
            scale[i]=some_array_int[i%large_scale] + (12 * (i/large_scale)) - 1;
        }



        for (int i = 0; i < number_of_keys; i++) {
            notePlay = scale[i];

            if ((notePlay + note)<=83){

                box[i].setText(soundManager.select_note((notePlay + note)));


                box[i].setTextColor(Color.parseColor("#555555"));
                if(notes[i%large_scale]==0){
                    box[i].setBackgroundResource(R.drawable.black_note);
                }
                else{
                    box[i].setBackgroundResource(R.drawable.white_note);
                }


            }

        }



        for (int i = 0; i < number_of_keys; i++) {
            box[i].setOnTouchListener(new View.OnTouchListener() {
                public boolean onTouch(View v, MotionEvent event) {
                    Button button = (Button) v;
                    if (event.getAction() == android.view.MotionEvent.ACTION_DOWN) {
                        Integer a = Integer.parseInt(button.getTag().toString());

                        notePlay = scale[a];

                        if ((notePlay + note)<=83) {

                            soundManager.play((notePlay + note));

                            if (notes[a % large_scale] == 0) {
                                box[a].setBackgroundColor(Color.parseColor("#000000"));
                                button.setBackgroundResource(R.drawable.sombra_black);
                            } else {
                                box[a].setBackgroundColor(Color.parseColor("#FFFFFF"));
                                button.setBackgroundResource(R.drawable.sombra);
                            }
                            button.setTextColor(Color.parseColor("#DDDDDD"));

                        }

                    } else if (event.getAction() == android.view.MotionEvent.ACTION_UP) {
                        Integer a = Integer.parseInt(button.getTag().toString());

                        if ((notePlay + note)<=83) {

                            if(notes[a%large_scale]==0){
                                box[a].setBackgroundColor(Color.parseColor("#000000"));
                                box[a].setBackgroundResource(R.drawable.black_note);
                            }
                            else{
                                box[a].setBackgroundResource(R.drawable.white_note);
                            }

                            box[a].setTextColor(Color.parseColor("#666666"));


                        }

                    }

                    return true;
                }

            });
        }




        // Create the InterstitialAd and set the adUnitId (defined in values/strings.xml).
        mInterstitialAd = newInterstitialAd();
        loadInterstitial();


        // Load an ad into the AdMob banner view.
        AdView adView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder()
                .setRequestAgent("android_studio:ad_template").build();
        adView.loadAd(adRequest);

        // Toasts the test ad message on the screen. Remove this after defining your own ad unit ID.
        //Toast.makeText(this, TOAST_TEXT, Toast.LENGTH_LONG).show();
    }







    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if (id == R.id.english_notation) {
            Toast.makeText(getApplicationContext(),"Sistema de notación inglesa",Toast.LENGTH_LONG).show();
            return true;
        }else if (id == R.id.german_notation) {
            Toast.makeText(getApplicationContext(),"Sistema de notación aleman",Toast.LENGTH_LONG).show();
            return true;

        }else if (id == R.id.latin_notation) {
            Toast.makeText(getApplicationContext(),"Sistema de notación latina",Toast.LENGTH_LONG).show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    private InterstitialAd newInterstitialAd() {
        InterstitialAd interstitialAd = new InterstitialAd(this);
        interstitialAd.setAdUnitId(getString(R.string.interstitial_ad_unit_id));
        interstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
               // mNextLevelButton.setEnabled(true);
            }

            @Override
            public void onAdFailedToLoad(int errorCode) {
                // mNextLevelButton.setEnabled(true);
            }

            @Override
            public void onAdClosed() {
                // Proceed to the next level.
                goToNextLevel();
            }
        });
        return interstitialAd;
    }

    private void showInterstitial() {
        // Show the ad if it's ready. Otherwise toast and reload the ad.

        if (mInterstitialAd != null && mInterstitialAd.isLoaded()) {
            mInterstitialAd.show();

        } else {

            goToNextLevel();
        }
    }

    private void loadInterstitial() {
        // Disable the next level button and load the ad.
      //  mNextLevelButton.setEnabled(false);
        AdRequest adRequest = new AdRequest.Builder()
                .setRequestAgent("android_studio:ad_template").build();
        mInterstitialAd.loadAd(adRequest);
    }

    private void goToNextLevel() {
        // Show the next level and reload the ad to prepare for the level after.

        mInterstitialAd = newInterstitialAd();
        loadInterstitial();
    }


    /** Called when the user clicks the Send button */
    public void sendMessage(View view) {
        Intent intent = new Intent(this, ListScalesActivity.class);

        intent.putStringArrayListExtra("musicalScalesListName", (ArrayList<String>) musicalScalesListName);
        intent.putStringArrayListExtra("musicalScalesListRange", (ArrayList<String>) musicalScalesListRange);

        startActivity(intent);
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        this.finish();
    }

    /** Called when the user clicks the Send button */
    public void setNote(View view) {
        //Button button = (Button) view;
        Button button = (Button) view;


        for (int i = 0; i < 12; i++) {
            //notes_list[i].setTextColor(Color.parseColor("#000000"));

            notes_list[i].setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.circle, null));



        }

        Integer a = Integer.parseInt(button.getTag().toString());


        //button.setTextColor(Color.parseColor("#FFFFFF"));



        button.setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.circle_h, null));






        //setTitle("Musical Scales-" + button.getText().toString() + " " + scaleName);

        scaleName = scaleName.substring(0, 1).toUpperCase() + scaleName.substring(1);

        scale_actual.setText(button.getText().toString() + " " + scaleName);


        if(mLevel>=6) {
            mLevel=1;
            showInterstitial();
        } else {
            mLevel=mLevel+1;
        }

        tono=a-1;

        note = tono;

        for (int i = 0; i < some_array_int.length; i++) {
            if (musical_system[(some_array_int[i%large_scale]- 1 + tono)%12].length()>1) {
                notes[i]=0;
            } else {
                notes[i]=1;
            }
        }


        for (int i = 0; i < number_of_keys; i++) {
            //box[i].setText(musical_system[(some_array_int[i%large_scale]- 1 + tono)%12] + i/large_scale);

            notePlay = scale[i];

            if ((notePlay + note)<=83){
                box[i].setText(soundManager.select_note((notePlay + note)));

                //box[i].setText("+"+(notePlay + note));

                box[i].setTextColor(Color.parseColor("#555555"));
                if(notes[i%large_scale]==0){
                    //box[i].setBackgroundColor(Color.parseColor("#000000"));
                    box[i].setBackgroundResource(R.drawable.black_note);
                }
                else{
                    //box[i].setBackgroundColor(Color.parseColor("#FFFFFF"));
                    box[i].setBackgroundResource(R.drawable.white_note);
                }
            }else{

                box[i].setText("");
                box[i].setBackgroundColor(Color.parseColor("#00000000"));

            }
        }





    }


}
