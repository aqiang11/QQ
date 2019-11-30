package com.GUI;

import com.actionadd.LoginAction;
import com.actionadd.RegisterAction;

import javax.swing.*;
import java.awt.*;

public class LoginFrame  extends JFrame {
    private JLabel jL1=new JLabel();    //上层图片标签
    private JLabel jL2=new JLabel();    //左下的图片标签
    private JLabel jL3=new JLabel("用户账号");
    private JLabel jL4=new JLabel("用户密码");
    private JTextField jT1=new JTextField();
    private JPasswordField jT2=new JPasswordField();
    private JButton jb1=new JButton("登    录");
    private JCheckBox jc1=new JCheckBox("记住密码");
    private JCheckBox jc2=new JCheckBox("自动登录");
    private JButton jb2=new JButton("注册账号");
    private ImageIcon imagetop,imageQQ;
    public  void create(){
        Container c=getContentPane();
        c.setLayout(null);
        imagetop = new ImageIcon("src/com/img/login.png");
        imagetop.setImage(imagetop.getImage().getScaledInstance(500,200,Image.SCALE_DEFAULT));  //设置图片大小和自适应
        imageQQ =new ImageIcon("src/com/img/左下角.jpg");
        imageQQ.setImage(imageQQ.getImage().getScaledInstance(130,130,Image.SCALE_DEFAULT));
        jL1.setIcon(imagetop);
        jL1.setBounds(0,0,500,200);

        jL2.setIcon(imageQQ);
        jL2.setBounds(10,200,130,130);
        jL1.setBackground(Color.white);
        jT1.setBounds(150,200,200,30);
        jL3.setBounds(350,200,150,30);
        jT2.setBounds(150,230,200,30);
        jL4.setBounds(350,230,150,30);
        jc1.setBounds(150,260,100,30);
        jc2.setBounds(250,260,100,30);
        jb1.setBounds(150,290,200,30);
        jb2.setBounds(400,290,90,30);
        c.add(jL1);
        c.add(jL2);
        c.add(jT1);
        c.add(jT2);
        c.add(jL3);
        c.add(jL4);
        c.add(jc1);
        c.add(jc2);
        c.add(jb1);
        c.add(jb2);


        jc2.setBackground(Color.white);
        jc1.setBackground(Color.white);
        jb1.addMouseListener(new LoginAction(jT1,jT2,this));
        jb2.addMouseListener(new RegisterAction());
        jT2.addKeyListener(new LoginAction(jT1,jT2,this));

        ImageIcon imgb1 =new ImageIcon("src/com/img/loginButton.jpg");
        imgb1.setImage(imgb1.getImage().getScaledInstance(220,30,Image.SCALE_DEFAULT));
        jb1.setIcon(imgb1);
        jb1.setHorizontalTextPosition(SwingConstants.CENTER);
        jb1.setOpaque(true);

        jb2.setBorderPainted(false);
        jb2.setBackground(Color.white);


        c.setBackground(Color.white);
        setTitle("QQ");
    }
    public void frameSize(){
        setResizable(false);
        setBounds(500,300,500,360);
        setBackground(Color.white);
        setVisible(true);
        setIconImage(Toolkit.getDefaultToolkit().getImage(LoginFrame.class.getResource("/com/img/QQ.jpg")));
    }
    public void frameSize(int x,int y,int width,int heigth,boolean a,boolean b){
        setBounds(x,y,width,heigth);
        setVisible(a);
        setResizable(b);
        setBackground(Color.white); setIconImage(Toolkit.getDefaultToolkit().getImage(LoginFrame.class.getResource("/com/img/QQ.jpg")));
    }
}
  //把下层面板加入登录界面
