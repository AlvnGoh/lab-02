//Program description: Display a list of cities and provides functionality to remove
// or add cities to the list. Scroll up or down to view the list. Click on add city, type into the
// input box and then click confirm to add a city. Click on delete city, type into the input box the city
// you want to delete, then click confirm.

package com.example.listycity;

import android.os.Bundle;
//Import android and java packages
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import java.util.ArrayList;
import java.util.Arrays;

import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Button;
import com.google.android.material.textfield.TextInputEditText;

//Main activity class
public class MainActivity extends AppCompatActivity {

    //attributes
    private ListView cityList;
    private ArrayAdapter<String> cityAdapter;
    private ArrayList<String> dataList;

    private Button add;
    private Button delete;
    private Button confirm;

    private TextInputEditText input;

    private Boolean addone;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //linking attributes to activity widgets
        cityList = findViewById(R.id.city_list);
        add = findViewById(R.id.add);
        delete = findViewById(R.id.delete);
        confirm = findViewById(R.id.confirm);
        input = findViewById(R.id.editInput);
        //boolean to decide whether to add or delete cities
        addone = true;

        //array of strings for the initial pool of cities
        String[] cities = {"Edmonton", "Vancouver", "Moscow", "Sydney", "Berlin", "Vienna", "Tokyo", "Beijing", "Osaka", "New Delhi"};

        //array list for the list of cities
        dataList = new ArrayList<>();
        dataList.addAll(Arrays.asList(cities));

        //set up the listview layout in the activity
        cityAdapter = new ArrayAdapter<>(this, R.layout.listycitylayout, dataList);
        cityList.setAdapter(cityAdapter);

        //set up clicking functionality for the buttons
        add.setOnClickListener(v -> adding());
        delete.setOnClickListener(v -> deleting());
        confirm.setOnClickListener(v -> readInput(dataList));

    }

    //decide that we want to add a city
    private void adding() {
        addone = true;
    }

    //decide that we want to delete a city
    private void deleting() {
        addone = false;
    }

    //read input
    private void readInput(ArrayList dataList) {
        //string is "" if input is empty else return a string
        String UserInput = input.getText() != null ? input.getText().toString().trim() : "";

        //if no input, tell client input required
        if (UserInput.isEmpty()) {
            input.setError("Input Required");
            return;
        }

        //add a city by adding the user input into the array list
        if (addone == true) {
            dataList.add(UserInput);
            cityAdapter = new ArrayAdapter<>(this, R.layout.listycitylayout, dataList);
            cityList.setAdapter(cityAdapter);
        }

        //delete a city by removing the user input from the array list
        if (addone == false) {
            //check if the city is in the array list first
            if (dataList.contains(UserInput) == false) {
                input.setError("Invalid City");
            }
            dataList.remove(UserInput);
            cityAdapter = new ArrayAdapter<>(this, R.layout.listycitylayout, dataList);
            cityList.setAdapter(cityAdapter);
        }
    }
}