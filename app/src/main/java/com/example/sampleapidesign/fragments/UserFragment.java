package com.example.sampleapidesign.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sampleapidesign.Adapters.UsersProfileAdapter;
import com.example.sampleapidesign.Api.RetrofitClient;
import com.example.sampleapidesign.Models.UserProfile;
import com.example.sampleapidesign.Models.UsersResponse;
import com.example.sampleapidesign.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserFragment extends Fragment {

    private RecyclerView recyclerView;
    private UsersProfileAdapter usersProfileAdapter;
    private List<UserProfile> userProfileList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.user_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.recyclerViewUsers);

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        Call<UsersResponse> call = RetrofitClient.getInstance().getApi().getAllProfiles();
        call.enqueue(new Callback<UsersResponse>() {
            @Override
            public void onResponse(Call<UsersResponse> call, Response<UsersResponse> response) {
                userProfileList = response.body().getUserProfiles();
                usersProfileAdapter = new UsersProfileAdapter(getActivity(), userProfileList);

                recyclerView.setAdapter(usersProfileAdapter);
            }

            @Override
            public void onFailure(Call<UsersResponse> call, Throwable t) {

            }
        });

    }
}
