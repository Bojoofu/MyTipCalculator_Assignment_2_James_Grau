/**
 * James Grau
 * February 2, 2019
 * Assignment 2
 **/

// This is the packages that this class belongs to
package com.example.mytipcalculator_assignment_2_james_grau;

// Import the needed packages

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.text.NumberFormat;

// This is the main class to run the whole application
public class MainActivity extends AppCompatActivity {
    // Create the needed variables
    Spinner spnTipAmount;
    EditText txtBillAmount;
    CheckBox chbRoundTip;
    TextView lblTipAmount, lblTotalAmount;
    private String tipAmount, finalAmount;

    // This is the main method to run the application
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // Create the instance and set the current view
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Set the attributes of the form the respective variables
        txtBillAmount = findViewById(R.id.txtBillAmount);
        spnTipAmount = findViewById(R.id.spnTipAmount);
        chbRoundTip = findViewById(R.id.chbRoundTip);
        lblTipAmount = findViewById(R.id.lblTipAmount);
        lblTotalAmount = findViewById(R.id.lblTotalAmount);
    }

    // This method is used to perform the calculation of the tip and final amount
    public void calculateTip(View view) {
        // Create the needed variables
        Double billAmount = Double.parseDouble(txtBillAmount.getText().toString()),
                tipAmount,
                finalAmount;

        // Check which option is selected from the dropdown
        if (spnTipAmount.getSelectedItem().toString().contains("15")) {
            // Set the tip amount
            tipAmount = Math.round((billAmount * .15)*100.00)/100.00;
        }else{
            // Set the tip amount
            tipAmount = Math.round((billAmount * .20)*100.00)/100.00;
        }

        // Check if the round option is selected
        if(chbRoundTip.isChecked()) {
            // Set the tip amount and rounded up to the closest whole number
            tipAmount = Math.round(Math.ceil(tipAmount)*100.00)/100.00;
        }

        // Calculate the final amount
        finalAmount = Math.round((billAmount + tipAmount)*100.00)/100.00;

        // Create a new formatter instance that will format to a currency format
        NumberFormat formatter = NumberFormat.getCurrencyInstance();

        // Assign the correct fields their values
        lblTipAmount.setText(formatter.format(tipAmount));
        lblTotalAmount.setText(formatter.format(finalAmount));
    }

    // This function is used to save the instance state
    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        // Initialize the instance state save
        super.onSaveInstanceState(outState, outPersistentState);

        // Set the string to be saved
        outState.putString(lblTipAmount.getText().toString(), tipAmount);
        outState.putString(lblTotalAmount.getText().toString(), finalAmount);
    }

    // This function is used to restore the instance state
    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState, PersistableBundle persistentState) {
        // Initialize the instance state restore
        super.onRestoreInstanceState(savedInstanceState, persistentState);

        // Restore the variables on the instance restore
        tipAmount = savedInstanceState.getString("tipAmount");
        finalAmount = savedInstanceState.getString("finalAmount");

        // Set the labels on the actual activity
        lblTipAmount.setText(tipAmount);
        lblTotalAmount.setText(finalAmount);
    }
}
