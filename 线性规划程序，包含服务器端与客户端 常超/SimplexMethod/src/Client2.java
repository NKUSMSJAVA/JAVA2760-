import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;
class SendAndRecieve2{
    static void send(Socket socket,PrintStream printStream,String string){
        printStream.println(string);
    }
    static String recieve(Socket socket,BufferedReader bufferedReader) throws IOException {
        String str = bufferedReader.readLine();
        return str;
    }
}

public class Client2 {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("192.168.1.8", 8000);
        PrintStream printStream = new PrintStream(socket.getOutputStream());
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        String string;
        while(true){
            string=SendAndRecieve2.recieve(socket,bufferedReader);
            System.out.println(string);
            if(string.equals("begin")){
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
        string=scanner.nextLine();
        String string1;
        while (!string.equals("end")) {
            SendAndRecieve2.send(socket,printStream,string);
            System.out.println(string=1+SendAndRecieve2.recieve(socket,bufferedReader));
            if (string.equals(1+"输入完毕！")) {
                System.out.println("成功跳出！");
                break;
            }
            System.out.println(string1=2+SendAndRecieve2.recieve(socket,bufferedReader));
            if (string1.equals(2+"输入完毕！")) {
                System.out.println("成功跳出！");
                break;
            }
            string=scanner.nextLine();
            SendAndRecieve2.send(socket,printStream,string);
            System.out.println(3+SendAndRecieve2.recieve(socket,bufferedReader));
            System.out.println(4+SendAndRecieve2.recieve(socket,bufferedReader));
            string=scanner.nextLine();
            SendAndRecieve2.send(socket,printStream,string);
            System.out.println(5+SendAndRecieve2.recieve(socket,bufferedReader));
            string=scanner.nextLine();
        }
        while(true) {
            //System.out.println("555");
            string = SendAndRecieve2.recieve(socket, bufferedReader);
            System.out.println(string);
            if (string.equals("end!")) {
                break;
            }
        }
    }
}
