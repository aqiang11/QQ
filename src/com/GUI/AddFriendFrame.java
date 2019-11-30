package com.GUI;

import com.database_link.PersonalSql;
import com.database_link.RelationSql;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class AddFriendFrame extends JFrame {
    private  JLabel jl账号=new JLabel("添加账号:");
    private  JLabel jl昵称=new JLabel("好友昵称:");
    private JTextField jt账号=new JTextField();
    private  JTextField jt昵称=new JTextField();
    private  JButton jb添加=new JButton("添加好友");
    private JButton jb取消=new JButton("取消");
    private String  user_id;
    private String friend_id;
    private int Group_id;
    public AddFriendFrame(String user_id ){
        this.user_id=user_id;
        Container c=getContentPane();
        jl账号.setBounds(20,10,80,30);
        jt账号.setBounds(100,10,150,30);

        jl昵称.setBounds(20,50,80,30);
        jt昵称.setBounds(100,50,150,30);
        jb添加.setBounds(40,100,100,40);
        jb取消.setBounds(160,100,100,40);


        JFrame jf=this;
        jb取消.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                jf.dispose();
            }
        });
        jb添加.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                friend_id=jt账号.getText();
                System.out.println(user_id);
                System.out.println(jt账号.getText());
                RelationSql resql = new RelationSql(user_id, jt账号.getText());
                RelationSql resqlFriends=new RelationSql(friend_id,user_id);
                if (friend_id.equals("")) {
                    JOptionPane.showInternalMessageDialog(null, "账号不能为空", "提示", JOptionPane.INFORMATION_MESSAGE);
                }else  if (resql.getFriends_nickname()!=null) {
                    JOptionPane.showInternalMessageDialog(null, "好友已在列表", "提示", JOptionPane.INFORMATION_MESSAGE);
                }
                else {
                    resql.setFriends_id(friend_id);
                    resql.setFriends_nickname(jt昵称.getText());
                    resql.setGroup_id(1);
                    resql.insert_Friends();
                    resqlFriends.setFriends_id(user_id);
                    resqlFriends.setGroup_id(1);
                    resqlFriends.setFriends_nickname(new PersonalSql(user_id).getNikneme());
                    resqlFriends.insert_Friends();
                }
            }
        });
        c.add(jl昵称);
        c.add(jl账号);
        c.add(jt昵称);
        c.add(jt账号);
        c.add(jb添加);
        c.add(jb取消);
        setLayout(null);
        setDefaultCloseOperation(2);
        setVisible(true);
        setBounds(500,300,300,200);
        setTitle("添加好友");
        setResizable(false);
    }
}
