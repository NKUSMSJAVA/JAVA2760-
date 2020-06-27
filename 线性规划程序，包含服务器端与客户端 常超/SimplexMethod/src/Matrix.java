import org.jetbrains.annotations.NotNull;

import java.util.Scanner;

public class Matrix {
    public Fraction fractions[][];
    //无参构造函数，需要手动输入
    public Matrix(Scanner scanner){
        System.out.print("输入1为分数输入通道，输入2为整数输入快捷通道！：");
        int choice=scanner.nextInt();
        if(choice==1){
            System.out.print("\n请输入待输入矩阵的规模m*n：");
            int m,n;
            m=scanner.nextInt();
            n=scanner.nextInt();
            int num=m*n;
            fractions=new Fraction[m][n];
            for(int indexOfM=0;indexOfM<m;indexOfM++){
                for(int indexOfN=0;indexOfN<n;indexOfN++){
                    System.out.print("\n请输入第"+(indexOfM+1)+"行，第"+(indexOfN+1)+"列的分数，格式：分子+空格+分母：");
                    int denom=scanner.nextInt();
                    int numer=scanner.nextInt();
                    fractions[indexOfM][indexOfN]=new Fraction(numer,denom);
                }
            }
        }
        else if(choice==2){
            System.out.print("\n请输入待输入矩阵的规模m*n：");
            int m,n;
            m=scanner.nextInt();
            n=scanner.nextInt();
            int num=m*n;
            fractions=new Fraction[m][n];
            for(int indexOfM=0;indexOfM<m;indexOfM++){
                for(int indexOfN=0;indexOfN<n;indexOfN++){
                    System.out.print("\n请输入第"+(indexOfM+1)+"行，第"+(indexOfN+1)+"列的整数：");
                    int denom=1;
                    int numer=scanner.nextInt();
                    fractions[indexOfM][indexOfN]=new Fraction(denom,numer);
                }
            }
        }
        else{
            System.out.println("输入错误！程序将退出！");
        }
        scanner.close();
    }
    //拷贝构造函数
    public Matrix(Matrix x){
        this.fractions=x.fractions;
    }
    //参数为矩阵的构造函数
    public Matrix(Fraction[][] x){
        this.fractions=x;
    }
    public Matrix(Integer m,Integer n){
        Fraction fraction[][]=new Fraction[m][n];
        //注意这个不会自动调用构造函数，只会空一个位置，留一个空指针
        for(int i=0;i<m;++i){
            for(int j=0;j<n;j++){
                Fraction subFraction=new Fraction();
                fraction[i][j]=subFraction;
            }
        }
        //初始化所有元素为0
        this.fractions=fraction;
    }
    //打印矩阵
    public void printMatrix(){
        int RowNum=fractions.length;
        int VolNum=fractions[0].length;
        for(int i=0;i<RowNum;i++){
            System.out.print("["+(i+1)+"] ");
            for(int j=0;j<VolNum;j++){
                fractions[i][j].printFraction();
            }
            System.out.print("\n");
        }
    }
    //判断矩阵是否大于等于0
    public boolean isBiggerThanZero(){
        int numOfRow=fractions.length;
        int numOfVol=fractions[0].length;
        for(int i=0;i<numOfRow;++i){
            for(int j=0;j<numOfVol;++j){
                if(fractions[i][j].numerator*fractions[i][j].denominator<0){
                    return false;
                }
            }
        }
        return true;
    }
    //矩阵乘法
    @NotNull
    static Matrix multi(Matrix a, Matrix b){
        Integer rowNumOfa=a.fractions.length;
        Integer volNumOfa=a.fractions[0].length;
        Integer rowNumOfb=b.fractions.length;
        Integer volNumOfb=b.fractions[0].length;
        if(volNumOfa!=rowNumOfb){
            System.out.println("error Matrix Multi,rowNumber is not equal to volNumber!");
            System.exit(1);
        }
        Matrix result=new Matrix(rowNumOfa,volNumOfb);
        for(int i=0;i<rowNumOfa;++i){
            for(int j=0;j<volNumOfb;++j){
                Fraction sum=new Fraction();
                for(int k=0;k<rowNumOfb;++k){
                    sum=Fraction.add(sum,Fraction.multi(a.fractions[i][k],b.fractions[k][j]));
                }
                result.fractions[i][j]=sum;
            }
        }
        return result;
    }
    //矩阵求逆，采用方法为高代中学的把原来的矩阵消为单位举证，则右边原来为单位矩阵的现在就是逆矩阵
    @NotNull
    static Matrix mriv(Matrix matrix){
        Integer rowNum=matrix.fractions.length;
        Integer volNum=matrix.fractions[0].length;
        if(rowNum!=volNum){
            System.out.println("error Matrix! Cannot mariv!");
            System.exit(2);
        }
        //先构造一个大矩阵，左边是原来的矩阵，右边是单位矩阵
        Matrix bigMatrix=new Matrix(rowNum,2*rowNum);
        for(int i=0;i<rowNum;++i){
            for(int j=0;j<rowNum;++j){
                bigMatrix.fractions[i][j]=matrix.fractions[i][j];
            }
            Fraction one=new Fraction(1,1);
            bigMatrix.fractions[i][i+rowNum]=one;
        }
        bigMatrix.printMatrix();
        //接下来对每个i，i位置为主元高斯消元。这里我们不考虑特别差的数，比如0.0000000000001什么的，只要是分数运算且不超过能算范围的
        //其实都没有误差
        for(int i=1;i<=rowNum;++i){
            MyUtil.GaussianElimination(bigMatrix,i,i);
        }
        //读出逆矩阵
        Matrix result=new Matrix(rowNum,rowNum);
        for(int i=0;i<rowNum;++i){
            for(int j=0;j<rowNum;++j){
                result.fractions[i][j]=bigMatrix.fractions[i][j+rowNum];
            }
        }
        return result;
    }
    //矩阵加法
    static Matrix add(@NotNull Matrix x, @NotNull Matrix y){
        if(x.fractions.length!=y.fractions.length){
            System.out.println("wrong add!");
            System.exit(3);
        }
        if(x.fractions[0].length!=y.fractions[0].length){
            System.exit(3);
        }
        int rowNum=x.fractions.length;
        int volNum=y.fractions[0].length;
        Matrix result=new Matrix(rowNum,volNum);
        for(int i=0;i<rowNum;++i){
            for(int j=0;j<volNum;++j){
                result.fractions[i][j]=Fraction.add(x.fractions[i][j],y.fractions[i][j]);
            }
        }
        return result;
    }
    //矩阵求负
    static Matrix NegMatrix(@NotNull Matrix x){
        int rowNum=x.fractions.length;
        int volNum=x.fractions[0].length;
        Matrix result=new Matrix(rowNum,volNum);
        for(int i=0;i<rowNum;++i){
            for (int j=0;j<volNum;++j){
                result.fractions[i][j]=Fraction.dim(new Fraction(),x.fractions[i][j]);
            }
        }
        return  result;
    }
    static Matrix makeSubMatrix(Matrix matrix,Integer[] I_row,Integer[] I_vol){
        Matrix result=new Matrix(I_row.length,I_vol.length);
        int rowNum=I_row.length;
        int volNum=I_vol.length;
        for(int i=0;i<rowNum;++i) {
            for(int j=0;j<volNum;++j) {
                //System.out.println("i=" + I_row[i] + "j=" + I_vol[j]);
                result.fractions[i][j] = matrix.fractions[I_row[i] - 1][I_vol[j] - 1];
            }
        }

        return result;
    }


}
