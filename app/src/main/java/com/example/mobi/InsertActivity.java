package com.example.mobi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mobi.models.Payments;
import com.example.mobi.models.Product;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class InsertActivity extends AppCompatActivity {
    EditText etBrand, etName, etPrice;
    Button btnAdd;
    DatabaseReference ref;
    Payments payment;
    long maxid=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert);
        etBrand = (EditText) findViewById(R.id.etBrand);
        etName = (EditText) findViewById(R.id.etName);
        etPrice = (EditText) findViewById(R.id.etPrice);
        btnAdd = (Button) findViewById(R.id.btnAddProduct);

        payment = new Payments();
        ref = FirebaseDatabase.getInstance().getReference().child("Payments");
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists())
                    maxid=(dataSnapshot.getChildrenCount());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        btnAdd.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                Double price = Double.parseDouble(etPrice.getText().toString().trim());
                String name = etName.getText().toString().trim();
                String brand = etBrand.getText().toString().trim();

                payment.setAmount(price);
                payment.setPhone(brand);
                payment.setReceipt(name);
                ref.child(name).setValue(payment);
                Toast.makeText(InsertActivity.this, "Imeweza bro!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
