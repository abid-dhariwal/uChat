package com.example.uchat;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.hbb20.CountryCodePicker;

public class LoginNumber extends AppCompatActivity {
    EditText etMobile;
    Button btnOTP;
    CountryCodePicker countryCode;
    ProgressBar ProgressbarPN;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_number);

        init();
        btnOTP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phoneNumber = etMobile.getText().toString().trim();
                if (!countryCode.isValidFullNumber()) {
                    etMobile.setError("Phone number is not valid!");
                    return;
                }
                Intent intent = new Intent(LoginNumber.this, OTP_Activity.class);
                intent.putExtra("phoneNumber",countryCode.getFullNumberWithPlus());
                startActivity(intent);
                ProgressbarPN.setVisibility(View.VISIBLE);
            }
        });
    }
    private void init(){
        etMobile = findViewById(R.id.etMobile);
        btnOTP = findViewById(R.id.btnOTP);
        countryCode=findViewById(R.id.CountryCode);
        countryCode.registerCarrierNumberEditText(etMobile);
        ProgressbarPN=findViewById(R.id.Login_ProgressBarPN);
        ProgressbarPN.setVisibility(View.GONE);
    }
}