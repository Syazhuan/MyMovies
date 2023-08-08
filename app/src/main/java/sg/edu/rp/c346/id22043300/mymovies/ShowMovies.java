package sg.edu.rp.c346.id22043300.mymovies;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;
import java.util.ArrayList;

public class ShowMovies extends AppCompatActivity {

    Button btnFilter, btnback;
    ArrayList<Movie> alMovie;
    Spinner spinner;
    ListView lv;
    CustomAdapter aaMovie;
    ArrayAdapter<String> aaRating;
    ArrayList<String> alRating;
    DBHelper db = new DBHelper(ShowMovies.this);
    boolean clicked = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_movies);

        lv = findViewById(R.id.lv);
        btnFilter = findViewById(R.id.btnFilter);
        spinner = findViewById(R.id.spinner2);
        btnback = findViewById(R.id.btnBack);
        alRating = new ArrayList<String>();
        aaRating = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, alRating);
        load();
        spinner.setAdapter(aaRating);
        spinner.setSelected(false);

        btnback.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ShowMovies.this, MainActivity.class);
                startActivity(intent);
            }
        });
        btnFilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String choseRating=alRating.get(spinner.getSelectedItemPosition());
                if (choseRating.equalsIgnoreCase("None"))
                {
                    load();
                }
                else {
                    alMovie.clear();
                    alMovie.addAll(db.getByRating(choseRating));
                    aaMovie.notifyDataSetChanged();
                    Toast.makeText(ShowMovies.this, spinner.getSelectedItem().toString(), Toast.LENGTH_SHORT).show();
                }
            }
        });

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int
                    position, long identity) {

                Movie selected = alMovie.get(position);
                Intent i = new Intent(ShowMovies.this,ModifyMovie.class);
                i.putExtra("Selected", selected);
                startActivity(i);
            }
        });
    }
    private void load()
    {
        alMovie = db.getAllMovies();
        aaMovie = new CustomAdapter(this,
                R.layout.row, alMovie);
        lv.setAdapter(aaMovie);
        loadSpinner();
    }
    private void loadSpinner ()
    {
        alRating.clear();
        alRating.addAll(db.getRating());
        // Toast.makeText(ShowMovies.this, alRating.get(0) + "", Toast.LENGTH_SHORT).show();
    }
    @Override
    protected void onResume() {
        super.onResume();
        load();
    }
}