package com.example.mobi;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.text.DecimalFormat;
import android.widget.Toast;

import com.example.mobi.models.Item;
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
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import butterknife.BindView;
import butterknife.ButterKnife;

//implementing onclicklistener
public class ShopActivity extends AppCompatActivity implements View.OnClickListener {


    DatabaseReference ref;
    Double totalAmount=0.0;
    ArrayList<Item> shoppingCartArr =new ArrayList<>();
    ArrayList<String> shoppingCart = new ArrayList<>();
    ArrayAdapter<String> adapter;
    private RecyclerAdapter mRecyclerAdapter;
    private RecyclerView.LayoutManager layoutManager;


    private static DecimalFormat df2 = new DecimalFormat("#.##");

    private IntentIntegrator qrScan;
    @BindView(R.id.buttonScan) Button buttonScan;
    @BindView(R.id.buttonPay) Button buttonPay;
    @BindView(R.id.txtTotal) TextView total;
    @BindView(R.id.txtTitle) TextView tvTitle;
    @BindView(R.id.shoppingCart) RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);
        ButterKnife.bind(this);
        mRecyclerAdapter = new RecyclerAdapter(shoppingCartArr);
        recyclerView.setAdapter(mRecyclerAdapter);
        layoutManager = new GridLayoutManager(this,1);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);


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
                        Item item = new Item();
                        item.setBrand(brand);
                        item.setName(name);
                        item.setPrice(price);

                        shoppingCartArr.add(item);
                        mRecyclerAdapter.notifyDataSetChanged();


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
                    startActivity(new Intent(ShopActivity.this, PaymentActivity.class).putExtra("shoppingCart",shoppingCartArr).putExtra("totalAmount",totalAmount));
                    break;

                default:
                    break;
            }
    }

}