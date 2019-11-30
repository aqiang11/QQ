package com.GUI;

import com.process.Register_process;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class RegisterFrame extends JFrame{
     public  String username;
     public String pwd;
     public JTextField jT1=new JTextField();
     public JPasswordField jT2=new JPasswordField();
     public JLabel jL1=new JLabel("注册账号");
     public JLabel jL2=new JLabel("注册密码");
     public JButton jb1=new JButton("申请");
     public JButton jb2=new JButton("重置");
    public static boolean isNumeric(String str){
        for (int i = str.length();--i>=0;){
            if (!Character.isDigit(str.charAt(i))){
                return false;
            }
        }
        return true;
    }
     public void createReg(){
        Container c=getContentPane();
        c.setLayout(null);
        jL1.setBounds(0,0,50,30);
        jT1.setBounds(50,0,200,30);
        jL2.setBounds(0,30,50,30);
        jT2.setBounds(50,30,200,30);
        jb1.setBounds(50,90,100,30);
        jb2.setBounds(150,90,100,30);
        c.add(jL1);
        c.add(jL2);
        c.add(jT1);
        c.add(jT2);
        c.add(jb1);
        c.add(jb2);
        jb1.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                if(jT1.getText().length()<5||jT2.getPassword().length<5){
                    JOptionPane.showInternalMessageDialog(null, "输入账号或密码过短", "提示", JOptionPane.INFORMATION_MESSAGE);
                }
                else  if (!isNumeric(jT1.getText())){
                    JOptionPane.showInternalMessageDialog(null, "请输入正确格式的账号", "提示", JOptionPane.INFORMATION_MESSAGE);
                }
                else {
                    Register_process resg = new Register_process(jT1, jT2);
                    if (resg.temp == 2)
                        dispose();
                }
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
        });
        jb2.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                jT1.setText("");
                jT2.setText("");
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
        });
        setVisible(true);
        setResizable(false);
        setBounds(500,400,300,180);
        setTitle("注册账号");
        setIconImage(Toolkit.getDefaultToolkit().getImage(RegisterFrame.class.getResource("/com/img/QQ.jpg")));
    }
}
