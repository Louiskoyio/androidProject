package com.example.mobi;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener {
    @BindView(R.id.btnShop) Button shopButton;
    @BindView(R.id.btnHelp) Button helpButton;
    @BindView(R.id.btnExit) Button exitButton;
    @BindView(R.id.welcometxt) TextView welcomeText;


    private PreferenceHelper preferenceHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);

        preferenceHelper = new PreferenceHelper(this);

        Typeface appleFont= Typeface.createFromAsset(getAssets(),"fonts/SF-Regular.ttf");
        Typeface appleFontBold= Typeface.createFromAsset(getAssets(),"fonts/SF-Bold.ttf");

        //setting fonts

        welcomeText.setTypeface(appleFontBold);
        shopButton.setTypeface(appleFont);
        helpButton.setTypeface(appleFont);
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
                Intent intent = new Intent(HomeActivity.this,LauncherActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                HomeActivity.this.finish();

                break;


            case R.id.btnShop:
                startActivity(new Intent(HomeActivity.this,ShopActivity.class));
                break;


            case R.id.btnAddProduct:
                startActivity(new Intent(HomeActivity.this, InsertActivity.class));
                break;

            default:
                break;
        }
    }
}
