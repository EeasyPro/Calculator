package com.example.calculator;

import android.annotation.SuppressLint;
import android.icu.math.BigDecimal;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Stack;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    TextView editText;
    TextView answer;

    private double summ = 0;
    private double current = 0;
    private int foo = 0;
    private boolean iswriting = true;
    private boolean flag = true;


    /*private Stack<Double> numbers = new Stack<>();*/
    private ConstraintLayout mainLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();

        editText = findViewById(R.id.editText);
        answer = findViewById(R.id.answer);

        Button ZERO = findViewById(R.id.button0);
        Button ONE = findViewById(R.id.button1);
        Button TWO = findViewById(R.id.button2);
        Button THREE = findViewById(R.id.button3);
        Button FOUR = findViewById(R.id.button4);
        Button FIVE = findViewById(R.id.button5);
        Button SIX = findViewById(R.id.button6);
        Button SEVEN = findViewById(R.id.button7);
        Button EIGHT = findViewById(R.id.button8);
        Button NINE = findViewById(R.id.button9);
        Button PLUS = findViewById(R.id.buttonPLUS);
        Button MINUS = findViewById(R.id.buttonMINUS);
        Button EQUAL = findViewById(R.id.buttonEQUAL);
        Button DEL = findViewById(R.id.buttonDEL);
        Button DIV = findViewById(R.id.buttonDIV);
        Button MUL = findViewById(R.id.buttonMUL);
        Button PM = findViewById(R.id.buttonPLUSMINUS);
        Button DellLastSymbol = findViewById(R.id.buttonDellLastSymbol);
        Button BAN = findViewById(R.id.buttonDellLastNumber);
        Button DOT = findViewById(R.id.buttonDOT);

        mainLayout = findViewById(R.id.mainLayout);

        ZERO.setOnClickListener(this);
        ONE.setOnClickListener(this);
        TWO.setOnClickListener(this);
        THREE.setOnClickListener(this);
        FOUR.setOnClickListener(this);
        FIVE.setOnClickListener(this);
        SIX.setOnClickListener(this);
        SEVEN.setOnClickListener(this);
        EIGHT.setOnClickListener(this);
        NINE.setOnClickListener(this);
        EQUAL.setOnClickListener(this);
        PLUS.setOnClickListener(this);
        MINUS.setOnClickListener(this);
        DEL.setOnClickListener(this);
        BAN.setOnClickListener(this);
        DIV.setOnClickListener(this);
        MUL.setOnClickListener(this);
        PM.setOnClickListener(this);
        DellLastSymbol.setOnClickListener(this);
        DOT.setOnClickListener(this);
    }

    public void Check()
    {
        String str = answer.getText().toString().trim();
        if (str.length() != 0) {
            str = str.substring(0, 1);
        }
        if(flag || ((str.equals("0"))&&(answer.length()==1)))  {
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

        switch (v.getId()) {
            case R.id.button0:
                Check();
                answer.setText(answer.getText() + "0");
                break;
            case R.id.button1:
                Check();
                answer.setText(answer.getText() + "1");
                break;
            case R.id.button2:
                Check();
                answer.setText(answer.getText() + "2");
                break;
            case R.id.button3:
                Check();
                answer.setText(answer.getText() + "3");
                break;
            case R.id.button4:
                Check();
                answer.setText(answer.getText() + "4");
                break;
            case R.id.button5:
                Check();
                answer.setText(answer.getText() + "5");
                break;
            case R.id.button6:
                Check();
                answer.setText(answer.getText() + "6");
                break;
            case R.id.button7:
                Check();
                answer.setText(answer.getText() + "7");
                break;
            case R.id.button8:
                Check();
                answer.setText(answer.getText() + "8");
                break;
            case R.id.button9:
                Check();
                answer.setText(answer.getText() + "9");
                break;


            case R.id.buttonPLUS:
                somefoo(foo);
                foo = 1;
                editText.setText(editText.getText() + " + ");
                break;
            case R.id.buttonMINUS:
                somefoo(foo);
                foo = 2;
                editText.setText(editText.getText() + " - ");
                break;
            case R.id.buttonMUL:
                somefoo(foo);
                foo = 3;
                editText.setText(editText.getText() + " × ");
                break;
            case R.id.buttonDIV:
                somefoo(foo);
                foo = 4;
                editText.setText(editText.getText() + " ÷ ");
                break;


            case R.id.buttonEQUAL:
                somefoo(foo);
                foo = 0;
                flag = true;
                editText.setText("");
                iswriting = false;
                break;
            case R.id.buttonDEL:
                foo = 0;
                summ = 0;
                iswriting = true;
                editText.setText("");
                answer.setText("0");
                break;
            case R.id.buttonPLUSMINUS:
                somefoo(5);
                break;
            case R.id.buttonDellLastSymbol:
                    String str = answer.getText().toString();
                    if (str.length() != 0) {
                        str = str.substring(0, str.length() - 1);
                        answer.setText(str);
                    }
                if(answer.length() == 0) {
                    answer.setText("0");
                }
                break;

            case R.id.buttonDellLastNumber:
                    answer.setText("0");
                break;

            case R.id.buttonDOT:
                answer.setText(answer.getText() + ".");
                break;

        }
    }

    private void Print(double summ, double current)
    {
        if(summ == (int)summ)
            answer.setText(String.valueOf((int)summ));
        else answer.setText("" + summ);

        if(current == (int)current)
            editText.setText(editText.getText() + "" +(int)current);
        else editText.setText(editText.getText() + "" + current);

        iswriting = true;
    }

    private void somefoo(int foo)
    {
        /*if(answer.getText().toString().equals(""))
            answer.setText("0");*/

        try {current = Double.parseDouble(answer.getText().toString());}
        catch (Exception e){
            answer.setText("0");
            Toast toast3 = Toast.makeText(getApplicationContext(),"Сработало исключение",Toast.LENGTH_SHORT);
            toast3.show();
        }

        /*current = Double.parseDouble(answer.getText().toString());*/

        switch (foo) {
            case 0:
                summ = current;
                Print(summ, current);
                break;
            case 1://plus
                summ += current;
                Print(summ, current);
                break;
            case 2://minus
                summ -= current;
                Print(summ, current);
                break;
            case 3://multiple
                summ *= current;
                Print(summ, current);
                break;
            case 4://div
                summ /= current;
                Print(summ, current);
                break;
            case 5://plus/minus
                current *= -1;
                if(current == (int)current)
                    answer.setText("" + (int)current);
                else answer.setText("" + current);
                break;
            default:
                summ+=current;
                break;
        }
    }

}
