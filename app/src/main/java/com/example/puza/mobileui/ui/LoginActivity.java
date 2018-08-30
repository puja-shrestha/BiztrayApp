package com.example.puza.mobileui.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.puza.mobileui.Interface.ApiInterface;
import com.example.puza.mobileui.Manager.ApiClient;
import com.example.puza.mobileui.R;
import com.example.puza.mobileui.models.LoginNew;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    private Button btnLogin;
    private EditText password;
    private EditText email;
    private  TextView textViewRegister;

    SharedPreferences pref;
    SharedPreferences.Editor editor;

    private ApiInterface registerManager;

    ProgressBar mProgressbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        pref = getApplicationContext().getSharedPreferences("pref", 0);
        editor = pref.edit();

        password = (EditText)findViewById(R.id.password);
        email = (EditText)findViewById(R.id.email);

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        btnLogin = (Button) findViewById(R.id.buttonLogin);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mProgressbar = (ProgressBar) findViewById(R.id.loginRequestProgressBar);

                mProgressbar.setVisibility(View.VISIBLE);

                postUserData();

                Log.v("email+password", email.getText().toString()+ " "+password.getText().toString());
            }
        });

        textViewRegister = (TextView) findViewById(R.id.textViewRegister);
        textViewRegister.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        } );
    }


    private void postUserData(){

        ApiInterface registerApi = ApiClient.getClient().create(ApiInterface.class);

        Call<LoginNew> call = registerApi.createLoginUser(getString(R.string.securitykey),
                email.getText().toString(),
                password.getText().toString());

        call.enqueue(new Callback<LoginNew>()
                {
            @Override
            public void onResponse(Call<LoginNew> call, Response<LoginNew> response)
            {
                LoginNew loginUser = response.body();

                String status = loginUser.getStatus();

                Log.v("Status", status.toString());

                if (status.equals("true"))
                {
                    Toast.makeText(LoginActivity.this, "Successfully Logged in", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);

                    editor.putString("user_id", loginUser.getMessage().getId());
                    editor.commit();

                    startActivity(intent);

                    mProgressbar.setVisibility(View.GONE);

                } else {
                    mProgressbar.setVisibility(View.GONE);
                    Toast.makeText(LoginActivity.this,"Error Occured",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<LoginNew> call, Throwable t) {

                Log.v("Error", t.getMessage());
                Log.d("on fail", t.getMessage());

            }
        });

    }
}
