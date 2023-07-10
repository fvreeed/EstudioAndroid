package com.example.falaq;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {


    TextView firstNumber;
    EditText secondNumber;
    TextView expression;
    Double operand = null;
    String lastOperation = "=";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        firstNumber = findViewById(R.id.firstNumber);
        secondNumber = findViewById(R.id.secondNumber);
        expression = findViewById(R.id.expression);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {

        outState.putString("OPERATION", lastOperation);
        if(operand!=null)
            outState.putDouble("OPERAND", operand);
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {

        super.onRestoreInstanceState(savedInstanceState);
        lastOperation = savedInstanceState.getString("OPERATION");
        operand= savedInstanceState.getDouble("OPERAND");
        firstNumber.setText(operand.toString());
        expression.setText(lastOperation);
    }

    public void onNumberClick(View view) {

        Button button = (Button)view;
        secondNumber.append(button.getText());
        if (lastOperation.equals("=") && operand != null) {
            operand = null;
        }
    }

    public void onOperationClick(View view) {

        Button button = (Button)view;
        String operation = button.getText().toString();
        String number = secondNumber.getText().toString();
        if (number.length() > 0) {
            number = number.replace(",", ".");
            try {
                performOperation(Double.valueOf(number), operation);
            } catch (NumberFormatException exception) {
                secondNumber.setText("");
            }
        }
        lastOperation = operation;
        expression.setText(lastOperation);
    }

    private void performOperation(Double number, String operation) {
        if (operand ==null) {
            operand = number;
        } else {
            if (lastOperation.equals("=")) {
                lastOperation = operation;
            }
            switch(lastOperation){
                case "=":
                    operand =number;
                    break;
                case "/":
                    if(number==0){
                        operand =0.0;
                    }
                    else{
                        operand /=number;
                    }
                    break;
                case "*":
                    operand *=number;
                    break;
                case "+":
                    operand +=number;
                    break;
                case "-":
                    operand -=number;
                    break;
            }
        }
        firstNumber.setText(operand.toString().replace('.', ','));
        secondNumber.setText("");
    }
}