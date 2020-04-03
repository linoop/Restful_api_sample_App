package com.example.sampleapidesign.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.sampleapidesign.Api.RetrofitClient;
import com.example.sampleapidesign.Models.LoginResponse;
import com.example.sampleapidesign.R;
import com.example.sampleapidesign.storage.SharedPrefManager;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Login extends AppCompatActivity {

    @BindView(R.id.editTextUserName)
    EditText editTextUserName;
    @BindView(R.id.editTextPassword)
    EditText editTextPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.textViewLogin, R.id.textViewSignUp})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.textViewLogin:
                userLogin();
                break;
            case R.id.textViewSignUp:
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                break;
        }
    }

    private void userLogin(){
        String name = editTextUserName.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();

        if(name.isEmpty()){
            editTextUserName.setError("Enter username");
            editTextUserName.requestFocus();
            return;
        }
        if(password.isEmpty()){
            editTextPassword.setError("Enter password");
            editTextPassword.requestFocus();
            return;
        }
        if(password.length()<6){
            editTextPassword.setError("Password should be at least 6 characher long");
            editTextPassword.requestFocus();
            return;
        }
        Call<LoginResponse> call = RetrofitClient.getInstance().getApi().userLogin(name, password);
        call.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                LoginResponse loginResponse = response.body();

                if(!loginResponse.isError()){

                    Toast.makeText(getApplicationContext(),"Login Success", Toast.LENGTH_LONG).show();
                    SharedPrefManager.getInstance(Login.this).saveProfile(loginResponse.getUserProfile());

                    Intent intent = new Intent(Login.this, ProfileActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                }
                else
                    Toast.makeText(getApplicationContext(),"Login not successful", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {

                Toast.makeText(getApplicationContext(),"Login Failure", Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

        if(SharedPrefManager.getInstance(this).isLoggedIn()){
            Intent intent = new Intent(this, ProfileActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        }
    }
}
