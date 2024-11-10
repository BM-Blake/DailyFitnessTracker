package com.example.dailyfitnesstracker;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class CaloriesActivity extends AppCompatActivity {

    private Toast toast;
    private double calculateCaloriesBurned(int stepsTaken, int age, int weight) {
        double met = 3.5;
        double stepsPerMile = 2000;
        double miles = stepsTaken / stepsPerMile;
        double ageFactor = 1 - (age - 20) * 0.001;
        return met * miles * ageFactor * weight;
    }

    @SuppressLint({"DefaultLocale", "SetTextI18n"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_calories);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        TextView caloriesBurned = findViewById(R.id.calories_burned);
        EditText ageInput = findViewById(R.id.age_input);
        EditText weightInput = findViewById(R.id.weigh_input);
        Button calculateButton = findViewById(R.id.calculate_button);
        Button backButton = findViewById(R.id.back_button);
        int stepsTaken = getIntent().getIntExtra("STEPS_TAKEN", 0);

        backButton.setOnClickListener(v -> finish());

        calculateButton.setOnClickListener(v -> {
            try {
                int weight = Integer.parseInt(weightInput.getText().toString());
                int age = Integer.parseInt(ageInput.getText().toString());

                double calories = calculateCaloriesBurned(stepsTaken, age, weight);
                caloriesBurned.setText("Calories Burned: " + Math.round(calories));
            } catch (NumberFormatException e) {

                if (toast != null) {
                    toast.cancel();
                }          // Handle the exception, e.g., show an error message
                toast = Toast.makeText(this, "Please enter valid age and weight", Toast.LENGTH_SHORT);
                toast.show();
            }
        }); // Closing parenthesis added here
    }
}