package com.example.calculator;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RadioButton;
import android.widget.Toast;

public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        RadioButton rb1 = findViewById(R.id.radioButton);
        RadioButton rb2 = findViewById(R.id.radioButton2);
        RadioButton rb3 = findViewById(R.id.radioButton3);

        rb1.setOnClickListener(radioButtonClickListener);
        rb2.setOnClickListener(radioButtonClickListener);
        rb3.setOnClickListener(radioButtonClickListener);
    }

    View.OnClickListener radioButtonClickListener = new View.OnClickListener() {
        @RequiresApi(api = Build.VERSION_CODES.M)
        @Override
        public void onClick(View v) {
            Intent intent1 = new Intent();
            RadioButton rb = (RadioButton) v;

            switch (rb.getId()) {
                case R.id.radioButton:
                    intent1.putExtra("color", 1);
                    Toast.makeText(getApplicationContext(), "color1", Toast.LENGTH_SHORT).show();
                    break;

                case R.id.radioButton2:
                    intent1.putExtra("color", 2);
                    Toast.makeText(getApplicationContext(), "color2", Toast.LENGTH_SHORT).show();
                    break;

                case R.id.radioButton3:
                    intent1.putExtra("color", 3);
                    Toast.makeText(getApplicationContext(), "color3", Toast.LENGTH_SHORT).show();
                    break;
            }
            setResult(RESULT_OK,intent1);
        }
    };
}
