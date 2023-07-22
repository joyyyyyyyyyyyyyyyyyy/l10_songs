package sg.edu.rp.c346.id22022096.songs;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomAdapter extends ArrayAdapter {
    Context parent_context;
    int layout_id;
    ArrayList<song> songlist;

    public CustomAdapter(Context context, int resource, ArrayList<song> objects) {
        super (context, resource, objects);
        parent_context = context;
        layout_id = resource;
        songlist = objects;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Obtain the LayoutInflater object
        LayoutInflater inflater = (LayoutInflater)
                parent_context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        // "Inflate" the View for each row
        View rowView = inflater.inflate(layout_id, parent, false);

        // Obtain the UI components and do the necessary binding
        TextView songTitle = rowView.findViewById(R.id.songTitle);
        TextView singers = rowView.findViewById(R.id.singers);
        TextView year = rowView.findViewById(R.id.year);
        TextView stars = rowView.findViewById(R.id.stars);

        // Obtain the Android Version information based on the position
        song currentVersion = songlist.get(position);

        // Set values to the TextView to display the corresponding information
        songTitle.setText(currentVersion.getTitle());
        singers.setText(currentVersion.getSingers());
        year.setText(String.valueOf(currentVersion.getYear()));
        stars.setText(String.valueOf(currentVersion.getStar()));

        return rowView;
    }
}



