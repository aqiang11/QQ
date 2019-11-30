package Server;

import java.io.*;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class ChatServerSocket {
    private ServerSocket server;
    private Socket socket;
    private BufferedReader reader;
    private  int port_id;
    private  String user_id;
    public ChatServerSocket(String user_id, int port_id){
        this.user_id=user_id;
        this.port_id=port_id;
    }
    public  void getserver(){
        try{
            server=new ServerSocket(port_id);
            System.out.println("服务器套接字已创建");
            while (true){
                System.out.println("等待客户机连接...");
                socket=server.accept();
                reader=new BufferedReader(new InputStreamReader(socket.getInputStream()));
                getClientMessage();
                System.out.println("aaaaaaaaaaaaaaa");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public  void getClientMessage(){
        try{
            while (true){
                System.out.println("客户机："+reader.readLine());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        try{
            if(reader!=null){
                reader.close();
            }
            if(socket!=null){
                socket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public  static  void main(String[] args){
        ChatServerSocket csk=new ChatServerSocket("17130120",6606);
        csk.getserver();
    }
}
