package com.example.sampleapidesign.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.sampleapidesign.R;
import com.example.sampleapidesign.storage.SharedPrefManager;

public class HomeFragment extends Fragment {

    private TextView textViewName, textViewPassword, textViewImageurl;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        return inflater.inflate(R.layout.home_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        textViewName = view.findViewById(R.id.textViewName);
        textViewPassword = view.findViewById(R.id.textViewPassword);
        textViewImageurl = view.findViewById(R.id.textViewImageUrl);

        textViewName.setText(SharedPrefManager.getInstance(getActivity()).getUser().getName());
        textViewPassword.setText(SharedPrefManager.getInstance(getActivity()).getUser().getPassword());
        textViewImageurl.setText(SharedPrefManager.getInstance(getActivity()).getUser().getImageurl());

    }
}
