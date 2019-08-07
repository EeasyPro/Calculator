package com.example.calculator;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import maes.tech.intentanim.CustomIntent;

public class MainActivity extends AppCompatActivity implements FirstNumBlockFragment.OnFragmentInteractionListener, SecondNumBlockFragment.OnFragmentInteractionListener {

    TextView editText;
    TextView answer;

    SharedPreferences sPref;
    final String SAVED_TEXT = "appStyle";
    final String SAVED_TEXT3 = "numMod";

    private String appStyle;
    private String numMod;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        sPref = getSharedPreferences("MyPref", MODE_PRIVATE);
        appStyle = sPref.getString(SAVED_TEXT, "");
        numMod = sPref.getString(SAVED_TEXT3, "");

        Toast.makeText(this,numMod,Toast.LENGTH_SHORT).show();

        switch (appStyle) {
            case "AppTheme": setTheme(R.style.AppTheme);
                break;
            case "LightTheme": setTheme(R.style.LightTheme);
                break;
            case "ColorThemeOne": setTheme(R.style.ColorThemeOne);
                break;
            default:
                Toast.makeText(this, "Default", Toast.LENGTH_SHORT).show();
                break;
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = findViewById(R.id.editText);
        answer = findViewById(R.id.answer);

        editText.setMaxLines(1);
        answer.setMaxLines(1);

        TextView title = findViewById(R.id.title);

        ImageButton settings_btn = findViewById(R.id.settings_btn);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        Fragment fragment = null;

        switch (numMod) {
            case "Normal":
                fragment = new FirstNumBlockFragment();
               ((FirstNumBlockFragment) fragment).setOnFragmentInteractionListener(this);
                title.setText("Normal");
                break;
            case "Engineer":
                fragment = new SecondNumBlockFragment();
                ((SecondNumBlockFragment) fragment).setOnFragmentInteractionListener(this);
                title.setText("Engineer");
                break;
            case "Programmer":
                fragment = new SecondNumBlockFragment();
                ((SecondNumBlockFragment) fragment).setOnFragmentInteractionListener(this);
                title.setText("Programmer");
                break;
        }

            if(fragment == null) fragment = new FirstNumBlockFragment();


        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.numContainer, fragment).commit();

        settings_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SettingsActivity.class);
                startActivity(intent);
                CustomIntent.customType(MainActivity.this,"left-to-right");
                closeActivity();
            }
        });
    }

    @Override
    public void closeActivity() {
        this.finish();
    }

    @Override
    public void onAnswerChanged(String answer) {
        this.answer.setText(answer);
    }

    @Override
    public void onEditTextChanged(String editText) {
        this.editText.setText(editText);
    }
}
