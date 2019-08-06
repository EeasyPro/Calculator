package com.example.calculator;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RadioButton;
import android.widget.SeekBar;
import android.widget.TextView;

import java.util.ArrayList;

import maes.tech.intentanim.CustomIntent;

public class SettingsActivity extends AppCompatActivity {

    private RadioButton rb ;
    private RadioButton rb1;
    private RadioButton rb2;

    private RadioButton calculatorMode1 ;
    private RadioButton calculatorMode2;
    private RadioButton calculatorMode3;

    private ArrayList <RadioButton> radio_list = new ArrayList<>();

    SharedPreferences sPref;
    final String SAVED_TEXT = "appStyle";
    final String SAVED_TEXT2 = "numbersSize";

    private String appStyle;
    private String numberOfDecimal;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        loadText();
        switch (appStyle) {
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

        calculatorMode1 = findViewById(R.id.calculatorMode1);
        calculatorMode2 = findViewById(R.id.calculatorMode2);
        calculatorMode3 = findViewById(R.id.calculatorMode3);

        final TextView numbersSizeValue = findViewById(R.id.numbersSizeValue);
        final SeekBar numbersSize = findViewById(R.id.numbersSize);

        if(numberOfDecimal.equals("")) numberOfDecimal = "0";
        numbersSizeValue.setText(numberOfDecimal);

        numbersSize.setProgress(Integer.valueOf(numberOfDecimal));
        numbersSize.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
               numbersSizeValue.setText(String.valueOf(numbersSize.getProgress()));
               numberOfDecimal = numbersSizeValue.getText().toString();
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });

        switch (appStyle) {
            case "AppTheme":
                rb.setChecked(true);
                calculatorMode1.setBackground(getDrawable(R.drawable.radio_btn_dark));
                calculatorMode2.setBackground(getDrawable(R.drawable.radio_btn_dark));
                calculatorMode3.setBackground(getDrawable(R.drawable.radio_btn_dark));
                break;
            case "LightTheme":
                rb1.setChecked(true);
                calculatorMode1.setBackground(getDrawable(R.drawable.radio_btn_light));
                calculatorMode2.setBackground(getDrawable(R.drawable.radio_btn_light));
                calculatorMode3.setBackground(getDrawable(R.drawable.radio_btn_light));
                break;
            case "ColorThemeOne":
                rb2.setChecked(true);
                calculatorMode1.setBackground(getDrawable(R.drawable.radio_btn_color));
                calculatorMode2.setBackground(getDrawable(R.drawable.radio_btn_color));
                calculatorMode3.setBackground(getDrawable(R.drawable.radio_btn_color));
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
        sPref = getSharedPreferences("MyPref", MODE_PRIVATE);
        SharedPreferences.Editor ed = sPref.edit();
        ed.putString(SAVED_TEXT2, numberOfDecimal);
        ed.apply();
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

    @Override
    public void onBackPressed() {
        startActivity(new Intent(this,MainActivity.class));
        this.finish();
    }

    void saveText(String appStyle) {
        sPref = getSharedPreferences("MyPref", MODE_PRIVATE);
        SharedPreferences.Editor ed = sPref.edit();
        ed.putString(SAVED_TEXT, appStyle);
        ed.apply();
    }

    public void loadText() {
        sPref = getSharedPreferences("MyPref", MODE_PRIVATE);
        numberOfDecimal = sPref.getString(SAVED_TEXT2,"");
        appStyle = sPref.getString(SAVED_TEXT, "");
    }
}