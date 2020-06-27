import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.Vector;

public class SimplexStaticMethod {
    static Matrix simplexTableCompute(@NotNull Matrix simplexMatrix, Integer[] I_B) {
        int rowNum=simplexMatrix.fractions.length;
        int volNum=0;
        if(rowNum>0){
            volNum=simplexMatrix.fractions[0].length;
        }
        while (!MyUtil.deltaIsBiggerThanZero(simplexMatrix)) {
            int k = MyUtil.findSmallestValueInDeltaIndex(simplexMatrix);
            System.out.print("k="+k+"\n");
            Vector<Integer> searchI_rVec = new Vector<Integer>();
            for (int i = 1; i < rowNum; ++i) {
                if (simplexMatrix.fractions[i][k - 1].isBiggerThanZero()) {
                    searchI_rVec.add(i);
                }
            }
            if (searchI_rVec.size() == 0) {
                System.out.println("该线性规划问题无解！");
                return null;
            }
            Fraction[][] divInI_r = new Fraction[1][searchI_rVec.size() + 1];
            divInI_r[0][searchI_rVec.size()] = new Fraction(1, 100000 / 2);
            for (int i = 0; i < searchI_rVec.size(); ++i) {
                divInI_r[0][i] = Fraction.div(simplexMatrix.fractions[searchI_rVec.get(i)][volNum - 1], simplexMatrix.fractions[searchI_rVec.get(i)][k - 1]);
            }
            //System.out.println(MyUtil.findSmallestValueInDeltaIndex(new Matrix(divInI_r)));
            Integer i_r = I_B[searchI_rVec.get(MyUtil.findSmallestValueInDeltaIndex(new Matrix(divInI_r)) - 1) - 1];
            System.out.println("i_r=" + i_r);
            simplexMatrix = MyUtil.GaussianElimination(simplexMatrix, MyUtil.findIndexInArray(I_B, i_r) + 1, k);
            I_B[MyUtil.findIndexInArray(I_B, i_r) - 1] = k;
            System.out.println("单纯形表：");
            simplexMatrix.printMatrix();
            System.out.print("I_B:");
            for (int i = 0; i < I_B.length; ++i) {
                System.out.print(I_B[i] + " ");
            }
            System.out.print("\n");
        }
        return simplexMatrix;
    }
    @NotNull
    @Contract(pure = true)
    static Fraction[] readResultFromSimplex(@NotNull Matrix simplexMatrix, @NotNull Integer[] I_B){
        Fraction[] result=new Fraction[simplexMatrix.fractions[0].length-1];
        int volNum=simplexMatrix.fractions[0].length;
        for(int i=0;i<result.length;++i){
            result[i]=MyUtil.IntegerToFraction(0);
        }
        for (Integer i=0;i<I_B.length;++i) {
            result[I_B[i]-1]=simplexMatrix.fractions[i+1][volNum-1];
        }
        return result;
    }
    @NotNull
    static Fraction[] deltaCompute(@NotNull Fraction[] c, @NotNull Integer[] I_B, Integer[] I_N, Matrix B, Matrix N){
        int length=c.length;
        Matrix BMatrix=new Matrix(1,I_B.length);
        MyUtil.printArray(I_B);
        for(int i=0;i<I_B.length;++i){
            BMatrix.fractions[0][i]=c[I_B[i]-1];
        }
        Matrix NMatrix=new Matrix(1,I_N.length);
        for(int i=0;i<I_N.length;++i){
            NMatrix.fractions[0][i]=c[I_N[i]-1];
        }
        NMatrix=Matrix.add(NMatrix,Matrix.NegMatrix(Matrix.multi(BMatrix,Matrix.multi(Matrix.mriv(B),N))));
        Fraction[] result=new Fraction[c.length];
        for(int i=0;i<c.length;++i){
            result[i]=MyUtil.IntegerToFraction(0);
        }
        for(int i=0;i<I_N.length;++i){
            result[I_N[i]-1]=NMatrix.fractions[0][i];
        }
        return result;
    }
    @NotNull
    static Matrix makeSimplexTable(@NotNull Matrix A, Fraction[] b, Fraction[] delta){
        Integer rowNum=A.fractions.length+1;
        Integer volNum=A.fractions[0].length+1;
        Matrix simplexTable=new Matrix(rowNum,volNum);
        //填充第一行
        for(int i=0;i<volNum-1;++i){
            simplexTable.fractions[0][i]=delta[i];
        }
        for(int i=1;i<rowNum;++i){
            for(int j=0;j<volNum-1;++j){
                simplexTable.fractions[i][j]=A.fractions[i-1][j];
            }
        }
        for(int i=1;i<rowNum;++i){
            simplexTable.fractions[i][volNum-1]=b[i-1];
        }
        return simplexTable;
    }
}
