package sg.edu.rp.c346.id22022096.songs;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

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
                String songtitle = etSong.getText().toString();
                String singer = etSingers.getText().toString();
                //String stryr = etYear.getText().toString().trim();

                if (songtitle.length() == 0 || singer.length() == 0){
                    Toast.makeText(MainActivity.this, "please fill in song title and singer name", Toast.LENGTH_SHORT).show();
                    return;
                }

                //etYear.getText() -> retrieves the input entered
                //Integer.parseInt(yr) -> converts string value stored in 'yr' to int
                String stryr = etYear.getText().toString().trim();
                //int year = Integer.parseInt(stryr);
                int year = Integer.valueOf(stryr);

                int star = getStars();

                //int checkradioid = ratings.getCheckedRadioButtonId();

                //if (checkradioid == R.id.starOne) {
                //star += 1;

                // } else if (checkradioid == R.id.starTwo) {
                //star += 2;

                //} else if (checkradioid == R.id.starThree) {
                //star += 3;

                //} else if (checkradioid == R.id.starFour) {
                //star += 4;

                //} else if (checkradioid == R.id.starFive) {
                // star += 5;

                // }

                // insert song into database
                long result = db.insertSong(songtitle, singer, year, star);

                //etSong.setText("");
                //etSingers.setText("");
                //etYear.setText("");

                if (result != -1) {
                    Toast.makeText(MainActivity.this, "song successfully inserted", Toast.LENGTH_LONG).show();
                    etSong.setText("");
                    etSingers.setText("");
                    etYear.setText("");
                } else {
                    Toast.makeText(MainActivity.this, "insertion failed", Toast.LENGTH_LONG).show();
                }

                // retrieve all tasks from database table
                //al = db.getSongs();
                //adapter.notifyDataSetChanged();
                //db.close();

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

    //private int getstars() {
    //int getstarsID = ratings.getCheckedRadioButtonId();
    //RadioButton star = findViewById(/getstarsID);
    //int stars = Integer.parseInt(star.getText().toString());
    //return stars;

    //}

    private int getStars() {
        int stars = 1;
        int checkedRadioButtonId = ratings.getCheckedRadioButtonId();

        if (checkedRadioButtonId == R.id.starOne) {
            stars = 1;
        } else if (checkedRadioButtonId == R.id.starTwo) {
            stars = 2;
        } else if (checkedRadioButtonId == R.id.starThree) {
            stars = 3;
        } else if (checkedRadioButtonId == R.id.starFour) {
            stars = 4;
        } else if (checkedRadioButtonId == R.id.starFive) {
            stars = 5;
        }

        return stars;
    }
}