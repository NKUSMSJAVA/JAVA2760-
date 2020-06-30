package com.example.administrator.calculator;

/**
 * Created by Administrator on 2020-06-12.
 */
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.lang.*;
import java.util.ArrayList;
import java.util.*;

public class InfixToSuffix {
    private static final Map<Character,Integer>basic =new HashMap();
    static {
        basic.put('-',1);
        basic.put('+', 1);
        basic.put('*', 2);
        basic.put('/', 2);
        basic.put('(', 0);
    }
    public String toSuffix(StringBuilder infix){
        List<String> queue = new ArrayList ();
        List<Character> stack = new ArrayList ();
        char[] charArr = infix.substring(0,infix.length()).toCharArray();
        String operator = "*/+-()";
        char ch;
        int len=0;
        for (int i = 0; i < charArr.length; i++) {
            ch = charArr[i];
            if(Character.isDigit(ch)) {
                len++;
            }else if(ch == '.'){
                len++;
            }else if(operator.indexOf(ch) != -1) {
                if(len > 0) {
                    queue.add(String.valueOf(Arrays.copyOfRange(charArr,i - len , i)));
                    len = 0;
                }
                if(ch == '(') {
                    stack.add(ch);
                    continue;
                }
                if (!stack.isEmpty()) {
                    int size = stack.size() - 1;
                    boolean flag = false;
                    while (size >= 0 && ch == ')' && stack.get(size) != '(') {
                        queue.add(String.valueOf(stack.remove(size)));
                        size--;
                        flag = true;
                    }
                    if(ch==')'&&stack.get(size) == '('){
                        flag = true;
                    }
                    while (size >= 0 && !flag && basic.get(stack.get(size)) >= basic.get(ch)) {
                        queue.add(String.valueOf(stack.remove(size)));
                        size--;
                    }
                }
                if(ch != ')') {
                    stack.add(ch);
                } else {
                    stack.remove(stack.size() - 1);
                }
            }
            if(i == charArr.length - 1) {
                if(len > 0) {
                    queue.add(String.valueOf(Arrays.copyOfRange(charArr, i - len+1, i+1)));
                }
                int size = stack.size() - 1;
                while (size >= 0) {
                    queue.add(String.valueOf(stack.remove(size)));
                    size--;
                }
            }

        }
        String a = queue.toString();
        return a;
    }//转化为后缀表达式
    public String dealEquation(String equation) {
        String[] arr = equation.substring(1,equation.length()-1).split(", ");  //toString()的格式[a, b, c, d]
        List<String> list = new ArrayList();
        for (int i = 0; i < arr.length; i++) {
            int size = list.size();
            switch (arr[i]) {
                case "+":
                    double plus = Double.parseDouble(list.remove(size - 2)) + Double.parseDouble(list.remove(size - 2));
                    list.add(String.valueOf(plus));
                    break;
                case "-":
                    double minus = Double.parseDouble(list.remove(size - 2)) - Double.parseDouble(list.remove(size - 2));
                    list.add(String.valueOf(minus));
                    break;
                case "*":
                    double multiply = Double.parseDouble(list.remove(size - 2)) * Double.parseDouble(list.remove(size - 2));
                    list.add(String.valueOf(multiply));
                    break;
                case "/":
                    double divide = Double.parseDouble(list.remove(size - 2)) / Double.parseDouble(list.remove(size - 2));
                    list.add(String.valueOf(divide));
                    break;
                default:
                    list.add(arr[i]);
                    break;
            }
        }
        if (list.size() == 1) {
            return list.get(0);
        } else return "error";
    }//计算结果
}
