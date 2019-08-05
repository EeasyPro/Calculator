package com.example.calculator;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.Toast;

import java.util.ArrayList;

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

        Button OkBtn = findViewById(R.id.ok);
        Button CanselBtn = findViewById(R.id.cansel);

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



        /*rb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    appStyle = "AppTheme";
                    saveText("AppTheme");
                    CloseActivity();
            }
        });

        rb1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    appStyle = "LightColor";
                    saveText("LightColor");
                    CloseActivity();
            }
        });

        rb2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                appStyle = "ColorThemeOne";
                saveText("ColorThemeOne");
                CloseActivity();
            }
        });*/
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

    public void onOkClick(View view)
    {
        this.finish();
        startActivity(new Intent(this,MainActivity.class));
    }

    public void onCanselClick(View view)
    {
        this.finish();
        startActivity(new Intent(this,MainActivity.class));
    }

    void saveText(String appStyle) {
        sPref = getSharedPreferences("MyPref", MODE_PRIVATE);
        SharedPreferences.Editor ed = sPref.edit();
        ed.putString(SAVED_TEXT, appStyle);
        ed.apply();
        Toast.makeText(this, "Text saved", Toast.LENGTH_SHORT).show();
    }

    public String loadText() {
        sPref = getSharedPreferences("MyPref", MODE_PRIVATE);
        appStyle = sPref.getString(SAVED_TEXT, "");
        Toast.makeText(this, "Text loaded:" + appStyle, Toast.LENGTH_SHORT).show();
        return appStyle;
    }
}