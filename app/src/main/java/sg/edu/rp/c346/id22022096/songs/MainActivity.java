package sg.edu.rp.c346.id22022096.songs;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Button btnInsert, btnList;
    EditText etSong, etSingers, etYear;
    TextView songTitle, singers, year, stars;
    RadioGroup ratings;

    ArrayList<song> al;
    ArrayAdapter<song> adapter;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnInsert = findViewById(R.id.btnInsert);
        btnList = findViewById(R.id.btnList);
        etSong = findViewById(R.id.etSong);
        etSingers = findViewById(R.id.etSingers);
        etYear = findViewById(R.id.etYear);
        songTitle = findViewById(R.id.songTitle);
        singers = findViewById(R.id.singers);
        year = findViewById(R.id.year);
        stars = findViewById(R.id.stars);
        ratings = findViewById(R.id.ratings);

        // initialise arraylist & arrayadapter
        al = new ArrayList<>();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, al);

        btnInsert.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                // Create the DBHelper object, passing in the
                // activity's Context
                DBHelper db = new DBHelper(MainActivity.this);

                // get user input
                String title = etSong.getText().toString();
                String singers = etSingers.getText().toString();

                //etYear.getText() -> retrieves the input entered
                //Integer.parseInt(yr) -> converts string value stored in 'yr' to int
                String yr = etYear.getText().toString().trim();
                int year = Integer.parseInt(yr);
                int stars = getstars();

                // insert song into database
                db.insertSong(title, singers, year, stars);

                etSong.setText("");
                etSingers.setText("");
                etYear.setText("");

                // retrieve all tasks from database table
                al = db.getSongs();
                adapter.notifyDataSetChanged();
                db.close();

            }
        });

        btnList.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, secondActivity.class );
                startActivity(intent);

            }
        });
    }

    private int getstars() {
        int getstarsID = ratings.getCheckedRadioButtonId();
        RadioButton star = findViewById(getstarsID);
        int stars = Integer.parseInt(star.getText().toString());
        return stars;

    }
}