package sg.edu.rp.c346.id22043300.mymovies;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class ModifyMovie extends AppCompatActivity {

    EditText etMovieID, etmt2, etgen2, ety2;
    Button btnUpdate, btnDelete, btnCancel;
    Spinner spinner;
    String rating;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_movie);

        etMovieID = findViewById(R.id.etMovieID);
        etmt2 = findViewById(R.id.et_mt2);
        etgen2 = findViewById(R.id.et_gen2);
        ety2 = findViewById(R.id.et_y2);
        btnUpdate = findViewById(R.id.btnUpdate);
        btnDelete = findViewById(R.id.btnDelete);
        btnCancel = findViewById(R.id.btnCancel);
        spinner = findViewById(R.id.spinner3);

        Intent i = getIntent();
        Movie movie = (Movie) i.getSerializableExtra("Selected");
        etMovieID.setText(movie.getId()+"");
        etMovieID.setEnabled(false);
        etmt2.setText(movie.getTitle());
        etgen2.setText(movie.getGenre());
        ety2.setText(movie.getYear()+"");

        // Get the list of ratings from the spinner's adapter
        ArrayList<String> ratingList = new ArrayList<>();
        for (int j = 0; j < spinner.getCount(); j++) {
            ratingList.add(spinner.getItemAtPosition(j).toString());
        }

        // Find the index of the movie's rating in the list
        int ratingIndex = ratingList.indexOf(movie.getRating());

        // Set the spinner selection to the correct rating index
        if (ratingIndex != -1) {
            spinner.setSelection(ratingIndex);
        }
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DBHelper db=new DBHelper(ModifyMovie.this);
                movie.updateDetails(etmt2.getText().toString(),etgen2.getText().toString(),
                        Integer.parseInt(ety2.getText().toString()),spinner.getSelectedItem().toString());
                db.updateMovie(movie);
                db.close();

                Toast.makeText(ModifyMovie.this, "Movie updated successful",
                        Toast.LENGTH_SHORT).show();
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DBHelper db=new DBHelper(ModifyMovie.this);
                db.deleteMovie(movie.getId());
                finish();
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ModifyMovie.this,
                        ShowMovies.class);
                startActivity(i);
            }
        });
    }
}