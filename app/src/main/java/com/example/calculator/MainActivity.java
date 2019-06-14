package com.example.calculator;

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
    private boolean writing_number = true;


    private Stack<Double> numbers = new Stack<>();
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

        /*final Animation animScale = AnimationUtils.loadAnimation(this, R.anim.scale);
        Button btnScale = (Button)findViewById(R.id.buttonDellLastNumber);
        btnScale.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View view) {
                view.startAnimation(animScale);
                Toast toast1 = Toast.makeText(getApplicationContext(),"Включена темная тема", Toast.LENGTH_SHORT);
                        mainLayout.setBackgroundColor(getResources().getColor(R.color.BlackTheme));
                toast1.show();
            }
        });*/


    }

    @Override
    public void onClick(View v) {

        if(foo == 0){
            answer.setText("");
            foo = -1;
        }

        if (!writing_number)
            answer.setText("");
        writing_number = true;

        switch (v.getId()) {
            case R.id.button1:
                if(foo == 6) foo =-1;
                numbers.push( 1.0);
               /* numbers.push( Double.parseDouble("233.5"));*/
                answer.setText(answer.getText() + "1");
                break;
            case R.id.button2:
                if(foo == 6) foo =-1;
                numbers.push( 2.0);
                answer.setText(answer.getText() + "2");
                break;
            case R.id.button3:
                if(foo == 6) foo =-1;
                numbers.push( 3.0);
                answer.setText(answer.getText() + "3");
                break;
            case R.id.button4:
                if(foo == 6) foo =-1;
                numbers.push( 4.0);
                answer.setText(answer.getText() + "4");
                break;
            case R.id.button5:
                if(foo == 6) foo =-1;
                numbers.push( 5.0);
                answer.setText(answer.getText() + "5");
                break;
            case R.id.button6:
                if(foo == 6) foo =-1;
                numbers.push( 6.0);
                answer.setText(answer.getText() + "6");
                break;
            case R.id.button7:
                if(foo == 6) foo =-1;
                numbers.push( 7.0);
                answer.setText(answer.getText() + "7");
                break;
            case R.id.button8:
                if(foo == 6) foo =-1;
                numbers.push( 8.0);
                answer.setText(answer.getText() + "8");
                break;
            case R.id.button9:
                if(foo == 6) foo =-1;
                numbers.push( 9.0);
                answer.setText(answer.getText() + "9");
                break;
            case R.id.button0:
                numbers.push( 0.0);
                answer.setText(answer.getText() + "0");
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
            case R.id.buttonDEL:
                foo = 0;
                editText.setText("");
                answer.setText("0");
                numbers.clear();
                summ = 0;
                mainLayout.setBackgroundColor(getResources().getColor(R.color.WhiteTheme));
                Toast toast2 = Toast.makeText(getApplicationContext(),"Включена светлая тема", Toast.LENGTH_SHORT);
                toast2.show();
                break;
            case R.id.buttonEQUAL:
                somefoo(foo);
                foo = 6;
                editText.setText("");
                break;
            case R.id.buttonPLUSMINUS:
                somefoo(5);
                break;
            case R.id.buttonDellLastSymbol:
                if(numbers.size()!=0) {
                    numbers.pop();
                    String str = answer.getText().toString();
                    if (str.length() != 0) {
                        str = str.substring(0, str.length() - 1);
                        answer.setText(str);
                    }
                }
                if(numbers.size() == 0) {
                    foo = 0;
                    current = 0;
                    answer.setText("0");
                }
                break;
            case R.id.buttonDellLastNumber:
                if(numbers.size()!=0) {
                    numbers.clear();
                    answer.setText("");
                }
                if(numbers.size() == 0) {
                    foo = 0;
                    current = 0;
                    answer.setText("0");
                }
                break;
        }
    }

    private void ConvertToNumber(Stack<Double>numbers) {
        int a = 0;
        current = 0;
        while(!numbers.empty()) {
            current += numbers.pop() * Math.pow(10, a);
            a++;
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
    }

    private void somefoo(int foo)
    {
        writing_number = false;

        if(numbers.size() != 0)
        ConvertToNumber(numbers);

        switch (foo) {
            case 0://delete
                //Происходит очистка суммы
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
            case 3://umnojenie
                summ *= current;
                Print(summ, current);
                break;
            case 4://delenie
                summ /= current;
                Print(summ, current);
                break;
            case 5://plus/minus
                current *= -1;
                if(current == (int)current)
                    answer.setText("" + (int)current);
                else answer.setText("" + current);
                break;
            case 6://equal
                current = summ;
                Print(summ, current);
                break;
            default:
                summ+=current;
                Print(summ, current);
                break;
        }
    }

}
