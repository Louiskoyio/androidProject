package com.example.mobi;


import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;


public class LauncherActivity extends AppCompatActivity implements View.OnClickListener {

    RelativeLayout rellay1;
    EditText etphonenumber, etpassword;
    CheckBox remember;
    Button loginBtn;
    TextView tvPhonenumber, tvPassword, tvLogin;

    Handler handler = new Handler();
    private final int LoginTask = 1;
    private PreferenceHelper preferenceHelper;
    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            rellay1.setVisibility(View.VISIBLE);

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launcher);


        preferenceHelper = new PreferenceHelper(this);


        etphonenumber = findViewById(R.id.etPhonenumber);
        etpassword = findViewById(R.id.etPassword);


        rellay1 = findViewById(R.id.rellay1);
        tvLogin = findViewById(R.id.tvLogin);
        tvPhonenumber = findViewById(R.id.tvUsername);
        tvPassword = findViewById(R.id.tvPassword);
        loginBtn= findViewById(R.id.btnLogin);
        remember = findViewById(R.id.rememberMe);




        Typeface appleFont= Typeface.createFromAsset(getAssets(),"fonts/SF-Regular.ttf");
        tvLogin.setTypeface(appleFont);
        tvPhonenumber.setTypeface(appleFont);
        tvPassword.setTypeface(appleFont);
        remember.setTypeface(appleFont);
        loginBtn.setTypeface(appleFont);


        handler.postDelayed(runnable, 2000);
        loginBtn.setOnClickListener(this);


    }


    public void onClick(View v) {


        switch (v.getId()) {

            case R.id.btnLogin:
               /* try {
                    login();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }*/
                startActivity(new Intent( LauncherActivity.this, HomeActivity.class));
                break;


            default:
                break;
        }
    }

    private void onTaskCompleted(String response, int task) {
        Log.d("responsejson", response.toString());
        switch (task) {
            case LoginTask:

                    Intent intent = new Intent(LauncherActivity.this,HomeActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                    this.finish();

        }
    }


}
