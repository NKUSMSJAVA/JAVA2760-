import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.io.PrintStream;
import java.util.Scanner;
import java.util.Vector;

public class TwoStage {
    private Matrix A;
    private Fraction[] b;
    private Fraction[] c;
    Integer[][] nlindex;

    @Contract(pure = true)
    public TwoStage(Matrix a, Fraction[] b, Fraction[] c,Integer[][] nlindex) {
        A = a;
        this.b = b;
        this.c = c;
        this.nlindex=nlindex;
    }

    @Contract(pure = true)
    public TwoStage(Fraction[][] A, Fraction[] b, Fraction[] c,Integer[][] nlindex) {
        this.A=new Matrix(A);
        this.b = b;
        this.c = c;
        this.nlindex=nlindex;
    }
    public TwoStage(TwoStage twoStage){
        this.A=twoStage.A;
        this.b=twoStage.b;
        this.c=twoStage.c;
        this.nlindex=twoStage.nlindex;
    }

    //first stage
    Matrix getExpandA(){
        int rowNum=A.fractions.length;
        int volNum=A.fractions[0].length;
        Matrix result=new Matrix(rowNum,volNum+rowNum);
        for(int i=0;i<rowNum;++i){
            for(int j=0;j<volNum;++j){
                result.fractions[i][j]=A.fractions[i][j];
            }
            result.fractions[i][i+volNum]=MyUtil.IntegerToFraction(1);
        }
        return result;
    }
    Fraction[] getExpandc(){
        int rowNum=A.fractions.length;
        int volNum=A.fractions[0].length;
        Fraction[] result=new Fraction[rowNum+volNum];
        for(int i=0;i<volNum;++i){
            result[i]=c[i];
        }
        return result;
    }
    static boolean hasResult(Fraction[] result,Integer[] I_B){
        for(Integer i=0;i<I_B.length;++i){
            if(result[I_B[i]-1].numerator!=0){
                return false;
            }
        }
        return true;
    }

    public static Vector<String> compute(Integer[][] c, Integer[][] aeq, Integer[][] beq, Integer[][] aleq, Integer[][] bleq, Integer[][] lindex, Integer[][] nindex) {
        Vector<String> resultOutput=new Vector<String>();
        ToStandard source=new ToStandard(c, aeq, beq, aleq, bleq, lindex, nindex);
        TwoStage aim=new TwoStage(source.toStamdard());
        aim.A.printMatrix();
        for(int i=0;i<aim.c.length;++i){
            aim.c[i].printFraction();
            System.out.print(" ");
        }
        System.out.print("\n");
        for(int i=0;i<aim.b.length;++i){
            aim.b[i].printFraction();
            System.out.print(" ");
        }
        //System.exit(0);
        Fraction[] aimcfraction=new Fraction[aim.c.length+aim.A.fractions.length];
        for(int i=0;i<aim.c.length;++i){
            aimcfraction[i]=MyUtil.IntegerToFraction(0);
        }
        for(int i=0;i<aim.A.fractions.length;++i){
            aimcfraction[i+aim.c.length]=MyUtil.IntegerToFraction(1);
        }
        //aim.c=aimcfraction;
        aim.A.printMatrix();
        for(Fraction f:aim.b){
            f.printFraction();
        }
        for(Fraction f:aim.c){
            f.printFraction();
        }
        //System.exit(0);
        //Integer[][] Afractions =new Integer[][]{{1,-1,6,-1,0},{1,1,2,0,-1}};
        //Fraction[] bfraction=new Fraction[]{MyUtil.IntegerToFraction(2),MyUtil.IntegerToFraction(1)};
        /*Fraction[] cfraction=aim.c;//new Fraction[]{MyUtil.IntegerToFraction(5),MyUtil.IntegerToFraction(0),MyUtil.IntegerToFraction(21),MyUtil.IntegerToFraction(0),MyUtil.IntegerToFraction(0)};
        Matrix cMatrix=new Matrix(1,cfraction.length+aim.A.fractions.length);
        //cMatrix.fractions[0][5]=MyUtil.IntegerToFraction(1);
        //cMatrix.fractions[0][6]=MyUtil.IntegerToFraction(1);
        for(int i=0;i<aim.A.fractions.length;++i){
            cMatrix.fractions[0][i+cfraction.length]=MyUtil.IntegerToFraction(1);
        }
         */
        TwoStage firstStage=new TwoStage(aim.A,aim.b,aimcfraction,aim.nlindex);
        Matrix AExpend=firstStage.getExpandA();
        Integer rowNum=firstStage.A.fractions.length;
        Integer volNUm=firstStage.A.fractions[0].length;
        Integer[] I_N=new Integer[volNUm];
        for(Integer i=0;i<volNUm;++i){
            I_N[i]=i+1;
        }
        Integer[] I_row=new Integer[rowNum];
        for(int i=0;i<rowNum;++i){
            I_row[i]=i+1;
        }
        Integer[] I_B=new Integer[rowNum];
        for(int i=0;i<rowNum;++i){
            I_B[i]=i+volNUm+1;
        }
        //MyUtil.printArray(I_B);
        //MyUtil.printArray(I_N);
        //MyUtil.printArray(I_row);
        Matrix N=Matrix.makeSubMatrix(AExpend,I_row,I_N);
        Matrix B=Matrix.makeSubMatrix(AExpend,I_row,I_B);
        Fraction[] delta=SimplexStaticMethod.deltaCompute(firstStage.c,I_B,I_N,B,N);
        //System.out.println("deltasize="+delta.length);
        for(int i=0;i<delta.length;++i){
            delta[i].printFraction();
        }
        System.out.print("\n");
        Matrix simplexTableInFirstStage=SimplexStaticMethod.makeSimplexTable(AExpend,firstStage.b,delta);
        simplexTableInFirstStage.printMatrix();
        simplexTableInFirstStage=SimplexStaticMethod.simplexTableCompute(simplexTableInFirstStage,I_B);
        if (simplexTableInFirstStage == null) {
            resultOutput.add("该问题无解！");
            return resultOutput;
        }
        Fraction[] result1=SimplexStaticMethod.readResultFromSimplex(simplexTableInFirstStage,I_B);
        for(int i=0;i<result1.length;++i){
            System.out.print("result1:");
            result1[i].printFraction();
        }
        Integer[] I_T=new Integer[rowNum];
        for(int i=0;i<rowNum;++i){
            I_T[i]=i+volNUm+1;
        }
        if(!TwoStage.hasResult(result1,I_T)){
            System.out.println("该问题无解！");
            resultOutput.add("该问题无解！");
            return resultOutput;
        }
        //result就是第一阶段得到的基本可行解
        //更新I_B
        //I_B= new Integer[]{2, 3};
        Vector<Integer> I_Npre=new Vector<Integer>();
        for(int i=0;i<I_N.length;++i){
            I_Npre.add(I_N[i]);
        }
        for(Integer i:I_B){
            I_Npre.remove(i);
        }
        int sizeofI_Npre=I_Npre.size();
        I_N=new Integer[sizeofI_Npre];
        for(int i=0;i<sizeofI_Npre;++i){
            I_N[i]=I_Npre.get(i);
        }
        System.out.print("LENGTH:"+I_B.length+" "+I_N.length);
        TwoStage secendStage=new TwoStage(firstStage.A,MyUtil.subArray(result1,I_B),aim.c,firstStage.nlindex);
        B=Matrix.makeSubMatrix(secendStage.A,I_row,I_B);
        B.printMatrix();
        Matrix mrivB=Matrix.mriv(B);
        mrivB.printMatrix();
        N=Matrix.makeSubMatrix(secendStage.A,I_row,I_N);
        delta=SimplexStaticMethod.deltaCompute(aim.c,I_B,I_N,B,N);
        //System.out.print(delta.length);
        for(int i=0;i<delta.length;++i){
            delta[i].printFraction();
        }
        System.out.print("\n");
        Matrix simplexSecondStage=SimplexStaticMethod.makeSimplexTable(Matrix.multi(mrivB,secendStage.A),MyUtil.subArray(result1,I_B),delta);
        simplexSecondStage.printMatrix();
        simplexSecondStage=SimplexStaticMethod.simplexTableCompute(simplexSecondStage,I_B);
        if (simplexSecondStage == null) {
            resultOutput.add("该问题无解！");
            return resultOutput;
        }
        Fraction[] result2=SimplexStaticMethod.readResultFromSimplex(simplexSecondStage,I_B);
        if(secendStage.nlindex.length!=0) {
            for (int i = 0; i < secendStage.nlindex[0].length; ++i) {
                result2[secendStage.nlindex[0][i] - 1] = Fraction.dim(result2[secendStage.nlindex[0][i] - 1], result2[i + result2.length - secendStage.nlindex[0].length]);
            }
            for (int i = 0; i < result2.length - secendStage.nlindex[0].length; ++i) {
                result2[i].printFraction();
                resultOutput.add(result2[i].outputFraction());
            }
        }
        else{
            for (int i = 0; i < result2.length; ++i) {
                result2[i].printFraction();
                resultOutput.add(result2[i].outputFraction());
            }
        }
        System.out.print("end!");
        return resultOutput;
    }
}
