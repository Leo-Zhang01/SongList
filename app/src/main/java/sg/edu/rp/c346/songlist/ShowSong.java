package sg.edu.rp.c346.songlist;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;

import java.util.ArrayList;

public class ShowSong extends AppCompatActivity {
    ListView listView;
    CustomAdapter aa;
    Button filter;
    ArrayList<Song> songs;
    DBHelper db;
    Spinner spinner;
    ArrayAdapter<String> adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_song);
        listView = findViewById(R.id.listView);
        filter = findViewById(R.id.filter);
        db = new DBHelper(ShowSong.this);
        spinner = findViewById(R.id.spinner);


        songs = db.getAllSong();
        SetAdapter(songs);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int
                    position, long identity) {
                Intent i = new Intent(getBaseContext(), Edit.class);
                Song song = songs.get(position);i.putExtra("data", song);
                startActivity(i);
            }
        });




        //spinner
        ArrayList<String> years = db.selectYears();
        adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, years);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                ArrayList<Song> songYear = new ArrayList<>();
                if(position == 0) return;
                songs = db.getAllSong();
                Log.v("item", (String) parent.getItemAtPosition(position));
                int year = Integer.parseInt((String)parent.getItemAtPosition(position));
                Log.i("items: ",(String.valueOf(year)));
                for (int i = 0 ; i<songs.size(); i++){
                    if(year == songs.get(i).getYear()){
                        songYear.add(songs.get(i));
                    }
                }
                songs = songYear;
                SetAdapter(songs);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub
            }
        });

        filter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<Song> arrayList = new ArrayList<>();
                for(int i = 0; i<songs.size();i++){

                    if(songs.get(i).getStars() == 5){
                        arrayList.add(songs.get(i));
                    }
                }

                songs = arrayList;
                SetAdapter(songs);
            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK){
            songs = db.getAllSong();
            Log.i("Activity result", "logged");
            aa.notifyDataSetChanged();
        }

    }

    @Override
    protected void onResume() {
        super.onResume();
        songs = db.getAllSong();
        SetAdapter(songs);
    }
    public void SetAdapter(ArrayList<Song> line){
        aa = new CustomAdapter(this, R.layout.row, line);
        Log.i("revision list","set adapter");
        listView.setAdapter(aa);
    }
}
