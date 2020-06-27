import org.jetbrains.annotations.Contract;

import java.util.Scanner;
import java.util.Vector;

public class ToStandard {
    Integer c[][];
    Integer Aeq[][];
    Integer beq[][];
    Integer Aleq[][];
    Integer bleq[][];
    Integer lindex[][];
    Integer nindex[][];

    public ToStandard() {
        c=null;
        Aeq=new Integer[0][0];
        beq=new Integer[0][0];
        Aleq=new Integer[0][0];
        bleq=new Integer[0][0];
        lindex=new Integer[0][0];
        nindex=new Integer[0][0];
    }
    public ToStandard(Scanner sc){
        System.out.println("------------------------------------------------------------------------------");
        System.out.println("输入对应的数字即可进行对应项目的输入。输入默认为矩阵形式，需要先输入矩阵的行数和列数，再输入矩阵。");
        System.out.println("矩阵输入格式任意，只要输入足量整数即可。当然，想输入分数，请升级为VIP，即可享受该服务与1KB/s高速");
        System.out.println("数据传输。百度网盘，想你所想！若是“侵犯客户隐私”，pandownload就是下场！");
        int choice=0;
        //Integer c[][]=new Integer[][]{{5,0,21,0,0}};
        //Integer Aeq[][]=new Integer[][]{{1,-1,6,-1,0},{1,1,2,0,-1}};
        //Integer beq[][]=new Integer[][]{{2},{1}};
        Integer c[][]=new Integer[0][0];
        Integer Aeq[][]=new Integer[0][0];
        Integer beq[][]=new Integer[0][0];
        Integer Aleq[][]=new Integer[0][0];
        Integer bleq[][]=new Integer[0][0];
        Integer lindex[][]=new Integer[0][0];
        Integer nindex[][] = new Integer[0][0];
        while (choice!=-1) {
            System.out.print("请输入项目:");
            if (choice==0) {
                System.out.print("\n");
                System.out.println("1.c，即需要极小化的系数");
                System.out.println("2.Aeq，即等于等式约束部分的系数矩阵");
                System.out.println("3.beq，即等式约束部分的约束结果");
                System.out.println("4.Aleq，即不等式部分小于的系数矩阵");
                System.out.println("5.bleq，不等式小于的约束结果");
                System.out.println("6.小于等于0变量指标集");
                System.out.println("7.无约束指标集");
                System.out.println("0:调出菜单");
                System.out.println("-1:退出输入");
                System.out.print("请输入项目:");
            }
            choice=sc.nextInt();
            if(choice==-1){
                break;
            }
            if(choice==0){
                continue;
            }
            System.out.print("输入待输入对象的行数和列数");
            int rowNumber;
            int volNumber;
            rowNumber=sc.nextInt();
            volNumber=sc.nextInt();
            switch (choice){
                case 1:
                    System.out.print("c["+rowNumber+"]["+volNumber+"]:");
                    c=new Integer[rowNumber][volNumber];
                    for(int i=0;i<rowNumber;++i){
                        for(int j=0;j<volNumber;++j){
                            c[i][j]=sc.nextInt();
                        }
                    }
                    break;

                case 2:
                    System.out.print("Aeq["+rowNumber+"]["+volNumber+"]:");
                    Aeq=new Integer[rowNumber][volNumber];
                    for(int i=0;i<rowNumber;++i){
                        for(int j=0;j<volNumber;++j){
                            Aeq[i][j]=sc.nextInt();
                        }
                    }
                    break;
                case 3:
                    System.out.print("beq["+rowNumber+"]["+volNumber+"]:");
                    beq=new Integer[rowNumber][volNumber];
                    for(int i=0;i<rowNumber;++i){
                        for(int j=0;j<volNumber;++j){
                            beq[i][j]=sc.nextInt();
                        }
                    }
                    break;
                case 4:
                    System.out.print("Aleq["+rowNumber+"]["+volNumber+"]:");
                    Aleq=new Integer[rowNumber][volNumber];
                    for(int i=0;i<rowNumber;++i){
                        for(int j=0;j<volNumber;++j){
                            Aleq[i][j]=sc.nextInt();
                        }
                    }
                    break;
                case 5:
                    System.out.print("bleq["+rowNumber+"]["+volNumber+"]:");
                    bleq=new Integer[rowNumber][volNumber];
                    for(int i=0;i<rowNumber;++i){
                        for(int j=0;j<volNumber;++j){
                            bleq[i][j]=sc.nextInt();
                        }
                    }
                    break;
                case 6:
                    System.out.print("lindex["+rowNumber+"]["+volNumber+"]:");
                    lindex=new Integer[rowNumber][volNumber];
                    for(int i=0;i<rowNumber;++i){
                        for(int j=0;j<volNumber;++j){
                            lindex[i][j]=sc.nextInt();
                        }
                    }
                    break;
                case 7:
                    System.out.print("nindex["+rowNumber+"]["+volNumber+"]:");
                    nindex=new Integer[rowNumber][volNumber];
                    for(int i=0;i<rowNumber;++i){
                        for(int j=0;j<volNumber;++j){
                            nindex[i][j]=sc.nextInt();
                        }
                    }
                    break;
            }
            System.out.println("输入完毕！");
        }
        if(c!=null){
            MyUtil.printArray(c[0]);
        }
        else{
            System.out.print("c is null!");
        }
        if(Aeq.length!=0){
            MyUtil.printArray(Aeq[0]);
        }
        else{
            System.out.print("Aeq is null!");
        }
        if(beq.length!=0){
            MyUtil.printArray(beq[0]);
        }
        else{
            System.out.print("beq is null!");
        }
        this.c=c;
        this.Aeq=Aeq;
        this.Aleq=Aleq;
        this.bleq=bleq;
        this.beq=beq;
        this.lindex=lindex;
        this.nindex=nindex;
    }

    @Contract(pure = true)
    public ToStandard(Integer[][] c, Integer[][] aeq, Integer[][] beq, Integer[][] aleq, Integer[][] bleq, Integer[][] lindex, Integer[][] nindex) {
        this.c = c;
        Aeq = aeq;
        this.beq = beq;
        Aleq = aleq;
        this.bleq = bleq;
        this.lindex = lindex;
        this.nindex = nindex;
    }
    public TwoStage toStamdard(){
        if(lindex!=null){
            deallindex();
        }

        Matrix A=generateA();
        Fraction[] c=generateC();
        Fraction[] b=generateB();

        Vector<Integer> llindex=new Vector<Integer>();
        for(int i=0;i<b.length;++i){
            if(b[i].isSmallerThanZero()){
                llindex.add(i);
            }
        }
        for(int i=0;i<llindex.size();++i){
            for(int j=0;j<A.fractions[0].length;++j){
                A.fractions[llindex.get(i)][j]=A.fractions[llindex.get(i)][j].getNeg();
            }
            b[llindex.get(i)]=b[llindex.get(i)].getNeg();
        }
        Integer[] nnindex;
        TwoStage result=new TwoStage(A,b,c,nindex);
        return  result;
    }

    private Fraction[] generateB() {
        Fraction[] result=new Fraction[beq.length+bleq.length];
        if(beq!=null) {
            for (int i = 0; i < beq.length; ++i) {
                result[i] = MyUtil.IntegerToFraction(beq[i][0]);
            }
        }
        if(bleq!=null) {
            for (int i = 0; i < bleq.length; ++i) {
                result[i + beq.length] = MyUtil.IntegerToFraction(bleq[i][0]);
            }
        }
        return result;
    }

    private Fraction[] generateC() {
        int AleqSize=Aleq.length;
        int AeqSize=Aeq.length;
        int nindexSize=0;
        int volSize=0;
        if(nindex.length!=0) {
            nindexSize = nindex[0].length;
        }
        if(Aleq.length!=0){
            volSize=Aleq[0].length;
        }
        else if(Aeq!=null){
            volSize=Aeq[0].length;
        }
        else{
            System.out.print("wtf?");
            System.exit(4);
        }
        Fraction[] result=new Fraction[volSize+nindexSize+AleqSize];
        for(int i=0;i<volSize+nindexSize+AleqSize;++i){
            result[i]=MyUtil.IntegerToFraction(0);
        }
        for(int i=0;i<volSize;++i){
            result[i]=MyUtil.IntegerToFraction(c[0][i]);
        }
        for(int i=0;i<nindexSize;++i){
            result[volSize+AleqSize+i]=result[nindex[0][i]-1].getNeg();
        }
        return result;
    }
    public void getToStandardMessage(){
        System.out.print(" ");
    }
    private Matrix generateA() {
        int AleqSize=0;
        int AeqSize=0;
        if(Aleq!=null) {
            AleqSize = Aleq.length;
        }
        if(Aeq!=null) {
            AeqSize = Aeq.length;
        }
        int nindexSize=0;
        int volSize=0;
        if(nindex!=null&&nindex.length!=0) {
            nindexSize = nindex[0].length;
        }
        if(Aleq.length!=0){
            volSize=Aleq[0].length;
        }
        else if(Aeq.length!=0){
            volSize=Aeq[0].length;
        }
        else{
            System.out.print("wtf?");
            System.exit(4);
        }
        Matrix result=new Matrix(AeqSize+AleqSize,volSize+nindexSize+AleqSize);
        for(int i=0;i<AeqSize;++i){
            for(int j=0;j<Aeq[0].length;++j){
                result.fractions[i][j]=MyUtil.IntegerToFraction(Aeq[i][j]);
            }
        }
        for(int i=0;i<AleqSize;++i){
            for(int j=0;j<Aleq[0].length;++j){
                result.fractions[AeqSize+i][j]=MyUtil.IntegerToFraction(Aleq[i][j]);
            }
            result.fractions[AeqSize+i][volSize+i]=MyUtil.IntegerToFraction(1);
        }
        for(int i=0;i<nindexSize;++i){
            for(int j=0;j<AeqSize+AleqSize;++j){
                result.fractions[j][i+volSize+AleqSize]=result.fractions[j][nindex[0][i]-1].getNeg();
            }
        }
        return result;
    }

    private void dealnindex() {
        if(nindex.length!=1){
            return;
        }
        int size=nindex[0].length;
        for(int i=0;i<size;++i){

        }
    }

    private void deallindex() {
        if(lindex.length==0){
            return;
        }
        if(lindex.length != 1){
            return;
        }
        int size=lindex[0].length;
        for(int i=0;i<size;++i){
            c[0][lindex[0][i]-1]*=-1;
            if(Aeq.length!=0){
                for(int j=0;j<Aeq.length;++j){
                    Aeq[j][lindex[0][i]-1]*=-1;
                }
            }
            if(Aleq.length!=0){
                for(int j=0;j<Aleq.length;++j){
                    Aleq[j][lindex[0][i]-1]*=-1;
                }
            }
        }
    }
}
