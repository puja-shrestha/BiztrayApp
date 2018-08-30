package com.example.puza.mobileui.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.puza.mobileui.Interface.ApiInterface;
import com.example.puza.mobileui.Manager.ApiClient;
import com.example.puza.mobileui.R;

import com.example.puza.mobileui.models.Registernew;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {

    private EditText first_name;
    private EditText last_name;
    private EditText email;
    private EditText password;
    private EditText confirm_password;
    private EditText mobile;
    private EditText address;
    private EditText location;
    private EditText terms_conditions;

    private Button registerBtn;

    private ApiInterface registerManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
//        registerManager = RegisterClient.getInstance();

        first_name = (EditText)findViewById(R.id.first_name);
        last_name = (EditText)findViewById(R.id.last_name);
        email = (EditText)findViewById(R.id.email);
        password = (EditText)findViewById(R.id.password);
        confirm_password = (EditText)findViewById(R.id.confirm_password);
        mobile = (EditText)findViewById(R.id.mobile);
        address = (EditText)findViewById(R.id.address);
        location = (EditText)findViewById(R.id.location);
        terms_conditions = (EditText)findViewById(R.id.terms_conditions);

        registerBtn = (Button) findViewById(R.id.registerBtn);

        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                postUserData();
            }
        });
    }

    private void postUserData(){
//        Registeruser user = new Registeruser(first_name.getText().toString().trim(),
//                last_name.getText().toString().trim(),
//                email.getText().toString().trim(),
//                password.getText().toString().trim(),
//                confirm_password.getText().toString().trim(),
//                mobile_number.getText().toString().trim(),
//                address.getText().toString().trim(),
//                location.getText().toString().trim(),
//                terms_condition.getText().toString().trim());
//        registerManager.createUser(user, new Callback<Registeruser>() {
//            @Override
//            public void onResponse(Call<Registeruser> call, Response<Registeruser> response) {
//
//                Registeruser registeruser = response.body();
////                if (response.isSuccessful() && registeruser != null) {
////                    Log.v("address", registeruser.getAddress().toString());
////                } else {
////                    Toast.makeText(RegisterActivity.this,
////                            String.format("Response is %s", String.valueOf(response.code()))
////                            , Toast.LENGTH_LONG).show();
////                }
//            }
//
//            @Override
//            public void onFailure(Call<Registeruser> call, Throwable t) {
//                Toast.makeText(RegisterActivity.this,
//                        "Error is " + t.getMessage()
//                        , Toast.LENGTH_LONG).show();
//            }
//        });


        ApiInterface registerApi = ApiClient.getClient().create(ApiInterface.class);

        Call<Registernew> call = registerApi.createRegisterUser(getString(R.string.securitykey),
                first_name.getText().toString(),
                last_name.getText().toString(),
                email.getText().toString(),
                password.getText().toString(),
                confirm_password.getText().toString(),
                mobile.getText().toString(),
                address.getText().toString(),
                location.getText().toString(),
                terms_conditions.getText().toString());

        call.enqueue(new Callback<Registernew>() {
            @Override
            public void onResponse(Call<Registernew> call, Response<Registernew> response) {
//                Log.d("url",call.request().url().toString());

                //Toast.makeText(RegisterActivity.this,call.request().url().toString(),Toast.LENGTH_LONG).show();

                Registernew registeruser = response.body();

                if (response.isSuccessful() && registeruser != null) {
                   //Log.v("address", registeruser.getAddress().toString());
                   //Toast.makeText(RegisterActivity.this,response.body().getMessage().toString(),Toast.LENGTH_SHORT).show();

                    Toast.makeText(RegisterActivity.this, "SignUp successful", Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                    startActivity(intent);


               } else {
                    Toast.makeText(RegisterActivity.this,response.body().getMessage().toString(),Toast.LENGTH_SHORT).show();

                   Toast.makeText(RegisterActivity.this,
                           String.format("Response is %s", String.valueOf(response.code()))
                           , Toast.LENGTH_LONG).show();
               }
            }


            @Override
            public void onFailure(Call<Registernew> call, Throwable t) {

                Toast.makeText(RegisterActivity.this,
                      "Error is " + t.getMessage()
                      , Toast.LENGTH_LONG).show();
            }
        });
    }
}
