import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class ServerExcutor {
    public static void main(String[] args) throws IOException {
        ServerSocket server = new ServerSocket(8000);
        Socket client = null;
        Executor service = Executors.newCachedThreadPool();
        boolean f = true;
        while(f){
            client = server.accept();
            System.out.println("与客户端连接成功！");
            //调用execute()方法时，如果必要，会创建一个新的线程来处理任务，但它首先会尝试使用已有的线程，
            //如果一个线程空闲60秒以上，则将其移除线程池；
            //另外，任务是在Executor的内部排队，而不是在网络中排队
            service.execute(new ServerThread(client));
        }
        server.close();
    }
}
