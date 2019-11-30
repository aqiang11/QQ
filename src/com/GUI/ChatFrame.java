package com.GUI;

import Server.ChatServerSocket;
import Server.StartServer;
import com.Dao.MessageDeal;
import com.database_link.PersonalSql;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ChatFrame extends  JFrame {
    private String user_id;
    private String Friend_id;
    private  Color color=Color.white;
    private  String myicon;
    private  String friend_icon;
    private  int port_id;
    private  int friendsPort_id;
    private StartServer startServer;
    private  JSplitPane jsp左=new JSplitPane();
    private  JPanel jp右=new JPanel();
    private  JPanel jp左上=new JPanel();
    private  JPanel jp左下=new JPanel();
    private  JSplitPane jsp总体=new JSplitPane();
    private JScrollPane jsc记录=new JScrollPane();
    private  JTextArea jt记录=new JTextArea(50,50);
    private  JTextArea jt消息=new JTextArea(30,50);
    private JButton jB发送=new JButton("发送");
    private  JButton jB发送文件=new JButton("选择文件");
    private JButton jB取消=new JButton("清空");

    private  JLabel jLabel好友头像=new JLabel();
    private  JLabel jLabel好友账号=new JLabel();
    private  JLabel jLabel好友生日=new JLabel();
    private  JLabel jLabel好友年龄=new JLabel();
    private  JLabel jLabel好友个性签名=new JLabel();
    private  JLabel jLabel好友网名=new JLabel();

    private PrintWriter writer;
    private BufferedReader reader;
    private  Socket socket;
    private ServerSocket server;
    private final String ipAdress="127.0.0.1";

    public  ChatFrame(String user_id ,String Friend_id,StartServer startServer){
        Container c=this.getContentPane();
        this.Friend_id= Friend_id;
        this.user_id=user_id;
        this.startServer=startServer;
        this.port_id=new PersonalSql(this.user_id).getPort_id();

        PersonalSql persql= new PersonalSql(Friend_id);

        this.friendsPort_id=persql.getPort_id();
        this.friend_icon=new PersonalSql(Friend_id).getMyicon();
        this.myicon=new PersonalSql(user_id).getMyicon();
        ImageIcon img小头像 =new ImageIcon(myicon);
        img小头像.setImage(img小头像.getImage().getScaledInstance(40,40,Image.SCALE_DEFAULT));

        jsp总体.setOrientation(JSplitPane.HORIZONTAL_SPLIT);
        jsp总体.setDividerLocation(700);
        jsp总体.setLeftComponent(jsp左);
        jsp总体.setRightComponent(jp右);

        jsp左.setOrientation(JSplitPane.VERTICAL_SPLIT);
        jsp左.setDividerLocation(400);
        jsp左.setTopComponent(jsc记录);
        jsp左.setBottomComponent(jp左下);
        jt记录.setBorder(BorderFactory.createLineBorder(Color.black));
        jsc记录.setViewportView(jt记录);
        jsc记录.setViewportView(jp左上);
        jp左上.setLayout(new GridLayout(100,1));

        jt消息.setBounds(10,10,500,400);
        jt消息.setBorder(BorderFactory.createLineBorder(Color.black));
        jB发送文件.setBounds(550,100,100,40);
        jB发送.setBounds(550,200,100,40);
        jB取消.setBounds(550,300,100,40);

        jp左下.setLayout(null);
        jp左下.add(jt消息);

        jp左下.add(jB发送文件);
        jp左下.add(jB发送);
        jp左下.add(jB取消);

        jp右.setLayout(null);
        jLabel好友个性签名.setText("个性签名："+persql.getSign_name());
        jLabel好友年龄.setText("年龄:"+persql.getAge());
        jLabel好友生日.setText("生日:"+persql.getBirthday());
        jLabel好友账号.setText("账号："+Friend_id);
        jLabel好友网名.setText("网名："+persql.getNikneme());
        ImageIcon img=null;
        if(persql.getMyicon()==null)
           img =new ImageIcon("src/com/img/login.png");
        else
            img=new ImageIcon(persql.getMyicon()+"");

        img.setImage(img.getImage().getScaledInstance(300,260,Image.SCALE_DEFAULT));
        jLabel好友头像.setIcon(img);

        jLabel好友头像.setBounds(0,0,300,260);
        jLabel好友头像.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        jLabel好友账号.setBounds(0,260,300,40);
        jLabel好友账号.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        jLabel好友账号.setFont(new Font("宋体",Font.BOLD,20));

        jLabel好友网名.setBounds(0,300,300,40);
        jLabel好友网名.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        jLabel好友网名.setFont(new Font("宋体",Font.BOLD,20));

        jLabel好友个性签名.setBounds(0,340,300,40);
        jLabel好友个性签名.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        jLabel好友个性签名.setFont(new Font("宋体",Font.BOLD,20));

        jLabel好友生日.setBounds(0,380,175,40);
        jLabel好友生日.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        jLabel好友生日.setFont(new Font("宋体",Font.BOLD,20));

        jLabel好友年龄.setBounds(175,380,125,40);
        jLabel好友年龄.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        jLabel好友年龄.setFont(new Font("宋体",Font.BOLD,20));

        jp右.add(jLabel好友个性签名);
        jp右.add(jLabel好友账号);
        jp右.add(jLabel好友头像);
        jp右.add(jLabel好友年龄);
        jp右.add(jLabel好友生日);
        jp右.add(jLabel好友网名);

        jB取消.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                jt消息.setText("");
            }
        });

        startServer.setMessageJpanel(jp左上);
        startServer.setFriends_id(Friend_id);

        connect();

        jB发送.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                Date now = new Date();
                SimpleDateFormat dateFormat = new SimpleDateFormat("MM-dd HH:mm:ss");//可以方便地修改日期格式
                String thisTime = dateFormat.format( now );
                JLabel jl时间=new JLabel("                          "+thisTime);
                JLabel jl头像=new JLabel(img小头像);
                jl时间.setFont(new Font("宋体",Font.PLAIN,20));

                System.out.println(jt消息.getText());
                String message=new MessageDeal().getsentMessage(jt消息.getText());

                writer.print(message+"\n");

                writer.flush();


                JLabel jl内容=new JLabel(new MessageDeal().getLocalstring(jt消息.getText()));    //把本地要发送的消息处理后返回到本地记录
                jl内容.setFont(new Font("微软雅黑",Font.BOLD,15));
                jl内容.setBorder(BorderFactory.createLoweredSoftBevelBorder());
                JPanel jp实时消息=new JPanel(new BorderLayout(5,0));
                jp实时消息.add(BorderLayout.NORTH,jl时间);
                jp实时消息.add(BorderLayout.EAST,jl头像);
                jp实时消息.add(BorderLayout.CENTER,jl内容);
                jp实时消息.add(BorderLayout.WEST,new JLabel("                  "));
                jl内容.setBackground( Color.pink);
                jp左上.add(jp实时消息);

                jt消息.setText("");
            }});

        c.add(BorderLayout.CENTER,jsp总体);
        setBounds(300,20,1000,800);
        setTitle("talking with："+Friend_id);
        setVisible(true);
        setDefaultCloseOperation(2);
        setLayout(new BorderLayout());
        setResizable(false);
    }

    public   void connect(){
        System.out.println("尝试连接");
        try{
            socket=new Socket(ipAdress,friendsPort_id);
            writer =new PrintWriter(socket.getOutputStream(),true);
            System.out.println("安全连接");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public  static void  main(String[] args){
        StartServer startServer=new StartServer("17130120",6001);
        startServer.run();
         new ChatFrame("11111","11111",startServer) ;
    }
}
