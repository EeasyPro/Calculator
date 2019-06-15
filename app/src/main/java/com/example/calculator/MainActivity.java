package com.example.calculator;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.Stack;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    TextView editText;
    TextView answer;

    private BigDecimal summ = BigDecimal.valueOf(0);
    private BigDecimal current = BigDecimal.valueOf(0);

    private int foo = 0;
    private boolean iswriting = true;
    private boolean flag = true;
    private boolean dotChecker = false;


    /*private Stack<Double> numbers = new Stack<>();*/
    private ConstraintLayout mainLayout;


    @TargetApi(Build.VERSION_CODES.KITKAT)
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Objects.requireNonNull(getSupportActionBar()).hide();

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
                summ = BigDecimal.valueOf(0);
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
                if(!dotChecker) {
                    dotChecker = true;
                    answer.setText(answer.getText() + ".");
                }
                break;

        }
    }

    @SuppressLint("SetTextI18n")
    private void Print(BigDecimal summ, BigDecimal current)
    {
        if(summ.doubleValue() == summ.intValue())
            answer.setText(String.valueOf(summ.intValue()));
        else answer.setText(String.valueOf(summ));

        if(current.doubleValue() == current.intValue())
            editText.setText(editText.getText() + String.valueOf(current.intValue()));
        else editText.setText(editText.getText() + String.valueOf(current));

        iswriting = true;
    }

    private void somefoo(int foo)
    {
        /*if(answer.getText().toString().equals(""))
            answer.setText("0");*/

        dotChecker = false;

        try {
            current = BigDecimal.valueOf(Double.parseDouble(answer.getText().toString()));}
        catch (Exception e){
            answer.setText("0");
            current = BigDecimal.valueOf(Double.parseDouble(answer.getText().toString()));
            Toast toast3 = Toast.makeText(getApplicationContext(),"Сработало исключение",Toast.LENGTH_SHORT);
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
                summ = (summ.divide(current, 10, BigDecimal.ROUND_FLOOR));
                Print(summ, current);
                break;
            case 5://plus/minus
                current = current.multiply(BigDecimal.valueOf(-1));
                if(current.doubleValue() == current.intValue())
                    answer.setText(String.valueOf(current.intValue()));
                else answer.setText(String.valueOf(current));
                break;
            default:
                summ = summ.add(current);
                break;
        }
    }

}
