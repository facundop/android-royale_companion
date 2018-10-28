package com.example.facundo.royalecompanion;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;

import com.google.android.gms.ads.MobileAds;

public class MainActivity extends AppCompatActivity {

    final Fragment settingsFragment = new SettingsFragment();
    final Fragment chestsFragment = new ChestsFragment();
    final Fragment profileFragment = new ProfileFragment();
    final FragmentManager fm = getSupportFragmentManager();
    Fragment activeFragment = profileFragment;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_profile:
                    fm.beginTransaction().hide(activeFragment).show(profileFragment).commit();
                    activeFragment = profileFragment;
                    return true;
                case R.id.navigation_chests:
                    fm.beginTransaction().hide(activeFragment).show(chestsFragment).commit();
                    activeFragment = chestsFragment;
                    return true;
                case R.id.navigation_settings:
                    fm.beginTransaction().hide(activeFragment).show(settingsFragment).commit();
                    activeFragment = settingsFragment;
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fm.beginTransaction().add(R.id.main_container, settingsFragment, "settingsFragment").hide(settingsFragment).commit();
        fm.beginTransaction().add(R.id.main_container, chestsFragment, "chestsFragment").hide(chestsFragment).commit();
        fm.beginTransaction().add(R.id.main_container, profileFragment, "profileFragment").commit();

        MobileAds.initialize(this, "ca-app-pub-1088902000251944~1740402091");


        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setItemIconTintList(null);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

}
