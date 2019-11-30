package com.process;
import com.GUI.LoginFrame;
import  com.database_link.*;

import javax.swing.*;
import java.sql.Connection;
import java.sql.SQLException;

public class LogAccount_Process {
    public  int flag=-1;
    public  int getResult(){
        return  flag;
    }
    public  LogAccount_Process(JTextField jT1, JPasswordField jT2) throws SQLException {
        String inputname=jT1.getText();
        String inputpwd=String.valueOf(jT2.getPassword());
        LogTestSql logsql= new LogTestSql();
        logsql.setPasswd(inputpwd);
        logsql.setUsername(inputname);
        flag=logsql.userAccount_Test();
    }
}
