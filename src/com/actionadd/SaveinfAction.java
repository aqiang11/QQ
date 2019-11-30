package com.actionadd;

import com.database_link.PersonalSql;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.PreparedStatement;

public class SaveinfAction extends MouseAdapter {
    private   String nikneme;
    private   String user_id;
    private   String  OnlineState;
    private  String birthday;
    private  String age;
    private  String sign_name;
    private  int port_id;

    public SaveinfAction(String  user_id, String nikneme, String OnlineState,  String sign_name ,String birthday ,int port_id){
        this.birthday=birthday;
        this.sign_name=sign_name;
        this.user_id=user_id;
        this.nikneme=nikneme;
        this.OnlineState=OnlineState;
        this.port_id=port_id;
        this.age=String.valueOf(2019-Integer.parseInt(birthday.substring(0,4)));
    }

    @Override
    public void mouseClicked(MouseEvent mouseEvent) {
        PersonalSql persql=new PersonalSql(user_id);
        persql.setAge(age);
        persql.setBirthday(birthday);
        persql.setNikneme(nikneme);
        persql.setOnlineState(OnlineState);
        persql.setSign_name(sign_name);
        persql.setPort_id(port_id);
        persql.Update_useraccount();
    }
}