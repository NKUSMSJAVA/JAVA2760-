import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.io.PrintStream;

public class Fraction{
    public Integer denominator;//分母
    public Integer numerator;//分子

    @Contract(pure = true)
    public Fraction(Integer denominator, Integer numerator) {
        this.denominator = denominator;
        this.numerator = numerator;
    }
    //使用该无参构造函数一定要搭配checkDemonZero使用，表明有分母为0的未被修改
    @Contract(pure = true)
    public Fraction(){
        denominator=1;
        numerator=0;
    }

    //打印分数的void函数，成员函数
    public void printFraction(){
        System.out.print(numerator+"/"+denominator+" ");
    }
    public String outputFraction(){
        return new String(numerator + "/" + denominator + " ");
    }
    //检查分母是否为0，是返回True
    public boolean checkDenomIsZero(){
        if(denominator==0){
            return true;
        }
        else{
            return false;
        }
    }
    public void setZero(){
        this.numerator=0;
        this.denominator=1;
    }
    public boolean isBiggerThanZero(){
        if(numerator*denominator>0){
            return true;
        }
        else{
            return false;
        }
    }
    public boolean isSmallerThanZero(){
        if(numerator*denominator<0){
            return true;
        }
        else{
            return false;
        }
    }
    //静态函数，加
    @NotNull
    static public Fraction add(Fraction a, Fraction b){
        Integer commonDenom=a.denominator*b.denominator;
        Integer addNumerator=a.numerator*b.denominator+b.numerator*a.denominator;
        Fraction result=new Fraction(commonDenom,addNumerator);
        result=MyUtil.FractionToSimple(result);//化简
        return result;
    }
    //静态函数，减
    @NotNull
    static public Fraction dim(Fraction a, Fraction b){
        Fraction fude=new Fraction(b.denominator,-b.numerator);
        Fraction result=Fraction.add(a,fude);//这儿已经化简
        return result;
    }
    //静态函数，乘
    @NotNull
    static public Fraction multi(Fraction a, Fraction b){
        Fraction result=new Fraction(a.denominator*b.denominator,a.numerator*b.numerator);
        result=MyUtil.FractionToSimple(result);
        return result;
    }
    //静态函数，除
    @NotNull
    static public Fraction div(Fraction a, Fraction b){
        Fraction diverse=new Fraction(b.numerator,b.denominator);
        diverse=Fraction.multi(a,diverse);
        return diverse;
    }
    public Fraction getNeg(){
        return new Fraction(this.denominator,-this.numerator);
    }
}