package com.example.mobi;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class HomeActivity2 extends AppCompatActivity implements View.OnClickListener {


    TextView txtBalance;
    Button shopButton;
    Button searchButton;
    Button historyButton;
    Button settingsButton;
    Button helpButton;
    Button exitButton;
    private PreferenceHelper preferenceHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home2);

        preferenceHelper = new PreferenceHelper(this);



        txtBalance = (TextView) findViewById(R.id.txtbalance);
        Typeface appleFont= Typeface.createFromAsset(getAssets(),"fonts/SF-Regular.ttf");
        Typeface appleFontBold= Typeface.createFromAsset(getAssets(),"fonts/SF-Bold.ttf");

        txtBalance.setTypeface(appleFontBold);

        shopButton = (Button) findViewById(R.id.btnShop);
        shopButton.setTypeface(appleFont);

        searchButton = (Button) findViewById(R.id.btnSearch);
        searchButton.setTypeface(appleFont);

        historyButton = (Button) findViewById(R.id.btnHistory);
        historyButton.setTypeface(appleFont);

        settingsButton = (Button) findViewById(R.id.btnSettings);
        settingsButton.setTypeface(appleFont);

        helpButton = (Button) findViewById(R.id.btnHelp);
        helpButton.setTypeface(appleFont);

        exitButton = (Button) findViewById(R.id.btnExit);
        exitButton.setTypeface(appleFont);

        shopButton.setOnClickListener(this);
        exitButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        //initiating the qr code scan

        switch (v.getId()) {

            case R.id.btnExit:
                preferenceHelper.putIsLogin(false);
                Intent intent = new Intent(HomeActivity2.this,LauncherActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                HomeActivity2.this.finish();

                break;

            case R.id.btnShop:
                startActivity(new Intent(HomeActivity2.this, ShopActivity.class));
                break;

            default:
                break;
        }
    }
}
