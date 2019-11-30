package com.process;

import com.database_link.Registersql;

import javax.swing.*;
import java.sql.SQLException;

public class Register_process {
    public int temp=0;
    public Register_process(JTextField jT1,JPasswordField jT2) {
        String inputname=jT1.getText();
        String inputpwd=String.valueOf(jT2.getPassword());
        int flag = -1;
        try {
            if(inputname.equals("")||inputpwd.equals("")) {
                JOptionPane.showInternalMessageDialog(null, "输入错误", "提示", JOptionPane.INFORMATION_MESSAGE);
            } else {
                flag=new Registersql(inputname,inputpwd).getResult();
                if (flag == 1) {
                    JOptionPane.showInternalMessageDialog(null, "账号已存在", "提示", JOptionPane.INFORMATION_MESSAGE);
                }
                else if(flag==2){
                    JOptionPane.showInternalMessageDialog(null, "账号创建成功", "提示", JOptionPane.INFORMATION_MESSAGE);
                }
                else ;
                temp=flag;
            };
        } catch (
                SQLException e) {
            e.printStackTrace();
        }
    }


}
