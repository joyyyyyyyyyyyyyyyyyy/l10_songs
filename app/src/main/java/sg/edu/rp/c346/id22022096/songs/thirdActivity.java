package sg.edu.rp.c346.id22022096.songs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class thirdActivity extends AppCompatActivity {

    Button btnUpdate, btnDelete, btnCancel;
    EditText etSong, etSingers, etYear;
    TextView songTitle, singers, year, stars, tvID;
    RadioGroup ratings;
    RadioButton rb1, rb2, rb3, rb4, rb5;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);

        btnUpdate = findViewById(R.id.btnUpdate);
        btnDelete = findViewById(R.id.btnDelete);
        btnCancel = findViewById(R.id.btnCancel);
        etSong = findViewById(R.id.etSong);
        etSingers = findViewById(R.id.etSingers);
        etYear = findViewById(R.id.etYear);
        songTitle = findViewById(R.id.songTitle);
        singers = findViewById(R.id.singers);
        year = findViewById(R.id.year);
        stars = findViewById(R.id.stars);
        tvID = findViewById(R.id.tvID);
        ratings = findViewById(R.id.ratings);
        rb1 = findViewById(R.id.starOne);
        rb2 = findViewById(R.id.starTwo);
        rb3 = findViewById(R.id.starThree);
        rb4 = findViewById(R.id.starFour);
        rb5 = findViewById(R.id.starFive);

        Intent i = getIntent();
        final song current = (song) i.getSerializableExtra("song");

        tvID.setText(current.get_id()+"");
        etSong.setText(current.getTitle());
        etSingers.setText(current.getSingers());
        etYear.setText(current.getYear()+"");
        switch (current.getStar()) {
            case 5: rb5.setChecked(true);
            break;
            case 4: rb4.setChecked(true);
            break;
            case 3: rb3.setChecked(true);
            break;
            case 2: rb2.setChecked(true);
            break;
            case 1: rb1.setChecked(true);

        }

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper db = new DBHelper(thirdActivity.this);
                current.setTitle(etSong.getText().toString().trim());
                current.setSingers(etSingers.getText().toString().trim());
                int year = 0;
                try {
                    year = Integer.valueOf(etYear.getText().toString().trim());
                } catch (Exception e){
                    Toast.makeText(thirdActivity.this, "invalid year", Toast.LENGTH_SHORT).show();
                    return;
                }
                current.setYear(year);

                int rbselect = ratings.getCheckedRadioButtonId();
                RadioButton rb = findViewById(rbselect);
                current.setStar(Integer.parseInt(rb.getText().toString()));
                int result = db.updateSong(current);
                if (result>0){
                    Toast.makeText(thirdActivity.this, "song has been updated", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent();
                    setResult(RESULT_OK);
                    finish();
                } else {
                    Toast.makeText(thirdActivity.this, "failed to update", Toast.LENGTH_SHORT).show();
                }

            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper db = new DBHelper(thirdActivity.this);
                int result = db.deleteSong(current.get_id());
                if (result>0){
                    Toast.makeText(thirdActivity.this, "song has been deleted", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent();
                    setResult(RESULT_OK);
                    finish();
                } else {
                    Toast.makeText(thirdActivity.this, "failed to delete", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}