package com.example.unitconverterapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText valueInput;
    private Spinner unitSpinner;
    private Button convertButton;
    private TextView resultText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        valueInput = findViewById(R.id.valueInput);
        unitSpinner = findViewById(R.id.unitSpinner);
        convertButton = findViewById(R.id.convertButton);
        resultText = findViewById(R.id.resultText);


        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.unit_options, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        unitSpinner.setAdapter(adapter);

        convertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                convertValue();
            }
        });
    }

    private void convertValue() {
        String inputValueStr = valueInput.getText().toString();
        if (inputValueStr.isEmpty()) {
            Toast.makeText(this, "Please enter a value", Toast.LENGTH_SHORT).show();
            return;
        }

        double inputValue = Double.parseDouble(inputValueStr);

        // Check if a unit is selected
        if (unitSpinner.getSelectedItem() == null) {
            Toast.makeText(this, "Please select a unit", Toast.LENGTH_SHORT).show();
            return;
        }

        String selectedUnit = unitSpinner.getSelectedItem().toString();

        double result;
        String conversionMessage = "The Desired Conversion is: ";
        switch (selectedUnit) {
            case "Centimeters to Meters":
                result = inputValue / 100;
                break;
            case "Meters to Centimeters":
                result = inputValue * 100;
                break;
            case "Grams to Kilograms":
                result = inputValue / 1000;
                break;
            case "Kilograms to Grams":
                result = inputValue * 1000;
                break;
            default:
                result = 0;
        }

        // Concatenate the message with the result
        String resultMessage = conversionMessage + String.valueOf(result);
        resultText.setText(resultMessage);
    }
}