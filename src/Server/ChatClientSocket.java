package Server;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.PrintWriter;
import java.net.Socket;

public class ChatClientSocket extends JFrame {
    private PrintWriter writer;
    Socket socket;
    private final String ipAdress="127.0.0.1";
    private int port_id;
    private JTextArea ta=new JTextArea();
    private  JTextField tf=new JTextField();
    private  Container cc;
    public ChatClientSocket(String title,int port_id){
        super(title);
        this.port_id=port_id;
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        cc=this.getContentPane();
        final JScrollPane jsc=new JScrollPane();
        jsc.setBorder(new BevelBorder(BevelBorder.RAISED));
        cc.add(jsc,BorderLayout.CENTER);
        jsc.setViewportView(ta);
        cc.add(BorderLayout.SOUTH,tf);
        tf.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                writer.println(tf.getText());
                ta.append(tf.getText()+'\n');
                ta.setSelectionEnd(ta.getText().length());
                tf.setText("");
            }
        });
    }


    public   void connect(){
        ta.append("尝试连接"+'\n');
        try{
            socket=new Socket(ipAdress,port_id);
            writer =new PrintWriter(socket.getOutputStream(),true);
            ta.append("安全连接");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
            ChatClientSocket clinet = new ChatClientSocket("向好友发送数据", 6606);
            clinet.setSize(200,200);
            clinet.setVisible(true);
            clinet.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
            clinet.connect();
    }
}


