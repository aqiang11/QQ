package com.GUI;

import Server.ChatServerSocket;
import Server.StartServer;
import com.Dao.LianXiRen;
import com.actionadd.HeadphotoAction;
import com.database_link.PersonalSql;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MainFrame extends JFrame {
    private  String user_id;
    private  int port;
    private  StartServer startserver;
    private Thread User_Thread;
    public MainFrame(String user_id) {
        this.user_id=user_id;
        this.port=new PersonalSql(user_id).getPort_id();
        startserver= new StartServer(user_id,port);
        Container c = getContentPane();
        JSplitPane jsp = new JSplitPane(JSplitPane.VERTICAL_SPLIT, true);
        jsp.setDividerLocation(160);
        jsp.setDividerSize(5);             //分割的宽度
        jsp.setContinuousLayout(true);   //设置调整时是否连续绘制

        JPanel jpanel上 = new JPanel();
        JPanel jPanel下 = new JPanel();

        jpanel上.setBackground(Color.white);
        jpanel上.setLayout(new GridLayout(2, 1));   //上部分两行一列
        CardLayout cardLayout下部分 = new CardLayout();
        jPanel下.setLayout(cardLayout下部分);           //下部分为卡片布局

        JPanel jpanel下面板1 = new JPanel();      //点击第一个图标出现的卡片
        JPanel jpanel下面板2 = new JPanel();    //点击第2个图标出现的卡片
        JPanel jpanel下面板3 = new JPanel();    //点击第3个图标出现的卡片

        jpanel下面板1.setLayout(new BorderLayout());
        jpanel下面板1.add(BorderLayout.CENTER, new LianXiRen(user_id, startserver).getJsc());
        JLabel Jl2_1 = new JLabel("欢迎加群");
        Jl2_1.setFont(new Font("宋体", Font.BOLD, 25));

        jpanel下面板2.add(Jl2_1);

        jPanel下.add(jpanel下面板1, "好友列表");  //new getFriendsTable()
        jPanel下.add(jpanel下面板2, "群组列表");
        jPanel下.add(jpanel下面板3, "最近列表");

        PersonalSql personalSql = new PersonalSql(user_id);  //返回登录账号的所有信息，

        JPanel jPanel头像签名 = new JPanel();
        JPanel jpanel好友群组 = new JPanel();

        GridBagLayout gb = new GridBagLayout();
        GridBagConstraints gc = new GridBagConstraints();
        jPanel头像签名.setLayout(gb);

        ImageIcon imageIcon个人头像 = new ImageIcon(personalSql.getMyicon() + "");
        JButton jb头像按钮 = new JButton();
        imageIcon个人头像.setImage(imageIcon个人头像.getImage().getScaledInstance(90, 90, Image.SCALE_DEFAULT));
        jb头像按钮.setIcon(imageIcon个人头像);
        jb头像按钮.setBackground(Color.white);
        jb头像按钮.addMouseListener(new HeadphotoAction(user_id, this));
        gc.gridx = 0;
        gc.gridy = 0;
        gc.weightx = 1;
        gc.weighty = 1;
        gc.ipadx = -30;
        gc.insets = new Insets(0, 0, 0, 0);
        jPanel头像签名.add(jb头像按钮, gc);
        String state = personalSql.getOnlineState();
        if (state.equals("online")) {
            state = "[在 线]";
        } else if (state.equals("buzy")) {
            state = "[忙 碌]";
        } else
            state = "[离 线]";

        String string个人展示 = "<html>" + personalSql.getNikneme() + "(" + user_id + ")" + "<br/>" + state + " <html/>";
        JLabel jl个人展示 = new JLabel(string个人展示);
        jl个人展示.setFont(new Font("宋体", Font.BOLD, 20));
        jl个人展示.setOpaque(true);
        jl个人展示.setBackground(Color.white);

        gc.gridx = 1;
        gc.gridy = 0;
        gc.weightx = 30;
        gc.weighty = 1;
        gc.fill = GridBagConstraints.BOTH;
        jPanel头像签名.add(jl个人展示, gc);
        jPanel头像签名.setBackground(Color.white);

        jpanel好友群组.setLayout(null);
        jpanel好友群组.setBackground(Color.white);

        ImageIcon img好友 = new ImageIcon("src/com/img/好友.PNG");
        ImageIcon img群组 = new ImageIcon("src/com/img/群组.PNG");
        ImageIcon img最近 = new ImageIcon("src/com/img/最近.PNG");

        JButton jb好友 = new JButton(img好友);
        JButton jb群组 = new JButton(img群组);
        JButton jb最近 = new JButton(img最近);
        jb好友.setMargin(new Insets(0, 0, 0, 0));
        jb群组.setMargin(new Insets(0, 0, 0, 0));
        jb最近.setMargin(new Insets(0, 0, 0, 0));
        jb好友.setBounds(0, 0, 120, 80);
        jb群组.setBounds(120, 0, 120, 80);
        jb最近.setBounds(240, 0, 120, 80);

        jb好友.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                cardLayout下部分.show(jPanel下, "好友列表");
            }
        });
        jb群组.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                cardLayout下部分.show(jPanel下, "群组列表");
            }
        });
        jb最近.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                cardLayout下部分.show(jPanel下, "最近列表");
            }
        });
        jpanel好友群组.add(jb好友);
        jpanel好友群组.add(jb群组);
        jpanel好友群组.add(jb最近);

        jpanel上.add(jPanel头像签名);
        jpanel上.add(jpanel好友群组);

        jsp.add(jpanel上);
        jsp.add(jPanel下);




        c.add(jsp, BorderLayout.CENTER);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("聊天主页面" + " (" + user_id + ")");
        setBounds(200, 100, 370, 700);
        setVisible(true);
        setIconImage(Toolkit.getDefaultToolkit().getImage(MainFrame.class.getResource("/com/img/QQ.jpg")));

        //new ChatServerSocket(user_id,port);
        User_Thread=new Thread (startserver);
        User_Thread.start();

    }
    public static void main(String[] args) {
        new MainFrame("17130120" );
            }
}
