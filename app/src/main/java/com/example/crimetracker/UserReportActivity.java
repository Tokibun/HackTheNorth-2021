package com.example.crimetracker;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.maps.GoogleMap;


public class UserReportActivity extends AppCompatActivity {
    private GoogleMap mMap;


    //database

    //reference to the XML stuff
    Button reportButton;
    EditText UserInput1;
    EditText UserInput2;
    EditText UserInput3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userreport);

        reportButton = findViewById(R.id.reportButton);
        UserInput1 = findViewById(R.id.UserInput1);
        UserInput2 = findViewById(R.id.UserInput2);
        UserInput3 = findViewById(R.id.UserInput3);


        reportButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                UserReport userReport = null;

                try {
                    userReport = new UserReport(-1, UserInput1.getText().toString(), UserInput2.getText().toString(), Integer.parseInt(UserInput3.getText().toString()));
                    Toast.makeText(UserReportActivity.this, userReport.toString(), Toast.LENGTH_SHORT).show();
                } catch (Exception e){
                    Toast.makeText(UserReportActivity.this, "Error adding crime", Toast.LENGTH_SHORT).show();
                }

                DataBase database = new DataBase(UserReportActivity.this);
                boolean success = false;
                if (userReport != null) {
                    success = database.addOne(userReport);
                }
                Toast.makeText(UserReportActivity.this, "Success= " + success, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.my_nav, menu);
        return true;
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Change the map type based on the user's selection.
        switch (item.getItemId()) {
            case R.id.normal_map:
                mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
                return true;
            case R.id.hybrid_map:
                startActivity(new Intent(this, HelpActivity.class));
                return true;
            case R.id.satellite_map:
                startActivity(new Intent(this, UserReportActivity.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
