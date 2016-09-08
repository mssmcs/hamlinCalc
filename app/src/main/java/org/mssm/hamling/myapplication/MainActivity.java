package org.mssm.hamling.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {



    Calculator calc;

    String display = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d("Lifecycle","Main Activity onCreate");
        calc = Calculator.getInstance();
    }

    @Override
    public void onPause() {
        super.onPause();  // Always call the superclass method first
        Log.d("Lifecycle","Main Activity onPause");
    }
    @Override
    public void onStart() {
        super.onStart();  // Always call the superclass method first
        Log.d("Lifecycle","Main Activity onStart");
    }

    @Override
    public void onStop() {
        super.onStop();  // Always call the superclass method first
        Log.d("Lifecycle","Main Activity onStop");
    }

    @Override
    public void onResume() {
        super.onResume();  // Always call the superclass method first
        Log.d("Lifecycle","Main Activity onResume");
        updateDisplay();
    }


    @Override
    public void onDestroy() {
        super.onDestroy();  // Always call the superclass method first
        Log.d("Lifecycle","Main Activity onDestroy");
    }

    public void updateDisplay()
    {
        display = calc.displayString();
        TextView tv = (TextView)findViewById(R.id.textView2);
        tv.setText(display);
    }

    public void onDigitClick(View v)
    {
        Button b = (Button)v;
        int digit = Integer.parseInt(b.getText().toString());

        calc.onDigit(digit);

        updateDisplay();
    }

    public void onChooseConstant(View v)
    {
        Intent startAct = new Intent(this, ConstantsEntry.class);
        Log.d("Cat","Starting new activity");
        startActivityForResult(startAct, 1);
        Log.d("Cat","After start activity");
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == 1) {
            if(resultCode == Activity.RESULT_OK){
                double constValue = data.getDoubleExtra("result", 0);
                calc.setValue(String.valueOf(constValue));
                updateDisplay();
            }
            if (resultCode == Activity.RESULT_CANCELED) {
                //Write your code if there's no result
                Log.d("Cat","Result Canceled");
            }
        }
    }//onActivityResult

    public void onPeriodClick(View v)
    {
        calc.onPeriod();

        updateDisplay();
    }





    public void onOperatorClick(View v)
    {
        Button b = (Button)v;
        char c = b.getText().charAt(0);
        switch (c)
        {
            case '+':
                calc.onOperator(Calculator.Operator.add);
                break;
            case '-':
                calc.onOperator(Calculator.Operator.subtract);
                break;
            case '*':
            case 'X':
                calc.onOperator(Calculator.Operator.multiply);
                break;
            case '/':
                calc.onOperator(Calculator.Operator.divide);
                break;
            case '=':
                calc.onOperator(Calculator.Operator.equals);
                break;
        }
    }
}
