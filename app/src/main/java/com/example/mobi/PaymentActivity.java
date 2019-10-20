package com.example.mobi;


import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.example.mobi.mpesa.ApiClient;
import com.example.mobi.mpesa.Utils;
import com.example.mobi.mpesa.model.AccessToken;
import com.example.mobi.mpesa.model.STKPush;
import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import timber.log.Timber;
import static com.example.mobi.Constants.BUSINESS_SHORT_CODE;
import static com.example.mobi.Constants.CALLBACKURL;
import static com.example.mobi.Constants.PARTYB;
import static com.example.mobi.Constants.PASSKEY;
import static com.example.mobi.Constants.TRANSACTION_TYPE;
import 	android.widget.PopupWindow;

public class PaymentActivity extends AppCompatActivity implements View.OnClickListener {

    private ApiClient mApiClient;
    private PopupWindow mPopupWindow;
    private Activity mActivity;
    private Context mContext;
    private ProgressDialog mProgressDialog;
    Double totalAmount = 1170.00;
    private RelativeLayout mRelativeLayout;


    @BindView(R.id.buttonReceipt) Button buttonReceipt;
    @BindView(R.id.buttonPay) Button buttonPay;
    @BindView(R.id.tvTotal) TextView total;
    @BindView(R.id.textView2) TextView tvTitle;
    @BindView(R.id.shoppingCart) ListView myCart;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);
        ButterKnife.bind(this);

        mProgressDialog = new ProgressDialog(this);
        mApiClient = new ApiClient();
        mApiClient.setIsDebug(true);
        mContext = getApplicationContext();
        mRelativeLayout = (RelativeLayout) findViewById(R.id.relativeLayout);



        buttonPay.setOnClickListener(this);
        buttonReceipt.setOnClickListener(this);

        getAccessToken();

        String[] bill = new String[]{
                "Samsung USB Type C Charger              500.00",
                "SparkleFresh Drinking Water 1 Litre       70.00",
                "Logitech Wireless Mouse                       600.00"
        };

        final List<String> items_list = new ArrayList<String>(Arrays.asList(bill));

        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>
                (this, android.R.layout.simple_list_item_1, items_list);

        myCart.setAdapter(arrayAdapter);


        ArrayList arrayList = new ArrayList<String>();
        ArrayAdapter adapter = new ArrayAdapter<>(PaymentActivity.this, android.R.layout.simple_list_item_1, arrayList);


        Typeface appleFontRegular = Typeface.createFromAsset(getAssets(), "fonts/SF-Regular.ttf");
        Typeface appleFontBold = Typeface.createFromAsset(getAssets(), "fonts/SF-Bold.ttf");

        buttonPay.setTypeface(appleFontRegular);
        total.setTypeface(appleFontBold);
        total.setText("TOTAL: 1,170.00");
    }

    public void getAccessToken() {
        mApiClient.setGetAccessToken(true);
        mApiClient.mpesaService().getAccessToken().enqueue(new Callback<AccessToken>() {
            @Override
            public void onResponse(Call<AccessToken> call, retrofit2.Response<AccessToken> response) {
                if (response.isSuccessful()) {
                    mApiClient.setAuthToken(response.body().accessToken);
                }
            }

            @Override
            public void onFailure(@NonNull Call<AccessToken> call, @NonNull Throwable t) {

            }
        });
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.buttonPay:
                LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(LAYOUT_INFLATER_SERVICE);
                View customView = inflater.inflate(R.layout.payment_popup,null);
                mPopupWindow = new PopupWindow(
                        customView,
                        RelativeLayout.LayoutParams.WRAP_CONTENT,
                        RelativeLayout.LayoutParams.WRAP_CONTENT
                );

                if(Build.VERSION.SDK_INT>=21){
                    mPopupWindow.setElevation(5.0f);
                }
                mPopupWindow.showAtLocation(mRelativeLayout, Gravity.CENTER,0,0);
                mPopupWindow.setFocusable(true);
                mPopupWindow.update();

                Button btnSTKRequest = (Button) customView.findViewById(R.id.btnSTKRequest);
                EditText phonenumber = (EditText) customView.findViewById(R.id.etPhoneNumber);

                btnSTKRequest.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String phone_number = phonenumber.getText().toString();
                        String amount = totalAmount.toString();
                        performSTKPush(phone_number, amount);
                        mPopupWindow.dismiss();
                    }
                });




                break;

            case R.id.buttonReceipt:
                startActivity(new Intent(PaymentActivity.this, ReceiptActivity.class));
                break;

            default:
                break;
        }
    }


    public void performSTKPush(String phone_number,String amount) {
        mProgressDialog.setMessage("Processing your request");
        mProgressDialog.setTitle("Please Wait...");
        mProgressDialog.setIndeterminate(true);
        mProgressDialog.show();
        String timestamp = Utils.getTimestamp();
        STKPush stkPush = new STKPush(
                BUSINESS_SHORT_CODE,
                Utils.getPassword(BUSINESS_SHORT_CODE, PASSKEY, timestamp),
                timestamp,
                TRANSACTION_TYPE,
                String.valueOf(amount),
                Utils.sanitizePhoneNumber(phone_number),
                PARTYB,
                Utils.sanitizePhoneNumber(phone_number),
                CALLBACKURL,
                "test", //The account reference
                "test"  //The transaction description
        );

        mApiClient.setGetAccessToken(false);

        mApiClient.mpesaService().sendPush(stkPush).enqueue(new Callback<STKPush>() {
            @Override
            public void onResponse(@NonNull Call<STKPush> call, @NonNull Response<STKPush> response) {
                mProgressDialog.dismiss();
                try {
                    if (response.isSuccessful()) {
                        Timber.d("post submitted to API. %s", response.body());
                    } else {
                        Timber.e("Response %s", response.errorBody().string());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(@NonNull Call<STKPush> call, @NonNull Throwable t) {
                mProgressDialog.dismiss();
                Timber.e(t);
            }
        });
    }


}