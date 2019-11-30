package com.actionadd;

import com.GUI.MainFrame;
import com.GUI.PersonInfFrame;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class HeadphotoAction implements MouseListener {
    private String user_id;
    private JFrame mainFrame;
    public  HeadphotoAction(String user_id,JFrame mainFrame){
        this.user_id=user_id;
        this.mainFrame=mainFrame;
    }

    @Override
    public void mouseClicked(MouseEvent mouseEvent) {
        if (mouseEvent.getButton() == MouseEvent.BUTTON1){   //判断鼠标左键
            if (mouseEvent.getClickCount() == 2) {
                // 处理鼠标双击
                new PersonInfFrame(user_id);
            }
         }
       else {
         if (mouseEvent.getClickCount() == 1) {
             mainFrame.dispose();
             new MainFrame(user_id);
         }
        }
    }

    @Override
    public void mousePressed(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseReleased(MouseEvent mouseEvent) {
        if(mouseEvent.isPopupTrigger()){

        }
    }

    @Override
    public void mouseEntered(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseExited(MouseEvent mouseEvent) {

    }
}
