package sg.edu.rp.c346.id22022096.songs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class secondActivity extends AppCompatActivity {

    Button btnStars, btnback;
    ListView lv;

    ArrayList<song> al;
    //ArrayAdapter<song> adapter;
    CustomAdapter adapter;

    @Override
    protected void onResume() {
        super.onResume();
        al = new ArrayList<song>();
        adapter = new CustomAdapter(this, R.layout.row, al);
        lv.setAdapter(adapter);
        Intent intent = getIntent();
        DBHelper db = new DBHelper(secondActivity.this);
        al.clear();
        al.addAll(db.getSongs());
        //db.close();
        adapter.notifyDataSetChanged();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second2);

        btnStars = findViewById(R.id.btnShowStars);
        btnback = findViewById(R.id.btnback);
        lv = findViewById(R.id.lv);

        DBHelper db = new DBHelper(this);
        al = db.getSongs();
        db.close();

        //al = new ArrayList<>();
        //adapter = new ArrayAdapter<>(secondActivity.this, android.R.layout.simple_list_item_1, al);
        //lv.setAdapter(adapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(secondActivity.this, thirdActivity.class);
                song Songs = al.get(position);
                i.putExtra("song", Songs);
                startActivity(i);

            }
        });

        btnStars.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                // Create the DBHelper object, passing in the
                // activity's Context
                DBHelper db = new DBHelper(secondActivity.this);

                // getTasks -> able to retrieve all data fields
                //ArrayList<song> data = db.getSongs();
                //ArrayList<String> data2 = db.getSongDetails();
                //db.close();

                //ArrayAdapter adapter = new ArrayAdapter(secondActivity.this, android.R.layout.simple_list_item_1,data );
                //lv.setAdapter(adapter);

                al.clear();
                al.addAll(db.getSongbyStars(5));
                adapter.notifyDataSetChanged();

            }
        });

        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }
}