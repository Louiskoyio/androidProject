package com.example.mobi;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import androidx.appcompat.app.AppCompatActivity;

import butterknife.BindView;

public class PaymentActivity extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.buttonReceipt) Button buttonReceipt;
    @BindView(R.id.buttonPay) Button buttonPay;
    @BindView(R.id.btnExit) Button exitButton;
    @BindView(R.id.btnExit) TextView textViewName;
    @BindView(R.id.btnExit) TextView textViewAddress;
    @BindView(R.id.txtPaybill) TextView txtPaybill;
    @BindView(R.id.tvTotal) TextView total;
    @BindView(R.id.textView2) TextView tvTitle;
    @BindView(R.id.shoppingCart) ListView myCart;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);



        buttonPay.setOnClickListener(this);
        buttonReceipt.setOnClickListener(this);

        String[] bill = new String[] {
                "Samsung USB Type C Charger              500.00",
                "SparkleFresh Drinking Water 1 Litre       70.00",
                "Logitech Wireless Mouse                       600.00"
        };

        final List<String> items_list = new ArrayList<String>(Arrays.asList(bill));

        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>
                (this, android.R.layout.simple_list_item_1, items_list);

        myCart.setAdapter(arrayAdapter);


        ArrayList arrayList = new ArrayList<String>();
        ArrayAdapter adapter = new ArrayAdapter<>(PaymentActivity.this, android.R.layout.simple_list_item_1,arrayList);


        Typeface appleFontRegular= Typeface.createFromAsset(getAssets(),"fonts/SF-Regular.ttf");
        Typeface appleFontBold= Typeface.createFromAsset(getAssets(),"fonts/SF-Bold.ttf");

        buttonPay.setTypeface(appleFontRegular);
        txtPaybill.setTypeface(appleFontRegular);
        total.setTypeface(appleFontBold);
        total.setText("TOTAL: 1,170.00");
    }


        public void openDialog() {
        Typeface appleFontRegular= Typeface.createFromAsset(getAssets(),"fonts/SF-Regular.ttf");
        Typeface appleFontBold= Typeface.createFromAsset(getAssets(),"fonts/SF-Bold.ttf");

        final AlertDialog alertDialog = new AlertDialog.Builder(this).create();

        // Set Custom Title
        TextView title = new TextView(this);
        // Title Properties
        title.setText("Confirm Transaction");
        title.setPadding(10, 10, 10, 10);   // Set Position
        title.setGravity(Gravity.CENTER);
        title.setTextColor(Color.BLACK);
        title.setTypeface(appleFontBold);
        title.setTextSize(20);
        alertDialog.setCustomTitle(title);

        // Set Message
        TextView msg = new TextView(this);
        // Message Properties
        msg.setTypeface(appleFontRegular);
        msg.setText("Confirm transaction? \n\n\n Account Balance:\t2,001 \n Bill:\t1,170 \n New Account Balance:\t831");
        msg.setGravity(Gravity.CENTER_HORIZONTAL);
        msg.setTextColor(Color.BLACK);
        alertDialog.setView(msg);

        // Set Button
        // you can more buttons
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL,"CONFIRM", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                confirmDialog();
            }
        });

        alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE,"CANCEL", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                // Perform Action on Button
            }
        });

        new Dialog(getApplicationContext());
        alertDialog.show();

        // Set Properties for OK Button
        final Button okBT = alertDialog.getButton(AlertDialog.BUTTON_NEUTRAL);
        LinearLayout.LayoutParams neutralBtnLP = (LinearLayout.LayoutParams) okBT.getLayoutParams();
        neutralBtnLP.gravity = Gravity.FILL_HORIZONTAL;
        okBT.setPadding(10, 10, 10, 10);   // Set Position
        okBT.setTextColor(Color.GREEN);
        okBT.setLayoutParams(neutralBtnLP);

        final Button cancelBT = alertDialog.getButton(AlertDialog.BUTTON_NEGATIVE);
        LinearLayout.LayoutParams negBtnLP = (LinearLayout.LayoutParams) okBT.getLayoutParams();
        negBtnLP.gravity = Gravity.FILL_HORIZONTAL;
        cancelBT.setTextColor(Color.RED);
        cancelBT.setLayoutParams(negBtnLP);
    }


public void confirmDialog(){
        Typeface appleFontRegular= Typeface.createFromAsset(getAssets(),"fonts/SF-Regular.ttf");
        Typeface appleFontBold= Typeface.createFromAsset(getAssets(),"fonts/SF-Bold.ttf");

        final AlertDialog alertDialog = new AlertDialog.Builder(this).create();

        // Set Custom Title
        TextView title = new TextView(this);
        // Title Properties
        title.setText("Confirm Transaction");
        title.setPadding(10, 10, 10, 10);   // Set Position
        title.setGravity(Gravity.CENTER);
        title.setTextColor(Color.BLACK);
        title.setTypeface(appleFontBold);
        title.setTextSize(20);
        alertDialog.setCustomTitle(title);

        // Set Message
        TextView msg = new TextView(this);
        // Message Properties
        msg.setTypeface(appleFontRegular);
        msg.setText("Transacion Confirmed\nNew Account Balance:\t831");
        msg.setGravity(Gravity.CENTER_HORIZONTAL);
        msg.setTextColor(Color.BLACK);
        alertDialog.setView(msg);

        // Set Button
        // you can more buttons
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL,"OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                buttonReceipt.setVisibility(View.VISIBLE);

            }
        });


        new Dialog(getApplicationContext());
        alertDialog.show();

        // Set Properties for OK Button
        final Button okBT = alertDialog.getButton(AlertDialog.BUTTON_NEUTRAL);
        LinearLayout.LayoutParams neutralBtnLP = (LinearLayout.LayoutParams) okBT.getLayoutParams();
        neutralBtnLP.gravity = Gravity.FILL_HORIZONTAL;
        okBT.setPadding(400, 10, 10, 10);   // Set Position
        okBT.setTextColor(Color.BLACK);
        okBT.setLayoutParams(neutralBtnLP);

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.buttonPay:
                openDialog();
                break;

            case R.id.buttonReceipt:
                startActivity(new Intent(PaymentActivity.this, ReceiptActivity.class));
                break;

            default:
                break;
        }
    }
}

