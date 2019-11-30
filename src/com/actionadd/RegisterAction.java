package com.actionadd;

import com.GUI.RegisterFrame;
import com.GUI.RegisterFrame;
import com.process.Register_process;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class RegisterAction implements MouseListener {
    RegisterFrame reg;
    @Override
    public void mouseClicked(MouseEvent mouseEvent) {
        reg=new RegisterFrame();
        reg.createReg();
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
