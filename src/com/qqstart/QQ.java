package com.qqstart;
import  com.GUI.LoginFrame;
public class QQ {
    LoginFrame jf=new LoginFrame();
    public void Init(){
        jf.create();
        jf.frameSize();
    }
    public  void close(){
        jf.dispose();
    }
    public  static  void main(String[] args){
       QQ qq= new QQ();
       QQ  qq1=new QQ();
       qq1.Init();
       qq.Init();
    }
}
