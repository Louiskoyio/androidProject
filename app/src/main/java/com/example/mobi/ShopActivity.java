package com.example.mobi;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

//implementing onclicklistener
public class ShopActivity extends AppCompatActivity implements View.OnClickListener {

    //View Objects
    private Button buttonScan;
    Button buttonPay;
    private TextView textViewName, textViewAddress;
    ListView myCart;
    TextView total, tvTitle;
    DatabaseReference ref;
    Double totalAmount=0.0;
    int counter;
    ArrayList<String> shoppingCartArr;
    ArrayAdapter<String> adapter;
    private static DecimalFormat df2 = new DecimalFormat("#.##");

    //qr code scanner object
    private IntentIntegrator qrScan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);


        //View objects
        buttonScan = (Button) findViewById(R.id.buttonScan);
        buttonPay = (Button) findViewById(R.id.buttonPay);
        myCart = (ListView) findViewById(R.id.shoppingCart);
        total = (TextView) findViewById(R.id.tvTotal);
        tvTitle = (TextView) findViewById(R.id.textView2);


        shoppingCartArr = new ArrayList<String>();
        adapter = new ArrayAdapter<>(ShopActivity.this, android.R.layout.simple_list_item_1,shoppingCartArr);
        myCart.setAdapter(adapter);

        Typeface appleFontRegular= Typeface.createFromAsset(getAssets(),"fonts/SF-Regular.ttf");
        Typeface appleFontBold= Typeface.createFromAsset(getAssets(),"fonts/SF-Bold.ttf");
        buttonScan.setTypeface(appleFontRegular);
        buttonPay.setTypeface(appleFontRegular);


        total.setTypeface(appleFontBold);
        //intializing scan object
        qrScan = new IntentIntegrator(this);


        buttonScan.setOnClickListener(this);
        buttonPay.setOnClickListener(this);

    }

    //Getting the scan results
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (result != null) {
            //if qrcode has nothing in it
            if (result.getContents() == null) {
                Toast.makeText(this, "Product Not Found", Toast.LENGTH_LONG).show();
            } else {
                //if qr contains data
                ref = FirebaseDatabase.getInstance().getReference().child("Products").child(result.getContents().toString());
                ref.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        String brand = dataSnapshot.child("brand").getValue().toString();
                        String name = dataSnapshot.child("name").getValue().toString();
                        Double price = Double.parseDouble(dataSnapshot.child("price").getValue().toString());

                        shoppingCartArr.add(brand + " " + name + "\t\t\t\t\t" + price);
                        adapter.notifyDataSetChanged();
                        totalAmount = totalAmount + price;
                        total.setText("TOTAL: " + totalAmount.toString());
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }
    @Override
    public void onClick(View v) {
        //initiating the qr code scan

            switch (v.getId()) {

                case R.id.buttonScan:
                    qrScan.initiateScan();
                    break;

                case R.id.buttonPay:
                    startActivity(new Intent(ShopActivity.this, PaymentActivity.class));
                    break;

                default:
                    break;
            }
    }

}