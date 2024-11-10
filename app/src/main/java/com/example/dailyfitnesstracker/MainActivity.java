package com.example.dailyfitnesstracker; // Replace with your package name

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private Toast toast; // Declare a Toast variable

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button calculateCaloriesButton = findViewById(R.id.calculate_calories_button);
        EditText stepsTakenInput = findViewById(R.id.steps_input); // Assuming this is the ID of your EditText

        calculateCaloriesButton.setOnClickListener(v -> {
            // Get the input value from the EditText
            String stepsTakenString = stepsTakenInput.getText().toString();

            // Check if input is empty
            if (stepsTakenString.isEmpty()) {
                // Cancel the previous Toast if it exists
                if (toast != null) {
                    toast.cancel();
                }

                // Show a Toast message (create a new Toast each time)
                toast = Toast.makeText(MainActivity.this, "Please enter steps taken", Toast.LENGTH_SHORT);
                toast.show();
                return; // Prevent further execution
            }

            // Convert the input to an integer (handle potential NumberFormatException)
            int stepsTaken = Integer.parseInt(stepsTakenString);

            // Create an Intent to start CaloriesActivity
            Intent intent = new Intent(MainActivity.this, CaloriesActivity.class);

            // Add the stepsTaken value as an extra to the Intent
            intent.putExtra("STEPS_TAKEN", stepsTaken);

            // Start the activity
            startActivity(intent);
        });
    }
}