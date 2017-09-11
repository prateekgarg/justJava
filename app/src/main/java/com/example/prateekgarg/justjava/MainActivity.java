

package com.example.prateekgarg.justjava;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import java.text.NumberFormat;

public class MainActivity extends AppCompatActivity {
    int quantity = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
        int price = calculatePrice();

        displayMessage(createOrderSummary(price));
    }

    /**
     * Calculates the price of the order based on the current quantity.
     *
     * @return the price
     */
    private int calculatePrice() {
        return quantity * 5;
    }
    /**
     * This method displays the given text on the screen.
     */
    private void displayMessage(String message) {
        TextView orderSummaryTextView = findViewById(R.id.order_summary_text_view);
        orderSummaryTextView.setText(message);
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void displayQantity(int number) {
        TextView quantityTextView = findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }

    public void increment(View view) {
        //int quantity = 3;
        displayQantity(++quantity);
    }

    public void decrement(View view) {
        //int quantity = 1;
        if (quantity <= 0) {
            displayQantity(0);
            quantity = 0;
        } else
            displayQantity(--quantity);
    }

    /*
    * Create a longer summary of the order. Hardcode the name for now
    * */
    public String createOrderSummary(int price) {
        String result = "Name: Awesome Possum\nQuantity: " + quantity + "\nTotoal: $" + price + "\nThank You!";
        return result;
    }
}
