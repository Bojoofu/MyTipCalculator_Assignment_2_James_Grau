/**
 * James Grau
 * February 2, 2019
 * Assignment 2
 **/

// This is the packages that this class belongs to
package com.example.mytipcalculator_assignment_2_james_grau;

// Import the needed packages

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
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

        // Set the selection of the spinner to the first item of the array
        spnTipAmount.setSelection(0, true);

        // Create a variable to store the view of the spinner
        View v = spnTipAmount.getSelectedView();

        // Set the textView color of the spinner to white
        ((TextView) v).setTextColor(Color.WHITE);

        // Add an event handler for when an item is selected
        spnTipAmount.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            // This function is used to handle when an item is selected
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // Set the textView color of the spinner to white
                ((TextView) view).setTextColor(Color.WHITE);
            }

            // This method is used to handle when nothing is selected
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    // This method is used to perform the calculation of the tip and final amount
    public void calculateTip(View view) {
        // Check if the bill amount was added/empty
        if (txtBillAmount.getText().toString().isEmpty()) {
            // Initialize an alert dialog as the bill amount wasn't entered
            AlertDialog.Builder builder = new AlertDialog.Builder(this);

            // Set the alert Message and Title
            builder.setMessage("Oops... The bill amount wasn't entered.  Please enter the bill amount and try again.").setTitle("Bill Amount Missing");

            // Set the alert 'OK' button
            builder.setPositiveButton("I'll try again.", new DialogInterface.OnClickListener() {
                // This function is used to perform the onClick action for the button
                public void onClick(DialogInterface dialog, int id) {
                    // Just return as the user will try again
                    return;
                }
            });

            // Initialize the dialog
            AlertDialog dialog = builder.create();

            // Show the alert dialog
            dialog.show();

            // Return as the user hasn't entered a bill amount
            return;
        } else if (Double.parseDouble(txtBillAmount.getText().toString()) < 0) {
            // Initialize an alert dialog as the bill amount isn't above 0
            AlertDialog.Builder builder = new AlertDialog.Builder(this);

            // Set the alert Message and Title
            builder.setMessage("Oops... The bill amount wasn't a valid amount.  Please enter a valid bill amount and try again.").setTitle("Invalid Bill Amount");

            // Set the alert 'OK' button
            builder.setPositiveButton("I'll try again.", new DialogInterface.OnClickListener() {
                // This function is used to perform the onClick action for the button
                public void onClick(DialogInterface dialog, int id) {
                    // Just return as the user will try again
                    return;
                }
            });

            // Initialize the dialog
            AlertDialog dialog = builder.create();

            // Show the alert dialog
            dialog.show();

            // Return as the user hasn't entered a bill amount
            return;
        }

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
