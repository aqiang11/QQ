package com.Dao;

import Server.ChatServerSocket;

public class MessageThread extends Thread {
    private int port_group[];
    private  String user_id;
    public MessageThread(String user_id, int[] port_group){
        this.port_group=port_group;
        this.user_id=user_id;
    }


    @Override
    public void run() {
        super.run();
        for(int i=0;i<port_group.length;i++) {
            new ChatServerSocket(user_id, port_group[i]).getserver();
        }
    }
}
