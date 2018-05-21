package sg.edu.rp.c346.songlist;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomAdapter extends ArrayAdapter<Song> {
    Context context;
    ArrayList<Song> songs;
    int resource;
    TextView tvtitle, singer, year;
    ImageView iv1, iv2, iv3, iv4, iv5;
    public CustomAdapter(Context context, int resource, ArrayList<Song> songs) {
        super(context, resource, songs);
        this.context = context;
        this.songs = songs;
        this.resource = resource;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View rowView = inflater.inflate(resource, parent, false);

        //Match the UI components with Java variables
        iv1=  rowView.findViewById(R.id.imageView1star);
        iv2=  rowView.findViewById(R.id.imageView2star);
        iv3=  rowView.findViewById(R.id.imageView3star);
        iv4=  rowView.findViewById(R.id.imageView4star);
        iv5=  rowView.findViewById(R.id.imageView5star);
        Song song = songs.get(position);
        int star = song.getStars();
        tvtitle =  rowView.findViewById(R.id.title);
        singer =  rowView.findViewById(R.id.singer);
        year =  rowView.findViewById(R.id.year);
        tvtitle.setText(song.getTitle());
        singer.setText(song.getSingers());
        year.setText(String.valueOf(song.getYear()));
        if (star == 5) {
            iv5.setImageResource(android.R.drawable.btn_star_big_on);
            iv4.setImageResource(android.R.drawable.btn_star_big_on);
            iv3.setImageResource(android.R.drawable.btn_star_big_on);
            iv2.setImageResource(android.R.drawable.btn_star_big_on);
            iv1.setImageResource(android.R.drawable.btn_star_big_on);
        }else if(star == 4){
            iv5.setImageResource(android.R.drawable.btn_star_big_on);
            iv4.setImageResource(android.R.drawable.btn_star_big_on);
            iv3.setImageResource(android.R.drawable.btn_star_big_on);
            iv2.setImageResource(android.R.drawable.btn_star_big_on);
        }else if(star == 3){
            iv5.setImageResource(android.R.drawable.btn_star_big_on);
            iv4.setImageResource(android.R.drawable.btn_star_big_on);
            iv3.setImageResource(android.R.drawable.btn_star_big_on);
        }else if(star == 2){
            iv5.setImageResource(android.R.drawable.btn_star_big_on);
            iv4.setImageResource(android.R.drawable.btn_star_big_on);

        }else if(star == 1){
            iv5.setImageResource(android.R.drawable.btn_star_big_on);
        }

        return rowView;
    }

}
