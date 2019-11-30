package com.GUI;

import com.actionadd.ChageHeadAction;
import com.database_link.PersonalSql;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class PersonInfFrame  extends JFrame implements  MouseListener {
    private  Color color=Color.white;
    private   String nikneme;
    private   String user_id;
    private   String  OnlineState;
    private   String  myicon;
    private  String birthday;
    private  String age;
    private  String sign_name;
    private  int  port_id;
    private  JLabel jLabel头像=new JLabel();
    private  JLabel jLabel账号=new JLabel();
    private  JLabel jLabel生日=new JLabel();
    private  JLabel jLabel年龄=new JLabel();
    private  JLabel jLabel个性签名=new JLabel();
    private  JButton  jb编辑=new JButton("编  辑");
    private  JButton jb更换头像=new JButton(" 更换头像 ");
    PersonalSql persql=null;

    public  PersonInfFrame(String user_id){

        this.user_id=user_id;
        persql=new PersonalSql(user_id);
        nikneme=persql.getNikneme();
        OnlineState=persql.getOnlineState();
        myicon=persql.getMyicon();
        birthday=persql.getBirthday();
        age=persql.getAge();
        sign_name=persql.getSign_name();
        port_id=persql.getPort_id();
        Container c=getContentPane();
        ImageIcon img头像 =new ImageIcon(myicon+"");
        img头像.setImage(img头像.getImage().getScaledInstance(120,100,Image.SCALE_DEFAULT));
        jLabel头像.setIcon(img头像);
        jLabel头像.setBounds(10,10,120,100);

        String text="<html>" + nikneme +"  ("+user_id+ ")"+"<br/>" + OnlineState + " ("+"端口:"+String.valueOf(port_id)+")" +"<html/>";
        jLabel账号.setText(text);
        jLabel账号.setFont(new Font("宋体",Font.BOLD,20));
        jLabel账号.setBounds(140,10,290,100);

        jLabel个性签名.setText(" 个性签名 :"+sign_name);
        jLabel个性签名.setBounds(0,110,400,60);
        jLabel个性签名.setFont(new Font("宋体",Font.BOLD,20));
        jLabel个性签名.setBorder(BorderFactory.createLineBorder(Color.black,1,true));

        jLabel年龄.setText(" 年龄:"+age);
        jLabel年龄.setBounds(0,170,200,60);
        jLabel年龄.setFont(new Font("宋体",Font.BOLD,20));
        jLabel年龄.setBorder(BorderFactory.createLineBorder(Color.black,1,true));

        jLabel生日.setText(" 生日:"+birthday);
        jLabel生日.setBounds(200,170,200,60);
        jLabel生日.setFont(new Font("宋体",Font.BOLD,20));
        jLabel生日.setBorder(BorderFactory.createLineBorder(Color.black,1,true));

       jb编辑.setBorder(BorderFactory.createLineBorder(color,0));
       jb编辑.setFont(new Font("宋体",Font.BOLD,15));
       jb编辑.setBackground(color);
       jb编辑.setBounds(20,250,100,40);
       jb编辑.addMouseListener(new EditinfFrame(user_id));
       jb更换头像.addMouseListener(new ChageHeadAction(user_id));

        jb更换头像.setBorder(BorderFactory.createLineBorder(color,0));
        jb更换头像.setFont(new Font("宋体",Font.BOLD,15));
        jb更换头像.setBounds(220,250,100,40);;
        jb更换头像.setBackground(color);

        c.add(jLabel头像);
        c.add(jLabel账号);
        c.add(jLabel个性签名);
        c.add(jLabel年龄);
        c.add(jLabel生日);
        c.add(jb更换头像);
        c.add(jb编辑);

        c.setBackground(color);
        setLayout(null);
        setDefaultCloseOperation(2);
        setVisible(true);
        setBounds(500,200,400,350);
        setIconImage(Toolkit.getDefaultToolkit().getImage(MainFrame.class.getResource("/com/img/QQ.jpg")));
        setTitle("个人资料");
        setResizable(false);
    }

    @Override
    public void mouseClicked(MouseEvent mouseEvent) {
    }

    @Override
    public void mousePressed(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseReleased(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseEntered(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseExited(MouseEvent mouseEvent) {

    }
}
