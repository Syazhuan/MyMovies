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

    private Button filterButton;
    private ArrayList<Movie> movies;
    private Spinner ratingSpinner;
    private ListView movieListView;
    private CustomAdapter movieAdapter;
    private ArrayAdapter<String> ratingAdapter;
    private ArrayList<String> ratings;
    private DBHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_movies);

        db = new DBHelper(ShowMovies.this);

        movieListView = findViewById(R.id.lv);
        filterButton = findViewById(R.id.btnFilter);
        ratingSpinner = findViewById(R.id.spinner2);
        ratings = new ArrayList<String>();
        ratingAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, ratings);

        initializeViews();
        setupFilterButton();
        setupListView();
    }

    private void initializeViews() {
        ratingSpinner.setAdapter(ratingAdapter);
        ratingSpinner.setSelected(false);
    }

    private void setupFilterButton() {
        filterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String selectedRating = ratings.get(ratingSpinner.getSelectedItemPosition());
                if (selectedRating.equalsIgnoreCase("None")) {
                    loadMovies();
                } else {
                    movies.clear();
                    movies.addAll(db.getByRating(selectedRating));
                    movieAdapter.notifyDataSetChanged();
                    Toast.makeText(ShowMovies.this, ratingSpinner.getSelectedItem().toString(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void setupListView() {
        movieListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Movie selected = movies.get(position);
                Intent intent = new Intent(ShowMovies.this, ModifyMovie.class);
                intent.putExtra("Selected", selected);
                startActivity(intent);
            }
        });
    }

    private void loadMovies() {
        movies = db.getAllMovies();
        movieAdapter = new CustomAdapter(this, R.layout.row, movies);
        movieListView.setAdapter(movieAdapter);
        loadRatings();
    }

    private void loadRatings() {
        ratings.clear();
        ratings.addAll(db.getRating());
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadMovies();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        db.close();
    }
}