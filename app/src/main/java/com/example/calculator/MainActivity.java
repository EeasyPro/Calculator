package com.example.calculator;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;

import static com.example.calculator.R.id.button0;
import static com.example.calculator.R.id.button1;
import static com.example.calculator.R.id.button2;
import static com.example.calculator.R.id.button3;
import static com.example.calculator.R.id.button4;
import static com.example.calculator.R.id.button5;
import static com.example.calculator.R.id.button6;
import static com.example.calculator.R.id.button7;
import static com.example.calculator.R.id.button8;
import static com.example.calculator.R.id.button9;
import static com.example.calculator.R.id.buttonDEL;
import static com.example.calculator.R.id.buttonDIV;
import static com.example.calculator.R.id.buttonDOT;
import static com.example.calculator.R.id.buttonDellLastNumber;
import static com.example.calculator.R.id.buttonDellLastSymbol;
import static com.example.calculator.R.id.buttonEQUAL;
import static com.example.calculator.R.id.buttonMINUS;
import static com.example.calculator.R.id.buttonMUL;
import static com.example.calculator.R.id.buttonPLUS;
import static com.example.calculator.R.id.buttonPLUSMINUS;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TextView editText;
    TextView answer;

    private BigDecimal summ = BigDecimal.valueOf(0);
    private BigDecimal current = BigDecimal.valueOf(0);

    private int foo = 0;
    private int n = 10;
    private boolean iswriting = true;
    private boolean flag = true;
    private boolean dotChecker = false;

    private ArrayList<Button> buttons_numb = new ArrayList<>();
    private ArrayList<Button> buttons_foo = new ArrayList<>();

    private LinearLayout mainLayout;

    @TargetApi(Build.VERSION_CODES.KITKAT)
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = findViewById(R.id.editText);
        answer = findViewById(R.id.answer);

        final Button ZERO = findViewById(button0);
        final Button ONE = findViewById(button1);
        final Button TWO = findViewById(button2);
        final Button THREE = findViewById(button3);
        final Button FOUR = findViewById(button4);
        final Button FIVE = findViewById(button5);
        final Button SIX = findViewById(button6);
        final Button SEVEN = findViewById(button7);
        final Button EIGHT = findViewById(button8);
        final Button NINE = findViewById(button9);

        final Button PLUS = findViewById(buttonPLUS);
        final Button MINUS = findViewById(buttonMINUS);
        final Button EQUAL = findViewById(buttonEQUAL);
        final Button DEL = findViewById(buttonDEL);
        final Button DIV = findViewById(buttonDIV);
        final Button MUL = findViewById(buttonMUL);
        final Button PM = findViewById(buttonPLUSMINUS);
        final Button DellLastSymbol = findViewById(buttonDellLastSymbol);
        final Button CE = findViewById(buttonDellLastNumber);
        final Button DOT = findViewById(buttonDOT);
        final Button SQRT = findViewById(R.id.sqrt);
        final Button POW = findViewById(R.id.pow);
        final Button FACT = findViewById(R.id.fact);

        mainLayout = findViewById(R.id.mainLayout);

        final ImageButton color_btn = findViewById(R.id.color_btn);

        Collections.addAll(buttons_numb, ZERO, ONE, TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE);
        Collections.addAll(buttons_foo, EQUAL, PLUS, MINUS, DEL, CE, DIV, MUL, PM, DellLastSymbol, DOT, SQRT, POW, FACT);

        for (Button button : buttons_numb)
            button.setOnClickListener(this);

        for (Button button : buttons_foo)
            button.setOnClickListener(this);

        color_btn.setOnClickListener(this);

        color_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SettingsActivity.class);
                startActivityForResult(intent, 1);
            }
        });
    }

    @SuppressLint("ResourceAsColor")
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (data == null) {
            return;
        }
        int color = data.getIntExtra("color", 0);
        switch (color) {
            case 1:
                mainLayout.setBackgroundColor(getResources().getColor(R.color.colorPrimary1));
                for (Button button : buttons_numb)
                    button.setBackground(getDrawable(R.drawable.button_numbers1));
                for (Button button : buttons_foo)
                    button.setBackground(getDrawable(R.drawable.button_foo1));
                break;
            case 2:
                mainLayout.setBackgroundColor(getResources().getColor(R.color.colorPrimary2));
                for (Button button : buttons_numb)
                    button.setBackground(getDrawable(R.drawable.button_numbers2));
                for (Button button : buttons_foo)
                    button.setBackground(getDrawable(R.drawable.button_foo2));
                break;
            case 3:
                mainLayout.setBackgroundColor(getResources().getColor(R.color.colorPrimary3));
                for (Button button : buttons_numb)
                    button.setBackground(getDrawable(R.drawable.button_numbers3));
                for (Button button : buttons_foo)
                    button.setBackground(getDrawable(R.drawable.button_foo3));
                break;
        }
    }

    public void Check() {

        String str = answer.getText().toString().trim();
        if (str.length() != 0) {
            str = str.substring(0, 1);
        }
        if (flag || ((str.equals("0")) && (answer.length() == 1))) {
            answer.setText("");
            flag = false;
        }
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onClick(View v) {

        if (iswriting) {
            answer.setText("");
            iswriting = false;
        }

        for (Button button : buttons_numb) {
            if (v == button) {
                Check();
                answer.setText(answer.getText() + button.getText().toString());
            }
        }

        switch (v.getId()) {
            case buttonPLUS:
                somefoo(foo);
                foo = 1;
                editText.setText(editText.getText() + " + ");
                break;
            case buttonMINUS:
                somefoo(foo);
                foo = 2;
                editText.setText(editText.getText() + " - ");
                break;
            case buttonMUL:
                somefoo(foo);
                foo = 3;
                editText.setText(editText.getText() + " × ");
                break;
            case buttonDIV:
                somefoo(foo);
                foo = 4;
                editText.setText(editText.getText() + " ÷ ");
                break;

            case buttonEQUAL:
                somefoo(foo);
                foo = 0;
                flag = true;
                editText.setText("");
                iswriting = false;
                break;
            case buttonDEL:
                foo = 0;
                summ = BigDecimal.valueOf(0);
                iswriting = true;
                editText.setText("");
                answer.setText("0");
                break;
            case buttonPLUSMINUS:
                somefoo(5);
                break;
            case buttonDellLastSymbol:
                String str = answer.getText().toString();
                if (str.length() != 0) {
                    str = str.substring(0, str.length() - 1);
                    answer.setText(str);
                }
                if (answer.length() == 0) {
                    answer.setText("0");
                }
                break;

            case buttonDellLastNumber:
                answer.setText("0");
                break;

            case buttonDOT:
                if (!dotChecker) {
                    dotChecker = true;
                    answer.setText(answer.getText() + ".");
                }
                break;

            case R.id.sqrt:
                somefoo(6);
                break;

            case R.id.pow:
                somefoo(foo);
                foo = 7;
                editText.setText(editText.getText() + "^");
                break;

            case R.id.fact:
                somefoo(8);
                break;
        }
    }

    @SuppressLint("SetTextI18n")
    private void Print(BigDecimal summ, BigDecimal current) {
        if (summ.doubleValue() == summ.intValue())
            answer.setText(String.valueOf(summ.intValue()));
        else answer.setText(String.valueOf(summ));

        if (current.doubleValue() == current.intValue())
            editText.setText(editText.getText() + String.valueOf(current.intValue()));
        else editText.setText(editText.getText() + String.valueOf(current));

        iswriting = true;
    }

    public static BigDecimal sqrt(BigDecimal value) {
        BigDecimal x = new BigDecimal(Math.sqrt(value.doubleValue()));
        return x.add(new BigDecimal(value.subtract(x.multiply(x)).doubleValue() / (x.doubleValue() * 2.0)));
    }

    private void somefoo(int foo) {
        dotChecker = false;

        try {
            current = BigDecimal.valueOf(Double.parseDouble(answer.getText().toString()));
        } catch (Exception e) {
            answer.setText("0");
            current = BigDecimal.valueOf(Double.parseDouble(answer.getText().toString()));
            Toast toast3 = Toast.makeText(getApplicationContext(), "Сработало исключение", Toast.LENGTH_SHORT);
            toast3.show();
        }


        switch (foo) {
            case 0:
                summ = current;
                Print(summ, current);
                break;
            case 1://plus
                summ = summ.add(current);
                Print(summ, current);
                break;
            case 2://minus
                summ = summ.subtract(current);
                Print(summ, current);
                break;
            case 3://multiple
                summ = summ.multiply(current);
                Print(summ, current);
                break;
            case 4://div
                try {
                    summ = (summ.divide(current, n, BigDecimal.ROUND_FLOOR));
                } catch (Exception e) {
                    Toast toast4 = Toast.makeText(getApplicationContext(), "Где-то поперхнулась одна \nЕлена Алексеевна", Toast.LENGTH_SHORT);
                    toast4.show();
                }
                Print(summ, current);
                break;
            case 5://plus-minus
                current = current.multiply(BigDecimal.valueOf(-1));
                if (current.doubleValue() == current.intValue())
                    answer.setText(String.valueOf(current.intValue()));
                else answer.setText(String.valueOf(current));
                break;
            case 6://sqrt
                current = (sqrt(current)).setScale(n, BigDecimal.ROUND_FLOOR);
                if (current.doubleValue() == current.intValue())
                    answer.setText(String.valueOf(current.intValue()));
                else answer.setText(String.valueOf(current));
                break;
            case 7://pow
                summ = summ.pow(current.intValue());
                Print(summ, current);
                break;
            case 8://fact
                BigDecimal pep = BigDecimal.valueOf(1);
                int pip = current.intValue();
                for (int i = 1; i <= pip; i++)
                    pep = pep.multiply(BigDecimal.valueOf(i));

                current = pep;
                if (current.doubleValue() == current.intValue())
                    answer.setText(String.valueOf(current.intValue()));
                else answer.setText(String.valueOf(current));
                break;
            default:
                summ = summ.add(current);
                break;
        }
    }
}
