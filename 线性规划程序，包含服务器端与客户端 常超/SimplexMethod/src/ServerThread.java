import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Vector;

public class ServerThread implements Runnable {
    private Socket serverThread;

    public ServerThread(Socket serverThread) {
        this.serverThread = serverThread;
    }
    public void getBack(String str,PrintStream out){
        boolean flag=true;
        if(str == null || "".equals(str)){
            flag = false;
        }
        else{
            if("-1".equals(str)){
                flag = false;
            }else{
                System.out.println(str);
                out.println("已接收:" + str);
            }
        }
        out.flush();
    }

    @Override
    public void run() {
        try {
            Integer c[][]=null;
            Integer Aeq[][]=new Integer[0][0];
            Integer beq[][]=new Integer[0][0];
            Integer Aleq[][]=new Integer[0][0];
            Integer bleq[][]=new Integer[0][0];
            Integer lindex[][]=new Integer[0][0];
            Integer nindex[][]=new Integer[0][0];
            PrintStream out = new PrintStream(serverThread.getOutputStream(),true);
            out.println("Welcome to my server!本产品致力于线性规划的计算，首先请您下载我们的白度客户端，并打开用户吉利计划，方便我们的分享，哦不，您的分享.输入项目内容为：\n1.c，即需要极小化的系数\n2.Aeq，即等于等式约束部分的系数矩阵\n3.beq，即等式约束部分的约束结果\n4.Aleq，即不等式部分小于的系数矩阵\n5.bleq，不等式小于的约束结果\n6.小于等于0变量指标集\n7.无约束指标集\n-1:退出输入\n输入对应的数字即可进行对应项目的输入。输入默认为矩阵形式，需要先输入矩阵的行数和列数，再输入矩阵。矩阵输入格式任意，只要输入足量整数即可。当然，想输入分数，请升级为VIP，即可享受该服务与1KB/s高速数据传输。白度客户端，想你所想！若是“侵犯客户隐私”，banpownload引以为戒！\n请输入项目：\nbegin");

            //获取Socket的输入流，用来接收从客户端发送过来的数据

            //System.out.println("111");
            //System.out.println("222");
            while(true){
                //接收从客户端发送过来的数据
                //System.out.println("333");
                BufferedReader buf = new BufferedReader(new InputStreamReader(serverThread.getInputStream()));
                String str =  buf.readLine();
                getBack(str,out);
                if (str.equals("-1")) {
                    out.println("输入完毕！");
                    break;
                }
                else if(str.equals("1")){
                    out.println("输入待输入对象的行数和列数:");
                    String rowAndVolum=buf.readLine();
                    getBack(rowAndVolum,out);
                    String[] rv=rowAndVolum.split(" ");
                    Integer row = Integer.parseInt(rv[0]);
                    System.out.println("row="+row);
                    Integer vol = Integer.parseInt(rv[1]);
                    System.out.println("vol="+vol);
                    out.println("请输入矩阵：");
                    String matrix=buf.readLine();
                    getBack(matrix,out);
                    String[] matrixEle = matrix.split(" ");
                    for(int i=0;i<matrixEle.length;++i){
                        System.out.print(matrixEle[i]+" ");
                    }
                    int k=0;
                    c = new Integer[row][vol];
                    for (int i = 0; i < row; ++i) {
                        for (int j = 0; j < vol; ++j) {
                            c[i][j] = Integer.parseInt(matrixEle[k]);
                            ++k;
                        }
                    }
                    System.out.println("yes!");
                }
                else if(str.equals("2")){
                    out.println("输入待输入对象的行数和列数:");
                    String rowAndVolum=buf.readLine();
                    getBack(rowAndVolum,out);
                    String[] rv=rowAndVolum.split(" ");
                    Integer row = Integer.parseInt(rv[0]);
                    System.out.println("row="+row);
                    Integer vol = Integer.parseInt(rv[1]);
                    System.out.println("vol="+vol);
                    out.println("请输入矩阵：");
                    String matrix=buf.readLine();
                    getBack(matrix,out);
                    String[] matrixEle = matrix.split(" ");
                    for(int i=0;i<matrixEle.length;++i){
                        System.out.print(matrixEle[i]+" ");
                    }
                    int k=0;
                    Aeq = new Integer[row][vol];
                    for (int i = 0; i < row; ++i) {
                        for (int j = 0; j < vol; ++j) {
                            Aeq[i][j] = Integer.parseInt(matrixEle[k]);
                            ++k;
                        }
                    }
                    System.out.println("yes!");
                }
                else if(str.equals("3")){
                    out.println("输入待输入对象的行数和列数:");
                    String rowAndVolum=buf.readLine();
                    getBack(rowAndVolum,out);
                    String[] rv=rowAndVolum.split(" ");
                    Integer row = Integer.parseInt(rv[0]);
                    System.out.println("row="+row);
                    Integer vol = Integer.parseInt(rv[1]);
                    System.out.println("vol="+vol);
                    out.println("请输入矩阵：");
                    String matrix=buf.readLine();
                    getBack(matrix,out);
                    String[] matrixEle = matrix.split(" ");
                    for(int i=0;i<matrixEle.length;++i){
                        System.out.print(matrixEle[i]+" ");
                    }
                    int k=0;
                    beq = new Integer[row][vol];
                    for (int i = 0; i < row; ++i) {
                        for (int j = 0; j < vol; ++j) {
                            beq[i][j] = Integer.parseInt(matrixEle[k]);
                            ++k;
                        }
                    }
                    System.out.println("yes!");
                }
                else if(str.equals("4")){
                    out.println("输入待输入对象的行数和列数:");
                    String rowAndVolum=buf.readLine();
                    getBack(rowAndVolum,out);
                    String[] rv=rowAndVolum.split(" ");
                    Integer row = Integer.parseInt(rv[0]);
                    System.out.println("row="+row);
                    Integer vol = Integer.parseInt(rv[1]);
                    System.out.println("vol="+vol);
                    out.println("请输入矩阵：");
                    String matrix=buf.readLine();
                    getBack(matrix,out);
                    String[] matrixEle = matrix.split(" ");
                    for(int i=0;i<matrixEle.length;++i){
                        System.out.print(matrixEle[i]+" ");
                    }
                    int k=0;
                    Aleq = new Integer[row][vol];
                    for (int i = 0; i < row; ++i) {
                        for (int j = 0; j < vol; ++j) {
                            Aleq[i][j] = Integer.parseInt(matrixEle[k]);
                            ++k;
                        }
                    }
                    System.out.println("yes!");
                }
                else if(str.equals("5")){
                    out.println("输入待输入对象的行数和列数:");
                    String rowAndVolum=buf.readLine();
                    getBack(rowAndVolum,out);
                    String[] rv=rowAndVolum.split(" ");
                    Integer row = Integer.parseInt(rv[0]);
                    System.out.println("row="+row);
                    Integer vol = Integer.parseInt(rv[1]);
                    System.out.println("vol="+vol);
                    out.println("请输入矩阵：");
                    String matrix=buf.readLine();
                    getBack(matrix,out);
                    String[] matrixEle = matrix.split(" ");
                    for(int i=0;i<matrixEle.length;++i){
                        System.out.print(matrixEle[i]+" ");
                    }
                    int k=0;
                    bleq = new Integer[row][vol];
                    for (int i = 0; i < row; ++i) {
                        for (int j = 0; j < vol; ++j) {
                            bleq[i][j] = Integer.parseInt(matrixEle[k]);
                            ++k;
                        }
                    }
                    System.out.println("yes!");
                }
                else if(str.equals("6")){
                    out.println("输入待输入对象的行数和列数:");
                    String rowAndVolum=buf.readLine();
                    getBack(rowAndVolum,out);
                    String[] rv=rowAndVolum.split(" ");
                    Integer row = Integer.parseInt(rv[0]);
                    System.out.println("row="+row);
                    Integer vol = Integer.parseInt(rv[1]);
                    System.out.println("vol="+vol);
                    out.println("请输入矩阵：");
                    String matrix=buf.readLine();
                    getBack(matrix,out);
                    String[] matrixEle = matrix.split(" ");
                    for(int i=0;i<matrixEle.length;++i){
                        System.out.print(matrixEle[i]+" ");
                    }
                    int k=0;
                    lindex = new Integer[row][vol];
                    for (int i = 0; i < row; ++i) {
                        for (int j = 0; j < vol; ++j) {
                            lindex[i][j] = Integer.parseInt(matrixEle[k]);
                            ++k;
                        }
                    }
                    System.out.println("yes!");
                }
                else if(str.equals("7")){
                    out.println("输入待输入对象的行数和列数:");
                    String rowAndVolum=buf.readLine();
                    getBack(rowAndVolum,out);
                    String[] rv=rowAndVolum.split(" ");
                    Integer row = Integer.parseInt(rv[0]);
                    System.out.println("row="+row);
                    Integer vol = Integer.parseInt(rv[1]);
                    System.out.println("vol="+vol);
                    out.println("请输入矩阵：");
                    String matrix=buf.readLine();
                    getBack(matrix,out);
                    String[] matrixEle = matrix.split(" ");
                    for(int i=0;i<matrixEle.length;++i){
                        System.out.print(matrixEle[i]+" ");
                    }
                    int k=0;
                    nindex = new Integer[row][vol];
                    for (int i = 0; i < row; ++i) {
                        for (int j = 0; j < vol; ++j) {
                            nindex[i][j] = Integer.parseInt(matrixEle[k]);
                            ++k;
                        }
                    }
                    System.out.println("yes!");
                }

                //System.out.println("444");

            }
            out.println("结果为：");
            Vector<String> result=TwoStage.compute(c,Aeq,beq,Aleq,bleq,lindex,nindex);
            for(int i=0;i<result.size();++i){
                System.out.println(result.get(i));
                out.println(result.get(i));
            }
            out.println("end!");
            serverThread.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
