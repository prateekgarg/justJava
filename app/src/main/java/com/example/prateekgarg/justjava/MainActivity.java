

package com.example.prateekgarg.justjava;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
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
        //displayMessage(createOrderSummary(price, whippedCreamState, chocolateState, nameEntered));
        sendIntent(createOrderSummary(price, whippedCreamState, chocolateState, nameEntered), nameEntered);
    }

    //This method is called from the onClick method of Order Button
    //Allows the app to pack data like name and order to send it over to an email app
    public void sendIntent(String message, String name) {
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:"));

        intent.putExtra(Intent.EXTRA_SUBJECT, "JustJava Coffee Order for " + name);
        intent.putExtra(Intent.EXTRA_TEXT, message);
        intent.putExtra(Intent.EXTRA_EMAIL, new String[]{"sampleEmail@outlook.com"});
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        } else {
            Log.e("Email Intent Problem", "Failed to get any package capable of handling email sending");
        }
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

     private void displayMessage(String message) {
        TextView orderSummaryTextView = findViewById(R.id.order_summary_text_view);
        orderSummaryTextView.setText(message);
     }
     */
    /**
     * This method displays the given quantity value on the screen.
     */
    private void displayQantity(int number) {
        TextView quantityTextView = findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }

    //Increment quantity, maxed out to 100
    public void increment(View view) {
        //int quantity = 3;
        if (quantity >= 100) {
            quantity = 100;
            displayQantity(100);
        } else {
            displayQantity(++quantity);
        }
    }

    //Decrement quantity, minimum set to 0 as we cannot have negative cups of coffee in an order
    public void decrement(View view) {
        //int quantity = 1;
        if (quantity <= 0) {
            displayQantity(0);
            quantity = 0;
        } else
            displayQantity(--quantity);
    }

    /*
    * Create a longer summary of the order. Did not do it as done in the course
    * */
    public String createOrderSummary(int price, boolean whippedCreamState, boolean chocolateState, String name) {
        String result = "Name: " + name + "\nAdd whipped cream? " + whippedCreamState +
                "\nAdd Chocolate? " + chocolateState + "\nQuantity: " + quantity + "\nTotoal: $" + price + "\n" + getString(R.string.thanks);
        return result;
    }
}
