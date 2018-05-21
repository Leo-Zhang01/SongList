package sg.edu.rp.c346.songlist;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class Edit extends AppCompatActivity {
    EditText etTitle, etSinger, etYear, etId;
    RadioButton radioButton;
    RadioGroup radioGroup;
    Button btnUpdate, btnDelete, btnCancel;
    DBHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        db = new DBHelper(Edit.this);
        etId = findViewById(R.id.etId);

        etTitle = findViewById(R.id.etTitle);
        etSinger = findViewById(R.id.etSinger);
        etYear = findViewById(R.id.etYear);
        radioGroup = findViewById(R.id.radioGroup);
        btnUpdate = findViewById(R.id.update);
        btnDelete = findViewById(R.id.delete);
        btnCancel = findViewById(R.id.cancel);
        Intent intent = getIntent();
        Song song = (Song)intent.getSerializableExtra("data");
        etId.setText(String.valueOf(song.getId()));
        etSinger.setText(song.getSingers());
        etTitle.setText(song.getTitle());
        etYear.setText(String.valueOf(song.getYear()));
        int star = song.getStars();
        if(star == 5){
            radioButton = findViewById(R.id.five);
            radioButton.setChecked(true);

        }else if (star == 4){
            radioButton = findViewById(R.id.four);
            radioButton.setChecked(true);

        }else if (star == 3){
            radioButton = findViewById(R.id.three);
            radioButton.setChecked(true);

        }else if (star == 2){
            radioButton = findViewById(R.id.two);
            radioButton.setChecked(true);

        }else if (star == 1){
            radioButton = findViewById(R.id.one);
            radioButton.setChecked(true);

        }

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int id = Integer.parseInt(etId.getText().toString());
                String title = etTitle.getText().toString();
                String singer = etSinger.getText().toString();
                int year = Integer.parseInt(etYear.getText().toString());
                radioButton = findViewById(radioGroup.getCheckedRadioButtonId());
                int stars = Integer.parseInt(radioButton.getText().toString());
                Song song = new Song(id, title, singer, year, stars);
                db.updateSong(song);
                setResult(9);

                finish();
            }
        });
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int id = Integer.parseInt(etId.getText().toString());
                db.deleteSong(id);

                finish();
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
