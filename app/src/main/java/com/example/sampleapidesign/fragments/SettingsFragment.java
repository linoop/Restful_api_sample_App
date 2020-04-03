package com.example.sampleapidesign.fragments;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.sampleapidesign.Activities.Login;
import com.example.sampleapidesign.Activities.MainActivity;
import com.example.sampleapidesign.Api.RetrofitClient;
import com.example.sampleapidesign.Models.DefaultResponse;
import com.example.sampleapidesign.Models.LoginResponse;
import com.example.sampleapidesign.Models.UserProfile;
import com.example.sampleapidesign.R;
import com.example.sampleapidesign.storage.SharedPrefManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SettingsFragment extends Fragment implements View.OnClickListener {

    private EditText editTextName, editTextPassword, editTextImageUrl;

    private EditText editTextCurrentPassword, editTextNewPassword;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.settings_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        editTextName = view.findViewById(R.id.editTextName);
        editTextPassword = view.findViewById(R.id.editTextPassword);
        editTextImageUrl = view.findViewById(R.id.editTextImageUrl);
        editTextNewPassword = view.findViewById(R.id.editTextNewPassword);
        editTextCurrentPassword = view.findViewById(R.id.editTextCurrentPassword);


        view.findViewById(R.id.buttonSave).setOnClickListener(this);
        view.findViewById(R.id.buttonChangePassword).setOnClickListener(this);
        view.findViewById(R.id.buttonDeleteAccount).setOnClickListener(this);
        view.findViewById(R.id.buttonDeleteAccount).setOnClickListener(this);
        view.findViewById(R.id.buttonLogout).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.buttonSave:
                updateProfile();
                break;
            case R.id.buttonChangePassword:
                changePassword();
                break;
            case R.id.buttonDeleteAccount:
                deleteAccount();
                break;
            case R.id.buttonLogout:
                logOutClicked();
                break;
        }

    }

    private void logOutClicked(){

        SharedPrefManager.getInstance(getActivity()).clear();

        Intent intent = new Intent(getActivity(), Login.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);

    }

    private void deleteAccount(){

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Are you sure?");
        builder.setMessage("This acton irreversible");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                UserProfile userProfile = SharedPrefManager.getInstance(getActivity()).getUser();
                Call<DefaultResponse> call = RetrofitClient.getInstance()
                .getApi()
                .deleteUser(userProfile.getId());
                call.enqueue(new Callback<DefaultResponse>() {
                    @Override
                    public void onResponse(Call<DefaultResponse> call, Response<DefaultResponse> response) {
                        if(!response.body().isErr()){
                            SharedPrefManager.getInstance(getActivity()).clear();
                            Intent intent = new Intent(getActivity(), MainActivity.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            startActivity(intent);
                        }
                    }

                    @Override
                    public void onFailure(Call<DefaultResponse> call, Throwable t) {

                    }
                });

            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    private void changePassword(){
        String currentPassword = editTextCurrentPassword.getText().toString().trim();
        String newPassword = editTextNewPassword.getText().toString().trim();
        if(currentPassword.isEmpty()){
            editTextCurrentPassword.setError("Not empty");
            editTextCurrentPassword.requestFocus();
            return;
        }
        if(newPassword.isEmpty()){
            editTextNewPassword.setError("Not empty");
            editTextNewPassword.requestFocus();
            return;
        }

        UserProfile userProfile = SharedPrefManager.getInstance(getActivity()).getUser();

        Call<DefaultResponse> call = RetrofitClient.getInstance().getApi()
                .updatePassword(currentPassword, newPassword, userProfile.getName());

        call.enqueue(new Callback<DefaultResponse>() {
            @Override
            public void onResponse(Call<DefaultResponse> call, Response<DefaultResponse> response) {
                Toast.makeText(getActivity(), response.body().getMsg(), Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<DefaultResponse> call, Throwable t) {

            }
        });


    }

    private void updateProfile(){
        String name = editTextName.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();
        String imageurl = editTextImageUrl.getText().toString().trim();

        if(name.isEmpty()){
            editTextName.setError("Name required");
            editTextName.requestFocus();
            return;
        }
        if(password.isEmpty()){
            editTextPassword.setError("Password required");
            editTextPassword.requestFocus();
            return;
        }
        if(imageurl.isEmpty()){
            editTextImageUrl.setError("image required");
            editTextImageUrl.requestFocus();
            return;
        }

        UserProfile userProfile = SharedPrefManager.getInstance(getActivity()).getUser();

        Call<LoginResponse> call = RetrofitClient.getInstance().getApi().updateProfile(userProfile.getId(), name, password, imageurl);

        call.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                Toast.makeText(getActivity(), response.body().getMessage(), Toast.LENGTH_LONG).show();

                if(!response.body().isError()){
                    SharedPrefManager.getInstance(getActivity()).saveProfile(response.body().getUserProfile());
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {

            }
        });

    }
}
