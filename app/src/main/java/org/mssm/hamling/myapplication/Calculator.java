package org.mssm.hamling.myapplication;

import android.util.Log;

/**
 * Created by hamling on 9/6/2016.
 */
public class Calculator {

    public enum Operator {
        none,
        add,
        subtract,
        multiply,
        divide,
        equals
    }
    //CalcState state = CalcState.begin;
    String   value = "";
    String   display;
    double   lastValue;
    Operator lastOp = Operator.none;

    private static Calculator instance = null;

    private Calculator()
    {

    }

    public static Calculator getInstance()
    {
        if (instance == null)
        {
            instance = new Calculator();
        }
        return instance;
    }

    public String opToString(Calculator.Operator op)
    {
        switch (op)
        {
            case none:
                return "<noop>";
            case subtract:
                return "-";
            case add:
                return "+";
            case multiply:
                return "*";
            case divide:
                return "/";
            case equals:
                return "=";
            default:
                return "<UnknownOp>";
        }
    }

    public String displayString()
    {
        String display = "";

        switch (lastOp) {
            case none:
                if (value.isEmpty()) {
                    display = "0";
                } else {
                    display = value;
                }
                break;
            case add:
            case subtract:
            case multiply:
            case divide:
                display = lastValue + " " + opToString(lastOp) + " " + value;
                break;
            case equals:
                display = "= " + lastValue;
                break;
        }

        return display;
    }

    void onDigit(int digit)
    {
        if (digit == 0 && value.equals("0"))
        {
            // ignore
        }
        else
        {
            value += Character.forDigit(digit, 10);
        }

    }

    void onPeriod()
    {
        if (value.contains("."))
        {
            // ignore
        }
        else if (value.isEmpty()) {
            value = "0.";
        }
        else
        {
            value += ".";
        }

    }

    void setValue(String val)
    {
        value = val;
    }

    void performLastOp()
    {
        double currValue = 0;

        switch (lastOp)
        {
            case add:
                currValue = Double.parseDouble(value);
                lastValue = lastValue + currValue;
                break;
            case subtract:
                currValue = Double.parseDouble(value);
                lastValue = lastValue - currValue;
                break;
            case multiply:
                currValue = Double.parseDouble(value);
                lastValue = lastValue * currValue;
                break;
            case divide:
                currValue = Double.parseDouble(value);
                lastValue = lastValue / currValue;
                break;
            case none:
            case equals:
            default:
                Log.d("Unexpected", "Unhandled case in performLastOp");
                break;
        }
    }

    public void onOperator(Operator op)
    {
        if (op == Operator.subtract)
        {
            if (value.isEmpty())
            {
                value = "-";
                return;
            }
        }

        switch (op)
        {
            case none:

            case add:
            case subtract:
            case multiply:
            case divide:
                if (lastOp == Operator.none)
                {
                    lastOp = op;
                    lastValue = Double.parseDouble(value);
                    value = "";
                }
                else
                {
                    performLastOp();
                    lastOp = op;
                    value = "";
                }
                break;
            case equals:
                performLastOp();
                lastOp = op;
                value = "";
                break;
        }
    }
}
