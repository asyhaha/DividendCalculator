package com.example.dividendcalculator;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    EditText investAmount, dividendRate, monthsInvested;
    Button calculateButton, clearButton, aboutButton;
    TextView resultText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        investAmount = findViewById(R.id.investAmount);
        dividendRate = findViewById(R.id.dividendRate);
        monthsInvested = findViewById(R.id.monthsInvested);

        calculateButton = findViewById(R.id.calculateButton);
        clearButton = findViewById(R.id.clearButton);
        aboutButton = findViewById(R.id.aboutButton);

        resultText = findViewById(R.id.resultText);

        // Calculate dividend when calculate button clicked
        calculateButton.setOnClickListener(view -> calculateDividend());

        // Clear inputs and result when clear button clicked
        clearButton.setOnClickListener(view -> clearInputs());

        // Open AboutActivity when about button clicked
        aboutButton.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, AboutActivity.class);
            startActivity(intent);
        });
    }

    private void calculateDividend() {
        try {
            double amount = Double.parseDouble(investAmount.getText().toString());
            double rate = Double.parseDouble(dividendRate.getText().toString());
            int months = Integer.parseInt(monthsInvested.getText().toString());

            if (months > 12) {
                resultText.setText("Number of months must be 12 or less.");
                return;
            }

            double monthlyDividend = (rate / 100) / 12 * amount;
            double totalDividend = monthlyDividend * months;

            DecimalFormat df = new DecimalFormat("0.00");
            resultText.setText("Monthly Dividend: RM " + df.format(monthlyDividend) +
                    "\nTotal Dividend: RM " + df.format(totalDividend));
        } catch (NumberFormatException e) {
            Toast.makeText(this, "Please enter valid numbers in all fields.", Toast.LENGTH_SHORT).show();
        }
    }

    private void clearInputs() {
        investAmount.setText("");
        dividendRate.setText("");
        monthsInvested.setText("");
        resultText.setText("");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.about) {
            startActivity(new Intent(this, AboutActivity.class));
        } else if (item.getItemId() == R.id.home) {
            startActivity(new Intent(this, MainActivity.class));
        }
        return true;
    }
}
