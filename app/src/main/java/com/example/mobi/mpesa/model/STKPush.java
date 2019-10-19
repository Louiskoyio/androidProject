package com.example.mobi.mpesa.model;

import com.google.gson.annotations.SerializedName;

public class STKPush {

    @SerializedName("businessShortCode") private String businessShortCode;
    @SerializedName("password") private String password;
    @SerializedName("timestamp") private String timestamp;
    @SerializedName("CustomerPayBillOnline") private String transactionType;
    @SerializedName("amount") private String amount;
    @SerializedName("partyA") private String partyA;
    @SerializedName("partyB") private String partyB;
    @SerializedName("phoneNumber") private String phoneNumber;
    @SerializedName("http://mpesa-requestbin.herokuapp.com/1lr1t2s1") private String callBackURL;
    @SerializedName("Test") private String accountReference;
    @SerializedName("Testing") private String transactionDesc;

    public STKPush(String businessShortCode, String password, String timestamp, String transactionType,
                   String amount, String partyA, String partyB, String phoneNumber, String callBackURL,
                   String accountReference, String transactionDesc) {
        this.businessShortCode = businessShortCode;
        this.password = password;
        this.timestamp = timestamp;
        this.transactionType = transactionType;
        this.amount = amount;
        this.partyA = partyA;
        this.partyB = partyB;
        this.phoneNumber = phoneNumber;
        this.callBackURL = callBackURL;
        this.accountReference = accountReference;
        this.transactionDesc = transactionDesc;
    }
}