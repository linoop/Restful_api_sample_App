package com.example.sampleapidesign.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.sampleapidesign.R;
import com.example.sampleapidesign.fragments.HomeFragment;
import com.example.sampleapidesign.fragments.SettingsFragment;
import com.example.sampleapidesign.fragments.UserFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class ProfileActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNav);

        bottomNavigationView.setOnNavigationItemSelectedListener(this);

        displayFragment(new HomeFragment());

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        Fragment fragment = null;
        switch (item.getItemId()){
            case R.id.menu_home:
                fragment = new HomeFragment();
                break;
            case R.id.menu_user:
                fragment = new UserFragment();
                break;
            case R.id.menu_settings:
                fragment = new SettingsFragment();
                break;
        }
        if(fragment!=null){
            displayFragment(fragment);
        }
        return false;
    }



    private void displayFragment(Fragment fragment){
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.relativeLayout, fragment)
                .commit();
    }
}
