package com.example.musicshop;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class OrderActivity extends AppCompatActivity {

    String[] addresses = {"andrei-borchenko@mail.ru"};
    String subject = "Order from MusicShop";
    String emailText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order2);

        setTitle("Your order");

        Intent intent = getIntent();
        String userName = intent.getStringExtra("userName");
        String goodsName = intent.getStringExtra("goddsName");
        int quantity = intent.getIntExtra("quantity", 0);
        double orderPrice = intent.getDoubleExtra("orderPrice", 0);
        double price = intent.getDoubleExtra("price", 0);
        TextView orderTextView = findViewById(R.id.orderTextView);
        emailText = "Customer Name: " + userName + "\n" + "GoodsName: " + goodsName + "\n" + "Quantity: " + quantity + "\n"
                +"OrderPrice: " + orderPrice
                + "\n" + "Price: " + price;
        orderTextView.setText(emailText);

    }

    public void submitOrder(View view) {
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_EMAIL, addresses);
        intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        intent.putExtra(Intent.EXTRA_TEXT, emailText);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }
}