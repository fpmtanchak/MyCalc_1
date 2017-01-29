package com.example.mycalc_1;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;


public class
MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText editText;
    private Button button1, button2, button3, button4, button5, button6, button7,
            button8, button9, button0, buttonComa, buttonAdd, buttonMult,
            buttonEq, buttonClean, buttonSub, buttonSqrt, buttonDiv, buttonPercent,
            buttonBack, buttonExponentiation, buttonSquare, buttonOff;
    int flagAction;
    double result, operand1, operand2;
    SharedPreferences sp;
    Boolean add_button;
    String style_calc;
    //    arrays for a buttons
    int[] button_ids;
    Button[] buttons_array;
    int len;
    Boolean flagPoint;
    int degree_before, degree_after;
    int buttonW, buttonH;
    Typeface CF;
    LinearLayout activity_main;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        operand1 = 0;
        operand2 = 0;
        flagAction = 0;
        result = 0;

        editText = (EditText) findViewById(R.id.editText);

        button_ids = new int[]{R.id.button0, R.id.button1, R.id.button2, R.id.button3, R.id.button4,
                R.id.button5, R.id.button6, R.id.button7, R.id.button8, R.id.button9,
                R.id.buttonAdd, R.id.buttonMult, R.id.buttonSub, R.id.buttonDiv,
                R.id.buttonClean, R.id.buttonComa, R.id.buttonEq, R.id.buttonSqrt,
                R.id.buttonPercent, R.id.buttonBack, R.id.buttonExponentiation, R.id.buttonSquare,
                R.id.buttonOff};

        buttons_array = new Button[]{button0, button1, button2, button3, button4, button5,
                button6, button7, button8, button9, buttonAdd, buttonMult, buttonSub,
                buttonDiv, buttonClean, buttonComa, buttonEq, buttonSqrt, buttonPercent,
                buttonBack, buttonExponentiation, buttonSquare, buttonOff};

        len = buttons_array.length;
        for (int i = 0; i < len; i++) {
            buttons_array[i] = (Button) findViewById(button_ids[i]);
            buttons_array[i].setOnClickListener(this);
        }

        clearVariables();
        showNumber(operand1);


        sp = PreferenceManager.getDefaultSharedPreferences(this);

        activity_main = (LinearLayout)findViewById(R.id.activity_main);
    }

    public void onResume() {
        add_button = sp.getBoolean("add_button", false);
        style_calc = sp.getString("style_calc", "1");
        super.onResume();

        Display display = getWindowManager().getDefaultDisplay();
        DisplayMetrics metricsB = new DisplayMetrics();
        display.getMetrics(metricsB);
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.button1:
                ClickNumber(1);
                break;
            case R.id.button2:
                ClickNumber(2);
                break;
            case R.id.button3:
                ClickNumber(3);
                break;
            case R.id.button4:
                ClickNumber(4);
                break;
            case R.id.button5:
                ClickNumber(5);
                break;
            case R.id.button6:
                ClickNumber(6);
                break;
            case R.id.button7:
                ClickNumber(7);
                break;
            case R.id.button8:
                ClickNumber(8);
                break;
            case R.id.button9:
                ClickNumber(9);
                break;
            case R.id.button0:
                ClickNumber(0);
                break;
            case R.id.buttonAdd:
                if (flagAction == 0) flagAction = 1;
                clearDegree();
                break;
            case R.id.buttonSub:
                if (flagAction == 0) flagAction = 2;
                clearDegree();
                break;
            case R.id.buttonMult:
                if (flagAction == 0) flagAction = 3;
                clearDegree();
                break;
            case R.id.buttonDiv:
                if (flagAction == 0) flagAction = 4;
                clearDegree();
                break;
            case R.id.buttonExponentiation:
                if (flagAction == 0) flagAction = 5;
                clearDegree();
                break;
            case R.id.buttonPercent:
                if (flagAction == 0) flagAction = 6;
                clearDegree();
                break;
            case R.id.buttonEq:
                switch (flagAction) {
                    case 1:
                        result = operand1 + operand2;
                        showNumber(result);
                        operand1 = result;
                        break;
                    case 2:
                        result = operand1 - operand2;
                        showNumber(result);
                        operand1 = result;
                        break;
                    case 3:
                        result = operand1 * operand2;
                        showNumber(result);
                        operand1 = result;
                        break;
                    case 4:
                        result = operand1 / operand2;
                        showNumber(result);
                        operand1 = result;
                        break;
                    case 5:
                        result = Math.pow(operand1, operand2);
                        showNumber(result);
                        operand1 = result;
                        break;
                    case 6:
                        result = (operand1 / 100) * operand2;
                        showNumber(result);
                        operand1 = result;
                        break;
                    default:
                        Toast.makeText(this, "Вкажіть дію!", Toast.LENGTH_LONG).show();
                }
                if (result % 1 != 0) {
                    degree_after = 5;
                }
                if (flagAction != 0) {
                    showNumber(result);
                    clearVariables_2();

                }
                break;
            case R.id.buttonClean:
                clearVariables();
                showNumber(operand1);
                break;
            case R.id.buttonComa:
                flagPoint = true;
                break;
            case R.id.buttonSquare:
                if (flagAction == 0) {
                    result = Math.pow(operand1, 2);
                    if (result != 0) degree_after = 5;
                    showNumber(result);
//                    clearVariables_2();
                    operand1 = result;
                } else {
                    Toast.makeText(this, R.string.other_operation, Toast.LENGTH_LONG).show();
                }
                break;
            case R.id.buttonSqrt:
                if (flagAction == 0) {
                    result = Math.sqrt(operand1);
                    if (result != 0) degree_after = 5;
                    showNumber(result);
                    operand1 = result;
                } else {
                    Toast.makeText(this, R.string.other_operation, Toast.LENGTH_LONG).show();
                }
                break;
            case R.id.buttonBack:
//                !!!!!!!!!!!!!! NULL     WHY ???????????
                String Temp = null;
                if (flagAction == 0) {
                    if (operand1 % 1 == 0 & degree_after == 0) {
                        Temp = Integer.toString((int) operand1);
                    } else {
                        Temp = Double.toString(operand1);
                    }
                    Temp = Temp.substring(0, Temp.length() - 1);

                    if (Temp.length() > 0) {
                        operand1 = Double.parseDouble(Temp);
                    } else {
                        operand1 = 0;
                    }
                    showNumber(operand1);

                } else {
                    if (flagAction == 0) {
                        if (operand2 % 1 == 0 & degree_after == 0) {
                            Temp = Integer.toString((int) operand2);
                        }
                    } else {
                        Temp = Double.toString(operand2);
                    }
                    Temp = Temp.substring(0, Temp.length() - 1);

                    if (Temp.length() > 0) {
                        operand2 = Double.parseDouble(Temp);
                    } else {
                        operand2 = 0;
                    }
                    showNumber(operand2);
                }
                if (degree_after > 0) {
                    degree_after--;
                } else {
                    flagPoint = false;
                }
                break;
            case R.id.buttonOff:
                finish();
                break;
        }
    }

    private void ClickNumber(int num) {
        if (flagAction == 0) {
            if (flagPoint) {
                degree_after++;
                if (degree_after <= 5) {
                    operand1 += num / Math.pow(10, degree_after);
                } else {
                    degree_after = 5;
                    Toast.makeText(this, R.string.limit_after, Toast.LENGTH_LONG).show();
                }
            } else {
                degree_before++;
                if (degree_before <= 10) {
                    operand1 = operand1 * 10 + num;
                } else {
                    degree_before = 10;
                    Toast.makeText(this, R.string.limit_before, Toast.LENGTH_LONG).show();
                }
            }
//            operand1 = operand1 * 10 + num;
            showNumber(operand1);
        } else {
            if (flagPoint) {
                degree_after++;
                if (degree_after <= 5) {
                    operand2 += num / Math.pow(10, degree_after);
                } else {
                    degree_after = 5;
                    Toast.makeText(this, R.string.limit_after, Toast.LENGTH_LONG).show();
                }
            } else {
                degree_before++;
                if (degree_before <= 10) {
                    operand2 = operand2 * 10 + num;
                } else {
                    degree_before = 10;
                    Toast.makeText(this, R.string.limit_before, Toast.LENGTH_LONG).show();
                }
            }
//            operand2 = operand2 * 10 + num;
            showNumber(operand2);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.options_menu:
                startActivity(new Intent(this, PrefActivity.class));
                // Toast.makeText(this, R.string.options, Toast.LENGTH_LONG).show();
                break;
            case R.id.about_menu:
                startActivity(new Intent(this, AboutActivity.class));
//                Toast.makeText(this, R.string.about_app, Toast.LENGTH_LONG).show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    //    Check if the fractional part is equal to zero
    private void showNumber(double num) {
        String corr = "";
        if (num > Integer.MAX_VALUE) {
            editText.setText("ERROR");
            Toast.makeText(this, R.string.degree_overflow, Toast.LENGTH_LONG).show();
        } else {
            if (num % 1 == 0 & degree_after == 0) {
                editText.setText(Integer.toString((int) num));
            } else {
                int part_int = (int) num;
                int part_frac = (int) Math.round(num % 1 * Math.pow(10, degree_after));
//                if (degree_after == 2 & part_frac < 10) {
//                    corr = "0";
//                }
                editText.setText(part_int + "." + corr + part_frac);
            }
        }

    }

    private void clearVariables() {
        operand1 = 0;
        operand2 = 0;
        flagAction = 0;
        result = 0;
        clearDegree();
    }

    private void clearVariables_2() {
//        operand1 = 0;
        operand2 = 0;
        flagAction = 0;
        result = 0;
        clearDegree();
    }

    private void clearDegree() {
        degree_before = 0;
        degree_after = 0;
        flagPoint = false;

    }
}
