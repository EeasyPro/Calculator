package com.example.calculator;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;


public class FirstNumBlockFragment extends Fragment implements View.OnClickListener {


    private OnFragmentInteractionListener listener;

    private ArrayList<Button> buttons_numb = new ArrayList<>();
    private ArrayList<Button> buttons_foo = new ArrayList<>();


    private String answer = "0";
    private String editText = "";


    private BigDecimal summ = BigDecimal.valueOf(0);
    private BigDecimal current = BigDecimal.valueOf(0);

    private int foo = 0;
    private int n = 10;

    private boolean flag = false;
    private boolean dotChecker = false;

    private boolean iswriting = false;

    SharedPreferences sPref;
    final String SAVED_TEXT2 = "numbersSize";


    private OnFragmentInteractionListener mListener;

    public FirstNumBlockFragment() {
        // Required empty public constructor
    }


    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_first_num_block, container, false);

        final Button ZERO = view.findViewById(R.id.button0);
        final Button ONE = view.findViewById(R.id.button1);
        final Button TWO = view.findViewById(R.id.button2);
        final Button THREE = view.findViewById(R.id.button3);
        final Button FOUR = view.findViewById(R.id.button4);
        final Button FIVE = view.findViewById(R.id.button5);
        final Button SIX = view.findViewById(R.id.button6);
        final Button SEVEN = view.findViewById(R.id.button7);
        final Button EIGHT = view.findViewById(R.id.button8);
        final Button NINE = view.findViewById(R.id.button9);

        final Button PLUS = view.findViewById(R.id.buttonPLUS);
        final Button MINUS = view.findViewById(R.id.buttonMINUS);
        final Button EQUAL = view.findViewById(R.id.buttonEQUAL);
        final Button DEL = view.findViewById(R.id.buttonDEL);
        final Button DIV = view.findViewById(R.id.buttonDIV);
        final Button MUL = view.findViewById(R.id.buttonMUL);
        final Button PM = view.findViewById(R.id.buttonPLUSMINUS);
        final Button DellLastSymbol = view.findViewById(R.id.buttonDellLastSymbol);
        final Button CE = view.findViewById(R.id.buttonDellLastNumber);
        final Button DOT = view.findViewById(R.id.buttonDOT);
        final Button SQRT = view.findViewById(R.id.sqrt);
        final Button FACT = view.findViewById(R.id.fact);
        final Button POW = view.findViewById(R.id.pow);
        final Button PERCENT = view.findViewById(R.id.percent);

        loadText();
        try {
            n = Integer.valueOf(Objects.requireNonNull(sPref.getString(SAVED_TEXT2, "")));}
        catch (Exception e){n = 10;}



        Collections.addAll(buttons_numb, ZERO, ONE, TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE);
        Collections.addAll(buttons_foo, EQUAL, PLUS, MINUS, DEL, CE, DIV, MUL, PM, DellLastSymbol, DOT, SQRT, FACT, POW, PERCENT);

        for (Button button : buttons_numb)
            button.setOnClickListener(this);

        for (Button button : buttons_foo)
            button.setOnClickListener(this);

        return view;
    }


    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public void Check() {
        String str = answer;
        listener.onAnswerChanged(answer);
        if (str.length() != 0) {
            str = str.substring(0, 1);
        }
        if (flag || ((str.equals("0")) && (answer.length() == 1))) {
            answer = "";
            listener.onAnswerChanged(answer);
            flag = false;
        }
    }

    @Override
    public void onClick(View v) {
        if (iswriting) {
            answer = "";
            listener.onAnswerChanged(answer);
            iswriting = false;
        }

        for (Button button : buttons_numb) {
            if (v == button) {
                Check();
                answer = answer + button.getText().toString();
                listener.onAnswerChanged(answer);
            }
        }

        switch (v.getId()) {
            case R.id.buttonPLUS:
                somefoo(foo);
                foo = 1;
                editText = editText + " + ";
                listener.onEditTextChanged(editText);
                break;
            case R.id.buttonMINUS:
                somefoo(foo);
                foo = 2;
                editText = editText + " - ";
                listener.onEditTextChanged(editText);
                break;
            case R.id.buttonMUL:
                somefoo(foo);
                foo = 3;
                editText = editText + " × ";
                listener.onEditTextChanged(editText);
                break;
            case R.id.buttonDIV:
                somefoo(foo);
                foo = 4;
                editText = editText + " ÷ ";
                listener.onEditTextChanged(editText);
                break;

            case R.id.buttonEQUAL:
                somefoo(foo);
                foo = 0;
                flag = true;
                editText = "";
                listener.onEditTextChanged(editText);
                iswriting = false;
                break;
            case R.id.buttonDEL:
                foo = 0;
                summ = BigDecimal.valueOf(0);
                iswriting = true;
                editText = "";
                listener.onEditTextChanged(editText);
                answer = "0";
                listener.onAnswerChanged(answer);
                break;
            case R.id.buttonPLUSMINUS:
                somefoo(5);
                break;
            case R.id.buttonDellLastSymbol:
                String str = answer;
                if (str.length() != 0) {
                    str = str.substring(0, str.length() - 1);
                    answer = str;
                    listener.onAnswerChanged(answer);
                }
                if (answer.length() == 0) {
                    answer = "0";
                    listener.onAnswerChanged(answer);
                }
                break;

            case R.id.buttonDellLastNumber:
                answer = "0";
                listener.onAnswerChanged(answer);
                break;

            case R.id.buttonDOT:
                if (!dotChecker) {
                    dotChecker = true;
                    answer = answer + ".";
                    listener.onAnswerChanged(answer);
                }
                break;

            case R.id.sqrt:
                somefoo(6);
                break;

            case R.id.pow:
                somefoo(foo);
                foo = 7;
                editText = editText + "^";
                listener.onEditTextChanged(editText);
                break;

            case R.id.fact:
                somefoo(8);
                break;

            case R.id.percent:
                somefoo(9);
                break;
        }
    }

    public static BigDecimal sqrt(BigDecimal value) {
        BigDecimal x = new BigDecimal(Math.sqrt(value.doubleValue()));
        return x.add(new BigDecimal(value.subtract(x.multiply(x)).doubleValue() / (x.doubleValue() * 2.0)));
    }

    private void somefoo(int foo) {
        dotChecker = false;

        try {
            current = BigDecimal.valueOf(Double.parseDouble(answer));
        } catch (Exception e) {
            answer = "0";
            listener.onAnswerChanged(answer);
            current = BigDecimal.valueOf(Double.parseDouble(answer));
            Toast toast3 = Toast.makeText(getContext(), "Сработало исключение", Toast.LENGTH_SHORT);
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
                    Toast toast4 = Toast.makeText(getContext(), "∞", Toast.LENGTH_SHORT);
                    toast4.show();
                }
                Print(summ, current);
                break;
            case 5://plus-minus
                current = current.multiply(BigDecimal.valueOf(-1));
                if (current.doubleValue() == current.intValue()) {
                    answer = String.valueOf(current.intValue());
                    listener.onAnswerChanged(answer);
                } else {
                    answer = String.valueOf(current);
                    listener.onAnswerChanged(answer);
                }
                break;
            case 6://sqrt
                try {
                    current = (sqrt(current)).setScale(n, BigDecimal.ROUND_FLOOR);
                } catch (Exception e) {
                    Toast.makeText(getContext(), "Ошибка", Toast.LENGTH_SHORT).show();
                    break;
                }
                if (current.doubleValue() == current.intValue()) {
                    answer = String.valueOf(current.intValue());
                    listener.onAnswerChanged(answer);
                } else {
                    answer = String.valueOf(current);
                    listener.onAnswerChanged(answer);
                }
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
                if (current.doubleValue() == current.intValue()) {
                    answer = String.valueOf(current.intValue());
                    listener.onAnswerChanged(answer);
                } else {
                    answer = String.valueOf(current);
                    listener.onAnswerChanged(answer);
                }
                break;
            case 9://percent
                String hundred = "0.01";
                if(current.equals("0")) return;
                else {
//                    summ = current.multiply(BigDecimal.valueOf(Double.parseDouble(hundred)));
                    answer = String.valueOf(current.multiply(BigDecimal.valueOf(Double.parseDouble(hundred))));
                    listener.onAnswerChanged(BigDecimal.valueOf(Double.parseDouble(answer)).toString());
                }
                break;
            default:
                summ = summ.add(current);
                break;
        }


        while (answer.contains(".") && ((answer.charAt(answer.length()-1) == '0')||(answer.charAt(answer.length()-1) == '.'))){
            answer = answer.substring(0,(answer.length()-1));
        }
        listener.onAnswerChanged(answer);
    }

    public void loadText() {
        sPref = this.getActivity().getSharedPreferences("MyPref", Context.MODE_PRIVATE);
    }

    @SuppressLint("SetTextI18n")
    private void Print(BigDecimal summ, BigDecimal current) {
        if (summ.doubleValue() == summ.intValue()) {
            answer = String.valueOf(summ.intValue());
            listener.onAnswerChanged(answer);
        } else {
            answer = String.valueOf(summ);
            listener.onAnswerChanged(answer);
        }

        if (current.doubleValue() == current.intValue()) {
            editText = editText + current.intValue();
            listener.onEditTextChanged(editText);
        } else {
            editText = editText + current;
            listener.onEditTextChanged(editText);
        }

        iswriting = true;
    }

    void setOnFragmentInteractionListener(OnFragmentInteractionListener listener) {
        this.listener = listener;
    }


    public interface OnFragmentInteractionListener {
        void onAnswerChanged(String answer);
        void onEditTextChanged(String editText);
        void closeActivity();
    }
}
