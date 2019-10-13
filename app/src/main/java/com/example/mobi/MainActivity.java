package com.example.mobi;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.mobi.models.Product;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public ArrayList<Product> cart = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }
}
