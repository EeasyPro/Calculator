package com.example.calculator;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RadioButton;

import java.util.ArrayList;

import maes.tech.intentanim.CustomIntent;

public class SettingsActivity extends AppCompatActivity {

    private RadioButton rb ;
    private RadioButton rb1;
    private RadioButton rb2;

    private ArrayList <RadioButton> radio_list = new ArrayList<>();
    SharedPreferences sPref;
    final String SAVED_TEXT = "saved_text";
    private String appStyle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        switch (loadText()) {
            case "AppTheme": setTheme(R.style.AppTheme);
                break;
            case "LightTheme": setTheme(R.style.LightTheme);
                break;
            case "ColorThemeOne": setTheme(R.style.ColorThemeOne);
                break;
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        rb = findViewById(R.id.radioButton);
        rb1 = findViewById(R.id.radioButton1);
        rb2= findViewById(R.id.radioButton2);

        switch (loadText()) {
            case "AppTheme": rb.setChecked(true);
                break;
            case "LightTheme": rb1.setChecked(true);
                break;
            case "ColorThemeOne": rb2.setChecked(true);
                break;
        }

        rb.setOnClickListener(radioButtonClickListener);
        rb1.setOnClickListener(radioButtonClickListener);
        rb2.setOnClickListener(radioButtonClickListener);
    }

    View.OnClickListener radioButtonClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            RadioButton rb = (RadioButton) v;
            switch (rb.getId()) {
                case R.id.radioButton:
                    saveText("AppTheme");
                    recreate();
                    break;
                case R.id.radioButton1:
                    saveText("LightTheme");
                    recreate();
                    break;
                case R.id.radioButton2:
                    saveText("ColorThemeOne");
                    recreate();
                    break;
                default:
                    break;
            }
        }
    };

    public void onBackClick(View view)
    {
        startActivity(new Intent(this,MainActivity.class));
        this.finish();
    }

    public void onOkClick(View view)
    {
        startActivity(new Intent(this,MainActivity.class));
        this.finish();
    }


    public void onCanselClick(View view)
    {
        startActivity(new Intent(this,MainActivity.class));
        this.finish();
    }

    @Override
    public void finish() {
        super.finish();
        CustomIntent.customType(this,"right-to-left");
    }

    void saveText(String appStyle) {
        sPref = getSharedPreferences("MyPref", MODE_PRIVATE);
        SharedPreferences.Editor ed = sPref.edit();
        ed.putString(SAVED_TEXT, appStyle);
        ed.apply();
    }

    public String loadText() {
        sPref = getSharedPreferences("MyPref", MODE_PRIVATE);
        appStyle = sPref.getString(SAVED_TEXT, "");
        return appStyle;
    }
}