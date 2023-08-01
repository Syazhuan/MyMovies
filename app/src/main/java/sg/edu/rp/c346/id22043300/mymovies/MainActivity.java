package sg.edu.rp.c346.id22043300.mymovies;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button btn_ins, btn_sl;
    EditText etMovieTitle, etGenre, etYear;
    Spinner spinner;
    String rating;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_ins = findViewById(R.id.btn_ins);
        etMovieTitle = findViewById(R.id.et_mt);
        etGenre = findViewById(R.id.et_genre);
        etYear = findViewById(R.id.et_y);
        btn_sl = findViewById(R.id.btn_sl);
        spinner = findViewById(R.id.spinner);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // Get the selected item from the spinner
                rating = parent.getItemAtPosition(position).toString();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Do something when nothing is selected
            }
        });

        btn_ins.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {


                //Convert all input into String
                String title = etMovieTitle.getText().toString();
                String genre = etGenre.getText().toString();
                String year= etYear.getText().toString();
                int finalYear=Integer.parseInt(year);

                // Create the DBHelper object, passing in the
                // activity's Context
                DBHelper db = new DBHelper(MainActivity.this);

                // Insert a task
                db.insertTask(title, genre, finalYear, rating);

                Toast.makeText(MainActivity.this, "Song insert successful",
                        Toast.LENGTH_SHORT).show();
            }
        });

        btn_sl.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ShowMovies.class);
                startActivity(intent);
            }
        });

    }
}