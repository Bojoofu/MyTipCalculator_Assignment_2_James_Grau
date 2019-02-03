package com.example.mytipcalculator_assignment_2_james_grau;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {
    // Create the needed variables
    Spinner spnTipAmount;
    EditText txtBillAmount;
    CheckBox chbRoundTip;
    TextView lblTipAmount, lblTotalAmount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Set the attributes of the form the respective variables
        txtBillAmount = findViewById(R.id.txtBillAmount);
        spnTipAmount = findViewById(R.id.spnTipAmount);
        chbRoundTip = findViewById(R.id.chbRoundTip);
        lblTipAmount = findViewById(R.id.lblTipAmount);
        lblTotalAmount = findViewById(R.id.lblTotalAmount);
    }

    public void calculateTip(View view) {
        // Create the needed variables
        Double billAmount = Double.parseDouble(txtBillAmount.getText().toString()),
                tipAmount,
                finalAmount;

        // Check which option is selected from the dropdown
        if(spnTipAmount.getSelectedItem() == "15%") {
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

        finalAmount = Math.round((billAmount + tipAmount)*100.00)/100.00;

        lblTipAmount.setText("$ " + tipAmount.toString());
        lblTotalAmount.setText("$ " + finalAmount.toString());
    }
}
