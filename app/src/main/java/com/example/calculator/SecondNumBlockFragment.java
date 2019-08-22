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
import java.util.Arrays;
import java.util.Collections;
import java.util.Objects;


public class SecondNumBlockFragment extends Fragment implements View.OnClickListener {


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
    private boolean dotCheckerAfterSign = false;
    private boolean dotCheckerAfterNumber = true;

    private boolean iswriting = false;

    SharedPreferences sPref;
    final String SAVED_TEXT2 = "numbersSize";


    private OnFragmentInteractionListener mListener;

    public SecondNumBlockFragment() {
        // Required empty public constructor
    }

    public void loadText() {
        sPref = this.getActivity().getSharedPreferences("MyPref", Context.MODE_PRIVATE);
    }


    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_second_num_block, container, false);

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
        final Button DIVISION = view.findViewById(R.id.buttonDIV);
        final Button MUL = view.findViewById(R.id.buttonMUL);
        final Button PM = view.findViewById(R.id.buttonPLUSMINUS);
        final Button DellLastSymbol = view.findViewById(R.id.buttonDellLastSymbol);
        final Button CE = view.findViewById(R.id.buttonDellLastNumber);
        final Button DOT = view.findViewById(R.id.buttonDOT);
        final Button SQRT = view.findViewById(R.id.sqrt);
        final Button FACT = view.findViewById(R.id.fact);
        final Button POW = view.findViewById(R.id.pow);

        final Button PI = view.findViewById(R.id.numPi);
        final Button DIV = view.findViewById(R.id.div);
        final Button MOD = view.findViewById(R.id.mod);
        final Button LEFTPAR = view.findViewById(R.id.leftPar);
        final Button RIGHTPAR = view.findViewById(R.id.rightPar);
        final Button EPS = view.findViewById(R.id.eps);

        final Button TAN = view.findViewById(R.id.tan);
        final Button COS = view.findViewById(R.id.cos);
        final Button SIN = view.findViewById(R.id.sin);
        final Button LOG = view.findViewById(R.id.log);
        final Button LN = view.findViewById(R.id.ln);


        loadText();
        try {
            n = Integer.valueOf(Objects.requireNonNull(sPref.getString(SAVED_TEXT2, "")));
        } catch (Exception e) {
            n = 10;
        }

        Collections.addAll(buttons_numb, ZERO, ONE, TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE);
        Collections.addAll(buttons_foo, EQUAL, PLUS, MINUS, DEL, CE, DIVISION, MUL, PM, DellLastSymbol, DOT, SQRT, FACT, POW, PI, DIV, MOD, LEFTPAR, RIGHTPAR, EPS, TAN, COS, SIN, LOG, LN);

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
                editText = editText + button.getText().toString();
                listener.onEditTextChanged(editText);
                dotCheckerAfterNumber = false;
            }
        }

        switch (v.getId()) {

            case R.id.buttonPLUS:
                dotCheckerAfterSign = false;
                dotCheckerAfterNumber = true;
                editText = editText + " + ";
                listener.onEditTextChanged(editText);
                break;
            case R.id.buttonMINUS:
                dotCheckerAfterSign = false;
                dotCheckerAfterNumber = true;
                editText = editText + " - ";
                listener.onEditTextChanged(editText);
                break;
            case R.id.buttonMUL:
                dotCheckerAfterSign = false;
                dotCheckerAfterNumber = true;
                editText = editText + " × ";
                listener.onEditTextChanged(editText);
                break;
            case R.id.buttonDIV:
                dotCheckerAfterSign = false;
                dotCheckerAfterNumber = true;
                editText = editText + " ÷ ";
                listener.onEditTextChanged(editText);
                break;

            case R.id.buttonEQUAL:
                Parser(editText);
                break;

            case R.id.buttonPLUSMINUS:
                if (editText.charAt(editText.length() - 1) == '-')
                    editText = editText.substring(0, editText.length() - 1);
                else editText = editText.substring(0, editText.length() - 1) + " -";
                listener.onEditTextChanged(editText);
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

            case R.id.buttonDellLastSymbol:
                String str = editText;
                if (str.length() != 0) {
                    str = str.substring(0, str.length() - 1);
                    editText = str;
                    listener.onEditTextChanged(editText);
                }
                if (editText.length() == 0) {
                    editText = "0";
                    listener.onEditTextChanged(editText);
                }
                break;

            case R.id.buttonDellLastNumber:
                answer = "0";
                listener.onAnswerChanged(answer);
                break;

            case R.id.buttonDOT:
                if (!dotCheckerAfterSign && !dotCheckerAfterNumber) {
                    dotCheckerAfterSign = true;
                    dotCheckerAfterNumber = true;
                    editText = editText + ".";
                    listener.onEditTextChanged(editText);
                }
                break;

            case R.id.numPi:
                Check();
//                editText = editText + Math.PI;
                editText = editText + "pi";
                listener.onEditTextChanged(editText);
                break;

            case R.id.eps:
                Check();
//                editText = editText + Math.E;
                editText = editText + "e";
                listener.onEditTextChanged(editText);
                break;

            case R.id.sqrt:
                editText = editText + "√";
                listener.onEditTextChanged(editText);
                break;

            case R.id.pow:
                editText = editText + "^";
                listener.onEditTextChanged(editText);
                break;

            case R.id.fact:
                editText = editText + "!";
                listener.onEditTextChanged(editText);
                break;

            case R.id.leftPar:
                editText = editText + "(";
                listener.onEditTextChanged(editText);
                break;

            case R.id.rightPar:
                editText = editText + ")";
                listener.onEditTextChanged(editText);
                break;

            case R.id.div:
                editText = editText + "div";
                listener.onEditTextChanged(editText);
                break;

            case R.id.mod:
                editText = editText + "mod";
                listener.onEditTextChanged(editText);
                break;

            case R.id.cos:
                editText = editText + "cos(";
                listener.onEditTextChanged(editText);
                break;

            case R.id.sin:
                editText = editText + "sin(";
                listener.onEditTextChanged(editText);
                break;

            case R.id.tan:
                editText = editText + "tg(";
                listener.onEditTextChanged(editText);
                break;

            case R.id.ln:
                editText = editText + "ln(";
                listener.onEditTextChanged(editText);
                break;

            case R.id.log:
                editText = editText + "log(";
                listener.onEditTextChanged(editText);
                break;
        }

        try {
            Parser(editText);
        } catch (Exception e) {
        }
    }

    public void Parser(String str) {
        char[] array = str.toCharArray();
        int start = 0;
        int finish = 0;

        for (int i = 1; i < str.length(); i++) {
            if (array[i] == '(')
                start = i + 1;
        }

        for (int i = start; i < str.length(); i++) {
            if (array[i] == ')') {
                finish = i;
                break;
            }
        }
        if (finish != 0) {
            String toPars = (Calculating(str.substring(start, finish)))/*.substring(start,finish)*/;
            toPars = deliteNull(toPars);
            str = str.substring(0, start - 1)
                    + toPars
                    + str.substring(finish + 1);
            Parser(str);
        } else {
            answer = Calculating(str);
            listener.onAnswerChanged(deliteNull(String.valueOf(answer)));
        }
    }

    private String Calculating(String str) {
        ArrayList<String> list = new ArrayList<>(Arrays.asList(str.split(" ")));
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).equals("pi"))
                list.set(i, String.valueOf(Math.PI));
            if (list.get(i).equals("e"))
                list.set(i, String.valueOf(Math.E));

            if (list.get(i).contains("^")) {
                String[] arr = list.get(i).split("\\^");
                list.set(i, ((new BigDecimal(arr[0])).pow(Integer.valueOf(arr[1]))).toString());
            }

            if (list.get(i).contains("div")) {
                String[] arr = list.get(i).split("div");
                int count = 0;
                while (Double.parseDouble(arr[0])>=Double.parseDouble(arr[1])){
                    arr[0] = String.valueOf(Double.parseDouble(arr[0]) - Double.parseDouble(arr[1]));
                    count++;
                }
                String[] arr2 = list.get(i).split("div");
                list.set(i, new BigDecimal(count).toString());
            }

            if (list.get(i).contains("mod")) {
                String[] arr = list.get(i).split("mod");
                int count = 0;
                while (Double.parseDouble(arr[0])>=Double.parseDouble(arr[1])){
                    arr[0] = String.valueOf(Double.parseDouble(arr[0]) - Double.parseDouble(arr[1]));
                    count++;
                }
                String[] arr2 = list.get(i).split("mod");
                list.set(i, new BigDecimal(arr2[0]).subtract(new BigDecimal(arr2[1]).multiply(new BigDecimal(count))).toString());
            }

            try {
                switch (list.get(i).substring(0, 3)) {
                    case ("cos"):
                        list.set(i, BigDecimal.valueOf(Math.cos(Double.valueOf(list.get(i).substring(3)))).toString());
                        break;
                    case ("sin"):
                        list.set(i, BigDecimal.valueOf(Math.sin(Double.valueOf(list.get(i).substring(3)))).toString());
                        break;
                    case ("log"):
                        list.set(i, BigDecimal.valueOf(Math.log10(Double.valueOf(list.get(i).substring(3)))).toString());
                        break;
                }
            } catch (Exception ignored) {
            }

            try {
                switch (list.get(i).substring(0, 2)) {
                    case ("tg"):
                        list.set(i, BigDecimal.valueOf(Math.tan(Double.valueOf(list.get(i).substring(3)))).toString());
                        break;
                    case ("ln"):
                        list.set(i, BigDecimal.valueOf(Math.log(Double.valueOf(list.get(i).substring(3)))).toString());
                        break;
                }
            } catch (Exception ignored) {
            }

            try {
                if (list.get(i).substring(0, 1).equals("!")) {
                    BigDecimal pep = BigDecimal.valueOf(1);
                    int pip = Integer.valueOf(list.get(i).substring(1));
                    for (int j = 1; j <= pip; j++)
                        pep = pep.multiply(BigDecimal.valueOf(j));
                    list.set(i, String.valueOf(pep));
                }
            } catch (Exception e) {
            }
            try {
                if (list.get(i).substring(list.get(i).length() - 1).equals("!")) {
                    BigDecimal pep = BigDecimal.valueOf(1);
                    int pip = Integer.valueOf(list.get(i).substring(0, list.get(i).length() - 1));
                    for (int j = 1; j <= pip; j++)
                        pep = pep.multiply(BigDecimal.valueOf(j));
                    list.set(i, String.valueOf(pep));
                }
            } catch (Exception e) {
            }

            try {
                if (list.get(i).substring(0, 1).equals("√"))
                    list.set(i, sqrt(BigDecimal.valueOf(Double.valueOf(list.get(i).substring(1)))).toString());
            } catch (Exception e) {
            }

            try {
                if (list.get(i).contains("1") || list.get(i).contains("2") || list.get(i).contains("3") ||
                        list.get(i).contains("4") || list.get(i).contains("5") || list.get(i).contains("6") ||
                        list.get(i).contains("7") || list.get(i).contains("8") || list.get(i).contains("9")) {
                    list.set(i, BigDecimal.valueOf(Double.valueOf(list.get(i))).toString());
                }
            } catch (Exception ignored) {
            }
        }

        try {
            for (int i = 1; i < list.size() - 1; i++) {
                switch (list.get(i)) {
                    case "×":
                        list.set(i, (BigDecimal.valueOf(Double.valueOf(list.get(i - 1))).multiply(BigDecimal.valueOf(Double.valueOf(list.get(i + 1))))).toString());
                        list.remove(i + 1);
                        list.remove(i - 1);
                        i = i - 2;
                        break;
                    case "÷":
                        list.set(i, (BigDecimal.valueOf(Double.valueOf(list.get(i - 1))).divide(BigDecimal.valueOf(Double.valueOf(list.get(i + 1))))).toString());
                        list.remove(i + 1);
                        list.remove(i - 1);
                        i = i - 2;
                        break;
                }
            }
        } catch (Exception e) {
        }

        try {
            for (int i = 1; i < list.size() - 1; i++) {
                switch (list.get(i)) {
                    case "+":
                        list.set(i, (BigDecimal.valueOf(Double.valueOf(list.get(i - 1))).add(BigDecimal.valueOf(Double.valueOf(list.get(i + 1))))).toString());
                        list.remove(i + 1);
                        list.remove(i - 1);
                        i = i - 2;
                        break;
                    case "-":
                        list.set(i, (BigDecimal.valueOf(Double.valueOf(list.get(i - 1))).subtract(BigDecimal.valueOf(Double.valueOf(list.get(i + 1))))).toString());
                        list.remove(i + 1);
                        list.remove(i - 1);
                        i = i - 2;
                        break;
                }
            }
        } catch (Exception e) {
        }
        return list.get(0);
    }

    public static BigDecimal sqrt(BigDecimal value) {
        BigDecimal x = new BigDecimal(Math.sqrt(value.doubleValue()));
        return x.add(new BigDecimal(value.subtract(x.multiply(x)).doubleValue() / (x.doubleValue() * 2.0)));
    }

    private void somefoo(int foo) {
        dotCheckerAfterSign = false;

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
                if (answer.equals("0")) return;
                else {
                    summ = (current.divide(BigDecimal.valueOf(100), n, BigDecimal.ROUND_FLOOR));
                    answer = String.valueOf(summ);
                    listener.onAnswerChanged(BigDecimal.valueOf(Double.parseDouble(answer)).toString());
                }
                break;
            default:
                summ = summ.add(current);
                break;
        }
        deliteNull(answer);
    }

    private String deliteNull(String str) {
        while (str.contains(".") && ((str.charAt(str.length() - 1) == '0') || (str.charAt(str.length() - 1) == '.'))) {
            str = str.substring(0, (str.length() - 1));
        }
        BigDecimal strNumber = (BigDecimal.valueOf(Double.valueOf(str)).setScale(n, BigDecimal.ROUND_FLOOR));
        listener.onAnswerChanged(strNumber.toString());
        return str;
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