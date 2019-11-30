package com.actionadd;

import com.database_link.PersonalSql;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;

public class ChageHeadAction implements MouseListener {
    private  String user_id;
    public  ChageHeadAction(String user_id){
        this.user_id=user_id;
    }
    @Override
    public void mouseClicked(MouseEvent mouseEvent) {
        JFrame frame = new JFrame();
        JFileChooser jfiel头像=new JFileChooser();
        jfiel头像.setCurrentDirectory(new File("F:\\IDEA-project\\QQ\\src\\com\\img"));
        jfiel头像.setFileSelectionMode(JFileChooser.FILES_ONLY);
        // 创建一个默认打开用户文件夹的问价选择器
        int flag = jfiel头像.showOpenDialog(frame);
        //若选择了文件，则打印选择了什么文件
        PersonalSql persql=new PersonalSql(user_id);
        persql.setMyicon("src/com/img/"+jfiel头像.getSelectedFile().getName());
        System.out.println("src/com/img/"+jfiel头像.getSelectedFile().getName());
        persql.Update_useraccount();
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
