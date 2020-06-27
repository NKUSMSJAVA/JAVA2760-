import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * 工具类，函数均为静态函数
 * 目录：
 * 1.辗转相除法寻找最大公约数：FindGreatestCommonDivisor,返回类型int，参数为int，int；
 * 2.化简分数：FractionToSimple，返回类型Fraction，参数类型Fraction；
 * 3.高斯消元法，确定某个位置为主元，消去一列。
 * 4.打印矩阵
 * 5.分数大小比较
 * 6.整数转化为分数
 * 7.单纯形表中找delta中最小的值的下标
 * 8.在整数数组中查询某整数的下标，错误返回-1
 * 9.整数矩阵转化为分数矩阵
 * 10.打印数组
 * 11.数组取子数组
 */
public class MyUtil {
    @Contract(pure = true)
    static public int FindGreatestCommonDivisor(int a, int b){//输出永远是正的
        if(a==0||b==0){
            return 1;
        }
        if(a<0){
            a=-a;
        }
        if(b<0){
            b=-b;
        }
        int k = 0;
        do {
            k = a % b;// 得到余数
            a = b;// 根据辗转相除法,把被除数赋给除数
            b = k;// 余数赋给被除数
        } while (k != 0);
        return a;// 返回被除数
    }
    @NotNull
    static Fraction FractionToSimple(@NotNull Fraction fraction){
        int a= fraction.numerator;
        int b= fraction.denominator;
        if((a<0&&b<0)||(b<0&&a>0)) {//保证分母不会小于0
             a= -a;
             b= -b;
        }
        Integer fgcd=MyUtil.FindGreatestCommonDivisor(b,a);
        Fraction result=new Fraction(b/fgcd,a/fgcd);
        if(result.numerator==0){
            result.denominator=1;
        }
        return result;
    }
    //取某个位置为主元的高斯消去法,这里k。ir均是指在矩阵中的位置，从一起
    @Nullable
    static Matrix GaussianElimination(@NotNull Matrix source, int i_r, int k){
        int rowNum=source.fractions.length;
        int volNum=source.fractions[0].length;
        if(k>volNum&&i_r>rowNum){
            return null;
        }
        Fraction mainElement=source.fractions[i_r-1][k-1];
        if(mainElement.numerator!=mainElement.denominator){
            //temp指的是消去第ir行主元为一的除数
            Fraction temp=new Fraction(mainElement.numerator,mainElement.denominator);
            for(int indexInI_r=0;indexInI_r<volNum;indexInI_r++){
                source.fractions[i_r-1][indexInI_r]=Fraction.multi(source.fractions[i_r-1][indexInI_r],temp);
            }
        }
        mainElement=source.fractions[i_r-1][k-1];//更新主元，这时候为1
        //进行高斯消元
        for(int i=0;i<rowNum;i++){
            if (i!=i_r-1) {
                Fraction temp=Fraction.div(source.fractions[i][k-1],mainElement);
                for(int j=0;j<volNum;j++){
                    source.fractions[i][j]=Fraction.dim(source.fractions[i][j],Fraction.multi(source.fractions[i_r-1][j],temp));
                }
            }
        }
        return source;
    }

    static void printMatrixDug(@NotNull Integer[][] matr){
        int m=matr.length;
        int n=matr[0].length;
        for(int i=0;i<m;++i){
            for(int j=0;j<n;++j){
                System.out.print(" "+matr[i][j]);
            }
            System.out.print("\n");
        }
    }
    static boolean isBiggerThanFraction(Fraction a,Fraction b){
        if(Fraction.dim(a,b).numerator*Fraction.dim(a,b).denominator>0){
            return true;
        }
        else{
            return false;
        }
    }
    @NotNull
    @Contract(pure = true)
    static Fraction IntegerToFraction(Integer x){
        return new Fraction(1,x);
    }
    static boolean deltaIsBiggerThanZero(@NotNull Matrix matrix){
        int volNum=matrix.fractions[0].length;
        for(int i=0;i<volNum-1;++i){
            if(matrix.fractions[0][i].isSmallerThanZero()){
                return false;
            }
        }
        return true;
    }
    static int findSmallestValueInDeltaIndex(@NotNull Matrix matrix){
        int sIndex=0;
        int volNum=matrix.fractions[0].length;
        Fraction sFraction=new Fraction(1,100000/3);
        for(int i=0;i<volNum-1;++i){
            //Fraction.dim(sFraction,matrix.fractions[0][i]).printFraction();
            matrix.fractions[0][i].printFraction();
            System.out.print("++++++++");
            Fraction.dim(sFraction,matrix.fractions[0][i]).printFraction();
            System.out.print("********");
            if((Fraction.dim(sFraction,matrix.fractions[0][i])).isBiggerThanZero()){
                System.out.print("这里测试i"+i+"\n");
                sFraction=matrix.fractions[0][i];
                sFraction.printFraction();
                sIndex=i+1;
            }
        }
        return sIndex;
    }
    @Contract(pure = true)
    static int findIndexInArray(@NotNull Integer[] integers, Integer integer){
        int length=integers.length;
        for(int i=0;i<length;++i){
            if(integer==integers[i]){
                return i+1;
            }

        }
        return -1;
    }
    @NotNull
    static Matrix IntegerMatrixToFraction(Integer[][] x){
        int rowNUm=x.length;
        int volNUm=x[0].length;
        Matrix result=new Matrix(rowNUm,volNUm);
        for(int i=0;i<rowNUm;++i){
            for(int j=0;j<volNUm;++j){
                result.fractions[i][j]=IntegerToFraction(x[i][j]);
            }
        }
        return result;
    }
    static void printArray(Integer[] x){
        for(Integer i=0;i<x.length;++i){
            System.out.print(x[i]+" ");
        }
        System.out.print("\n");
    }
    static Fraction[] subArray(Fraction[] fractions,Integer[] I){
        Fraction[] result=new Fraction[I.length];
        for(int i=0;i<I.length;++i){
            result[i]=fractions[I[i]-1];
        }
        return result;
    }
}
