package com.example.android.justjava;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import java.text.NumberFormat;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends ActionBarActivity {
    int quantity=1;
    boolean hasChecked;
    boolean hasChocolateChecked;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
     /*   int numberOfCoffees= quantity;
        display(numberOfCoffees);
        displayPrice(numberOfCoffees * 5);
        */
        CheckBox checkBox = (CheckBox) findViewById(R.id.notify_me_checkbox);
        CheckBox chocolateCheckBox = (CheckBox) findViewById(R.id.notify_me_chocolateCheckbox);

         hasChecked= checkBox.isChecked();
         hasChocolateChecked = chocolateCheckBox.isChecked();

        int price = calculatePrice();
        String displayMessage;
        EditText nameTextView = (EditText) findViewById(R.id.editText);
        String name = nameTextView.getText().toString();

        displayMessage= getResources().getString(R.string.name) + " " + name + "\n" + getResources().getString(R.string.order_summary_whipped_cream)+ " " + hasChecked +" \n" + getResources().getString(R.string.order_summary_chocolate)+" " + hasChocolateChecked + " \n"+ getResources().getString(R.string.quantity)+ "" + quantity + "\nTotal $" + price + "\n"+ getResources().getString(R.string.thank_you);
        //displayMessage(displayMessage);

        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_SUBJECT, getResources().getString(R.string.order_summary_email_subject));
        intent.putExtra(Intent.EXTRA_TEXT, displayMessage);

        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }

    }

    private int calculatePrice(){
        int cupPrice=5;
        if (hasChocolateChecked){
            cupPrice = cupPrice + 2;
        }
        if (hasChecked){
            cupPrice = cupPrice + 1;
        }

         return quantity *cupPrice;
    }
    /**
     * This method displays the given quantity value on the screen.
     */
    private void display(int number) {
        TextView quantityTextView = (TextView) findViewById(
                R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }


    public void increment(View view) {
        if (quantity==100){
            return;
        }else {
            quantity = quantity + 1;
            display(quantity);
        }
    }

    public void decrement(View view) {
        if (quantity >1) {
            quantity = quantity - 1;
            display(quantity);
        }
    }

}