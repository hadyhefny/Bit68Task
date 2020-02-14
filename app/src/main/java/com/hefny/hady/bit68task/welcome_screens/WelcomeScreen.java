package com.hefny.hady.bit68task.welcome_screens;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.tabs.TabLayout;
import com.hefny.hady.bit68task.R;
import com.hefny.hady.bit68task.ui.MainActivity;

import static android.view.WindowManager.LayoutParams.FLAG_FULLSCREEN;

public class WelcomeScreen extends AppCompatActivity {

    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private static final String KEY = "FIRST_LOGIN_KEY";
    private static final String TAG = "WelcomeScreen";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_screen);
        getWindow().setFlags(FLAG_FULLSCREEN, FLAG_FULLSCREEN);
        sharedPreferences = getPreferences(MODE_PRIVATE);
        editor = sharedPreferences.edit();
        if (sharedPreferences.getBoolean(KEY, false)) {
            launchMainActivity();
        }
        editor.putBoolean(KEY, true);
        editor.apply();

        PagerAdapter adapter = new PagerAdapter(getSupportFragmentManager(), FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        TabLayout tabLayout = findViewById(R.id.tabs);
        ViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);

        MaterialButton skipButton = findViewById(R.id.btnSkip);
        MaterialButton finishButton = findViewById(R.id.btnFinish);


        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (position == adapter.getCount() - 1) {
                    skipButton.setVisibility(View.GONE);
                    finishButton.setVisibility(View.VISIBLE);
                } else {
                    skipButton.setVisibility(View.VISIBLE);
                    finishButton.setVisibility(View.GONE);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        finishButton.setOnClickListener(l -> launchMainActivity());
        skipButton.setOnClickListener(l -> launchMainActivity());

    }

    private void launchMainActivity() {
        startActivity(new Intent(WelcomeScreen.this, MainActivity.class));
        finish();
    }
}