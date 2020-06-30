package com.example.administrator.calculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    Button btn_0;
    Button btn_1;
    Button btn_2;
    Button btn_3;
    Button btn_4;
    Button btn_5;
    Button btn_6;
    Button btn_7;
    Button btn_8;
    Button btn_9;
    Button btn_point;
    Button btn_clear;
    Button btn_del;
    Button btn_plus;
    Button btn_minus;
    Button btn_multiply;
    Button btn_divide;
    Button btn_equal;
    Button btn_left;//(
    Button btn_right;//)
    private TextView et_input;//显示文本
    private StringBuilder pending = new StringBuilder();

    private void initView() {
        btn_0 = findViewById(R.id.btn_0);
        btn_1 = findViewById(R.id.btn_1);
        btn_2 = findViewById(R.id.btn_2);
        btn_3 = findViewById(R.id.btn_3);
        btn_4 = findViewById(R.id.btn_4);
        btn_5 = findViewById(R.id.btn_5);
        btn_6 = findViewById(R.id.btn_6);
        btn_7 = findViewById(R.id.btn_7);
        btn_8 = findViewById(R.id.btn_8);
        btn_9 = findViewById(R.id.btn_9);
        btn_point = findViewById(R.id.btn_point);
        btn_clear = findViewById(R.id.btn_clear);
        btn_del = findViewById(R.id.btn_del);
        btn_plus = findViewById(R.id.btn_plus);
        btn_minus = findViewById(R.id.btn_minus);
        btn_multiply = findViewById(R.id.btn_multiply);
        btn_divide = findViewById(R.id.btn_divide);
        btn_equal = findViewById(R.id.btn_equal);
        et_input = findViewById(R.id.et_input);
        btn_left = findViewById(R.id.btn_left);
        btn_right = findViewById(R.id.btn_right);

        btn_0.setOnClickListener(this);
        btn_1.setOnClickListener(this);
        btn_2.setOnClickListener(this);
        btn_3.setOnClickListener(this);
        btn_4.setOnClickListener(this);
        btn_5.setOnClickListener(this);
        btn_6.setOnClickListener(this);
        btn_7.setOnClickListener(this);
        btn_8.setOnClickListener(this);
        btn_9.setOnClickListener(this);
        btn_point.setOnClickListener(this);
        btn_plus.setOnClickListener(this);
        btn_equal.setOnClickListener(this);
        btn_minus.setOnClickListener(this);
        btn_multiply.setOnClickListener(this);
        btn_del.setOnClickListener(this);
        btn_divide.setOnClickListener(this);
        btn_clear.setOnClickListener(this);
        btn_divide.setOnClickListener(this);
        btn_left.setOnClickListener(this);
        btn_right.setOnClickListener(this);
    }//监听
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }
    public void onClick(View v) {
        int last = 0;
        if(pending.length()!=0)
        {
            last = pending.codePointAt(pending.length()-1);
        }
        switch (v.getId()) {
            case R.id.btn_0:
                pending = pending.append("0");
                et_input.setText(pending);
                break;
            case R.id.btn_1:
                pending = pending.append("1");
                et_input.setText(pending);
                break;
            case R.id.btn_2:
                pending = pending.append("2");
                et_input.setText(pending);
                break;
            case R.id.btn_3:
                pending = pending.append("3");
                et_input.setText(pending);
                break;
            case R.id.btn_4:
                pending = pending.append("4");
                et_input.setText(pending);
                break;
            case R.id.btn_5:
                pending = pending.append("5");
                et_input.setText(pending);
                break;
            case R.id.btn_6:
                pending = pending.append("6");
                et_input.setText(pending);
                break;
            case R.id.btn_7:
                pending = pending.append("7");
                et_input.setText(pending);
                break;
            case R.id.btn_8:
                pending = pending.append("8");
                et_input.setText(pending);
                break;
            case R.id.btn_9:
                pending = pending.append("9");
                et_input.setText(pending);
                break;
            case R.id.btn_plus:
                pending = pending.append("+");
                et_input.setText(pending);
                break;
            case R.id.btn_minus:
                pending = pending.append("-");
                et_input.setText(pending);
                break;
            case R.id.btn_multiply:
                pending = pending.append("*");
                et_input.setText(pending);
                break;
            case R.id.btn_divide:
                pending = pending.append("/");
                et_input.setText(pending);
                break;
            case R.id.btn_point:
                if (judje1()) {
                    pending = pending.append(".");
                    et_input.setText(pending);
                }
                break;
            case R.id.btn_right:
                if((last>='0' &&last<='9'||last==')')&&judje2()==true ) {
                    pending = pending.append(")");
                    et_input.setText(pending);
                }
                break;
            case R.id.btn_left:
                pending = pending.append("(");
                et_input.setText(pending);
                break;
            case R.id.btn_del:
                if (pending.length() != 0) {
                    pending = pending.delete(pending.length() - 1, pending.length());
                    et_input.setText(pending);
                }
                break;
            case R.id.btn_clear:
                pending = pending.delete(0, pending.length());
                et_input.setText(pending);
                break;
            case R.id.btn_equal:
                if ((pending.length() > 1)) {
                    InfixToSuffix inf = new InfixToSuffix();
                    String result;
                    try {
                       ; String a = inf.toSuffix(pending)
                        result = inf.dealEquation(a);
                        if(result.substring(result.length()-2).equals(".0")) { //String.valueOf()会把double转为String，但是整数的结尾有固定的".0"
                            result=result.substring(0,result.length()-2);
                        }
                    } catch (Exception ex) {
                        result = "error";
                    }
                    et_input.setText(pending + "=" + result);
                    pending = pending.delete(0, pending.length());
                    if (Character.isDigit(result.charAt(0))) { //如果没有报错，保留结果继续计算
                        pending = pending.append(result);
                    }
                }
                break;
            default:
                break;
        }
    }
    private boolean judje1() {
        String a = "+-*/.";
        int[] b = new int[a.length()];
        int max;
        for (int i = 0; i < a.length(); i++) {
            String c = "" + a.charAt(i);
            b[i] = pending.lastIndexOf(c);
        }
        Arrays.sort(b);
        if (b[a.length() - 1] == -1) { //没有运算符
            max = 0;
        } else {  //最后一个运算符
            max = b[a.length() - 1];
        }
        if (pending.indexOf(".", max) == -1) {
            return true;
        } else {
            return false;
        }
    }//判断是否可以输入小数点
    private boolean judje2(){
        int a=0,b=0;
        for(int i = 0 ; i < pending.length() ;i++){
            if(pending.charAt(i)=='(' ) {
                a++;
            }
            if(pending.charAt(i)==')' ) {
                b++;
            }
        }
        if(a == b)
            return false;
        if(a > b)
            return true;
        return false;
    }//判断左右括号数量
}
