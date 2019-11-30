package Server;

import com.Dao.MessageDeal;
import com.database_link.PersonalSql;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;


public  class StartServer   implements Runnable {
    private String user_id;
    private int friends_port;
    private String friends_id;
    private int port_id;
    private Socket socket;
    private ServerSocket server;
    private BufferedReader reader;
    private JPanel jp记录 = new JPanel();

    public StartServer(String user_id, int port_id) {
        this.port_id = port_id;
        this.user_id = user_id;
    }

    public void setMessageJpanel(JPanel jp记录) {
        this.jp记录 = jp记录;
    }

    public JPanel getMessageJpanel() {
        return jp记录;
    }

    public void setFriends_id(String friends_id) {
        this.friends_id = friends_id;
    }

    public String getFriends_id() {
        return friends_id;
    }


    @Override
    public void run() {
        try {
            getserver();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void getserver() {
        try {
            server = new ServerSocket(port_id);
            System.out.println("服务器套接字已创建");
            while (true) {
                System.out.println("等待客户机连接...");
                socket = server.accept();
                reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                getMessage(friends_id, getMessageJpanel());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void getMessage(String friends_id, JPanel jPanel) {//int friends_port){
        //  this.friends_port = friends_port;
        this.friends_id = friends_id;
        try {
            while (true) {
                String message = "";
                while ((message = reader.readLine()) != null) {
                    message = new MessageDeal().producegetMessage(message);
                    System.out.println("message:" + message);
                    JLabel jl内容 = new JLabel(message);
                    jl内容.setFont(new Font("微软雅黑", Font.BOLD, 15));
                    jl内容.setBorder(BorderFactory.createLoweredSoftBevelBorder());
                    JPanel jp实时消息 = new JPanel(new BorderLayout(5, 0));

                    ImageIcon img小头像 = new ImageIcon(new PersonalSql(getFriends_id()).getMyicon() + "");
                    img小头像.setImage(img小头像.getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT));
                    JLabel jl头像 = new JLabel(img小头像);

                    Date now = new Date();
                    SimpleDateFormat dateFormat = new SimpleDateFormat("MM-dd HH:mm:ss");//可以方便地修改日期格式
                    String thisTime = dateFormat.format(now);
                    JLabel jl时间 = new JLabel("                          " + thisTime);
                    jl时间.setFont(new Font("宋体", Font.PLAIN, 20));
                    jp实时消息.add(BorderLayout.NORTH, jl时间);
                    jp实时消息.add(BorderLayout.WEST, jl头像);
                    jp实时消息.add(BorderLayout.CENTER, jl内容);
                    jp实时消息.add(BorderLayout.EAST, new JLabel("                  "));
                    jl内容.setBackground(Color.pink);
                    jPanel.add(jp实时消息);
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            if (reader != null) {
                reader.close();
            }
            if (socket != null) {
                socket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

