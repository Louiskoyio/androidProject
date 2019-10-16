package com.example.mobi;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mobi.models.Product;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class InsertActivity extends AppCompatActivity {
    EditText etBrand, etName, etPrice;
    Button btnAdd;
    DatabaseReference ref;
    Product product;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert);
        etBrand = (EditText) findViewById(R.id.etBrand);
        etName = (EditText) findViewById(R.id.etName);
        etPrice = (EditText) findViewById(R.id.etPrice);
        btnAdd = (Button) findViewById(R.id.btnAddProduct);

        product = new Product();
        ref = FirebaseDatabase.getInstance().getReference().child("Products");
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Double price = Double.parseDouble(etPrice.getText().toString().trim());
                String name = etName.getText().toString().trim();
                String brand = etBrand.getText().toString().trim();

                product.setPrice(price);
                product.setBrand(brand);
                product.setName(name);

                ref.push().setValue(product);
                Toast.makeText(InsertActivity.this, "Imeweza bro!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
