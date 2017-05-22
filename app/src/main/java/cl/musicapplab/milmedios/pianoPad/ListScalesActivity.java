package cl.musicapplab.milmedios.pianoPad;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ListScalesActivity extends AppCompatActivity  {

    final List<String> elements = Arrays.asList("Element 1", "Element 2", "Element 3",
            "Element 4", "Element 5");

    private String[] monthsArray = { "JAN", "FEB", "MAR", "APR", "MAY", "JUNE", "JULY",
            "AUG", "SEPT", "OCT", "NOV", "DEC" };

    ArrayList<String> mylist = new ArrayList<String>();
    ArrayList<String> musicalScalesListName= new ArrayList<String>();
    ArrayList<String> musicalScalesListRange= new ArrayList<String>();

    private ListView monthsListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);


        int actionBarTitleId = Resources.getSystem().getIdentifier("action_bar_title", "id", "android");
        if (actionBarTitleId > 0) {
            TextView title = (TextView) findViewById(actionBarTitleId);
            if (title != null) {
                title.setTextColor(Color.RED);
            }
        }

        musicalScalesListName = getIntent().getStringArrayListExtra("musicalScalesListName");
        musicalScalesListRange = getIntent().getStringArrayListExtra("musicalScalesListRange");


        monthsListView = (ListView) findViewById(R.id.months_list);

        monthsListView.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, musicalScalesListName ) {
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                TextView textView = (TextView) super.getView(position, convertView, parent);

                textView.setTextColor(Color.parseColor("#f49e61"));

                return textView;
            }
        });



        monthsListView.setClickable(true);
        monthsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {

                Object o = monthsListView.getItemAtPosition(position);

                String ssss = musicalScalesListName.get(position).toString();

                Intent intent = new Intent(ListScalesActivity.this, PianoPadActivity.class);
                intent.putExtra("position", (int)position);
                intent.putExtra("LongValue", (int)-80142777);
                intent.putStringArrayListExtra("musicalScalesListName", (ArrayList<String>) musicalScalesListName);
                intent.putStringArrayListExtra("musicalScalesListRange", (ArrayList<String>) musicalScalesListRange);

                startActivity(intent);

            }
        });


    }
}
