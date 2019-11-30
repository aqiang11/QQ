package com.actionadd;

import com.GUI.LoginFrame;
import com.GUI.MainFrame;
import com.process.LogAccount_Process;

import javax.swing.*;
import java.awt.event.*;
import java.sql.SQLException;
public class LoginAction implements MouseListener,KeyListener{
    private JTextField jT1;
    private JPasswordField jT2;
    private LoginFrame login;
    private String  Account_number;
    public LoginAction(JTextField jT1, JPasswordField jT2,LoginFrame login) {
        this.jT1=jT1;
        this.jT2=jT2;
        this.login=login;
    }
    @Override
    public void mouseClicked(MouseEvent mouseEvent) {
        try {
            int flag=-1;
             flag= new  LogAccount_Process(jT1,jT2).getResult();
             Account_number=jT1.getText();
             if(flag==-1){
                 JOptionPane.showInternalMessageDialog(null, "输入错误", "提示", JOptionPane.INFORMATION_MESSAGE);
             }
             else if (flag==0){
                JOptionPane.showInternalMessageDialog(null, "账号不存在", "提示", JOptionPane.INFORMATION_MESSAGE);
            }
             else  if(flag==1){
                 JOptionPane.showInternalMessageDialog(null, "密码错误", "提示", JOptionPane.INFORMATION_MESSAGE);
             }
             else{
                 login.dispose();
                 MainFrame mf=new MainFrame(jT1.getText());
             }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void mousePressed(MouseEvent mouseEvent) { }

    @Override
    public void mouseReleased(MouseEvent mouseEvent) { }

    @Override
    public void mouseEntered(MouseEvent mouseEvent) { }

    @Override
    public void mouseExited(MouseEvent mouseEvent) { }

    @Override
    public void keyTyped(KeyEvent keyEvent) {

    }

    @Override
    public void keyPressed(KeyEvent keyEvent) {
        if(keyEvent.getKeyChar()=='\n'){
            try {
                int flag=-1;
                flag= new  LogAccount_Process(jT1,jT2).getResult();
                Account_number=jT1.getText();
                if(flag==-1){
                    JOptionPane.showInternalMessageDialog(null, "输入错误", "提示", JOptionPane.INFORMATION_MESSAGE);
                }
                else if (flag==0){
                    JOptionPane.showInternalMessageDialog(null, "账号不存在", "提示", JOptionPane.INFORMATION_MESSAGE);
                }
                else  if(flag==1){
                    JOptionPane.showInternalMessageDialog(null, "密码错误", "提示", JOptionPane.INFORMATION_MESSAGE);
                }
                else{
                    login.dispose();
                    MainFrame mf=new MainFrame(Account_number);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

        public void keyReleased(KeyEvent keyEvent) {

    }
}

