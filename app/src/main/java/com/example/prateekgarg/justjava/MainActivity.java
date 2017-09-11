

package com.example.prateekgarg.justjava;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

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
        boolean whippedCreamState = ((CheckBox) findViewById(R.id.whipped_cream_checkbox)).isChecked();
        boolean chocolateState = ((CheckBox) findViewById(R.id.chocolate_checkbox)).isChecked();
        EditText name = findViewById(R.id.name);
        String nameEntered = name.getText().toString();
        //Log.v("Whipped Cream checkbox",  "" + whippedCreamState);
        //Log.v("Chocolate checkbox",  "" + chocolateState);
        int price = calculatePrice(whippedCreamState, chocolateState);
        displayMessage(createOrderSummary(price, whippedCreamState, chocolateState, nameEntered));
    }

    /**
     * Calculates the price of the order based on the current quantity.
     *
     * @return the price
     */
    private int calculatePrice(boolean whippedCream, boolean chocolate) {
        int price = 5;
        if (whippedCream) {
            price += 1;
        }
        if (chocolate) {
            price += 2;
        }
        return quantity * price;
    }
    /**
     * This method displays the given text on the screen.
     */
    private void displayMessage(String message) {
        TextView orderSummaryTextView = findViewById(R.id.order_summary_text_view);
        //orderSummaryTextView.
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
        if (quantity >= 100) {
            quantity = 100;
            displayQantity(100);
        } else {
            displayQantity(++quantity);
        }
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
    public String createOrderSummary(int price, boolean whippedCreamState, boolean chocolateState, String name) {
        String result = "Name: " + name + "\nAdd whipped cream? " + whippedCreamState + "\nAdd Chocolate? " + chocolateState + "\nQuantity: " + quantity + "\nTotoal: $" + price + "\nThank You!";
        return result;
    }
}
