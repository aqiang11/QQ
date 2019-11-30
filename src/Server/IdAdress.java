package Server;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class IdAdress  {
    public  static void main(String[] args)  {
        InetAddress ip;
        try{
            ip=InetAddress.getLocalHost();
            System.out.println(ip.getHostName());
            System.out.println(ip.getHostAddress());
            System.out.println(ip.getAddress().toString());

        }catch (UnknownHostException e){
            e.printStackTrace();
        }



    }

}
