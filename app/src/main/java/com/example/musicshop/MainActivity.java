package com.example.musicshop;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.AndroidException;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    int quantity = 0;
    Spinner spinner;

    ArrayList spinnerArraylist;
    ArrayAdapter spinnerArrayAdapter;
    HashMap goodsMap;
    String goodsName;
    double price;
    EditText userNameEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
         spinner = findViewById(R.id.spinner);
         spinner.setOnItemSelectedListener(this);
         spinnerArraylist = new ArrayList();
         spinnerArraylist.add("cement");
         spinnerArraylist.add("brick");
         spinnerArraylist.add("expanded clay");
         createSpinner();
         userNameEditText = findViewById(R.id.editTextTextPersonName);
         goodsMap = new HashMap();
         goodsMap.put("cement", 500);
        goodsMap.put("brick", 1500);
        goodsMap.put("expanded clay", 2000);
    }

    public void increaseQuantity(View view) {
        quantity++;
        TextView quantityTextView = findViewById(R.id.orderQuantityTextView);
        quantityTextView.setText(quantity + "");
        TextView priceTextView = findViewById(R.id.orderPriceTextView);
        priceTextView.setText("" + quantity * price);
    }

    public void decreaseQuantity(View view) {
        if(quantity>0) {
            quantity--;
        }
        TextView quantityTextView = findViewById(R.id.orderQuantityTextView);
        quantityTextView.setText(quantity + "");
        TextView priceTextView = findViewById(R.id.orderPriceTextView);
        priceTextView.setText("" + quantity * price);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        goodsName = spinner.getSelectedItem().toString();
        price = (Integer) goodsMap.get(goodsName);
        System.out.println(price);
        TextView priceTextView = findViewById(R.id.orderPriceTextView);
        priceTextView.setText("" + quantity * price);

        ImageView goodsImageView = findViewById(R.id.goodsImageView);
        switch (goodsName) {
            case "cement":
                goodsImageView.setImageResource(R.drawable.portlandsement);
                break;
            case "brick":
                goodsImageView.setImageResource(R.drawable.brick);
                break;
            case "expanded clay":
                goodsImageView.setImageResource(R.drawable.exp_clay);
                break;
            default:
                goodsImageView.setImageResource(R.drawable.portlandsement);
        }
    }

    public void createSpinner() {
        spinnerArrayAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, spinnerArraylist);
        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(spinnerArrayAdapter);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public void addtoCart(View view) {
        Order order = new Order();
        order.userName = userNameEditText.getText().toString();

        order.goddsName = goodsName;

        order.quantity = quantity;

        order.orderPrice = quantity*price;

        order.price = price;

        Intent orderIntent = new Intent(MainActivity.this, OrderActivity.class);
        orderIntent.putExtra("userName", order.userName);
        orderIntent.putExtra("goddsName", order.goddsName);
        orderIntent.putExtra("quantity", order.quantity);
        orderIntent.putExtra("orderPrice", order.orderPrice);
        orderIntent.putExtra("price", order.price);
        startActivity(orderIntent);
    }
}