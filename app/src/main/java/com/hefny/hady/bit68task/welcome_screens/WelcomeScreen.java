package com.hefny.hady.bit68task.welcome_screens;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import com.cuneytayyildiz.onboarder.OnboarderActivity;
import com.cuneytayyildiz.onboarder.OnboarderPage;
import com.hefny.hady.bit68task.R;
import com.hefny.hady.bit68task.ui.MainActivity;

import java.util.ArrayList;
import java.util.List;

import static android.view.WindowManager.LayoutParams.FLAG_FULLSCREEN;

public class WelcomeScreen extends OnboarderActivity {

    private List<OnboarderPage> onboarderPages;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private static final String KEY = "FIRST_LOGIN_KEY";
    private static final String TAG = "WelcomeScreen";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(FLAG_FULLSCREEN, FLAG_FULLSCREEN);
        sharedPreferences = getPreferences(MODE_PRIVATE);
        editor = sharedPreferences.edit();
        if (sharedPreferences.getBoolean(KEY, false)) {
            launchMainActivity();
        }
        editor.putBoolean(KEY, true);
        editor.apply();

        onboarderPages = new ArrayList<>();

        OnboarderPage onboarderPage1 = new OnboarderPage.Builder()
                .title("page 1")
                .description("page 1 of on boarding pages")
                .imageResourceId(R.drawable.delivery)
                .backgroundColorId(R.color.white)
                .build();

        OnboarderPage onboarderPage2 = new OnboarderPage.Builder()
                .title("page 2")
                .description("page 2 of on boarding pages")
                .imageResourceId(R.drawable.food)
                .backgroundColorId(R.color.white)
                .build();

        onboarderPages.add(onboarderPage1);
        onboarderPages.add(onboarderPage2);

        setDividerVisibility(View.GONE);
        setSkipButtonHidden();
        setFinishButtonTitle("Finish");
        setFinishButtonBackgroundColor(R.color.red);
        setNextButtonBackgroundColor(R.color.white);
        setNextButtonTextColor(R.color.white);
        setActiveIndicatorColor(R.color.red);
        setInactiveIndicatorColor(android.R.color.darker_gray);
        initOnboardingPages(onboarderPages);
    }

    @Override
    public void onFinishButtonPressed() {
        // Define your actions when the user press 'Finish' button
        launchMainActivity();
    }

    private void launchMainActivity() {
        startActivity(new Intent(WelcomeScreen.this, MainActivity.class));
        finish();
    }
}