package sg.edu.rp.c346.id22022096.songs;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class secondActivity extends AppCompatActivity {

    Button btnStars;
    ListView lv;

    ArrayList<song> al;
    ArrayAdapter<song> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second2);

        btnStars = findViewById(R.id.btnShowStars);
        lv = findViewById(R.id.lv);

        al = new ArrayList<>();
        adapter = new ArrayAdapter<>(secondActivity.this, android.R.layout.simple_list_item_1, al);
        lv.setAdapter(adapter);

        btnStars.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                // Create the DBHelper object, passing in the
                // activity's Context
                DBHelper db = new DBHelper(secondActivity.this);

                // getTasks -> able to retrieve all data fields
                ArrayList<song> data = db.getSongs();
                ArrayList<String> data2 = db.getSongDetails();
                db.close();

                ArrayAdapter adapter = new ArrayAdapter(secondActivity.this, android.R.layout.simple_list_item_1,data );
                lv.setAdapter(adapter);

            }
        });

    }
}