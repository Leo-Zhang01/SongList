package sg.edu.rp.c346.songlist;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText etTitle, etSinger, etYear;
    RadioButton radioButton;
    RadioGroup radioGroup;
    Button btnInsert, btnShow;
    DBHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etTitle = findViewById(R.id.etTitle);
        etSinger = findViewById(R.id.etSinger);
        etYear = findViewById(R.id.etYear);
        radioGroup = findViewById(R.id.radioGroup);
        btnInsert = findViewById(R.id.button4);
        btnShow = findViewById(R.id.button5);
        db = new DBHelper(MainActivity.this);
        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = etTitle.getText().toString();
                String singer = etSinger.getText().toString();
                int year = Integer.parseInt(etYear.getText().toString());
                radioButton = findViewById(radioGroup.getCheckedRadioButtonId());
                int stars = Integer.parseInt(radioButton.getText().toString());
                Song song = new Song(title, singer, year, stars);
                db.insertSong(song);
                Toast.makeText(getBaseContext(),"Inserted",Toast.LENGTH_LONG).show();
            }
        });
        btnShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(),ShowSong.class);
                startActivity(intent);
            }
        });
    }
}
