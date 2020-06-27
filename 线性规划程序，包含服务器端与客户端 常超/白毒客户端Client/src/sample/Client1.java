package sample;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

class SendAndRecieve{
    static void send(Socket socket,PrintStream printStream,String string){
        printStream.println(string);
    }
    static String recieve(Socket socket,BufferedReader bufferedReader) throws IOException {
        String str = bufferedReader.readLine();
        return str;
    }
}

public class Client1 {
    public static String useSocket(String cNum, String c, String aeqNum, String aeq, String beqNum, String beq, String aleqNum, String aleq, String bleqNum, String bleq, String lindexNum, String lindex, String nindexNum, String nindex) throws IOException {
        Socket socket = new Socket("192.168.1.8", 8000);
        PrintStream printStream = new PrintStream(socket.getOutputStream());
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        String[] projectIndex = {"1", "2", "3", "4", "5", "6", "7", "-1"};
        String[] NumIndex = {cNum, aeqNum, beqNum, aleqNum, bleqNum, lindexNum, nindexNum};
        String[] MatrixStrs = {c, aeq, beq, aleq, bleq, lindex, nindex};
        String string;
        while (true) {
            string = SendAndRecieve.recieve(socket, bufferedReader);
            System.out.println(string);
            if (string.equals("begin")) {
                break;
            }
            //string=bufferedReader.readLine();
            //System.out.println(string);
            //System.out.println("000");
            //if(string.equals("begin")||string==null){//这儿很小的问题，==和equals是不一样的，调了半天突然想到。。
            //System.out.println("111");
            //break;

            //}
        }
        Scanner scanner = new Scanner(System.in);
        int index = 0;
        string = projectIndex[0];
        String string1;
        while (!string.equals("end")) {
            SendAndRecieve.send(socket, printStream, string);
            System.out.println(string = 1 + SendAndRecieve.recieve(socket, bufferedReader));
            if (string.equals(1 + "输入完毕！")) {
                System.out.println("成功跳出！");
                break;
            }
            System.out.println(string1 = 2 + SendAndRecieve.recieve(socket, bufferedReader));
            if (string1.equals(2 + "输入完毕！")) {
                System.out.println("成功跳出！");
                break;
            }
            string = NumIndex[index];
            SendAndRecieve.send(socket, printStream, string);
            System.out.println(3 + SendAndRecieve.recieve(socket, bufferedReader));
            System.out.println(4 + SendAndRecieve.recieve(socket, bufferedReader));
            string = MatrixStrs[index];
            SendAndRecieve.send(socket, printStream, string);
            System.out.println(5 + SendAndRecieve.recieve(socket, bufferedReader));
            string = projectIndex[++index];
            while(NumIndex[index].equals("Defaulte")){
                string=projectIndex[++index];
                if(string.equals("-1")){
                    break;
                }
            }
        }
        String result = " ";
        while (true) {
            //System.out.println("555");
            string = SendAndRecieve.recieve(socket, bufferedReader);
            result = result + string + " ";
            System.out.println(string);
            if (string.equals("end!")) {
                break;
            }

        }
        return result;
    }
}
