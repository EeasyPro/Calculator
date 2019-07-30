package com.example.calculator;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;

import java.util.ArrayList;
import java.util.Collections;

public class SettingsActivity extends AppCompatActivity {

    private LinearLayout settingsLayout;

    private RadioButton rb ;
    private RadioButton rb1;
    private RadioButton rb2;
    private RadioButton rb3;

    private ArrayList <RadioButton> radio_list = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        settingsLayout = findViewById(R.id.settingsLayout);
        settingsLayout.setBackgroundResource(R.drawable.layoutstyle);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        setTitle(null);

        Button OkBtn = findViewById(R.id.ok);
        Button CanselBtn = findViewById(R.id.cansel);

        rb = findViewById(R.id.radioButton);
        rb1 = findViewById(R.id.radioButton1);
        rb2 = findViewById(R.id.radioButton2);
        rb3 = findViewById(R.id.radioButton3);

        Collections.addAll(radio_list, rb, rb1, rb3, rb2);
    }


    public void onOkClick(View view)
    {
        int n = 0;
        Intent intent1 = new Intent();
        for (RadioButton rbtn: radio_list) {
            if (rbtn.isChecked()) intent1.putExtra("color", n);
            n++;
        }
        setResult(RESULT_OK,intent1);
        this.finish();
    }

    public void onCanselClick(View view)
    {
        this.finish();
    }
}