package com.example.unitconverter;

import android.os.Bundle;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private Spinner fromSpinner, toSpinner;
    private EditText inputValue;
    private Button convertButton;
    private TextView resultText;

    private final String[] units = {"Feet", "Inches", "Centimeters", "Meters", "Yards"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inputValue = findViewById(R.id.inputValue);
        fromSpinner = findViewById(R.id.fromSpinner);
        toSpinner = findViewById(R.id.toSpinner);
        convertButton = findViewById(R.id.convertButton);
        resultText = findViewById(R.id.resultText);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, units);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        fromSpinner.setAdapter(adapter);
        toSpinner.setAdapter(adapter);

        convertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String input = inputValue.getText().toString().trim();

                if (input.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Please enter a value", Toast.LENGTH_SHORT).show();
                    return;
                }

                double value = Double.parseDouble(input);
                String fromUnit = fromSpinner.getSelectedItem().toString();
                String toUnit = toSpinner.getSelectedItem().toString();

                double result = convertLength(value, fromUnit, toUnit);
                resultText.setText("Result: " + result + " " + toUnit);
            }
        });
    }

    private double convertLength(double value, String fromUnit, String toUnit) {
        double meters;

        switch (fromUnit) {
            case "Feet":
                meters = value * 0.3048;
                break;
            case "Inches":
                meters = value * 0.0254;
                break;
            case "Centimeters":
                meters = value * 0.01;
                break;
            case "Yards":
                meters = value * 0.9144;
                break;
            case "Meters":
            default:
                meters = value;
                break;
        }

        switch (toUnit) {
            case "Feet":
                return meters / 0.3048;
            case "Inches":
                return meters / 0.0254;
            case "Centimeters":
                return meters / 0.01;
            case "Yards":
                return meters / 0.9144;
            case "Meters":
            default:
                return meters;
        }
    }
}
